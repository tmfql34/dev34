package com.kosmo.ORMtest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.ibatis.io.Resources;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

public class DBCPCallTestForSpring {

	public static void main(String[] args) {
		
//		GenericXmlApplicationContext ctx
//		= new GenericXmlApplicationContext("mybatis-context.xml");
		
		FileSystemResource resource = new FileSystemResource("C:\\DEV34\\workspace_spring\\SPRING_ORM\\src\\main\\webapp\\WEB-INF\\servlet-context.xml");
		BeanFactory bf = new XmlBeanFactory(resource);

		Connection conn = null;
		try {
			DataSource ds = (DataSource)bf.getBean("pmj");
			conn = ds.getConnection();
			
			if(conn == null) {
				System.out.println("conn null");
			} else {
				System.out.println("conn open");
				
				PreparedStatement pstmt = conn.prepareStatement("select count(*) as cnt from member");
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					System.out.println(rs.getInt("cnt"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
