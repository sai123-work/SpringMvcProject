package com.luv2code.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;


@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Customer> getCustomers() {
		
		//get current HIbernate session
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		//write the query
		
		Query<Customer> theQuery = currentSession.createQuery("from Customer order by lastName", Customer.class);
		//execute the query and get result list
		
		List<Customer> customers = theQuery.getResultList();
		
		//return the result
		
		return customers;
	}


	@Override
	public void saveCustomer(Customer theCustomer) {
		// get current session
		Session currentSession = sessionFactory.getCurrentSession();
		
		//write query
		currentSession.saveOrUpdate(theCustomer);
		
	}


	@Override
	public Customer getCustomer(int theId) {
//		getting the current session of hibernate
		Session currentSession = sessionFactory.getCurrentSession();
		
//		writing the query to get the data from database
		Customer theCustomer = currentSession.get(Customer.class, theId);
		return theCustomer;
	}


	@Override
	public void deleteCustomer(int theId) {
		
//		getting the current session of hibernate
		Session currentSession = sessionFactory.getCurrentSession();
		
//		Writing the Query
		Query theQuery = currentSession.createQuery("delete from Customer where id= :customerId");
		theQuery.setParameter("customerId", theId);
		theQuery.executeUpdate();
		
	}








}
