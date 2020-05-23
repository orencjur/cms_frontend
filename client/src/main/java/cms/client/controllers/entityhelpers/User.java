package cms.client.controllers.entityhelpers;

import javafx.beans.property.SimpleStringProperty;

public class User {

    private SimpleStringProperty name;
    private SimpleStringProperty surname;
    private SimpleStringProperty username;
    private SimpleStringProperty userVehicle;
    private SimpleStringProperty availability;
    private SimpleStringProperty licenceNumber;

    public User(String fullname,String username,String vehicle, String licenceNumber,String availibility){
        try {
            String [] full = fullname.split(":");
            name = new SimpleStringProperty(full[0]);
            surname   = new SimpleStringProperty(full[1]);
        }catch (Exception e){//idiot with fucking name
            name = new SimpleStringProperty(fullname);
            surname = new SimpleStringProperty("who the fuck knows");
        }
        this.username = new SimpleStringProperty(username);
        this.userVehicle = new SimpleStringProperty(vehicle);
        this.licenceNumber = new SimpleStringProperty(licenceNumber);
        this.availability = new SimpleStringProperty(availibility);
    }

    public void setUserVehicle(String userVehicle) {
        this.userVehicle.set(userVehicle);
    }

    public User (String fullname, String username){
        try {
        String [] full = fullname.split(" ");
        name = new SimpleStringProperty(full[0]);
        surname   = new SimpleStringProperty(full[1]);
        }catch (Exception e){//idiot with fucking name
            name = new SimpleStringProperty(fullname);
            surname = new SimpleStringProperty("who the fuck knows");
        }

        this.username = new SimpleStringProperty(username);
        this.userVehicle = new SimpleStringProperty("Dispatcher");
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public String getUserVehicle() {
        return userVehicle.get();
    }

    public SimpleStringProperty userVehicleProperty() {
        return userVehicle;
    }

    public String getAvailability() {
        return availability.get();
    }

    public SimpleStringProperty availabilityProperty() {
        return availability;
    }

    public String getLicenceNumber() {
        return licenceNumber.get();
    }

    public SimpleStringProperty licenceNumberProperty() {
        return licenceNumber;
    }
}
