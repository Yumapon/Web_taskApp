package businesslogic;

import java.util.ArrayList;

import dto.Task_List;
import dto.UserContext;
import exception.DuplicationError;
import exception.falseLogionException;
import exception.notExistException;

@BusinessLogicInterface
public interface BusinessLogic {

	//ログインロジック
	void login(UserContext usercontext) throws falseLogionException, notExistException;

	//ログアウトロジック
	void logout();

	//Task保存ロジック
	void taskstorage(Task_List task, UserContext userContext);

	//一覧取得ロジック
	ArrayList<Task_List> getList();

	//Task削除ロジック
	void deleteTask(String[] taskNumList);

	//user登録ロジック
	void register(UserContext userContext) throws DuplicationError;

}
