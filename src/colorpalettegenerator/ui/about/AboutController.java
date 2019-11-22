package colorpalettegenerator.ui.about;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AboutController {

    @FXML
    private VBox vbox;

    @FXML
    void mousePress(MouseEvent event) {
        Stage stage = (Stage) vbox.getScene().getWindow();
        stage.close();
    }

}
