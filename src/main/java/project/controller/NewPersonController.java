package project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import project.model.Person;

public class NewPersonController {

    private final MainAppController mainWindowController = MainAppController.getController();

    @FXML private TextField firstname;
    @FXML private TextField lastname;
    @FXML private TextField street;
    @FXML private TextField city;
    @FXML private TextField postcode;
    @FXML private TextField phone;

    @FXML
    private void initialize() {

    }

    @FXML
    private void editConfirmed() {
        if(checkInputText(firstname) && checkInputText(lastname) && checkInputText(street) && checkInputText(city) && checkInputText(postcode) && checkInputText(phone)) {

            Person person = new Person(
                    firstname.getText(),
                    lastname.getText(),
                    street.getText(),
                    city.getText(),
                    postcode.getText(),
                    phone.getText()
            );
            mainWindowController.addNewUser(person);

            showAlert(Alert.AlertType.ERROR, "Dodano użytkownika!");
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
