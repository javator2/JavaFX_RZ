package project.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import project.model.Person;


public class EditPersonController {

    private final MainAppController mainWindowController = MainAppController.getController();

    private Person person;

    @FXML private TextField firstname;
    @FXML private TextField lastname;
    @FXML private TextField street;
    @FXML private TextField city;
    @FXML private TextField postcode;
    @FXML private TextField phone;

    @FXML
    private void initialize() {
        person = mainWindowController.getSelectedPerson();
        if(person != null) {
            firstname.setText(person.getFirstName());
            lastname.setText(person.getLastName());
            street.setText(person.getStreetName());
            city.setText(person.getCityName());
            postcode.setText(person.getCityCode());
            phone.setText(person.getPhoneNumber());
        }
    }

    @FXML
    private void editConfirmed() {
        if(checkInputText(firstname) && checkInputText(lastname) && checkInputText(street) && checkInputText(city) && checkInputText(postcode) && checkInputText(phone)) {
            person.setFirstName(firstname.getText());
            person.setLastName(lastname.getText());
            person.setStreetName(street.getText());
            person.setCityName(city.getText());
            person.setCityCode(postcode.getText());
            person.setPhoneNumber(phone.getText());

            mainWindowController.refreshData();

            showAlert(Alert.AlertType.ERROR, "Zapisano zmiany!");
        } else {
            showAlert(Alert.AlertType.ERROR, "Niepoprawne dane w polu edycji. Żadne z pól nie może być puste!");
        }
    }

    private boolean checkInputText(TextField field) {
        String text = field.getText();
        return text != null && !text.isEmpty();
    }

    private void showAlert(Alert.AlertType type, String text) {
        Alert alert = new Alert(type);
        alert.setHeaderText(text);
        alert.setContentText(null);
        alert.show();
    }
}
