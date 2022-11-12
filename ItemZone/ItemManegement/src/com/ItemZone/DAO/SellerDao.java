package com.ItemZone.DAO;

import java.util.List;

import com.ItemZone.bean.Product;
import com.ItemZone.bean.Seller;
import com.ItemZone.exception.ProductException;
import com.ItemZone.exception.SellerException;

public interface SellerDao {
	public Seller SellerlogIn(String email,String password) throws SellerException;
	public Seller SellerReg(Seller s) throws SellerException;
	public List<Product> sellerProduct(int sellerid) throws ProductException;
	public String updateItem(int pid,int np,int ai);
	public Product addItem(Product p) throws ProductException;
	public String deleteItem(int sid,int pid);
}
