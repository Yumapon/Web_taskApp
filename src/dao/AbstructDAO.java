package dao;

import java.sql.Connection;

public abstract class AbstructDAO {
	//sql文
	String getpasssql = "SELECT password FROM User_ID WHERE id = ?";//パスワード取得用
	String stragetasksql = "INSERT INTO task_list VALUES (?, ?, ?, ?, ?)";//task格納用
	String getlistsql = "SELECT * FROM task_list";//リスト取得用
	String deletetasksql = "DELETE FROM task_list WHERE ";//タスク削除用（１）
	String deletetasksqlwhere = "num = ?";//タスク削除用（２）
	String deketetasksqlor = " OR ";//タスク削除用（３）
	String deletetasksqlend = ";";//タスク削除用（４）
	String registersql = "INSERT INTO User_ID VALUES (?, ?)";

	//メンバ
	Connection conn = null;

	public AbstructDAO(Connection conn) {
		this.conn = conn;
	}

}
