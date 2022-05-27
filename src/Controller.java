import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {



    //========GRAFO========//
    public Group graphGroup;
    public TextField addWeightField;
    public TextField addInitialNode;
    public TextField addTimeField;
    public TextField addToField;

    //=========USER========//
    public TableView<User> userTableView;
    public TableColumn<User, String> nameTableColumn;
    public TableColumn<User, String> typeTableColumn;
    public TextField nameField;
    public TextField typeField;

    //=========POI=======//
    public TableView<Node> poiTableView;
    public TableColumn<Node,String> poiNameTableColumn;
    public TableColumn<Node,String> poiLocationTableColumn;
    public TableColumn<Node,String> poiTypeTableColumn;
    public TextField poiNameField;
    public TextField poiLocationField;
    public TextField poiTypeField;

    //========LOG========//
    public TableView<Log> logTableView;
    public TableColumn<Log,String> logDateTableColumn;
    public TableColumn<Log,String> logDescriptionTableColumn;
    public TableColumn<Log,String> logUserTableColumn;
    public TableColumn<Log,String> logPoiIDTableColumn;
    public TextField logDateField;
    public TextField logDescriptionField;
    public TextField logUserField;
    public TextField logPoiField;



    ObservableList<User> users = FXCollections.observableArrayList();
    ObservableList<Node> nodes = FXCollections.observableArrayList();
    ArrayList<Node> nodeBin = new ArrayList<>(nodes);
    ArrayList<User> userBin = new ArrayList<>(users);
    public static EdgeWeightedDigraphProj graph = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        poiTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        typeTableColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        userTableView.setEditable(true);

        poiNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        poiLocationTableColumn.setCellValueFactory(new PropertyValueFactory<>("Location"));
        poiTypeTableColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        poiTableView.setEditable(true);

        nameTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        poiNameTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    private ObservableList<User> getInputUserFromFile() throws IOException{
        for(int i : DataBase.userST.keys()){
            users.add(DataBase.userST.get(i));
            System.out.println(DataBase.userST.get(i));
        }
        System.out.println(users);
        return users;
    }

    private ObservableList<Node> getInputPoiFromFile() throws IOException{
        for(int i : DataBase.nodeST.keys()){
            nodes.add(DataBase.nodeST.get(i));
            System.out.println(DataBase.nodeST.get(i));
        }
        System.out.println("NODES IMPRIMIR");
        System.out.println(nodes);
        return nodes;
    }

    public void handleInputUserFile(ActionEvent actionEvent){
        DataBase.clearUser();
        InputOutputTxt.readUserFromFile();
        userTableView.getItems().clear();
        users.clear();

        try{
            userTableView.getItems().addAll(getInputUserFromFile());
        }catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("Leu Txt Users\n");
    }

    public void handleOutputUserFile(ActionEvent actionEvent){
        System.out.println(users);
        InputOutputTxt.writeUserToFile();
        System.out.println("Guardou users para TXT");
    }


    public void handleAddUser(ActionEvent actionEvent){
        User u = new User(nameField.getText(), Type.stringToType(typeField.getText()));
        DataBase.insertUserST(u);
        userTableView.getItems().add(u);
        users.add(u);
        nameField.setText("");
        typeField.setText("");
    }

    public void handleRemoveUser(ActionEvent actionEvent){
        ObservableList<User> u = userTableView.getSelectionModel().getSelectedItems();
        userTableView.getItems().removeAll(u);
        users.removeAll(u);
    }

    public void handleEditUser(TableColumn.CellEditEvent<User,String> userStringEditEvent){
        User u = userTableView.getSelectionModel().getSelectedItem();
        DataBase.editUserST(u,userStringEditEvent.getNewValue(),null);
    }

    public void handleInputPoiFile(ActionEvent actionEvent){
        DataBase.clearPoi();
        InputOutputTxt.readNodesFromFile();
        poiTableView.getItems().clear();
        nodes.clear();
        try{
            poiTableView.getItems().addAll(getInputPoiFromFile());
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println("Leu Txt POIs");
    }

    public void handleOutputPoiFile(ActionEvent actionEvent){
        System.out.println(nodes);
        InputOutputTxt.writeNodesToFile();
        System.out.println("Guardou Pois/Nodes para Txt");
    }

    public void handleOutputBinUserFile(ActionEvent actionEvent){ //BINARIO NAO FUNCIONA
        saveUsersToBinFile();
        System.out.println("Guardou BIN Users");
    }

    private void saveUsersToBinFile(){
        File f = new File(".//data//OutputUsers.bin");
        try{
            FileOutputStream fout = new FileOutputStream(f);
            ObjectOutputStream oout = new ObjectOutputStream(fout);
            oout.writeObject(new ArrayList<User>(users));
            System.out.println("Wrote " + users.size() + "Users");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void handleInputBinUserFile(ActionEvent actionEvent){
        readUsersFromBinFile();
        System.out.println("Leu do ficheiro Bin");
    }

    private void readUsersFromBinFile(){
        File f = new File(".//data//OutputUsers.bin");
        try{
            FileInputStream fin = new FileInputStream(f);
            ObjectInputStream oin = new ObjectInputStream(fin);
            userBin = (ArrayList<User>) oin.readObject();
            userTableView.getItems().addAll(userBin);
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public void handleInputBinPoiFile(ActionEvent actionEvent){
        readPoiFromBinFile();
        System.out.println("Leu de ficheiro Bin");
    }

    private void readPoiFromBinFile(){
        File f = new File(".//data//OutputNodes.bin");
        try{
            FileInputStream fin = new FileInputStream(f);
            ObjectInputStream oin = new ObjectInputStream(fin);
             nodeBin = (ArrayList<Node>) oin.readObject();
            poiTableView.getItems().addAll(nodeBin);
        }catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void handleOutputBinPoiFile(ActionEvent actionEvent){
        savePoiToBinFile();
        System.out.println("Guardou BIN Poi");
    }

    private void savePoiToBinFile(){
        File f = new File(".//data//OutputNodes.bin");
        try{
            FileOutputStream fout = new FileOutputStream(f);
            ObjectOutputStream oout = new ObjectOutputStream(fout);
            oout.writeObject(new ArrayList<Node>(nodes));
            System.out.println("Wrote " + nodes.size() + "Users");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void handleRemovePoi(ActionEvent actionEvent){
        ObservableList<Node> n = poiTableView.getSelectionModel().getSelectedItems();
        poiTableView.getItems().removeAll(n);
        nodes.removeAll(n);
    }

    public void handleEditPoi(TableColumn.CellEditEvent<Node, String> poiEditEvent){
        Node n = poiTableView.getSelectionModel().getSelectedItem();
        DataBase.editNodeST(n, poiEditEvent.getNewValue(),null, null);
    }

    public void handleEditLog(TableColumn.CellEditEvent<Log, String> actionEvent){

    }

    public void handleAddEdgeToGrafo(ActionEvent actionEvent){

    }

}
