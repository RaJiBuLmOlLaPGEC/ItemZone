package com.ItemZone.DAO;

import java.util.List;

import com.ItemZone.bean.Customer;
import com.ItemZone.bean.Order;
import com.ItemZone.bean.Product;
import com.ItemZone.bean.ProductSeller;
import com.ItemZone.exception.CustException;
import com.ItemZone.exception.OrderException;
import com.ItemZone.exception.ProductException;

public interface BuyerDao {
	public Customer loginCustomer(String e,String p) throws CustException;
	public Customer CustomerReg(Customer c) throws CustException;
	public List<ProductSeller> showProduct(String ct) throws ProductException;
	public String buyProduct(int cid,int pid,int quantity);
	public List<Product> ProductByCategory(String ct) throws ProductException;
	
}
