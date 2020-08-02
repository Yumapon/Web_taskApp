package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.DTO;
import dto.Task_List;
import dto.UserContext;
import exception.DuplicationError;
import exception.notExistException;

public class DefaultTask_ListDAO extends AbstructDAO implements DAO {

	//メンバ
	PreparedStatement pre_statement;

	//デフォルトコンストラクタ
	public DefaultTask_ListDAO(Connection conn) {
		super(conn);
	}

	@Override
	//入力されたIDのパスワードを検索する
	public String passwordCheckDAO(UserContext usercontext) throws notExistException {

		//テスト用：文字列passwordを返す
		//return "password";

		//メンバ
		int id = usercontext.getUserId();
		String password = null;

		//dao
		try {
			//パスワード取得用のSQLを用意
			pre_statement = conn.prepareStatement(getpasssql);
			pre_statement.setInt(1, id);

			//SQL実行
			ResultSet result_set = pre_statement.executeQuery();
			result_set.next();
			password = result_set.getString("password");

		} catch (SQLException e) {
			System.out.println("SQLエラーです");
			//e.printStackTrace();
		}
		return password;
	}

	@Override
	//タスクオブジェクトをtask_list(DB)に格納
	public void taskstorageDAO(String taskNum, Date deadLine, String taskName, String content, String client) {
		try {
			//task格納用のSQLを用意
			pre_statement = conn.prepareStatement(stragetasksql);

			pre_statement.setString(1, taskNum);
			pre_statement.setDate(2, deadLine);
			pre_statement.setString(3, taskName);
			pre_statement.setString(4, content);
			pre_statement.setString(5, client);

			//SQL実行(iには更新行数が格納される)
			@SuppressWarnings("unused")
			int i = pre_statement.executeUpdate();

			} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	//DBからタスク一覧を取得
	public ArrayList<Task_List> getListDAO() {

		//メンバ
		ResultSet result_set = null;
		ArrayList<Task_List> tasklist = new ArrayList<Task_List>();

		try {
			//list取得用のSQLを用意
			pre_statement = conn.prepareStatement(getlistsql);

			//SQL実行
			result_set = pre_statement.executeQuery();

			//確認用
			result_set.last();
			int number_of_row = result_set.getRow();
			System.out.println(number_of_row);
			result_set.beforeFirst();   //最初に戻る

			//取得したResultSetから、task配列オブジェクトを生成する。（num以外）
			//タスクをリストに格納
			while(result_set.next()) {
				Task_List task = new Task_List();
					task.setTaskNum(result_set.getString("num"));
					task.setDeadline(result_set.getDate("deadline"));
					task.setTaskname(result_set.getNString("name"));
					task.setContent(result_set.getNString("content"));
					task.setClient(result_set.getNString("client"));
				tasklist.add(task);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tasklist;
	}

	@Override
	//DBから指定されたタスクを削除する
	public void deleteTaskDAO(String[] taskNumList) {

		//taskNumListの長さから、SQLを作成する
		String sql = deletetasksql;
		for(int i = 0; i < taskNumList.length; i++)
		{
			if(i > 0)
			{
				sql = sql + deketetasksqlor + deletetasksqlwhere;
			}else
			{
				sql = sql + deletetasksqlwhere;
			}
		}
			sql = sql + deletetasksqlend;

			System.out.println(sql);


			//task削除用のSQLを用意
			try {
				pre_statement = conn.prepareStatement(sql);
				for(int i = 0; i < taskNumList.length; i++)
				{
					pre_statement.setString(i+1, taskNumList[i]);
				}

				//SQL実行(iには更新行数が格納される)
				@SuppressWarnings("unused")
				int i = pre_statement.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	//ユーザ登録機能
	public void registerDAO(UserContext userContext) throws DuplicationError {

		System.out.println("登録DAO");
		//ユーザ登録用のSQLを用意
		try {
			pre_statement = conn.prepareStatement(registersql);
			pre_statement.setInt(1, userContext.getUserId());
			pre_statement.setString(2,userContext.getPassword());

			//SQL実行(iには更新行数が格納される)
			@SuppressWarnings("unused")
			int j = pre_statement.executeUpdate();

		} catch(SQLException e){
			int errorCode = e.getErrorCode();
			System.out.println("SQLException#getErrorCode = " + errorCode);
			if (errorCode == 1062) {
				System.out.println("!!一意制約違反発生");
				throw new DuplicationError();
			}else {
				e.printStackTrace();
			}
		}
	}

	@Override
	public DTO doFind(String primaryKey) {
		DTO dto = new Task_List();
		return dto;
	}

}
