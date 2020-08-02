package factory;

import businesslogic.BusinessLogic;
import proxy.BusinessLogicProxy;

public class BusinessLogicFactory {

	BusinessLogic logic = null;

	public BusinessLogic getLogic() {
		logic = new BusinessLogicProxy();
		return logic;
	}

}
