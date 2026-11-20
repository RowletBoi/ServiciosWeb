
package org.example.serviciosweb.controller;

import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.serviciosweb.model.Customer;
import org.example.serviciosweb.service.CustomerService;

import java.time.LocalDateTime;
import java.util.List;

public class CustomerController {

    @FXML
    public Button btnConsultar;
    @FXML
    public Label lblEstado;
    @FXML
    public TableView<Customer> tblCustomers;
    @FXML
    public TableColumn<Customer, Integer> colId;
    @FXML
    public TableColumn<Customer, String> colFirstName;
    @FXML
    public TableColumn<Customer, String> colLastName;
    @FXML
    public TableColumn<Customer, String> colEmail;
    @FXML
    public TableColumn<Customer, String> colPhone;
    @FXML
    public TableColumn<Customer, LocalDateTime> colDate;
    @FXML
    public TableColumn<Customer, String> colCountry;
    @FXML
    public TableColumn<Customer, String> colCity;
    @FXML
    public TableColumn<Customer, String> colAddress;
    @FXML
    public TableColumn<Customer, Double> colAnnual;
    @FXML
    public TableColumn<Customer, Integer> colCredit;
    @FXML
    public TableColumn<Customer, String> colStatus;
    @FXML
    public TableColumn<Customer, Boolean> colHasUnpain;
    @FXML
    public TableColumn<Customer, Double> colUnpaidAmount;

    public final CustomerService customerService = new CustomerService();

    @FXML
    public void initialize() {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date_of_birth"));
        colCountry.setCellValueFactory(new PropertyValueFactory<>("country"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colAnnual.setCellValueFactory(new PropertyValueFactory<>("annual_income"));
        colCredit.setCellValueFactory(new PropertyValueFactory<>("credit_score"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colHasUnpain.setCellValueFactory(new PropertyValueFactory<>("has_unpaid_debt"));
        colUnpaidAmount.setCellValueFactory(new PropertyValueFactory<>("unpaid_debt_amount"));
    }

    @FXML
    public void consultarCustomer() {

        Task<List<Customer>> task = new Task<List<Customer>>() {

            @Override
            protected List<Customer> call() throws Exception {
                return customerService.obtenerCustomers();
            }
        };

        btnConsultar.disableProperty().bind(task.runningProperty());

        lblEstado.setText("Consultando API REST......");

        task.setOnSucceeded(e -> {

            List<Customer> customers = task.getValue();

            tblCustomers.setItems(
                    FXCollections.observableArrayList(customers)
            );

            lblEstado.setText("Customers encontrados: " + customers.size());
        });

        // En caso de error
        task.setOnFailed(e -> {

            Throwable error = task.getException();

            lblEstado.setText("Error: " + error.getMessage());
        });

        Thread hilo = new Thread(task);
        hilo.setDaemon(true);
        hilo.start();
    }
}
