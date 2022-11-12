package com.ItemZone.Application;

import java.util.List;
import java.util.Scanner;

import com.ItemZone.DAO.AdminDao;
import com.ItemZone.DAO.AdminDaoImpl;
import com.ItemZone.bean.Customer;
import com.ItemZone.bean.Order;
import com.ItemZone.bean.Seller;
import com.ItemZone.exception.CustException;
import com.ItemZone.exception.OrderException;
import com.ItemZone.exception.SellerException;

public class AdminOperations {
	public static void selectOption() {
		System.out.println("Please Select the correct option given below..");
		System.out.println("A. View the registered buyer list.\n"
				+ "B. View the registered Seller list.\n"
				+ "C. View the daily dispute report.\n"
				+ "D. View the daily selling report.\n"
				+ "E. Solve the dispute report.");
		Scanner sc= new Scanner(System.in);
		String choise=sc.next().toUpperCase();
		switch (choise) {
		case "A": {
			//View the registered buyer list
			AdminDao adao=new AdminDaoImpl();
			List<Customer> allCustomers;
			try {
				allCustomers = adao.allCustomers();
				for(Customer c:allCustomers) {
					System.out.println("Customer Id is --> "+c.getCid());
					System.out.println("Customer name is --> "+c.getCname());
					System.out.println("Address is --> "+c.getCaddress());
					System.out.println("Mobile number --> "+c.getMobile());
					System.out.println("mail Id --> "+ c.getCemail());
					System.out.println("=========================================");
					
				}
			} catch (CustException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			AdminOperations.selectOption();
			break;
		}
		case "B": {
//			View the registered Seller list
			AdminDao adao=new AdminDaoImpl();
			try {
				List<Seller>allSeller= adao.allSellers();
				for(Seller s:allSeller) {
					System.out.println("Seller Id is --> "+s.getSid());
					System.out.println("Seller name is --> "+s.getSname());
					System.out.println("Address is --> "+s.getSaddress());
					System.out.println("mail Id --> "+s.getSemail());
					System.out.println("=========================================");
				}
			} catch (SellerException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			AdminOperations.selectOption();
			break;
		}
		case "D":{
			System.out.println("View the selling report..");
			AdminDao adao= new AdminDaoImpl();
			try {
				List<Order>todaysOrder= adao.showTodaysOrder();
				if(todaysOrder!=null) {
					for(Order o:todaysOrder) {
						System.out.println("Order id :"+o.getOrderid());
						System.out.println("Customer id :"+o.getCustomerid());
						System.out.println("Seller id :"+o.getSellerid());
						System.out.println("Category id :"+o.getCategoryid());
						System.out.println("Order quantity :"+o.getQuantity());
						System.out.println("Total order price :"+o.getTotalprice());
						System.out.println("==========================================");
						
					}
				}
				else {
					System.out.println("No order found today.");
				}
				
			} catch (OrderException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			AdminOperations.selectOption();
			break;
		}
		default:
			System.out.println("Unexpected value: " + choise);
			AdminOperations.selectOption();
			
		}
	}

}
