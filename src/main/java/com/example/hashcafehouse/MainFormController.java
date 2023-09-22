package com.example.hashcafehouse;

import alerts.AlertMessages;
import dataAccess.ProductDataAccess;
import dataAccess.UserDataAccess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Data;
import model.Item;
import model.Product;

import java.io.File;
import java.net.URL;
import java.security.cert.Extension;
import java.util.*;

public class MainFormController implements Initializable{

    @FXML
    private Button addBtn;

    @FXML
    private AnchorPane chartView;

    @FXML
    private Button clearBtn;

    @FXML
    private AreaChart<?, ?> customerChart;

    @FXML
    private Button customersBtn;

    @FXML
    private Button dashboardBtn;

    @FXML
    private TableColumn<Product, Date> date;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button importBtn;

    @FXML
    private AreaChart<?, ?> incomeChart;

    @FXML
    private Button inventoryBtn;

    @FXML
    private StackPane mainForm;

    @FXML
    private Button menuBtn;

    @FXML
    private TableColumn<Product, Integer> price;

    @FXML
    private TextField priceF;

    @FXML
    private TableColumn<Product,String> productId;

    @FXML
    private TextField productIdF;

    @FXML
    private ImageView productImage;

    @FXML
    private TableColumn<Product,String> productName;

    @FXML
    private TextField productNameF;

    @FXML
    private TableColumn<Product,String> productType;

    @FXML
    private ComboBox<Product> productTypeF;

    @FXML
    private Button signoutBtn;

    @FXML
    private ComboBox<Product> statusF;

    @FXML
    private TableColumn<Product,Integer> stock;

    @FXML
    private TextField stockF;

    @FXML
    private AnchorPane tableView;

    @FXML
    private Button updateBtn;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private AnchorPane menuForm;

    @FXML
    private TableColumn<?, ?> menuPrice;

    @FXML
    private TableColumn<?, ?> menuProductName;

    @FXML
    private TableColumn<?, ?> menuQuantity;

    @FXML
    private TableView<?> menuTable;

    @FXML
    private Button payBtn;

    @FXML
    private Button receiptBtn;

    @FXML
    private Button removeBtn;

    @FXML
    private TextField amountPay;

    private Image image;


    private String[] typeList = {"Meals", "Drinks"};

    private String[] statusList = {"Available", "Unavailable"};

    private ObservableList<Item> itemListData;

    public void displayName(){
        String user = Data.username;
        user = user.substring(0,1).toUpperCase() + user.substring(1);
    }


    public ObservableList<Item> menuGetData(){
        return itemListData;
    }

    @FXML
    void addProduct(ActionEvent event) {

        if(productIdF.getText().isEmpty() ||
                productNameF.getText().isEmpty() ||
                productTypeF.getSelectionModel().getSelectedItem() == null||
                stockF.getText().isEmpty() ||
                priceF.getText().isEmpty() ||
                statusF.getSelectionModel().getSelectedItem() == null ||
                Data.path == null){
            AlertMessages alert = new AlertMessages();
            alert.errorMessage("error","Error Message","Please fill all blank fields");
        }
        else{
            String checkProd = productId.getText();
            Product productwithid = ProductDataAccess.getProductId(checkProd);
            if(productwithid != null){
                AlertMessages alert = new AlertMessages();
                alert.errorMessage("alert","Error Message",checkProd + " is already taken");
            }
            else{
                try {
                    Product product = new Product();
                    product.setProductId(productIdF.getText());
                    product.setProductName(productNameF.getText());
                    product.setType(String.valueOf(productTypeF.getSelectionModel().getSelectedItem()));
                    product.setStock(Integer.parseInt(stockF.getText()));
                    product.setStatus(String.valueOf(statusF.getSelectionModel().getSelectedItem()));
                    product.setPrice(Integer.parseInt(priceF.getText()));

                    String path = Data.path;
                    path = path.replace("\\", "\\\\");

                    product.setImage(path);

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    product.setDate(sqlDate);
                    ProductDataAccess.save(product);

                    inventoryShowData();
                    clearProduct();
                }
                catch(Exception e){
                    e.printStackTrace();
                }

            }

        }

    }

    @FXML
    void clearProduct() {
        productIdF.setText("");
        productNameF.setText("");
        productTypeF.getSelectionModel().clearSelection();
        stockF.setText("");
        priceF.setText("");
        statusF.getSelectionModel().clearSelection();
        Data.path = "";
        productImage.setImage(null);

    }

    @FXML
    void customers(ActionEvent event) {

    }

    @FXML
    void dashboard(ActionEvent event) {

    }

    @FXML
    void deleteProduct(ActionEvent event) {

        if(Data.id == 0){
            AlertMessages alert = new AlertMessages();
            alert.errorMessage("error","Error Message","Please fill all blank fields");
        }
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText("Are you sure want to delete product ID: " + productId.getText());
            Optional<ButtonType> option = alert.showAndWait();

            if(option.get().equals(ButtonType.OK)){
                try{
                    ProductDataAccess.delete(Data.id);
                    AlertMessages alertSuccess = new AlertMessages();
                    alertSuccess.errorMessage("error","Error Message","Successfully Deleted!");

                    inventoryShowData();
                    clearProduct();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            else {
                AlertMessages alertWarning = new AlertMessages();
                alertWarning.errorMessage("error","Error Message","Cancelled");
            }
        }

    }

    @FXML
    void importProduct(ActionEvent event) {
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("open image file","*png","*jpg"));

        File file = openFile.showOpenDialog(mainForm.getScene().getWindow());

        if(file != null){
            Data.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(),120,127,false,true);

            productImage.setImage(image);
        }
    }

    @FXML
    void inventory() {
    }

    @FXML
    void chooseType() {

        List<String> typeLi = new ArrayList<>();

        for(String data: typeList){
            typeLi.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(typeLi);
        productTypeF.setItems(listData);

    }

    @FXML
    void menu() {
    }

    @FXML
    void chooseStatus() {

        List<String> statusLi = new ArrayList<>();

        for(String data: statusList){
            statusLi.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(statusLi);
        statusF.setItems(listData);

    }


    @FXML
    void signout(ActionEvent event) {
       try{
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setTitle("Error Message");
           alert.setHeaderText(null);
           alert.setContentText("Are you sure you want to logout?");
           Optional<ButtonType> option = alert.showAndWait();

           if(option.get().equals(ButtonType.OK)){

               signoutBtn.getScene().getWindow().hide();

               Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
               Stage stage = new Stage();

               Scene scene = new Scene(root);
               stage.setTitle("login");
               stage.setScene(scene);
               stage.show();
           }

       }
       catch(Exception e){
           e.printStackTrace();
       }
    }

    @FXML
    void updateProduct(ActionEvent event) {
        if(productIdF.getText().isEmpty() ||
                productNameF.getText().isEmpty() ||
                productTypeF.getSelectionModel().getSelectedItem() == null||
                stockF.getText().isEmpty() ||
                priceF.getText().isEmpty() ||
                statusF.getSelectionModel().getSelectedItem() == null ||
                Data.path == null){
            AlertMessages alert = new AlertMessages();
            alert.errorMessage("error","Error Message","Please fill all blank fields");
        }
        else{
            Product product = new Product();
            product.setProductId(productIdF.getText());
            product.setProductName(productNameF.getText());
            product.setType(String.valueOf(productTypeF.getSelectionModel().getSelectedItem()));
            product.setStock(Integer.parseInt(stockF.getText()));
            product.setPrice(Integer.parseInt(priceF.getText()));
            product.setStatus(String.valueOf(statusF.getSelectionModel().getSelectedItem()));

            String path = Data.path;
            path = path.replace("\\", "\\\\");

            product.setImage(path);

            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            product.setDate(sqlDate);

            try{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Error Message");
                alert.setContentText(null);
                alert.setContentText("Are you sure you want to update product ID: " + productIdF);
                Optional<ButtonType> option = alert.showAndWait();

                if(option.get().equals(ButtonType.OK)){
                    ProductDataAccess.update(product, Data.id);

                    AlertMessages alertSuccess = new AlertMessages();
                    alertSuccess.errorMessage("information","Error Message","Successfully Updated!");
                }
                else {
                    AlertMessages alertSuccess = new AlertMessages();
                    alertSuccess.errorMessage("error","Error Message","Cancelled!");
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }

    }

    public ObservableList<Product> inventoryDataList(){
        ObservableList<Product> listData = FXCollections.observableArrayList();
        try{
            Product product = ProductDataAccess.getProducts();
            listData.add(product);
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return listData;
    }

    private ObservableList<Product> inventoryListData;

    public void inventoryShowData(){
        inventoryListData = inventoryDataList();
        productId.setCellValueFactory(new PropertyValueFactory<Product, String>("productId"));
        productName.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
        productType.setCellValueFactory(new PropertyValueFactory<Product, String>("type"));
        stock.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stock"));
        price.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
        date.setCellValueFactory(new PropertyValueFactory<Product, Date>("date"));

        productTable.setItems(inventoryListData);


    }

    public void selectProducts(){
        Product product = productTable.getSelectionModel().getSelectedItem();
        int num = productTable.getSelectionModel().getSelectedIndex();

        if((num-1) < -1) return;

        productIdF.setText(product.getProductId());
        productNameF.setText(product.getProductName());
        stockF.setText(String.valueOf(product.getStock()));
        price.setText(String.valueOf(product.getPrice()));

        Data.path = "File:" + product.getImage();
        image = new Image(Data.path,120,127,false,true);
        productImage.setImage(image);
    }

    @FXML
    void payBill(ActionEvent event) {

    }

    @FXML
    void recepitBill(ActionEvent event) {

    }

    @FXML
    void removeBill(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        chooseStatus();
        chooseType();
        inventoryShowData();

    }
}
