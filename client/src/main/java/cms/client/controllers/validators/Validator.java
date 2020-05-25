package cms.client.controllers.validators;

import cms.client.controllers.AbstractController;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class Validator{
    private final static Logger LOG = Logger.getLogger(Validator.class);
    private AbstractController parent;
    private static List<String> allowed = Arrays.asList("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-.:_~/?#[]!$&'()+,;=))".split(""));

    public Validator(AbstractController parent){
        this.parent=parent;
    }


    public boolean validateTextFields(List<TextField> textFields) {

        for(TextField textField:textFields) {
            if (textField.getText().trim().equals("")) {
                parent.displayError("Please fill "+textField.getId());
                return false;
            }
            boolean check = textField.getText().trim().chars().allMatch(c -> {
                if(allowed.contains(Character.toString(c))){
                    return true;
                }
                parent.displayError("sorry you cannot use '"+Character.toString(c)+"' in "+textField.getId());
                return false;
            });
            if(!check){
                return false;
            }
        }
        return true;
    }

    public boolean validateString(String s){
        boolean check = s.trim().chars().allMatch(c -> {
            if(allowed.contains(Character.toString(c))){
                return true;
            }
            parent.displayError("sorry you cannot use '"+Character.toString(c)+"'");
            return false;
        });
        return check;
    }
}
