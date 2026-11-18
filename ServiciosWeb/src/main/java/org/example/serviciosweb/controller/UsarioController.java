
package org.example.serviciosweb.controller;

import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.serviciosweb.model.Usuario;
import org.example.serviciosweb.service.UsuarioService;

import java.util.List;

public class UsarioController {

    @FXML
    public Button btnConsultar;
    @FXML
    public TableView<Usuario> tblUsuarios;
    @FXML
    public TableColumn<Usuario, Integer> colId;
    @FXML
    public TableColumn<Usuario, String> colNombre;
    @FXML
    public TableColumn<Usuario, String> colUsername;
    @FXML
    public TableColumn<Usuario, String> colCorreo;
    @FXML
    public TableColumn<Usuario, String> coltelefono;
    @FXML
    public TableColumn<Usuario, String> colciudad;
    @FXML
    public TableColumn<Usuario, String> colEmpresa;
    @FXML
    public Label lblEstado;

    public final UsuarioService usuarioService = new UsuarioService();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colciudad.setCellValueFactory(new PropertyValueFactory<>("ciudad"));
        coltelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colEmpresa.setCellValueFactory(new PropertyValueFactory<>("empresa"));
    }

    @FXML
    public void consultarUsuario() {

        Task<List<Usuario>> task = new Task<List<Usuario>>() {
            @Override
            protected List<Usuario> call() throws Exception {
                return usuarioService.obtenerUsuario();
            }
        };

        btnConsultar.disableProperty().bind(task.runningProperty());

        lblEstado.setText("Consultando API REST......");

        task.setOnSucceeded(e -> {
            List<Usuario> usuarios = task.getValue();

            tblUsuarios.setItems(
                    FXCollections.observableArrayList(usuarios)
            );

            lblEstado.setText("Usuarios encontrados: " + usuarios.size());
        });

        // En caso de error
        task.setOnFailed(e -> {
            Throwable error = task.getException();
            lblEstado.setText(error.getMessage());
        });

        Thread hilo = new Thread(task);
        hilo.setDaemon(true);
        hilo.start();
    }
}
