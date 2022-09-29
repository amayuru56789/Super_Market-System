package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import model.Customer;

import java.sql.SQLException;

public class CustomerFormController {
    public JFXTextField txtId;
    public JFXTextField txtTitle;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtCode;

    public void SaveCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Customer c1 = new Customer(
                txtId.getText(),txtTitle.getText(),txtName.getText(),txtAddress.getText(),txtCity.getText(),txtProvince.getText(),txtCode.getText()
        );

        if (addCustomer(c1)){
            new Alert(Alert.AlertType.CONFIRMATION,"Saved...").show();
        }else {
            new Alert(Alert.AlertType.WARNING,"Try Again..").show();
        }

    }

    private boolean addCustomer(Customer c1) throws SQLException, ClassNotFoundException {

        return new CustomerController().saveCustomer(c1);

    }

    public void selectCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String custId = txtId.getText();
        Customer c1 = new CustomerController().getCustomer(custId);
        if (c1==null){
            new Alert(Alert.AlertType.WARNING,"Empty Result set").show();
        }else {
            setData(c1);
        }

    }

    void setData(Customer c1) {

        txtId.setText(c1.getId());
        txtTitle.setText(c1.getTitle());
        txtName.setText(c1.getName());
        txtAddress.setText(c1.getAddress());
        txtCity.setText(c1.getCity());
        txtProvince.setText(c1.getProvince());
        txtCode.setText(c1.getPostalCode());

    }

    public void updateCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        Customer c1 = new Customer(
                txtId.getText(),txtTitle.getText(),
                txtName.getText(),txtAddress.getText(),txtCity.getText(),
                txtProvince.getText(),txtCode.getText()
        );
        if (new CustomerController().updateCustomer(c1))
            new Alert(Alert.AlertType.CONFIRMATION,"Updated...").show();
        else
            new Alert(Alert.AlertType.WARNING,"Try Again").show();

    }
}
