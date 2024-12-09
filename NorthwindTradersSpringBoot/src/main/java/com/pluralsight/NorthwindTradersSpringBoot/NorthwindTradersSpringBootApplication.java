package com.pluralsight.NorthwindTradersSpringBoot;

import com.pluralsight.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class NorthwindTradersSpringBootApplication {
	private static ApplicationContext context;


	public static void main(String[] args) {
		context = SpringApplication.run(NorthwindTradersSpringBootApplication.class, args);

		int options;
		do{
			System.out.println("Please select from the following choices:");
			System.out.println("1 - Add Product");
			System.out.println("2 - List All Products");
			System.out.println("99 - Quit");
			options = Console.PromptForInt("Enter your choice: ");

			switch(options){
				case 1 -> processAddProduct();
				case 2 -> processGetAllProducts();
			}
		}while(options != 99);
	}

	private static void processGetAllProducts() {
		ProductDAO productDAO1 = context.getBean(ProductDAO.class);
		List<Product> products = productDAO1.getAll();
		for(Product product : products){
			System.out.println(product);
		}
	}

	private static void processAddProduct() {
		ProductDAO productDAO = context.getBean(ProductDAO.class);

		int productId = Console.PromptForInt("Please enter the product ID: ");
		String name = Console.PromptForString("Please enter the Product Name: ");
		String categoty = Console.PromptForString("Please enter the Category: ");
		double price = Console.PromptForDouble("Please enter the Price: ");
		Product product = new Product(productId, name, categoty, price);

		productDAO.add(product);
	}
}
