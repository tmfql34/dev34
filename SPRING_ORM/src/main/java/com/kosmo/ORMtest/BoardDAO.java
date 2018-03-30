package com.kosmo.ORMtest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;





@Repository
public class BoardDAO {
	
//	@Autowired
//	private DBConnect db;
//	
	@Autowired
	SqlSession conn;
	
	//상세보기
	
	//게시물수
//	@Override
//	public int boardCount() {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		DBConnect db = new DBConnect();
//		int cnt = 0;
//		String sql = "select count(*) cnt from board";
//		try {
//			conn = db.dbConn();
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			if(rs.next()) {
//				cnt  = rs.getInt("cnt");
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			db.dbClose(rs, pstmt, conn);
//		}
//		return cnt;
//	}
//
//	//전체목록보기

//		public ArrayList<BoardVO> boardList1(int sseq, int eseq, String searchGubun, String searchStr) {
//			//String sql = "select bseq,btype,btitle,bcon,mseq,bfile_path,bfile_name,bfile_size,to_char(regdate, 'YYYY-MM-DD') regdate from board order by regdate desc";
//
//			String sql =
//					"select rnum, bseq,btype,btitle,bcon,mseq,bfile_path,bfile_name,bfile_size,regdate " +
//					"from "+
//					"("+
//					"    select  rownum rnum, bseq,btype,btitle,bcon,mseq,bfile_path,bfile_name,bfile_size, regdate"+
//					"    from "+
//					"        (select bseq,btype,btitle,bcon,mseq,bfile_path,bfile_name,bfile_size,to_char(regdate, 'YYYY-MM-DD') regdate from board "
//					+ " 		where "+ searchGubun +" like ?"
//					+ " 		order by regdate desc) t "+
//					")"+
//					"where rnum >=? and rnum <=?";
//
//			System.out.println(sql);
//
//			Connection conn = null;
//			PreparedStatement pstmt = null;
//			ResultSet rs = null;
//			DBConnect db = new DBConnect();
//			ArrayList<BoardVO> list = new ArrayList<BoardVO>();
//			try {
//				conn = db.dbConn();
//				pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, "%"+searchStr+"%");
//				pstmt.setInt(2, sseq);
//				pstmt.setInt(3, eseq);
//				rs = pstmt.executeQuery();
//				while(rs.next()) {
//					BoardVO vo = new BoardVO();
//					vo.setBseq(rs.getInt("bseq"));
//					vo.setBtype(rs.getString("btype"));
//					vo.setBtitle(rs.getString("btitle"));
//					vo.setBcon(rs.getString("bcon"));
//					vo.setMseq(rs.getInt("mseq"));
//					vo.setBfile_path(rs.getString("bfile_path"));
//					vo.setBfile_name(rs.getString("bfile_name"));
//					vo.setBfile_size(rs.getInt("bfile_size"));
//					vo.setRegdate(rs.getString("regdate"));
//					list.add(vo);
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} finally {
////				db.dbClose(rs, pstmt, conn);
//			}
//			return list;
//		}
//
//

	public List<BoardVO> boardList(@Param("sseq")int sseq, @Param("eseq")int eseq) {
		//String sql = "select bseq,btype,btitle,bcon,mseq,bfile_path,bfile_name,bfile_size,to_char(regdate, 'YYYY-MM-DD') regdate from board order by regdate desc";

//		String sql =
//				"select rnum, bseq,btype,btitle,bcon,mseq,bfile_path,bfile_name,bfile_size,regdate " +
//				"from "+
//				"("+
//				"    select  rownum rnum, bseq,btype,btitle,bcon,mseq,bfile_path,bfile_name,bfile_size, regdate"+
//				"    from "+
//				"        (select bseq,btype,btitle,bcon,mseq,bfile_path,bfile_name,bfile_size,to_char(regdate, 'YYYY-MM-DD') regdate from board order by regdate desc) t "+
//				")"+
//				"where rnum >=? and rnum <=?";
//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
////		DBConnect db = new DBConnect();
//		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
//		try {
////			conn = db.dbConn();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, sseq);
//			pstmt.setInt(2, eseq);
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				BoardVO vo = new BoardVO();
//				vo.setBseq(rs.getInt("bseq"));
//				vo.setBtype(rs.getString("btype"));
//				vo.setBtitle(rs.getString("btitle"));
//				vo.setBcon(rs.getString("bcon"));
//				vo.setMseq(rs.getInt("mseq"));
//				vo.setBfile_path(rs.getString("bfile_path"));
//				vo.setBfile_name(rs.getString("bfile_name"));
//				vo.setBfile_size(rs.getInt("bfile_size"));
//				vo.setRegdate(rs.getString("regdate"));
//				list.add(vo);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
////			db.dbClose(rs, pstmt, conn);
//		}
//		return list;
		
		List<BoardVO> list = conn.selectList("boardNameSpace.plist");
		System.out.println(list.size());
		for(int i=0; i<list.size(); i++) {
			BoardVO bvo = (BoardVO) list.get(i);
			System.out.println(bvo.getBtitle());
		}
		
		
		return list;
		
	}

	//수정하기(첨부파일용)
	public int boardUpdateForUpload(BoardVO vo) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		DBConnect db = new DBConnect();
//		String sql = "update board set btitle=?,bcon=?,bfile_path=?,bfile_name=?,bfile_size=? where bseq=?";
//		int res = 0;
//		try {
//			conn = db.dbConn();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, vo.getBtitle());
//			pstmt.setString(2, vo.getBcon());
//			pstmt.setString(3, vo.getBfile_path());
//			pstmt.setString(4, vo.getBfile_name());
//			pstmt.setLong(5, vo.getBfile_size());
//			pstmt.setInt(6, vo.getBseq());
//			res = pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			db.dbClose(rs, pstmt, conn);
//		}
//		return res;
		
		int res=conn.update("boardNameSpace.update", vo);
		System.out.println(res);
		
		return res;
		
	}


	//수정하기
	public int boardUpdate(BoardVO vo) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		DBConnect db = new DBConnect();
//		String sql = "update board set btitle=?,bcon=? where bseq=?";
//		int res = 0;
//		try {
//			conn = db.dbConn();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, vo.getBtitle());
//			pstmt.setString(2, vo.getBcon());
//			pstmt.setInt(3, vo.getBseq());
//			res = pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			db.dbClose(rs, pstmt, conn);
//		}
//		return res;
		
		int res=conn.update("boardNameSpace.update", vo);
		System.out.println(res);
		
		return res;
		
	}

	//삭제하기
	public int boardDelete(int bseq) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		DBConnect db = new DBConnect();
//		String sql = "delete from board where bseq=?";
//		int res = 0;
//		try {
//			conn = db.dbConn();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, bseq);
//			res = pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			db.dbClose(rs, pstmt, conn);
//		}
//		return res;
		
		
		
		BoardVO vo=new BoardVO();
		vo.setBseq(bseq);
		
		
		int res=conn.delete("boardNameSpace.delete", vo);
		System.out.println(res);
		
		return res;
		
	}

	//입력하기
	public int boardInsert(BoardVO vo) {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		int res = 0;
//		DBConnect db = new DBConnect();
//		String sql = "insert into board(bseq,btype,btitle,bcon,mseq,bfile_path,bfile_name,bfile_size,regdate) values(board_seq.nextval , ?, ?, ?, ?, ?, ?, ?, sysdate)";
//		try {
//			conn = db.dbConn();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, vo.getBtype());
//			pstmt.setString(2, vo.getBtitle());
//			pstmt.setString(3, vo.getBcon());
//			pstmt.setInt(4, vo.getMseq());
//			pstmt.setString(5, vo.getBfile_path());
//			pstmt.setString(6, vo.getBfile_name());
//			pstmt.setLong(7, vo.getBfile_size());
//			res = pstmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			db.dbClose(rs, pstmt, conn);
//		}
//		return res;
		
		int res=conn.insert("boardNameSpace.insert", vo);
		System.out.println(res);
		
		return res;
		
		
	}

}

