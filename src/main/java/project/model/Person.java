package project.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty streetName;
    private StringProperty cityName;
    private StringProperty cityCode;
    private StringProperty phoneNumber;

    public Person(String firstName, String lastName, String streetName, String cityName, String cityCode, String phoneNumber) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.streetName = new SimpleStringProperty(streetName);
        this.cityName = new SimpleStringProperty(cityName);
        this.cityCode = new SimpleStringProperty(cityCode);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setStreetName(String streetName) {
        this.streetName.set(streetName);
    }

    public void setCityName(String cityName) {
        this.cityName.set(cityName);
    }

    public void setCityCode(String cityCode) {
        this.cityCode.set(cityCode);
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public Person(String firstName, String lastName) {
        this(firstName, lastName, "Empty", "Empty", "Empty", "Empty");
    }


    public StringProperty getFirstNameProperty() {
        return firstName;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty getLastNameProperty() {
        return lastName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public String getStreetName() {
        return streetName.get();
    }

    public StringProperty streetNameProperty() {
        return streetName;
    }

    public String getCityName() {
        return cityName.get();
    }

    public StringProperty cityNameProperty() {
        return cityName;
    }

    public String getCityCode() {
        return cityCode.get();
    }

    public StringProperty cityCodeProperty() {
        return cityCode;
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

}
