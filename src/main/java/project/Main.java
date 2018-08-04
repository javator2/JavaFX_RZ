package project;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import project.controller.EditPersonController;
import project.controller.MainAppController;
import project.model.Person;

import java.io.IOException;

public class Main extends Application {

    private Stage stage;
    private VBox layout;

    private ObservableList<Person> personList = FXCollections.observableArrayList();

    public Main() {
        personList.add(new Person("TestA", "TestAA"));
        personList.add(new Person("TestB", "TestBB"));
        personList.add(new Person("TestC", "TestCC"));
    }

    public ObservableList<Person> getPersonList() {
        return personList;
    }

    public static void main(String[] args) {
        launch();
    }

    public void start(Stage primaryStage) throws Exception {
            this.stage = primaryStage;
            this.stage.setTitle("Moja aplikacja w JavaFX");
            loadView();
    }

    public Scene getScene() {
        return this.stage.getScene();
    }

    private void loadView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/FXML/app.fxml"));
            layout = loader.load();

            Scene scene = new Scene(layout);
            stage.setScene(scene);
            stage.show();

            MainAppController controller = loader.getController();
            controller.setMain(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadPersonEdit() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/FXML/personedit.fxml"));
            VBox window = loader.load();

            Stage editStage = new Stage();
            editStage.setTitle("Edytuj osobę");
            Scene scene = new Scene(window);
            EditPersonController cl = (EditPersonController)loader.getController();
            cl.setStage(editStage);
            editStage.initOwner(stage);
            editStage.setScene(scene);
            editStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadNewPerson() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/FXML/newperson.fxml"));
            VBox window = loader.load();

            Stage editStage = new Stage();
            editStage.setTitle("Dodaj osobę");
            Scene scene = new Scene(window);
            editStage.initOwner(stage);
            editStage.setScene(scene);
            editStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
