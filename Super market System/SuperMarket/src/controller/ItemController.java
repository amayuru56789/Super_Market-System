package controller;

import db.DbConnection;
import model.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemController extends ItemService{

    public List<Double> getAllUnitPrice() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement
                ("SELECT * FROM Item").executeQuery();
        List<Double> price = new ArrayList<>();
        while (rst.next()){
            price.add(rst.getDouble(4));
        }
        return price;
    }

    public List<String> getAllItemCodes() throws SQLException, ClassNotFoundException {

        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement
                ("SELECT * FROM Item").executeQuery();
        List<String> code = new ArrayList<>();
        while (rst.next()){
            code.add(rst.getString(1));
        }
        return code;
    }

    @Override
    public boolean saveItem(Item i) {
        return false;
    }

    @Override
    public boolean updateItem(Item i) {
        return false;
    }

    @Override
    public boolean deleteItem(String code) {
        return false;
    }

    public Item getItem(String code) throws SQLException, ClassNotFoundException {

        ResultSet rst = DbConnection.getInstance().getConnection().prepareStatement
                ("SELECT * FROM Item WHERE itemCode='" + code + "'").executeQuery();
        if (rst.next()){
            return new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5)
            );
        }
        return null;

    }

    @Override
    public ArrayList<Item> getAllItem() {
        return null;
    }

}
