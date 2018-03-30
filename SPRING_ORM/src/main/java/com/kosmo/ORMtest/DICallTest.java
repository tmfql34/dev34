package com.kosmo.ORMtest;

import java.util.ArrayList;

import org.springframework.context.support.GenericXmlApplicationContext;

public class DICallTest {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx
		 = new GenericXmlApplicationContext("classpath:test.xml");

//		TestDAO dao = (TestDAO)ctx.getBean("aaaa");
//		ArrayList<BoardVO> list = dao.boardList(1, 10);
//		System.out.println(list.size() + "건 출력");


	}

}
