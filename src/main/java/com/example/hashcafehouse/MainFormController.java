package com.example.hashcafehouse;

import alerts.AlertMessages;
import dataAccess.CustomerDataAccess;
import dataAccess.ProductDataAccess;
import dataAccess.ReceiptDataAccess;
import dataAccess.UserDataAccess;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.*;

import java.io.File;
import java.net.URL;
import java.security.cert.Extension;
import java.util.*;

public class MainFormController implements Initializable {

    @FXML
    private Button addBtn;

    @FXML
    private AnchorPane chartView;

    @FXML
    private Button clearBtn;

    @FXML
    private AreaChart<Date, Integer> customerChart;

    @FXML
    private Button customerBtn;

    @FXML
    private Button customersBtn;

    @FXML
    private TableColumn<ReceiptTable, String> customerDate;

    @FXML
    private AnchorPane customerForm;

    @FXML
    private AnchorPane customerView;

    @FXML
    private TableColumn<ReceiptTable, String> customerId;

    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableColumn<ReceiptTable, String> customerTotal;

    @FXML
    private Label dashUsername;

    @FXML
    private Label dashEmail;

    @FXML
    private Button dashboardBtn;

    @FXML
    private TableColumn<ProductTable, String> date;

    @FXML
    private Button deleteBtn;

    @FXML
    private Button importBtn;

    @FXML
    private AreaChart<Date, Double> incomeChart;

    @FXML
    private Button inventoryBtn;

    @FXML
    private StackPane mainForm;

    @FXML
    private Button menuBtn;

    @FXML
    private GridPane menuGridPane;

    @FXML
    private TableColumn<ProductTable, String> price;

    @FXML
    private TextField priceF;

    @FXML
    private TableColumn<ProductTable, String> productId;

    @FXML
    private TextField productIdF;

    @FXML
    private ImageView productImage;

    @FXML
    private TableColumn<ProductTable, String> productName;

    @FXML
    private TextField productNameF;

    @FXML
    private TableColumn<ProductTable, String> productType;

    @FXML
    private ComboBox<Product> productTypeF;

    @FXML
    private Button signoutBtn;

    @FXML
    private ComboBox<Product> statusF;

    @FXML
    private TableColumn<ProductTable, String> stock;

    @FXML
    private TextField stockF;

    @FXML
    private AnchorPane tableView;

    @FXML
    private Button updateBtn;

    @FXML
    private TableView<ProductTable> productTable;

    @FXML
    private AnchorPane menuForm;

    @FXML
    private TableColumn<ReceiptTable, String> cashier;

    @FXML
    private TableColumn<CustomerTable, String> menuPrice;

    @FXML
    private TableColumn<CustomerTable, String> menuProductName;

    @FXML
    private TableColumn<CustomerTable, String> menuQuantity;

    @FXML
    private TableView<CustomerTable> menuTable;

    @FXML
    private TableView<ReceiptTable> receiptTable;

    @FXML
    private Label menuTotal;

    @FXML
    private Label menuamount;

    @FXML
    private Label menuChange;

    @FXML
    private Button payBtn;

    @FXML
    private Button receiptBtn;

    @FXML
    private Button removeBtn;

    @FXML
    private TextField amountPay;

    @FXML
    private Label noOfCustomers;

    @FXML
    private Label noOfSoldProducts;

    @FXML
    private Label todayIncome;

    @FXML
    private Label totalIncome;

    private int cid;
    private int getId;
    private Image image;
    private double total;
    private double amount;
    private double change;


    private String[] typeList = {"Meals", "Drinks"};

    private String[] statusList = {"Available", "Unavailable"};

  //  private ObservableList<Item> itemListData;
    private ObservableList<ReceiptTable> customerListData;

    public void displayName() {
        String user = Data.username;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);
        dashUsername.setText(user);

        String email = Data.email;
        dashEmail.setText(email);
    }

    public void displayDashboardCustomers(){
        incomeChart.getData().clear();

        try{
            Receipt receipt = ReceiptDataAccess.getCountId();
            int countId = receipt.getId();
            noOfCustomers.setText(String.valueOf(countId));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void displayDashboardTodayIncome(){
        incomeChart.getData().clear();

        try{
            Receipt receipt = ReceiptDataAccess.getSumIdWithdate();
            float sumId = receipt.getSumTotal();
            System.out.println(sumId);
            todayIncome.setText("$ " + sumId);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void displayDashboardTotalIncome(){
        incomeChart.getData().clear();

        try{
            Receipt receipt = ReceiptDataAccess.getSumId();
            float sumId = receipt.getSumTotal();
            System.out.println(sumId);
            totalIncome.setText("$ "+ sumId);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void displayDashboardSoldProducts(){
        incomeChart.getData().clear();

        try{
            Customer customer = CustomerDataAccess.getQuantity();
            int noOfPrducts = customer.getQuantity();
            noOfSoldProducts.setText(String.valueOf(noOfPrducts));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void dashboardIncomeChart(){

        incomeChart.getData().clear();

        XYChart.Series chart = new XYChart.Series();
        try{
            Receipt receipt = ReceiptDataAccess.getSumTotalAndDate();
            chart.getData().add(new XYChart.Data<>(String.valueOf(receipt.getDate()),receipt.getSumTotal()));

            incomeChart.getData().add(chart);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void dashboardCustomerChart(){

        customerChart.getData().clear();

        XYChart.Series chart = new XYChart.Series();
        try{
            Receipt receipt = ReceiptDataAccess.getSumIdAndDate();
            chart.getData().add(new XYChart.Data<>(String.valueOf(receipt.getDate()),receipt.getId()));

            customerChart.getData().add(chart);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }



    public ObservableList<Customer> menuDisplayOrder() {

        ObservableList<Customer> listData = FXCollections.observableArrayList();

        try {
            Customer customer = CustomerDataAccess.getCustomerDetails();
            listData.add(customer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    return  listData;
    }

    public void customerShowData(){
        customerListData = ReceiptDataAccess.getReciepts();

        customerId.setCellValueFactory(cellData -> cellData.getValue().customerIdProperty());
        customerTotal.setCellValueFactory(cellData -> cellData.getValue().totalProperty());
        customerDate.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        cashier.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());

        receiptTable.setItems(customerListData);
    }


    public void menuAmount(){
        menuGetTotal();
        if(amountPay.getText().isEmpty() || total == 0){
            AlertMessages alert = new AlertMessages();
            alert.errorMessage("error","Error Message","invalid");
        }
        else{
            amount = Double.parseDouble(amountPay.getText());
            if(amount < total){
                menuChange.setText("$ 0.0");
            }
            else{
                change = (amount - total);
                menuChange.setText("$" + change);
            }
        }
    }





    public void menuShowData(){  //Duplicate of menuShowOrderData

        //menuListData = menuDisplayOrder();

        menuProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        menuQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        menuPrice.setCellValueFactory(new PropertyValueFactory<>("productName"));

        //menuTable.setItems(menuListData);
    }

    public void menuRestart(){
        total = 0;
        change = 0;
        amount = 0;
        menuTotal.setText("$0.0");
        menuamount.setText("");
        menuChange.setText("$0.0");

    }

    public ObservableList<CustomerTable> menuListData;
    private ObservableList<CustomerTable> menuOrderListData;

    public void menuShowOrderData(){

        customerId();

        menuOrderListData = CustomerDataAccess.getItemsWhereId(cid); //menuGetOrder()

        menuProductName.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        menuQuantity.setCellValueFactory(cellData -> cellData.getValue().quantityProperty());
        menuPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty());

        menuTable.setItems(menuOrderListData);

    }



    public void menuGetTotal(){
         customerId();
         Customer total1 = CustomerDataAccess.getTotal(cid,total);
         total = total1.getPrice();
    }

    public void menuDisplayTotal(){
        menuGetTotal();
        menuTotal.setText("$" + total);
    }

    public void menuDisplayCard(){

        ObservableList<Item> itemListData = FXCollections.observableArrayList();

//        itemListData.clear();
        itemListData.addAll(ProductDataAccess.getItems());

        int row = 0;
        int column = 0;

        menuGridPane.getChildren().clear();
        menuGridPane.getRowConstraints().clear();
        menuGridPane.getColumnConstraints().clear();

        for(int i = 0;i < itemListData.size(); i++){

            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("productCard.fxml"));
                AnchorPane pane = loader.load();
                ProductCardController card = loader.getController();
                card.setData(itemListData.get(i));

                if(column == 3){
                    column = 0;
                    row += 1;
                }


                menuGridPane.add(pane,column++,row);
                GridPane.setMargin(pane, new Insets(10));


            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void switchForm(ActionEvent event){

        if(event.getSource() == dashboardBtn){
            tableView.setVisible(false);
            chartView.setVisible(true);
            menuForm.setVisible(false);
            customerView.setVisible(false);

            displayDashboardCustomers();
            displayDashboardTodayIncome();
            displayDashboardTotalIncome();
            displayDashboardSoldProducts();

            dashboardIncomeChart();
            dashboardCustomerChart();
        }
        else if(event.getSource() == inventoryBtn){
            tableView.setVisible(true);
            chartView.setVisible(false);
            menuForm.setVisible(false);
            customerView.setVisible(false);

            chooseStatus();
            chooseType();
            inventoryShowData();
        }
        else if(event.getSource() == menuBtn){
            tableView.setVisible(false);
            chartView.setVisible(false);
            menuForm.setVisible(true);
            customerView.setVisible(false);

            menuDisplayCard();
            menuDisplayTotal();
            menuShowOrderData();

        }
        else if(event.getSource() == customerBtn){
            tableView.setVisible(false);
            chartView.setVisible(false);
            menuForm.setVisible(false);
            customerView.setVisible(true);


            customerShowData();

        }
    }


    public void customerId(){

        try{
            Customer cId = CustomerDataAccess.getMaxId();
            cid = cId.getCustomerId();
            Receipt checkId = ReceiptDataAccess.getMaxId();
            int checkid = checkId.getCustomerId();

            if(cid == 0){
                cid += 1;
            }
            else if(cid == checkid){
                cid += 1;
            }

            Data.cID = cid;

        }
        catch (Exception e){
            e.printStackTrace();

        }




    }

    public void menuSelectOrder(){ //menuTable
        CustomerTable customerTable = menuTable.getSelectionModel().getSelectedItem();
        int num = menuTable.getSelectionModel().getSelectedIndex();

        if((num-1) < -1) return;

        getId = customerTable.getId();

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
            String checkProd = productIdF.getText();
            Product productwithid = ProductDataAccess.getProductId(checkProd);
            if(productwithid != null){
                AlertMessages alert = new AlertMessages();
                alert.errorMessage("error","Error Message",checkProd + " is already taken");
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
        Data.id = 0;
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
            alert.setHeaderText("Are you sure want to delete product ID: " + productIdF.getText());
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
                statusF.getSelectionModel().getSelectedItem() == null || Data.id == 0 || Data.path == null){
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
           // path = path.replace("\\", "\\\\");

            product.setImage(path);

            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            product.setDate(sqlDate);

            try{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Error Message");
                alert.setContentText(null);
                alert.setContentText("Are you sure you want to update product ID: " + productIdF.getText());
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



    private ObservableList<ProductTable> inventoryListData;

    public void inventoryShowData(){
        inventoryListData = ProductDataAccess.getProducts();
        productId.setCellValueFactory(cellData -> cellData.getValue().productIdProperty());
        productName.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
        productType.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        stock.setCellValueFactory(cellData -> cellData.getValue().stockProperty());
        price.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        date.setCellValueFactory(cellData -> cellData.getValue().dateProperty());

        productTable.setItems(inventoryListData);


    }

    public void selectProducts(){
        ProductTable product = productTable.getSelectionModel().getSelectedItem();
        int num = productTable.getSelectionModel().getSelectedIndex();

        if((num-1) < -1) return;

        productIdF.setText(product.getProductId());
        productNameF.setText(product.getProductName());
        stockF.setText(String.valueOf(product.getStock()));
        priceF.setText(String.valueOf(product.getPrice()));


        Data.path = "File:" + product.getImage();
        image = new Image(Data.path,120,127,false,true);
        productImage.setImage(image);

        Data.date = String.valueOf(product.getDate());
        Data.id = product.getId();

        System.out.println("sd");
    }

    @FXML
    void payBill(ActionEvent event) {

        if(total == 0){
            AlertMessages alert = new AlertMessages();
            alert.errorMessage("error","Error Message","Please Choose Your Order first!");
        }
        else{
            try{
                if(amount == 0){
                    AlertMessages alert = new AlertMessages();
                    alert.errorMessage("error","Error Message","Something Wrong!");
                }
                else{
                    customerId();
                    menuGetTotal();

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    Receipt receipt = new Receipt();
                    receipt.setCustomerId(cid);
                    receipt.setTotal(total);
                    receipt.setDate(sqlDate);
                    receipt.setUsername(Data.username);
                    ReceiptDataAccess.save(receipt);

                    menuShowOrderData();
                    menuRestart();
                }
            }
            catch (Exception e){

            }
        }

    }

    @FXML
    void recepitBill(ActionEvent event) {

    }

    @FXML
    void removeBill(ActionEvent event) {

        if(getId == 0){
            AlertMessages alert = new AlertMessages();
            alert.errorMessage("error","Error Message","Please select the order you want to remove");
        }
        else{
            try {
                CustomerDataAccess.delete(getId);

                menuShowOrderData();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        displayName();

        displayDashboardCustomers();
        displayDashboardTotalIncome();
        displayDashboardTodayIncome();
        displayDashboardSoldProducts();
        dashboardIncomeChart();
        dashboardCustomerChart();


        chooseStatus();
        chooseType();
        inventoryShowData();

        menuDisplayOrder();
        menuDisplayCard();
        menuShowOrderData();
        menuDisplayTotal();



    }
}
