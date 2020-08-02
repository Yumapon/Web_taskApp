/*
 * クラス名：
 * 作成日：
 * 作成者：
 * 修正日：
 * 修正者：
 * ver：1.0.0
 */
package dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import environmentInfo.GetEnvironmentInfo;
import obj.DBAccessInfo;

public class DBAccess {
	//メンバ
	private Connection conn = null;
	PreparedStatement  pre_statement = null;
	String driver = null;
	String url = null;
	String user = null;
	String password = null;
	GetEnvironmentInfo getEnvInfo = new GetEnvironmentInfo();

	//コンストラクタ
	public DBAccess() {
		//DBの設定値を取得する
		DBAccessInfo dbAccessInfo = getEnvInfo.getDBAccessInfo();

		//設定値の取り出し
		driver = dbAccessInfo.getDriver();
		url = dbAccessInfo.getUrl();
		user = dbAccessInfo.getUser();
		password = dbAccessInfo.getPassword();

		System.out.println("★ DBAccess");
		System.out.println("・Driver = " + driver);
		System.out.println("・url = " + url);
		System.out.println("・user = " + user);
		System.out.println("・password = " + password);

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("DBに接続しました");
			//自動コミットOFF
			conn.setAutoCommit(false);
		} catch (SQLException | ClassNotFoundException  e) {//コネクションの確立に失敗
			System.out.println("DBとの接続に失敗しました");
			e.printStackTrace();
		}
	}

	//コネクションを配布する
	public Connection getConnection() {
		return conn;
	}

	//コネクションの破棄
	public void closeConnection() {
		try {
			conn.close();
			System.out.println("DBから切断しました");
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			System.out.println("DBから切断できませんでした");
			e.printStackTrace();
		}
	}
}
