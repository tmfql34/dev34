package com.kosmo.ORMtest;


import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class DBCPCallTestForMyBatis {
	
	public SqlSession dbConn(){
		SqlSessionFactory dbFactory = null; //DBConnect = new DBConnect()
		SqlSession conn = null; //Connection
		Reader reader = null;
		
		try {
			reader = Resources.getResourceAsReader("WEB-INF/servlet-context.xml");
			dbFactory = new SqlSessionFactoryBuilder().build(reader);
			conn = dbFactory.openSession();
			
			if(conn != null) {
				System.out.println("conn open");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return conn;
	}

	public void dbClose(SqlSession conn) {
		if(conn != null) {
			conn.close();
			System.out.println("conn close");
		}
	}
	public static void main(String[] args) {
//		DBCPCallTestForMyBatis my = new DBCPCallTestForMyBatis();
//		SqlSession conn = my.dbConn();
//		my.dbClose(conn);
		SqlSessionFactory dbFactory = null; //DBConnect = new DBConnect()
		SqlSession conn = null; //Connection
		Reader reader = null;
		
		try {
			reader = Resources.getResourceAsReader("mybatis-context.xml");
			dbFactory = new SqlSessionFactoryBuilder().build(reader);
			//설정을 읽은 후 커넥션 풀 준비됨
			
			if(dbFactory == null){
				System.out.println("factory null");
			} else {
				System.out.println("factory open");
			}
			
			conn = dbFactory.openSession();
			
			if(conn == null){
				System.out.println("conn null");
			} else {
				System.out.println("conn open");
			}
			
			//TODO
			List<Object> list = conn.selectList("boardNameSpace.list");
			System.out.println(list.size());
			for(int i=0; i<list.size(); i++) {
				BoardVO bvo = (BoardVO) list.get(i);
//				System.out.println(bvo.getBtitle());
			}
			
			
//			BoardVO vo = new BoardVO();
//			vo.setBtitle("title33333");
//			int res = conn.insert("boardNameSpace.insert", vo);
//			
//			conn.commit();
//
//			System.out.println(res);
//			
			
			
			BoardDAO dao=new BoardDAO();
//		BoardVO vo = new BoardVO();
//		vo.setBtitle("title33333");
			int res=dao.boardDelete(10);
			System.out.println(res);
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(conn != null) {
				System.out.println("conn close");
				conn.close();
			}
		}
		
	}

}
