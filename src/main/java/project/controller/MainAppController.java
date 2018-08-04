package project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import project.Main;
import project.model.Person;


public class MainAppController {
    private Main main;
    private static MainAppController controller;
    private Person oldSelectedPerson;

    @FXML private TableView<Person> mainTable;
    @FXML private TableColumn<Person, String> firstNameCol;
    @FXML private TableColumn<Person, String> lastNameCol;
    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label streetNameLabel;
    @FXML private Label cityNameLabel;
    @FXML private Label cityCodeLabel;
    @FXML private Label phoneLabel;

    static MainAppController getController() {
        return controller;
    }

    @FXML
    private void initialize() {
        controller = this;
        firstNameCol.setCellValueFactory(cell -> cell.getValue().getFirstNameProperty());
        lastNameCol.setCellValueFactory(cell -> cell.getValue().getLastNameProperty());
    }

    @FXML
    private void newButtonAction() {
        main.loadNewPerson();
    }

    @FXML
    private void editButtonAction() {
        if(mainTable.getSelectionModel().getSelectedItem() != null) main.loadPersonEdit();
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(null);
            alert.initOwner(main.getScene().getWindow());
            alert.setTitle("Błąd");
            alert.setHeaderText("Nie zaznaczono żadnego użytkownika");
            alert.show();
        }
    }

    @FXML
    private void deleteButtonAction() {
        Person selected = mainTable.getSelectionModel().getSelectedItem();
        if(selected != null) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setContentText(null);
            confirm.setHeaderText("Czy na pewno chcesz usunąć użytkownika " + selected.getFirstName() + " " + selected.getLastName() + "?");
            if(confirm.showAndWait().get().equals(ButtonType.OK)) {
                main.getPersonList().remove(selected);
                selectSwitch();
                mainTable.refresh();
            }
        }
    }

    @FXML
    private void selectSwitch() {
        selectSwitch(false);
    }

    private void selectSwitch(boolean force) {
        Person currSelectedPerson = mainTable.getSelectionModel().getSelectedItem();
        if(currSelectedPerson != null) {
            if (oldSelectedPerson != currSelectedPerson || force) {
                oldSelectedPerson = currSelectedPerson;
                firstNameLabel.setText(currSelectedPerson.getFirstName());
                lastNameLabel.setText(currSelectedPerson.getLastName());
                streetNameLabel.setText(currSelectedPerson.getStreetName());
                cityNameLabel.setText(currSelectedPerson.getCityName());
                cityCodeLabel.setText(currSelectedPerson.getCityCode());
                phoneLabel.setText(currSelectedPerson.getPhoneNumber());
            }
        } else {
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            streetNameLabel.setText("");
            cityNameLabel.setText("");
            cityCodeLabel.setText("");
            phoneLabel.setText("");
        }
    }

    void addNewUser(Person person) {
        main.getPersonList().add(person);
        mainTable.refresh();
    }

    public void setMain(Main main) {
        this.main = main;
        mainTable.setItems(main.getPersonList());
    }

    void refreshData() {
        mainTable.refresh();
        selectSwitch(true);
    }

    public TableView getTableView() {
        return mainTable;
    }
}
