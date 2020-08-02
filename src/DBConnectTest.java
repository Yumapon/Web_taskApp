

import transactionManager.DefaultTransactionManager;
import transactionManager.TransactionManager;

public class DBConnectTest {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		TransactionManager tm = new DefaultTransactionManager();
		if(tm.isTransaction()) {
			System.out.println("トランザクションは開始されています");
		}else {
			System.out.println("トランザクションは開始されていません");
		}
		tm.beginTransaction();
		tm.getConnection();
		if(tm.isTransaction()) {
			System.out.println("トランザクションは開始されています");
		}else {
			System.out.println("トランザクションは開始されていません");
		}
		tm.commit();
		if(tm.isTransaction()) {
			System.out.println("トランザクションは開始されています");
		}else {
			System.out.println("トランザクションは開始されていません");
		}
		tm.endTransaction();
		tm.beginTransaction();
		if(tm.isTransaction()) {
			System.out.println("トランザクションは開始されています");
		}else {
			System.out.println("トランザクションは開始されていません");
		}
		tm.commit();
		if(tm.isTransaction()) {
			System.out.println("トランザクションは開始されています");
		}else {
			System.out.println("トランザクションは開始されていません");
		}
		tm.beginTransaction();
		tm.beginTransaction();
		tm.beginTransaction();
		tm.endTransaction();
		tm.beginTransaction();
		tm.beginTransaction();
		tm.beginTransaction();


	}

}
