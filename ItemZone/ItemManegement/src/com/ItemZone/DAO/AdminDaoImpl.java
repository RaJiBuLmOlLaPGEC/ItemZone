package com.ItemZone.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.ItemZone.bean.Admin;
import com.ItemZone.bean.Customer;
import com.ItemZone.bean.Order;
import com.ItemZone.bean.Seller;
import com.ItemZone.exception.AdminException;
import com.ItemZone.exception.CustException;
import com.ItemZone.exception.OrderException;
import com.ItemZone.exception.SellerException;
import com.ItemZone.utility.DBUtil;

public class AdminDaoImpl implements AdminDao{

	@Override
	public Admin logInAdmin(String email, String password)throws AdminException{
		Admin admin=null;
		try (Connection conn=DBUtil.provideConnection()){
			PreparedStatement ps=conn.prepareStatement("select * from administrator where username=?");
			
			ps.setString(1,email);
			
			
			ResultSet rs= ps.executeQuery();
			
			if(rs.next()) {
				PreparedStatement ps2=conn.prepareStatement("select * from administrator where username=? AND password=?");
				ps2.setString(2,password);
				ps2.setString(1,email);
				
				ResultSet rs2= ps2.executeQuery();
				
				if(rs2.next()) {
					admin=new Admin(rs2.getString("aname"), email, password);
				}else {
					admin=new Admin(null, email, null);
				}
				
			}
			else {
				admin=null;
			}
		} catch (SQLException e) {
			throw new AdminException(e.getMessage());
		}
		return admin;
	}

	@Override
	public List<Customer> allCustomers() throws CustException {
		List<Customer> allCustomer=new ArrayList<>();
		try (Connection conn= DBUtil.provideConnection()){
			PreparedStatement ps=conn.prepareStatement("select * from customer");
			ResultSet rs =ps.executeQuery();
			while(rs.next()) {
				int cid=rs.getInt("customerId");
				String cname=rs.getString("cname");
				String caddress=rs.getString("caddress");
				String cmobile=rs.getString("cmobile");
				String cemail=rs.getString("cemail");
				String password=rs.getString("password");
				
				Customer c=new Customer(cid, cname, caddress, cmobile, cemail, password);
				allCustomer.add(c);
				
			}
			if(allCustomer.size()==0) {
				throw new CustException("No Buyer found");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new CustException(e.getMessage());
		}
		return allCustomer;
	}

	@Override
	public List<Seller> allSellers() throws SellerException {
		List<Seller> allSeller=new ArrayList<>();
		try (Connection conn= DBUtil.provideConnection()){
			PreparedStatement ps=conn.prepareStatement("select * from seller");
			ResultSet rs =ps.executeQuery();
			while(rs.next()) {
				int sid=rs.getInt("sellerid");
				String sname=rs.getString("sname");
				String saddress=rs.getString("saddress");
				String semail=rs.getString("semail");
				String password=rs.getString("password");
				
				Seller s=new Seller(sid, sname, saddress, semail, password);
				allSeller.add(s);
				
			}
			if(allSeller.size()==0) {
				throw new SellerException("No Seller found");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			throw new SellerException(e.getMessage());
		}
		return allSeller;
	}

	@Override
	public List<Order> showTodaysOrder() throws OrderException {
		List<Order> todaysOrder=new LinkedList<>();
		try (Connection conn= DBUtil.provideConnection()){
			
			PreparedStatement ps=conn.prepareStatement("select curdate() from dual");
			ResultSet rs= ps.executeQuery();
			if(rs.next()) {
				String date=rs.getString("curdate()");
				PreparedStatement ps2=conn.prepareStatement("select * from orderdetails where OrderDate=?");
				ps2.setString(1, date);
				ResultSet rs2= ps2.executeQuery();
				while(rs2.next()) {
					int oid=rs2.getInt("orderid");
					int cid=rs2.getInt("customerid");
					int sid=rs2.getInt("sellerid");
					int ctid=rs2.getInt("categoryid");
					int quantity=rs2.getInt("quantity");
					int oprice=rs2.getInt("orderprice");
					String odate=rs2.getString("OrderDate");
					
					Order o= new Order(oid, cid, sid,ctid, quantity, oprice, odate);
					todaysOrder.add(o);
				}
			}
			else {
				throw new OrderException("No order Found Today.");
			}
			
			
		} catch (Exception e) {
			throw new OrderException(e.getMessage());
		}
		return todaysOrder;
	}

}
