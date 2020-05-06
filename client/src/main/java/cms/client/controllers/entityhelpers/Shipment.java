package cms.client.controllers.entityhelpers;

import javafx.beans.property.SimpleStringProperty;

public class Shipment {
    private SimpleStringProperty expedion;
    private SimpleStringProperty status;
    private SimpleStringProperty completion;
    private SimpleStringProperty vehicle;
    private SimpleStringProperty driver;
    private SimpleStringProperty destination;

    public String getDriver() {
        return driver.get();
    }

    public SimpleStringProperty driverProperty() {
        return driver;
    }





    public Shipment(String s, String s1, String s2, String s3, String s4, String s5) {
        this.expedion = new SimpleStringProperty(s);
        this.status = new SimpleStringProperty(s1);
        this.completion =new SimpleStringProperty(s2);
        this.vehicle =new SimpleStringProperty(s3);
        this.driver =new SimpleStringProperty(s4);
        this.destination = new SimpleStringProperty(s5);
    }

    public String getExpedion() {
        return expedion.get();
    }

    public SimpleStringProperty expedionProperty() {
        return expedion;
    }

    public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty statusProperty() {
        return status;
    }

    public String getCompletion() {
        return completion.get();
    }

    public SimpleStringProperty completionProperty() {
        return completion;
    }

    public String getVehicle() {
        return vehicle.get();
    }

    public SimpleStringProperty vehicleProperty() {
        return vehicle;
    }

    public String getDestination() {
        return destination.get();
    }

    public SimpleStringProperty destinationProperty() {
        return destination;
    }
}
