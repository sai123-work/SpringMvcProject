package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@GetMapping("/list")
	public String listCustomers(Model theModel){
		
		//get the data(customers) from CustomerDAO
		
		List<Customer> theCustomers = customerService.getCustomers();
		
		//add the customers  to model
		
		theModel.addAttribute("customers", theCustomers);
		
		
		return "list-customers";
	}
	
	  @GetMapping("/showFormForAdd")
      public String showFormForAdd(Model theModel){
		  
		 Customer theCustomer = new Customer();
		 theModel.addAttribute("customer", theCustomer);
    	  
    	  return "customer-form";
      }
	  
	  @PostMapping("/saveCustomer")
	  public String saveCustomer(@ModelAttribute("customer") Customer theCustomer){
		  customerService.saveCustomer(theCustomer);
		  
		  //System.out.println(theCustomer.toString());
		  
		 return "redirect:/customer/list";
	  }
	  
	  @GetMapping("/showFormForUpdate")
	  public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel){
		  
//		  get the customer from service
		  Customer theCustomer=customerService.getCustomer(theId);
		  
//		  set customer as a model attribute
		  theModel.addAttribute(theCustomer);

//		  update the prepopulate form
		  return "customer-form";
		  }
	  
	  
	  @GetMapping("/delete")
	  public String deleteCustomer(@RequestParam("customerId") int theId){
		  
		  customerService.deleteCustomer(theId);
		  return "redirect:/customer/list";
	  }

}
