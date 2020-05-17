package cms.client.controllers.validators;

import cms.client.controllers.AbstractController;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;

import java.util.List;

public class Validator {
    private final static Logger LOG = Logger.getLogger(Validator.class);
    private AbstractController parent;

    public Validator(AbstractController parent){
        this.parent=parent;
    }


    public boolean validate(List<JFXTextField> textFields) {
        for(TextField textField:textFields) {
            if (textField.getText().trim().equals("")) {
                parent.displayError("Please fill "+textField.getId());
                return false;
            }
            LOG.debug(textField.getText());
            if (textField.getText().trim().contains(" ")) {
                parent.displayError("sorry you cannot use whitespaces in "+textField.getId());
                return false;
            }
        }
        return true;
        }
}
