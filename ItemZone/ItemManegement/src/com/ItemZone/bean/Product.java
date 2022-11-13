package com.ItemZone.bean;

public class Product {
	private int pid;
	private String pname;
	private int price;
	private int unit;
	private int sellerid;
	private int cid;
	public Product(int pid, String pname, int price, int unit, int sellerid, int cid) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.price = price;
		this.unit = unit;
		this.sellerid = sellerid;
		this.cid = cid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getUnit() {
		return unit;
	}
	public void setUnit(int unit) {
		this.unit = unit;
	}
	public int getSellerid() {
		return sellerid;
	}
	public void setSellerid(int sellerid) {
		this.sellerid = sellerid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", price=" + price + ", unit=" + unit + ", sellerid="
				+ sellerid + ", cid=" + cid + "]";
	}
	
	
}
