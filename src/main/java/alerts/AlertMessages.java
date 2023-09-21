package alerts;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
public class AlertMessages {

    private Alert alert;

    public void errorMessage(String alertType,String message, String Content){

        if(alertType == "error"){
            alert = new Alert(Alert.AlertType.ERROR);
        }
        else if(alertType == "information"){
            alert = new Alert(Alert.AlertType.INFORMATION);
        }
        alert.setTitle(message);
        alert.setHeaderText(null);
        alert.setContentText(Content);
        alert.showAndWait();
    }
}
