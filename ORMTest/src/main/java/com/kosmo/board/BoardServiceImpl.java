package com.kosmo.board;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardDAO dao;
	
//	public void setBoardDAO(BoardDAO dao) {
//		this.dao = dao;
//	}

	public int boardCount() {
		return dao.boardCount();
	}


	/**
	 * 목록
	 * @return ArrayList<BoardVO>
	 */
	public ArrayList<BoardVO> boardList(int sseq, int eseq) {
		return dao.boardList(sseq, eseq);
	}


	public ArrayList<BoardVO> boardSearchList(int sseq, int eseq, String searchGubun, String searchStr) {
		return dao.boardSearchList(sseq, eseq, searchGubun, searchStr);
	}


	/**
	 * 수정
	 * @param BoardVO
	 * @return int
	 */
	public int boardUpdate(BoardVO vo) {
		return dao.boardUpdate(vo);
	}
	/**
	 * 수정하기(첨부파일용)
	 * @param vo
	 * @return
	 */
	public int boardUpload(BoardVO vo){
		return dao.boardUpload(vo);
	}


	/**
	 * 삭제
	 * @param BoardVO
	 * @return int
	 */
	 public int boardDelete(int bseq) {
		 return dao.boardDelete(bseq);
	 }
	/**
	 * 입력
	 * @param BoardVO
	 * @return int
	 */
	 public int boardInsert(BoardVO vo) {
		 return dao.boardInsert(vo);
	 }
	/**
	 * 상세보기
	 * @param BoardVO
	 * @return BoardVO
	 */
	 public BoardVO boardDetail(int bseq) {
		 return dao.boardDetail(bseq);
	 }
}
