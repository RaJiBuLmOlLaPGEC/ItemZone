package com.ItemZone.bean;

public class Order {

	private int orderid;
	private int customerid;
	private int sellerid;
	private int categoryid;
	private int quantity;
	private int totalprice;
	private String orderdate;
	public Order(int orderid, int customerid, int sellerid, int categoryid, int quantity, int totalprice,
			String orderdate) {
		super();
		this.orderid = orderid;
		this.customerid = customerid;
		this.sellerid = sellerid;
		this.categoryid = categoryid;
		this.quantity = quantity;
		this.totalprice = totalprice;
		this.orderdate = orderdate;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public int getSellerid() {
		return sellerid;
	}
	public void setSellerid(int sellerid) {
		this.sellerid = sellerid;
	}
	public int getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	public String getOrderdate() {
		return orderdate;
	}
	public void setOrderdate(String orderdate) {
		this.orderdate = orderdate;
	}
	@Override
	public String toString() {
		return "Order [orderid=" + orderid + ", customerid=" + customerid + ", sellerid=" + sellerid + ", categoryid="
				+ categoryid + ", quantity=" + quantity + ", totalprice=" + totalprice + ", orderdate=" + orderdate
				+ "]";
	}
	
	
}
