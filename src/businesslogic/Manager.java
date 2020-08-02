package businesslogic;

import dao.DAO;
import dto.DTO;
import factory.DaoFactory;

public class Manager {
	DaoFactory daoFactory = new DaoFactory();

	DTO doFind(String tableName, String primaryKey) {
		DAO dao = daoFactory.getDao(tableName, null);
		DTO dto = dao.doFind(primaryKey);
		return dto;
	}

}
