package cms.client.controllers.validators;

import javafx.scene.control.TextField;

import java.util.List;

public interface ValidatorInterface {
    public void validate();
    public boolean validateTextFields(List<TextField> textFields);
}
