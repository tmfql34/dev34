package com.kosmo.board;
import java.util.ArrayList;
public interface Board {

/**
 *
 * @return
 */
public int boardCount();

public ArrayList<BoardVO> boardSearchList(int sseq, int eseq, String searchGubun, String searchStr);
/**
 * 목록
 * @return ArrayList<BoardVO>
 */
public ArrayList<BoardVO> boardList(int sseq, int eseq);
/**
 * 수정
 * @param BoardVO
 * @return int
 */
public int boardUpdate(BoardVO vo);
/**
 * 수정하기(첨부파일용)
 * @param vo
 * @return
 */
public int boardUpload(BoardVO vo);


/**
 * 삭제
 * @param BoardVO
 * @return int
 */
 public int boardDelete(int bseq);
/**
 * 입력
 * @param BoardVO
 * @return int
 */
 public int boardInsert(BoardVO vo);
/**
 * 상세보기
 * @param BoardVO
 * @return BoardVO
 */
 public BoardVO boardDetail(int bseq);
}
