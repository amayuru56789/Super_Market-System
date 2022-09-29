package controller;

import db.DbConnection;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerController extends CustomerService{
    @Override
    public boolean saveCustomer(Customer c) throws SQLException, ClassNotFoundException {

        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Customer VALUES (?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,c.getId());
        stm.setObject(2,c.getTitle());
        stm.setObject(3,c.getName());
        stm.setObject(4,c.getAddress());
        stm.setObject(5,c.getCity());
        stm.setObject(6,c.getProvince());
        stm.setObject(7,c.getPostalCode());

        return (stm.executeUpdate()>0);
    }

    @Override
    public boolean updateCustomer(Customer c1) throws SQLException, ClassNotFoundException {

        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement
                ("UPDATE Customer SET custTitle=? ,custName=? ,custAddress=? ,city=? ,province=? ,postalCode=? WHERE id=?");
        stm.setObject(1,c1.getTitle());
        stm.setObject(2,c1.getName());
        stm.setObject(3,c1.getAddress());
        stm.setObject(4,c1.getCity());
        stm.setObject(5,c1.getProvince());
        stm.setObject(6,c1.getPostalCode());
        return stm.executeUpdate()>0;

    }

    @Override
    public boolean deleteCustomer(String id) {
        return false;
    }

    @Override
    public Customer getCustomer(String id) throws SQLException, ClassNotFoundException {

        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement stm = con.prepareStatement("SELECT * FROM  Customer WHERE id=?");
        stm.setObject(1,id);
        ResultSet rst = stm.executeQuery();
        if (rst.next()){
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            );
        }else {
            return null;
        }

    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        return null;
    }
}
