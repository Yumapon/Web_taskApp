package transactionManager;

import java.sql.Connection;

public interface TransactionManager {

	//トランザクションの開始
	void beginTransaction();

	//トランザクションの終了
	void commit();

	//ロールバック
	void rollback();

	//トランザクションの終了
	void endTransaction();

	//トランザクションの状態確認
	boolean isTransaction();

	//コネクションの取得
	Connection getConnection();

	//コネクションの返却
	void returnConnection();

}
