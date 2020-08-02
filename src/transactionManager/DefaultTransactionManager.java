package transactionManager;

import java.sql.Connection;
import java.sql.SQLException;

import connectionPool.DBConnectionPool;
import dbaccess.DBAccess;
import exception.NoDBAccessException;

public class DefaultTransactionManager implements TransactionManager{

	//Transactionの状態
	boolean transactionStatus = false;
	//コネクション
	Connection conn = null;
	DBAccess dba = null;
	final int SLEEPTIME = 300;
	boolean repeat = false;

	@Override
	public void beginTransaction() {
		System.out.println("トランザクションを開始します");
		//TransactionStatusを開始状態に
		transactionStatus = true;
	}

	@Override
	public void commit() {
			System.out.println("結果をコミットします");
			try {
				dba.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void rollback() {
		try {
			conn.rollback();
			dba.closeConnection();
			transactionStatus = false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isTransaction() {
		return transactionStatus;
	}

	@Override
	public void endTransaction() {
		//TransactionStatusを終了状態に
		transactionStatus = false;
		//コネクションの返却
		this.returnConnection();
	}

	@Override
	public Connection getConnection() {
		if(this.isTransaction()) {
			//Connectionの取得
			try {
				dba = DBConnectionPool.getDBAccess();
			} catch (NoDBAccessException e) {
				if(!repeat) {
					//時間をおいてもう一度アクセス
					try {
						System.out.println("コネクションの取得に再チャレンジします。");
						Thread.sleep(SLEEPTIME);
						repeat = true;
						beginTransaction();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}else {
					System.out.println("DB混みすぎて無理でしたわ。。。");
				}
			}
		}else {
			System.out.println("トランザクションを開始してください");
		}
		return dba.getConnection();
	}

	@Override
	public void returnConnection() {
		//コネクションの返却
		DBConnectionPool.returnDBAccess(dba);
		dba = null;
	}

}
