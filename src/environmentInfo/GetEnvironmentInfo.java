/*
 * クラス名：
 * 作成日：
 * 作成者：
 * 修正日：
 * 修正者：
 * ver：1.0.0
 */
package environmentInfo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import connectionPool.DBConnectionPool;
import dbaccess.DBAccess;
import exception.NoDBAccessException;
import obj.DBAccessInfo;
import obj.DatabaseMetaDataInfo;

//設定ファイルから値を取得する
public class GetEnvironmentInfo {

	Properties properties = new Properties();
	String DBEnvironmentFile = "./DBprofile.properties";
	FileInputStream fis = null;
	DBAccessInfo dbAccessInfo = null;
	DatabaseMetaDataInfo dmdi = null;

	//コネクション
	Connection conn = null;
	DBAccess dba = null;
	final int SLEEPTIME = 300;
	boolean repeat = false;

	//DBの環境設定をテキストファイルから読み込む
	public DBAccessInfo getDBAccessInfo() {
		try {
			fis = new FileInputStream(DBEnvironmentFile);
			try {
				properties.load(fis);

				//DBへ接続するためのInfoObject
				String driver = properties.getProperty("DRIVER");
				String url = properties.getProperty("URL");
				String user = properties.getProperty("USER");
				String password = properties.getProperty("PASSWORD");

				dbAccessInfo = new DBAccessInfo(driver, url, user, password);

			} catch (IOException e) {
				System.out.println("設定ファイルの値が不正です。");
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			System.out.println("DBの設定ファイルが見つかりませんでした。");
			e.printStackTrace();
		}
	return dbAccessInfo;

	}

	public DatabaseMetaDataInfo getDatabaseMetaDataInfo() {
		try {
			fis = new FileInputStream(DBEnvironmentFile);
			try {
				properties.load(fis);

				//DBのテーブル情報取得
				int number_of_db = Integer.parseInt((String)properties.get("NUMBER_OF_DB"));
				String[] dbName = new String[number_of_db];
				for(int i = 0; i < number_of_db; i++) {
					dbName[i] = properties.getProperty("DBNAME" + (i + 1));
				}

				//Connectionの取得
				try {
					dba = DBConnectionPool.getDBAccess();
				} catch (NoDBAccessException e) {
					System.out.println("システムエラー");
					e.printStackTrace();
				}

				Connection conn = dba.getConnection();
				DatabaseMetaData dbmd = conn.getMetaData();
				String schema = "スキーマ名";
				String table = "%";
				// VIEWや、SYSTEM TABLEは必要な場合
				String types[] = { "TABLE", "VIEW", "SYSTEM TABLE" };
				ResultSet rs = dbmd.getTables(null, schema, table, types);
				while (rs.next()) {
				    String tableName= rs.getString("TABLE_NAME");
				    System.out.println("「" + schema +  "/" + tableName + "」");
				    ResultSet rs2 = dbmd.getColumns(null, schema, tableName, "%");
				    while (rs2.next()) {
				        System.out.println(
				        rs2.getString("COLUMN_NAME")
				        + " / "
				        + rs2.getString("TYPE_NAME")
				        + " / "
				        + rs2.getString("COLUMN_SIZE"));
				    }
				}

			} catch (IOException | SQLException e) {
				System.out.println("設定ファイルの値が不正です。");
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return dmdi;
	}
}
