package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class DataSourceDB implements Initializable {
    private ObservableList<TableModel> data = FXCollections.observableArrayList();

    @FXML
    private TextField query_field;

    @FXML
    private TableView<TableModel> tableView;

    @FXML
    private TableColumn<TableModel, String> col_first;

    @FXML
    private TableColumn<TableModel, String> col_last;

    @FXML
    void ButtonClick(ActionEvent event) {
        String query_string  = query_field.getText();
        DBQuery(query_string);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public static void DBTest() {
        String conn_url = "jdbc:mysql://localhost:3306/employees?user=root&password=abcd1234!@";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(conn_url);
            ResultSet rs = null;
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("select first_name from employees");

            while(rs!=null && rs.next()){
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public void DBQuery(String query_string) {
        String conn_url = "jdbc:mysql://localhost:3306/employees?user=root&password=abcd1234!@";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(conn_url);
            ResultSet rs = null;
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query_string);

            while(rs!=null && rs.next()){
                data.add(new TableModel(rs.getString("first_name"), rs.getString("last_name")));
            }

            col_first.setCellValueFactory(new PropertyValueFactory<TableModel, String>("first_name"));
            col_last.setCellValueFactory(new PropertyValueFactory<TableModel, String>("last_name"));
            tableView.setItems(data);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
