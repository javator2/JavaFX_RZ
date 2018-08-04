package project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import project.Main;
import project.model.Person;


public class MainAppController {
    private Main main;
    private static MainAppController controller;

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

    public static MainAppController getController() {
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
        if(selectedPerson != null) main.loadPersonEdit();
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
        Person selected = getSelectedPerson();
        if(selected != null) {
            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
            confirm.setContentText(null);
            confirm.setHeaderText("Czy na pewno chcesz usunąć użytkownika " + selected.getFirstName() + " " + selected.getLastName() + "?");
            if(confirm.showAndWait().get().equals(ButtonType.OK)) {
                TableView.TableViewSelectionModel<Person> selModel = mainTable.getSelectionModel();
                int row = selModel.getSelectedIndex();

                main.getPersonList().remove(selected);

                if(row > 0) {
                    selModel.select(row - 1);
                } else {
                    if(main.getPersonList().size() > 0) {
                        selModel.selectFirst();
                    } else {
                        selModel.clearSelection();
                    }
                }
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
            if (selectedPerson != currSelectedPerson || force) {
                selectedPerson = currSelectedPerson;
                firstNameLabel.setText(selectedPerson.getFirstName());
                lastNameLabel.setText(selectedPerson.getLastName());
                streetNameLabel.setText(selectedPerson.getStreetName());
                cityNameLabel.setText(selectedPerson.getCityName());
                cityCodeLabel.setText(selectedPerson.getCityCode());
                phoneLabel.setText(selectedPerson.getPhoneNumber());
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

    public void addNewUser(Person person) {
        main.getPersonList().add(person);
        mainTable.refresh();
    }

    public void setMain(Main main) {
        this.main = main;
        mainTable.setItems(main.getPersonList());
    }

    public Person getSelectedPerson() {
        return this.selectedPerson;
    }

    public void refreshData() {
        mainTable.refresh();
        selectSwitch(true);
    }

}
