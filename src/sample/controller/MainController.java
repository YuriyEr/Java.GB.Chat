package sample.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import sample.model.Messages;
import sample.model.Users;

public class MainController {
    @FXML
    private ListView<String> userListView, messagesListView;

    @FXML
    private Label labelID;

    @FXML
    private TextField textFielsSend;


    Messages messages = new Messages(FXCollections.observableArrayList());
    Users users = new Users(FXCollections.observableArrayList());

    public MainController(){
        messages.addElement("Привет!");
        users.addElement("Вы");
        users.addElement("Иван");
        users.addElement("Федор");
    }

    @FXML
    public void initialize() {
        userListView.setItems(FXCollections.observableArrayList(users.getList()));
        messagesListView.setItems(FXCollections.observableArrayList(messages.getList()));
    }

    @FXML
    public void exit() {
        System.exit(0);
    }

    @FXML
    public void addWord(){
        String message = users.getElement(0) + " - " + textFielsSend.getText();
        messages.addElement(message);
        Integer size =  messagesListView.getItems().size();
        messagesListView.getItems().add(size-1, message);
        textFielsSend.clear();
        messagesListView.scrollTo(size-1);
    }
}
