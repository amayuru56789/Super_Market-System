package controller;

import model.Item;

import java.sql.SQLException;
import java.util.ArrayList;

abstract public class ItemService {

    abstract public boolean saveItem(Item i);
    abstract public boolean updateItem(Item i);
    abstract public boolean deleteItem(String code);
    abstract public Item getItem(String code) throws SQLException, ClassNotFoundException;
    abstract public ArrayList<Item> getAllItem();

}
