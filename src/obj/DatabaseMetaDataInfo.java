package obj;

//データベースのメタデータ
public class DatabaseMetaDataInfo {

	private String driver_version = null;
	private String driver_name = null;
	private String db_name = null;
	private String db_ver = null;
	private String table_name = null;
	private String[] column = null;

	public DatabaseMetaDataInfo() {

	}

	public DatabaseMetaDataInfo(String db_name) {
		this.db_name = db_name;
	}

	public String getDriver_version() {
		return driver_version;
	}
	public void setDriver_version(String driver_version) {
		this.driver_version = driver_version;
	}
	public String getDriver_name() {
		return driver_name;
	}
	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}
	public String getDb_name() {
		return db_name;
	}
	public void setDb_name(String db_name) {
		this.db_name = db_name;
	}
	public String getDb_ver() {
		return db_ver;
	}
	public void setDb_ver(String db_ver) {
		this.db_ver = db_ver;
	}

	public String[] getColumn() {
		return column;
	}

	public void setColumn(String[] column) {
		this.column = column;
	}

	public String getTable_name() {
		return table_name;
	}

	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}


}
