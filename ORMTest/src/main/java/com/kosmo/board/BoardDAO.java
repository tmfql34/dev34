package com.kosmo.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAO implements Board {

	@Autowired
	SqlSession conn;
	
	//상세보기
	@Override
	public BoardVO boardDetail(@Param("bseq")int bseq) {
		return conn.selectOne("boardNames.boardDetail", bseq);
	}

	//게시물수
	@Override
	public int boardCount() {
		return conn.selectOne("boardNames.boardCount");
	}

	//전체목록보기---------------------@썼음!
	@Override
	public ArrayList<BoardVO> boardSearchList(int sseq, int eseq, String searchGubun, String searchStr) {
		BoardVO vo = new BoardVO();
		vo.setSseq(sseq);
		vo.setEseq(eseq);
		vo.setSearchGubun(searchGubun);
		vo.setSearchStr(searchStr);
		return (ArrayList<BoardVO>)(ArrayList)conn.selectList("boardNames.boardSearchList",vo);
		
	}



	//전체목록보기
	@Override
	public ArrayList<BoardVO> boardList(int sseq, int eseq) {
		BoardVO vo = new BoardVO();
		vo.setSseq(sseq);
		vo.setEseq(eseq);
		return (ArrayList<BoardVO>)(ArrayList)conn.selectList("boardNames.boardList",vo);
	}

	//수정하기(첨부파일용)->Upload
	@Override
	public int boardUpload(BoardVO vo) {
		return conn.selectOne("boardNames.boardUpload",vo);
	}


	//수정하기
	@Override
	public int boardUpdate(BoardVO vo) {
		return conn.selectOne("boardNames.boardUpdate",vo);
	}

	//삭제하기
	@Override
	public int boardDelete(int bseq) {
		return conn.selectOne("boardNames.baordDelete",bseq);
	}

	//입력하기
	@Override
	public int boardInsert(BoardVO vo) {
		return conn.selectOne("boardNames.boardInsert",vo);
	}

}

