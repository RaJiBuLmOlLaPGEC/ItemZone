package com.ItemZone.Application;

import java.util.List;
import java.util.Scanner;

import com.ItemZone.DAO.SellerDao;
import com.ItemZone.DAO.SellerDaoImpl;
import com.ItemZone.bean.Product;
import com.ItemZone.exception.ProductException;

public class SellerOperation {

	public static void selectOperation() {
		System.out.println("Please Enter currect option");
		System.out.println("A. Create a list of items to sell.\n"
				+ "B. Update Item price, quantity, etc.\n"
				+ "C. Add a new Item to the list.\n"
				+ "D. Remove items from the list.");
		Scanner sc= new Scanner(System.in);
		String choise=sc.next().toUpperCase();
		switch (choise) {
		case "A":{
			System.out.println("Enter your Seller Id");
			int sellerid=sc.nextInt();
			SellerDao sdao=new SellerDaoImpl();
			try {
				List<Product> allproducts =sdao.sellerProduct(sellerid);
				if(allproducts!=null) {
					for(Product p:allproducts) {
						System.out.println("Product id: "+p.getPid());
						System.out.println("Product name :"+p.getPname());
						System.out.println("Product price :"+p.getPrice());
						System.out.println("Product unit :"+p.getUnit());
						System.out.println("============================");
					}
					
				}
				else {
					System.out.println(" No Product Found!!");
				}
			} catch (ProductException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			SellerOperation.selectOperation();
			break;
		}
		case "B":{
			System.out.println("Enter Product Id to update details: ");
			int pid=sc.nextInt();
			System.out.println("Enter updated price");
			int newprice=sc.nextInt();
			System.out.println("Add Quantity :");
			int newunit=sc.nextInt();
			SellerDao sdao= new SellerDaoImpl();
			
			System.out.println(sdao.updateItem(pid, newprice, newunit));
			SellerOperation.selectOperation();
			break;
		}
		case "C":{
			System.out.println("Enter seller Id to add item");
			int sid=sc.nextInt();
			System.out.println("Enter Product name: ");
			String name=sc.next();
			System.out.println("Enter price: ");
			int price=sc.nextInt();
			System.out.println("Enter Quantity :");
			int unit=sc.nextInt();
			System.out.println("Enter Category Id:");
			int ctid=sc.nextInt();
			
			Product p= new Product(0, name, price, unit, sid, ctid);
			SellerDao sdao= new SellerDaoImpl();
			try {
				Product np= sdao.addItem(p);
				System.out.println("Item Id is"+np.getPid());
			} catch (ProductException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			SellerOperation.selectOperation();
			break;
		}
		case "D":{
			System.out.println("Enter seller Id to delete item");
			int sid=sc.nextInt();
			System.out.println("Enter Product Id: ");
			int pid=sc.nextInt();
			SellerDao sdao= new SellerDaoImpl();
			sdao.deleteItem(sid, pid);
			SellerOperation.selectOperation();
			break;
		}
		default:
			System.out.println("Invalid input");
			SellerOperation.selectOperation();
		}
	}
}
