package controller;

import model.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

abstract public class CustomerService {

    abstract public boolean saveCustomer(Customer c) throws SQLException, ClassNotFoundException;
    abstract public boolean updateCustomer(Customer c) throws SQLException, ClassNotFoundException;
    abstract public boolean deleteCustomer(String id);
    abstract public Customer getCustomer(String id) throws SQLException, ClassNotFoundException;
    abstract public ArrayList<Customer> getAllCustomers();

}
