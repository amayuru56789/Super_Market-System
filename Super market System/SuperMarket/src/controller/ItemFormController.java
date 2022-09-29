package controller;

import com.jfoenix.controls.JFXTextField;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;
import views.tm.ItemTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemFormController {
    public JFXTextField txtItemCode;
    public JFXTextField txtDescription;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtqtyOnHand;
    public TableView tblItem;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colSize;
    public TableColumn colQTY;

    public void initialize(){

        colCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("QtyOnHand"));

        try {
            loadAllItems();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void loadAllItems() throws SQLException, ClassNotFoundException {

        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement stm = con.prepareStatement("SELECT * FROM Item");
        ResultSet rst = stm.executeQuery();
        ObservableList<ItemTM> obList = FXCollections.observableArrayList();
        while (rst.next()){
            obList.add(new ItemTM(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getInt(5)
            ));
        }
        tblItem.setItems(obList);

    }

    public void saveItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Item i1 = new Item(txtItemCode.getText(),
                txtDescription.getText(),
                txtPackSize.getText(),
                Double.parseDouble(txtUnitPrice.getText()),
                Integer.parseInt(txtqtyOnHand.getText())
        );

        if (saveItem(i1)){
            new Alert(Alert.AlertType.CONFIRMATION,"Saved...").show();
        }else {
            new Alert(Alert.AlertType.WARNING,"Try again").show();
        }

    }

    private boolean saveItem(Item i1) throws SQLException, ClassNotFoundException {

        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Item VALUES(?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,i1.getItemCode());
        stm.setObject(2,i1.getDescription());
        stm.setObject(3,i1.getPackSize());
        stm.setObject(4,i1.getUnitPrice());
        stm.setObject(5,i1.getQtyOnHand());

        return (stm.executeUpdate()>0);
    }
}
