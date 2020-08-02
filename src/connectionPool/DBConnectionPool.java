/*
 * クラス名：
 * 作成日：
 * 作成者：
 * 修正日：
 * 修正者：
 * ver：1.0.0
 */
package connectionPool;

import java.util.ArrayDeque;
import java.util.Queue;

import dbaccess.DBAccess;
import exception.NoDBAccessException;

//コネクションをプールするクラス
public class DBConnectionPool {

	//コネクション保持用キュー作成
	private static Queue<DBAccess> connectionPool = new ArrayDeque<>();
	//DBへのコネクション数
	final static int NUMBER_OF_DB_CONNECTIONS = 5;

	//スタティックイニシャライザ
	static {
		for(int i = 0; i < NUMBER_OF_DB_CONNECTIONS; i++) {
			DBAccess dba = new DBAccess();
			connectionPool.add(dba);
		}
		System.out.println("DBへのコネクションを" + NUMBER_OF_DB_CONNECTIONS + "個確立しました");
	}

	//DBAccessを外部に渡すメソッド
	public static DBAccess getDBAccess() throws NoDBAccessException{
		DBAccess dba = connectionPool.poll();
		if(dba != null) {
			System.out.println("コネクションを貸し出します。");
			return dba;
		}else {
			System.out.println("コネクションは全て使用中です。");
			throw new NoDBAccessException();
		}
	}

	//DBAccessを返してもらうメソッド
	public static void returnDBAccess(DBAccess dba) {
		System.out.println("コネクションが返却されました。");
		connectionPool.add(dba);
	}
}
