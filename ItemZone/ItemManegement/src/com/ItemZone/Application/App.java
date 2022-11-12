package com.ItemZone.Application;

import java.util.Scanner;

import com.ItemZone.DAO.AdminDao;
import com.ItemZone.DAO.AdminDaoImpl;
import com.ItemZone.DAO.BuyerDao;
import com.ItemZone.DAO.BuyerDaoImpl;
import com.ItemZone.DAO.SellerDao;
import com.ItemZone.DAO.SellerDaoImpl;
import com.ItemZone.bean.Admin;
import com.ItemZone.bean.Customer;
import com.ItemZone.bean.Seller;
import com.ItemZone.exception.AdminException;
import com.ItemZone.exception.CustException;
import com.ItemZone.exception.SellerException;



public class App {
	public static void main(String[] args){
		System.out.println("Welcome To ItemZone...");
		System.out.println("Please Select the correct option given below..");
		System.out.println("1.Administrator\n2. Buyer\n3. Seller");
		
		Scanner sc= new Scanner(System.in);
		int choise =sc.nextInt();
		
		switch(choise) {
			case 1:{
				System.out.println("Welcome To admin log in Page");
				System.out.println("Enter your username :");
				String username= sc.next();
				System.out.println("Enter your password :");
				String password= sc.next();
				AdminDao adao=new AdminDaoImpl();
				Admin result;
				try {
					result = adao.logInAdmin(username, password);
					if(result==null) {
						System.out.println("Invalid User Name!");
					}
					else if(result.getPassword()!=null) {
						System.out.println("welcome "+result.getName());
						AdminOperations.selectOption();
					}
					else {
						System.out.println("Invalid password!");
					}
				} catch (AdminException e) {
					
					System.out.println(e.getMessage());
				}
				
				break;
			}
			case 2:{
				System.out.println("Welcome To Customer Page");
				System.out.println("Please Select the correct option given below..");
				System.out.println("1. Login As Buyer\n2. New Buyer? Register Now!!");
				int choise1=sc.nextInt();
				switch (choise1) {
				case 1: {
					System.out.println("Enter your email id: ");
					String email= sc.next();
					System.out.println("Enter your password: ");
					String password=sc.next();
					BuyerDao bdao= new BuyerDaoImpl();
					try {
						Customer c= bdao.loginCustomer(email, password);
						if(c==null) {
							System.out.println("Invalid email!!!");
						}
						else if(c.getCemail()!=null && c.getCpassword()!=null) {
							System.out.println("welcome "+c.getCname());
							CustomerOperation.selectOption();
						}
						else {
							System.out.println("Invalid password!!!");
						}
					} catch (CustException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					
					break;
				}
				case 2:{
					System.out.println("Enter your details..");
					System.out.println("Enter your name :");
					String name=sc.next();
					System.out.println("Enter your city :");
					String address=sc.next();
					System.out.println("Enter your mobile number :");
					String mobile=sc.next();
					System.out.println("Enter your email :");
					String email=sc.next();
					System.out.println("Enter your password :");
					String password=sc.next();
					
					Customer c=new Customer(0,name, address, mobile, email, password);
					BuyerDao bdao=new BuyerDaoImpl();
					try {
						Customer newc= bdao.CustomerReg(c);
						if(newc==null) {
							System.out.println("Not registered!!");
						}
						else {
							System.out.println("Register Sucessfull "+newc.getCname());
							System.out.println("Your id number is :"+newc.getCid());
							CustomerOperation.selectOption();
						}
					} catch (CustException e) {
						// TODO Auto-generated catch block
						System.out.println(e.getMessage());
					}
					break;
				}
				default:
					System.out.println("Unexpected value: " + choise1);
				}
				break;
			}
			case 3:{
				System.out.println("Welcome To Seller Page");
				System.out.println("Please Select the correct option given below..");
				System.out.println("1. Login As seller\n2. New Seller? Register Now!!");
				int choise1 =sc.nextInt();
				switch(choise1) {
					case 1:{
						System.out.println("Enter your login details!!");
						System.out.println("Enter your username :");
						String username= sc.next();
						System.out.println("Enter your password :");
						String password= sc.next();
						SellerDao sdao=new SellerDaoImpl();
						Seller s;
						try {
							s = sdao.SellerlogIn(username, password);
							if(s==null) {
								System.out.println("Invalid User Name!");
							}
							else if(s.getSpassword()!=null) {
								System.out.println("welcome "+s.getSname());
								
								
							}
							else {
								System.out.println("Invalid password!");
							}
						} catch (SellerException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
						}
						break;	
					}
					case 2:{
						System.out.println("Enter your details!!!");
						System.out.println("Enter your name:");
						String name= sc.next();
						System.out.println("Enter your address :");
						String address= sc.next();
						System.out.println("Enter your email :");
						String email= sc.next();
						System.out.println("Enter your password :");
						String password= sc.next();
						
						Seller s=new Seller(name, address, email, password);
						SellerDao sdao=new SellerDaoImpl();
						Seller newseller;
						try {
							newseller= sdao.SellerReg(s);
							if(newseller!=null) {
								System.out.println("Register Sucessfull\n Your Id is "+newseller.getSid());
							}
							else {
								System.out.println("Not registered !!");
							}
							
						} catch (SellerException e) {
							// TODO Auto-generated catch block
							System.out.println(e.getMessage());
						}
						break;
					}
					
				}
				
				break;
			}
			
			default:
				System.out.println("Unexpected value: " + choise);
		
		}
		sc.close();
	}
}
