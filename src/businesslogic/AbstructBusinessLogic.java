package businesslogic;

import factory.DaoFactory;

//DefaultKibanServiceのシステム的な仕様
public abstract class AbstructBusinessLogic {

	//daofactoryクラスを取得
	DaoFactory factory = new DaoFactory();

	//Managerのインスタンスを取得する
	Manager mg = new Manager();
}
