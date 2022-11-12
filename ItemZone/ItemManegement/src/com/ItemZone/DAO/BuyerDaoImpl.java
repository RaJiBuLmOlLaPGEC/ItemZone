package com.ItemZone.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.ItemZone.bean.Customer;
import com.ItemZone.bean.Order;
import com.ItemZone.bean.Product;
import com.ItemZone.bean.ProductSeller;
import com.ItemZone.exception.CustException;
import com.ItemZone.exception.OrderException;
import com.ItemZone.exception.ProductException;
import com.ItemZone.utility.DBUtil;

public class BuyerDaoImpl implements BuyerDao{

	@Override
	public Customer loginCustomer(String e, String p) throws CustException {
		Customer c=null;
		try(Connection conn= DBUtil.provideConnection()) {
			PreparedStatement ps= conn.prepareStatement("select * from customer where cemail=?");
			ps.setString(1,e);
			ResultSet rs= ps.executeQuery();
			if(rs.next()) {
				PreparedStatement ps2=conn.prepareStatement("select * from customer where cemail=? AND password=?");
				ps2.setString(1, e);
				ps2.setString(2, p);
				
				ResultSet rs2=ps2.executeQuery();
				if(rs2.next()) {
					int cid=rs2.getInt("customerId");
					String n=rs2.getString("cname");
					String a=rs2.getString("caddress");
					String m=rs2.getString("cmobile");
					String email=rs2.getString("cemail");
					String pass=rs2.getString("password");
					c=new Customer(cid, n, a, m, email, pass);
				}
				else {
					c=new Customer(0, null, null, null, e, null);
				}
			}
			else {
				c=null;
			}
		} catch (Exception e2) {
			// TODO: handle exception
			throw new CustException(e2.getMessage());
		}
		
		
		
		return c;
	}

	@Override
	public Customer CustomerReg(Customer c) throws CustException {
		Customer newc=null;
		try (Connection conn=DBUtil.provideConnection()){
			PreparedStatement ps= conn.prepareStatement("insert into customer (cname,caddress,cmobile,cemail,password) values (?,?,?,?,?)");
			ps.setString(1,c.getCname());
			ps.setString(2,c.getCaddress());
			ps.setString(3,c.getMobile());
			ps.setString(4,c.getCemail());
			ps.setString(5,c.getCpassword());
			int x=ps.executeUpdate();
			if(x>0) {
				PreparedStatement ps2= conn.prepareStatement("select customerId from customer where cemail=? AND password=?");
				ps2.setString(1, c.getCemail());
				ps2.setString(2,c.getCpassword());
				ResultSet rs= ps2.executeQuery();
				if(rs.next()) {
					int x2=rs.getInt("customerId");
					newc=new Customer(x2, c.getCname(), c.getCaddress(), c.getMobile(), c.getCemail(),c.getCpassword());
				}
				
			}
			else {
				throw new CustException("Some field are not filled!!");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return newc;
	}

	@Override
	public List<ProductSeller> showProduct(String ct) throws ProductException {
		List<ProductSeller> allproducts=null;
		try(Connection conn=DBUtil.provideConnection()) {
			PreparedStatement ps=conn.prepareStatement("select p.pid,p.pname,p.price,p.unit,s.sellerId,s.sname,c.ctname from category c INNER JOIN product p INNER JOIN seller s ON p.cid=c.ctid AND p.sellerid=s.sellerId AND c.ctname=?");
			ps.setNString(1,ct);
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				int pid=rs.getInt("pid");
				String pname=rs.getString("pname");
				int price=rs.getInt("price");
				int unit=rs.getInt("unit");
				int sellerId= rs.getInt("sellerId");
				String sname=rs.getString("sname");
				String ctname=rs.getString("ctname");
				
				ProductSeller pseller=new ProductSeller(pid, pname, price, unit, sellerId, sname, ctname);
				allproducts.add(pseller);
			}
		} catch (Exception e) {
			throw new ProductException();
		}
		return allproducts;
	}

	@Override
	public String buyProduct(int cid,int pid, int quantity) {
		String status="Sorry Not able to placed the order!";
		try (Connection conn=DBUtil.provideConnection()){
			PreparedStatement ps= conn.prepareStatement("select sellerid,unit,price,cid from product where pid=?");
			PreparedStatement ps3=conn.prepareStatement("select curdate() from dual");
			ResultSet rs3= ps3.executeQuery();
			String orderdate=rs3.getString("curdate()");
			ps.setInt(1, pid);
			ResultSet rs= ps.executeQuery();
			if(rs.next()) {
				int sellerid=rs.getInt("sellerid");
				int unit=rs.getInt("unit");
				int price=rs.getInt("price");
				int ctid=rs.getInt("cid");
				int orderprice=(int)(quantity*price);
				if(unit<=0) {
					status="Out Of Stock";
					return status;
				}
				else if(unit<quantity){
					status="we have only"+unit+"product";
					return status;
				}
				else {
					PreparedStatement ps2= conn.prepareStatement("insert into orderdetails(customerid,sellerid,categoryid,quantity,orderprice,OrderDate) values(?,?,?,?,?,?)");
					ps2.setInt(1,cid);
					ps2.setInt(2,sellerid);
					ps2.setInt(3, ctid);
					ps2.setInt(4,quantity);
					ps2.setInt(5,orderprice);
					ps2.setString(6,orderdate);
					int x=ps2.executeUpdate();
					if(x>0) {
						PreparedStatement ps4=conn.prepareStatement("update product set unit=unit-? where pid=?");
						ps4.setInt(1,quantity);
						ps4.setInt(2,pid);
						ps4.executeUpdate();
						status="Order Placed Sucessfully!";
						return status;
					}
				}
			}
			
			
			
		} catch (Exception e) {
			status=e.getMessage();
		}
		
		return status;
	}

	@Override
	public List<Product> ProductByCategory(String ct) throws ProductException {
		List<Product> allproduct=null;
		try (Connection conn= DBUtil.provideConnection()){
			PreparedStatement ps= conn.prepareStatement("select ctid from category where ctname=?");
			ps.setString(1, ct);
			ResultSet rs= ps.executeQuery();
			if(rs.next()) {
				int ctid=rs.getInt("ctid");
				PreparedStatement ps2= conn.prepareStatement("select * from product where cid=?");
				ps2.setInt(1, ctid);
				ResultSet rs2= ps2.executeQuery();
				while(rs2.next()) {
					int pid=rs2.getInt("pid");
					String pname=rs2.getString("pname");
					int price=rs2.getInt("price");
					int unit=rs2.getInt("unit");
					int sellerid=rs2.getInt("sellerid");
					int cid=rs2.getInt("cid");
					
					Product p=new Product(pid, pname, price, unit, sellerid, cid);
					allproduct.add(p);
				}
			}
			
		} catch (Exception e) {
			throw new ProductException(e.getMessage());
		}
		
		return allproduct;
	}

}
