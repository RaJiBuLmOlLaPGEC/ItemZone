package com.ItemZone.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.ItemZone.bean.Product;
import com.ItemZone.bean.Seller;
import com.ItemZone.exception.ProductException;
import com.ItemZone.exception.SellerException;
import com.ItemZone.utility.DBUtil;

public class SellerDaoImpl implements SellerDao{

	@Override
	public Seller SellerlogIn(String email,String password) throws SellerException {
		Seller s=null;
		try (Connection conn=DBUtil.provideConnection()){
			PreparedStatement ps=conn.prepareStatement("select * from seller where semail=?");
			
			ps.setString(1,email);
			
			
			ResultSet rs= ps.executeQuery();
			
			if(rs.next()) {
				PreparedStatement ps2=conn.prepareStatement("select * from seller where semail=? AND password=?");
				ps2.setString(2,password);
				ps2.setString(1,email);
				
				ResultSet rs2= ps2.executeQuery();
				
				if(rs2.next()) {
					int sid=rs2.getInt("sellerId");
					String n=rs2.getString("sname");
					String a=rs2.getString("saddress");
					String e=rs2.getString("semail");
					String p=rs2.getString("password");
					s=new Seller(sid,n,a,e,p);
				}else {
					s=new Seller(0,null,null,email,null);
				}
				
			}
			else {
				s=null;
			}
		} catch (SQLException e) {
			throw new SellerException(e.getMessage());
		}
		
		return s;
	}

	@Override
	public Seller SellerReg(Seller s) throws SellerException {
		Seller seller=null;
		try (Connection conn= DBUtil.provideConnection()){
			PreparedStatement ps=conn.prepareStatement("insert into seller (sname,saddress,semail,password) values(?,?,?,?)");
			ps.setString(1,s.getSname());
			ps.setString(2,s.getSaddress());
			ps.setString(3,s.getSemail());
			ps.setString(4,s.getSpassword());
			
			int x= ps.executeUpdate();
			if(x>0) {
				PreparedStatement ps2=conn.prepareStatement("select * from seller where semail=? AND password=?");
				ps2.setString(1,s.getSemail());
				ps2.setString(2,s.getSpassword());
				ResultSet rs =ps2.executeQuery();
				if(rs.next()) {
					seller=new Seller(rs.getInt("sellerId"),s.getSname(),s.getSaddress(),s.getSemail(),s.getSpassword());
				}
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			throw new SellerException(e.getMessage());
		}
		return seller;
	}

	@Override
	public List<Product> sellerProduct(int sellerid) throws ProductException {
		List<Product> myproducts=new LinkedList<>();
		try(Connection conn=DBUtil.provideConnection()) {
			PreparedStatement ps=conn.prepareStatement("select * from product where sellerid=?");
			ps.setInt(1, sellerid);
			ResultSet rs= ps.executeQuery();
			while(rs.next()) {
				int pid=rs.getInt("pid");
				String pname=rs.getString("pname");
				int price=rs.getInt("price");
				int unit=rs.getInt("unit");
				int seid=rs.getInt("sellerid");
				int cid=rs.getInt("cid");
				
				Product p= new Product(pid, pname, price, unit, seid, cid);
				myproducts.add(p);
			}
			
		} catch (Exception e) {
			throw new ProductException(e.getMessage());
		}
		
		return myproducts;
	}

	@Override
	public String updateItem(int pid,int np, int ai) {
		String ans="Not updated!";
		try(Connection conn= DBUtil.provideConnection()) {
			PreparedStatement ps= conn.prepareStatement("update product set price=?,unit=unit+? where pid=?");
			ps.setInt(1, np);
			ps.setInt(2, ai);
			ps.setInt(3, pid);
			int x= ps.executeUpdate();
			if(x>0) {
				ans="Updated Sucessfully";
				return ans;
			}
		} catch (Exception e) {
			ans=e.getMessage();
		}
		
		return ans;
	}

	@Override
	public Product addItem(Product p) throws ProductException {
		try (Connection conn= DBUtil.provideConnection()){
			PreparedStatement ps= conn.prepareStatement("insert into product (pname,price,unit,sellerid,cid) values(?,?,?,?,?)");
			ps.setString(1,p.getPname());
			ps.setInt(2, p.getPrice());
			ps.setInt(3, p.getUnit());
			ps.setInt(4, p.getSellerid());
			ps.setInt(5, p.getCid());
			int x= ps.executeUpdate();
			if(x>0) {
				PreparedStatement ps2=conn.prepareStatement("select pid from product where pname=? AND sellerid=?");
				ps2.setString(1,p.getPname());
				ps2.setInt(2, p.getSellerid());
				ResultSet rs= ps2.executeQuery();
				if(rs.next()) {
					int pid=rs.getInt("pid");
					System.out.println(pid);
					p.setPid(pid);
					System.out.println("Item added sucessfully!");
				}
				
			}
			else {
				throw new ProductException("Not added Item!!");
			}
			
		} catch (Exception e) {
			throw new ProductException(e.getMessage());
		}
		
		return p;
	}

	@Override
	public String deleteItem(int sid, int pid) {
		String ans="Not deleted!!";
		try(Connection conn=DBUtil.provideConnection()) {
			PreparedStatement ps= conn.prepareStatement("delete from product where pid=? AND sellerid=?");
			ps.setInt(1, pid);
			ps.setInt(2, sid);
			int x=ps.executeUpdate();
			if(x>0) {
				ans="Item deleted Sucessfully!";
				return ans;
			}
		} catch (Exception e) {
			ans=e.getMessage();
		}
		
		return ans;
	}

}
