package com.ItemZone.DAO;

import java.util.List;

import com.ItemZone.bean.Admin;
import com.ItemZone.bean.Customer;
import com.ItemZone.bean.Order;
import com.ItemZone.bean.Seller;
import com.ItemZone.exception.AdminException;
import com.ItemZone.exception.CustException;
import com.ItemZone.exception.OrderException;
import com.ItemZone.exception.SellerException;

public interface AdminDao {
	public Admin logInAdmin(String email,String password)throws AdminException;
	public List<Customer> allCustomers() throws CustException;
	public List<Seller> allSellers() throws SellerException;
	public List<Order> showTodaysOrder() throws OrderException;
	
}
