package com.kosmo.ORMtest;

public class BoardVO {
	private int bseq;
	private String btype;
	private String btitle;
	private String bcon;
	private int mseq;
	private String bfile_path;
	private String bfile_name;
	private long bfile_size;
	private String regdate;

	private int totalCount;
	private String searchGubun;
	private String searchStr;


	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getSearchGubun() {
		return searchGubun;
	}
	public void setSearchGubun(String searchGubun) {
		this.searchGubun = searchGubun;
	}
	public String getSearchStr() {
		return searchStr;
	}
	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}
	public int getBseq() {
		return bseq;
	}
	public void setBseq(int bseq) {
		this.bseq = bseq;
	}
	public String getBtype() {
		return btype;
	}
	public void setBtype(String btype) {
		this.btype = btype;
	}
	public String getBtitle() {
		return btitle;
	}
	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}
	public String getBcon() {
		return bcon;
	}
	public void setBcon(String bcon) {
		this.bcon = bcon;
	}
	public int getMseq() {
		return mseq;
	}
	public void setMseq(int mseq) {
		this.mseq = mseq;
	}
	public String getBfile_path() {
		return bfile_path;
	}
	public void setBfile_path(String bfile_path) {
		this.bfile_path = bfile_path;
	}
	public String getBfile_name() {
		return bfile_name;
	}
	public void setBfile_name(String bfile_name) {
		this.bfile_name = bfile_name;
	}
	public long getBfile_size() {
		return bfile_size;
	}
	public void setBfile_size(long bfile_size) {
		this.bfile_size = bfile_size;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
}
