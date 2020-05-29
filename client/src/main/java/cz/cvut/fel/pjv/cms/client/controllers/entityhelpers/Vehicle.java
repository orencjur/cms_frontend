package cz.cvut.fel.pjv.cms.client.controllers.entityhelpers;

import javafx.beans.property.SimpleMapProperty;
import javafx.beans.property.SimpleStringProperty;

public class Vehicle {
    private SimpleStringProperty licencePlate;
    private SimpleStringProperty driver;
    private SimpleStringProperty available;

    public Vehicle(String licencePlate,String driver) {
        this.licencePlate = new SimpleStringProperty(licencePlate);
        if(driver.trim().equals("null")){
            this.driver=new SimpleStringProperty("none");
            this.available= new SimpleStringProperty("yes");
        }else {
            this.driver= new SimpleStringProperty(driver);
            this.available= new SimpleStringProperty("no");
        }
    }

    public String getLicencePlate() {
        return licencePlate.get();
    }

    public SimpleStringProperty licencePlateProperty() {
        return licencePlate;
    }

    public String getDriver() {
        return driver.get();
    }

    public SimpleStringProperty driverProperty() {
        return driver;
    }

    public String getAvailable() {
        return available.get();
    }

    public SimpleStringProperty availableProperty() {
        return available;
    }
}
