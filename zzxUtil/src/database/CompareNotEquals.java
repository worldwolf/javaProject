package database;

import java.io.UnsupportedEncodingException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


/**
 * �Ա����ݿ⣬��ʾ��ͬ�ֶεĽű�
 * @author Administrator
 *
 */
public class CompareNotEquals {
	private static Connection getConn(String url,String username,String password   ) {
	    String driver = "com.mysql.jdbc.Driver";
	   /* String url = "jdbc:mysql://192.168.1.197:3306/cloudkit_demo?useUnicode=true&characterEncoding=UTF-8";
	    String username = "root";
	    String password = "mysqlroot";*/
	    Connection conn = null;
	    try {
	        Class.forName(driver); //classLoader,���ض�Ӧ����
	        conn = (Connection) DriverManager.getConnection(url, username, password);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return conn;
	}
	
	
	/**
	 * ��ȡָ�����ݿ�� ��,�� ����
	 * cloudkit_demo cloudkit_test
	 * @param TABLE_SCHEMA
	 * @return
	 */
	public static Set<String> getTableNameColumnName(String table_schema){
		Set<String> databaseSet = new HashSet<String>();
		Connection conn = getConn("jdbc:mysql://192.168.1.197:3306/"+table_schema+"?useUnicode=true&characterEncoding=UTF-8", 
				"root", 
				"mysqlroot");
		 String sql  = "select * from information_schema.`COLUMNS`  where TABLE_SCHEMA = '"+table_schema+"'";
		    PreparedStatement pstmt;
		    try {
		        pstmt = (PreparedStatement) conn.prepareStatement(sql);
		        ResultSet resultSet = pstmt.executeQuery();
		        while(resultSet.next()){
		        	String  table_name = resultSet.getString("TABLE_NAME");
		        	String  column_name = resultSet.getString("COLUMN_NAME");
		        	databaseSet.add(table_name+","+column_name);
		        }
		        pstmt.close();
		        conn.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		return databaseSet;
	}
	
	/**
	 * ��ȡ����
	 * @param tableColumnSet
	 * @return
	 */
	public static Set<String> getTableSet(Set<String> tableColumnSet){
		Set<String> tableSet = new HashSet<String>();
		if(tableColumnSet == null){
			return new HashSet<String>();
		}
		for(String tableColumn : tableColumnSet){
			tableSet.add(tableColumn.split(",")[0]);
		}
		return tableSet;
	}
	
	
	/**
	 * type : ҵ��
	 * �Ƚ� ��
	 * @return
	 */
	public Set<String> getCompareTableColumnSet(){
		Set<String> demoSet = getTableNameColumnName("cloudkit_demo");
		Set<String> testSet = getTableNameColumnName("cloudkit_test");
		System.out.println("---------------���в�ͬ---------------");
		if(demoSet != null && testSet != null){
			for(String tableNameColumnName : demoSet){
				if(!testSet.contains(tableNameColumnName)){
					System.out.println(tableNameColumnName);
				}
			}
		}
		demoSet = getTableSet(demoSet);
		testSet = getTableSet(testSet);
		System.out.println("---------------��ͬ---------------");
		if(demoSet != null && testSet != null){
			for(String tableNameColumnName : demoSet){
				if(!testSet.contains(tableNameColumnName)){
					System.out.println(tableNameColumnName);
				}
			}
		}
		return null;
	}
	
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		new CompareNotEquals().getCompareTableColumnSet();
		"22".getBytes("utf-8");
		//System.out.println(getConn());
	}
}
