package com.ItemZone.Application;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Scanner;

import com.ItemZone.DAO.BuyerDao;
import com.ItemZone.DAO.BuyerDaoImpl;
import com.ItemZone.bean.Order;
import com.ItemZone.bean.Product;
import com.ItemZone.bean.ProductSeller;
import com.ItemZone.exception.OrderException;
import com.ItemZone.exception.ProductException;

public class CustomerOperation {
	public static void selectOption() {
		Scanner sc= new Scanner(System.in);
		System.out.println("Please select currect option..");
		System.out.println("A. Search and view Items by category");
		System.out.println("B. Select and view all the buyers and also their Items category-wise");
		System.out.println("C. Selects Items to buy");
		System.out.println("D. Exit Application.");
		String choise=sc.next().toUpperCase();
		switch (choise) {
		case "A":{
			System.out.println("Please enter category name :");
			String category=sc.next();
			BuyerDao bdao= new BuyerDaoImpl();
			try {
				List<Product> ps=bdao.ProductByCategory(category);
				if(ps.size()>0) {
					for(Product p:ps) {
						System.out.println("Product Id is :"+ p.getPid());
						System.out.println("Product name is :"+p.getPname());
						System.out.println("Product Price is :"+p.getPrice());
						System.out.println("Available quantity :"+p.getUnit());
						System.out.println("Seller Id :"+p.getSellerid());
						System.out.println("Category name :"+category);
						System.out.println("==========================================");
					}
				}
				else {
					System.out.println("Sorry No Item found under this category..");
				}
				
			} catch (ProductException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			CustomerOperation.selectOption();
			break;
		}
		case "B":{
			System.out.println("Please enter category name :");
			String category=sc.next();
			BuyerDao bdao= new BuyerDaoImpl();
			try {
				List<ProductSeller> ps =bdao.showProduct(category);
				if(ps!=null) {
					for(ProductSeller p:ps) {
						System.out.println("Product Id is :"+ p.getPid());
						System.out.println("Product name is :"+p.getPname());
						System.out.println("Product Price is :"+p.getPrice());
						System.out.println("Available quantity :"+p.getUnit());
						System.out.println("Seller Id :"+p.getSellerId());
						System.out.println("seller Name :"+p.getSname());
						System.out.println("Category name :"+p.getCtname());
						System.out.println("==========================================");
					}
				}
				else {
					System.out.println("Sorry No Item found under this category..");
				}
				
			} catch (ProductException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			CustomerOperation.selectOption();
			break;
		}
		case "C":{
			System.out.println("Enter your id to buy any product: ");
			int cid=sc.nextInt();
			System.out.println("Please enter product Id To buy :");
			int pid=sc.nextInt();
			System.out.println("Enter Quentity: ");
			int q=sc.nextInt();
			BuyerDao bdao= new BuyerDaoImpl();
			String result=bdao.buyProduct(cid, pid, q);
			System.out.println(result);
			CustomerOperation.selectOption();
			break;
		}
		case "D":{
			System.out.println("Thanks for use ItemZone");
			break;
		}
		default:
			System.out.println("Invalid Input");
			CustomerOperation.selectOption();
		}
	}
}
