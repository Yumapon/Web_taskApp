package factory;

import java.sql.Connection;

import dao.DAO;
import dao.DefaultTask_ListDAO;

public class DaoFactory {

	DAO dao = null;

	public DAO getDao(String tableName, Connection conn) {
			if(tableName.equals("task_list")) {
				//タスクアプリ用のDAOを生成
				dao = new DefaultTask_ListDAO(conn);
			}
		return dao;
	}

}
