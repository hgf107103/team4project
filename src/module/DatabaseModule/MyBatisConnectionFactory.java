package module.DatabaseModule;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisConnectionFactory {
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		try {
			Reader reader = Resources.getResourceAsReader("module/DatabaseModule/mybatis_config.xml");
			
			if (sqlSessionFactory == null) {
				System.out.println("sqlSessionFactory Build Start");
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			}
		} catch (IOException e) {
			System.out.println("sqlSessionFactory Build error : " + e);
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("sqlSessionFactory Build error : " + e);
			e.printStackTrace();
		}
	}
	
	public static SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
}
