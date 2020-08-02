package dao;
import java.sql.Date;
import java.util.ArrayList;

import dto.DTO;
import dto.Task_List;
import dto.UserContext;
import exception.DuplicationError;
import exception.notExistException;

//DAOが提供するサービス
public interface DAO {

	//入力されたユーザIDのパスワードを取得する
	String passwordCheckDAO(UserContext usercontext) throws notExistException;

	//タスクをtask_list(DB)に格納
	void taskstorageDAO(String taskNum, Date deadLine, String taskName, String content, String client);

	//一覧を取得
	ArrayList<Task_List> getListDAO();

	//タスクを削除する
	void deleteTaskDAO(String[] taskNumList);

	//ユーザIDを登録する
	void registerDAO(UserContext userContext) throws DuplicationError;

	//doFind
	DTO doFind(String primaryKey);

}
