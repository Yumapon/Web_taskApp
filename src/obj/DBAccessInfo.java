/*
 * クラス名：
 * 作成日：
 * 作成者：
 * 修正日：
 * 修正者：
 * ver：1.0.0
 */
package obj;

public class DBAccessInfo {
	//JDBC driver
	private String driver = null;

	//URL
	private String url = null;

	//user
	private String user = null;

	//password
	private String password = null;

	//コンストラクタ
	public DBAccessInfo(String driver, String url, String user, String password) {
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public DBAccessInfo() {

	}

	//getter setter
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}


}
