package com.ItemZone.bean;

public class Customer {

	private int cid;
	private String cname;
	private String caddress;
	private String mobile;
	private String cemail;
	private String cpassword;
	public Customer(int cid, String cname, String caddress, String mobile, String cemail, String cpassword) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.caddress = caddress;
		this.mobile = mobile;
		this.cemail = cemail;
		this.cpassword = cpassword;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCaddress() {
		return caddress;
	}
	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCemail() {
		return cemail;
	}
	public void setCemail(String cemail) {
		this.cemail = cemail;
	}
	public String getCpassword() {
		return cpassword;
	}
	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}
	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", cname=" + cname + ", caddress=" + caddress + ", mobile=" + mobile
				+ ", cemail=" + cemail + ", cpassword=" + cpassword + "]";
	}
	
}
