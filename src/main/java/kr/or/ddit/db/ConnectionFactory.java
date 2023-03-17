package kr.or.ddit.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import oracle.net.aso.b;

/**
 * Factory Object[Method] Pattern
 *	: 필요 객체의 생성을 전담하는 객체를 별도 운영하는 구조.
 */
public class ConnectionFactory {
	private static String url;
	private static String user;
	private static String password;
	
	private static DataSource ds;
	
	static {
		String path = "/kr/or/ddit/db/dbInfo.properties";
		try(
			InputStream is = ConnectionFactory.class.getResourceAsStream(path);
		) {
			Properties dbInfo = new Properties();
			dbInfo.load(is);
			
			url = dbInfo.getProperty("url");
			user = dbInfo.getProperty("user");
			password = dbInfo.getProperty("password");
			
//			Class.forName(dbInfo.getProperty("driverClassName"));
			
			BasicDataSource bds = new BasicDataSource();
			bds.setDriverClassName(dbInfo.getProperty("driverClassName"));
			bds.setUrl(url);
			bds.setUsername(user);
			bds.setPassword(password);
			
			bds.setInitialSize(Integer.parseInt(dbInfo.getProperty("initialSize"))); // bds.setMaxIdle(5); 같아야함
			bds.setMaxTotal(Integer.parseInt(dbInfo.getProperty("maxTotal"))); 
			bds.setMaxWaitMillis(Long.parseLong(dbInfo.getProperty("maxWait")));
			bds.setMaxIdle(Integer.parseInt(dbInfo.getProperty("maxIdle")));
			
			ds = bds;
			
		} 
		
		catch (IOException e1) {
			throw new RuntimeException(e1);
		}
	}
	
	public static Connection getConnection() throws SQLException {
		

		
//		Connection conn = DriverManager.getConnection(url, user, password);
		return ds.getConnection();
	}
}
