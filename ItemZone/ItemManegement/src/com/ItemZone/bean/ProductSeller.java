package com.ItemZone.bean;

public class ProductSeller {

	private int pid;
	private String pname;
	private int price;
	private int unit;
	private int sellerId;
	private String sname;
	private String ctname;
	public ProductSeller(int pid, String pname, int price, int unit, int sellerId, String sname, String ctname) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.price = price;
		this.unit = unit;
		this.sellerId = sellerId;
		this.sname = sname;
		this.ctname = ctname;
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
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getCtname() {
		return ctname;
	}
	public void setCtname(String ctname) {
		this.ctname = ctname;
	}
	@Override
	public String toString() {
		return "ProductSeller [pid=" + pid + ", pname=" + pname + ", price=" + price + ", unit=" + unit + ", sellerId="
				+ sellerId + ", sname=" + sname + ", ctname=" + ctname + "]";
	}
	
	
}
