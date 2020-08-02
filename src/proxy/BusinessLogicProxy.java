/*
 * クラス名：
 * 作成日：
 * 作成者：
 * 修正日：
 * 修正者：
 * ver：1.0.0
 */
package proxy;

import java.util.ArrayList;

import businesslogic.BusinessLogic;
import dto.Task_List;
import dto.UserContext;
import exception.DuplicationError;
import exception.falseLogionException;
import exception.notExistException;

/**
 * @author okamotoyuuma
 *
 */
public class BusinessLogicProxy implements BusinessLogic {

	@Override
	public void login(UserContext usercontext) throws falseLogionException, notExistException {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void logout() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void taskstorage(Task_List task, UserContext userContext) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public ArrayList<Task_List> getList() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void deleteTask(String[] taskNumList) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void register(UserContext userContext) throws DuplicationError {
		// TODO 自動生成されたメソッド・スタブ

	}

}
