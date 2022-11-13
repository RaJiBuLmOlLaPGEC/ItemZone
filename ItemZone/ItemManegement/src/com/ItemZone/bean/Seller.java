package com.ItemZone.bean;

public class Seller {

	private int sid;
	private String sname;
	private String saddress;
	private String semail;
	private String spassword;
	public Seller(int sid, String sname, String saddress, String semail, String spassword) {
		super();
		this.sid = sid;
		this.sname = sname;
		this.saddress = saddress;
		this.semail = semail;
		this.spassword = spassword;
	}
	
	public Seller(String sname, String saddress, String semail, String spassword) {
		super();
		this.sname = sname;
		this.saddress = saddress;
		this.semail = semail;
		this.spassword = spassword;
	}

	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSaddress() {
		return saddress;
	}
	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}
	public String getSemail() {
		return semail;
	}
	public void setSemail(String semail) {
		this.semail = semail;
	}
	public String getSpassword() {
		return spassword;
	}
	public void setSpassword(String spassword) {
		this.spassword = spassword;
	}
	@Override
	public String toString() {
		return "Seller [sid=" + sid + ", sname=" + sname + ", saddress=" + saddress + ", semail=" + semail
				+ ", spassword=" + spassword + "]";
	}
	
}
