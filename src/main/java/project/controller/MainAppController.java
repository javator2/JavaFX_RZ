package project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import project.Main;
import project.model.Person;


public class MainAppController {
    private Main main;

    private Person selectedPerson = null;

    @FXML private TableView<Person> mainTable;
    @FXML private TableColumn<Person, String> firstNameCol;
    @FXML private TableColumn<Person, String> lastNameCol;
    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label streetNameLabel;
    @FXML private Label cityNameLabel;
    @FXML private Label cityCodeLabel;
    @FXML private Label phoneLabel;

    @FXML
    private void initialize() {
        firstNameCol.setCellValueFactory(cell -> cell.getValue().getFirstNameProperty());
        lastNameCol.setCellValueFactory(cell -> cell.getValue().getLastNameProperty());
    }

    @FXML
    private void newButtonAction() {
        System.out.println("New");
    }

    @FXML
    private void editButtonAction() {
        main.loadPersonEdit();
    }

    @FXML
    private void deleteButtonAction() {
        System.out.println("delete");
    }

    @FXML
    private void selectSwitch() {
        Person currSelectedPerson = mainTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != currSelectedPerson) {
            selectedPerson = currSelectedPerson;
            firstNameLabel.setText(selectedPerson.getFirstName());
            lastNameLabel.setText(selectedPerson.getLastName());
            streetNameLabel.setText(selectedPerson.getStreetName());
            cityNameLabel.setText(selectedPerson.getCityName());
            cityCodeLabel.setText(selectedPerson.getCityCode());
            phoneLabel.setText(selectedPerson.getPhoneNumber());
        }
    }

    public void setMain(Main main) {
        this.main = main;
        mainTable.setItems(main.getPersonList());
    }

}
