package colorpalettegenerator.ui.main;

import colorpalettegenerator.cpgen.ColorPaletteGenerator;
import colorpalettegenerator.util.ImageUtil;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class MainController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="canvas"
    private Canvas canvas; // Value injected by FXMLLoader

    @FXML // fx:id="tfNumOfHues"
    private TextField tfNumOfHues;

    @FXML // fx:id="tfNumOfSaturations"
    private TextField tfNumOfSaturations;

    @FXML // fx:id="tfMinSaturation"
    private TextField tfMinSaturation;

    @FXML // fx:id="tfMaxSaturation"
    private TextField tfMaxSaturation;

    @FXML // fx:id="tfNumOfBrightnesses"
    private TextField tfNumOfBrightnesses;

    @FXML // fx:id="tfMinBrightness"
    private TextField tfMinBrightness;

    @FXML // fx:id="tfMaxBrightness"
    private TextField tfMaxBrightness;

    @FXML
    void about(ActionEvent event) throws IOException {
        Stage aboutStage = new Stage();
        aboutStage.initOwner(canvas.getScene().getWindow());
        aboutStage.initModality(Modality.APPLICATION_MODAL);
        aboutStage.initStyle(StageStyle.UNDECORATED);
        aboutStage.setTitle("About Color palette generator");
        Parent root = FXMLLoader.load(getClass().getResource("../about/about.fxml"));
        aboutStage.setScene(new Scene(root, 360, 160));
        aboutStage.showAndWait();
    }

    @FXML
    void close(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void generate(ActionEvent event) {
        final int numOfHues = Integer.parseInt(tfNumOfHues.getText());
        final int numOfSaturations = Integer.parseInt(tfNumOfSaturations.getText());
        final double minSaturation = Integer.parseInt(tfMinSaturation.getText()) / 100d;
        final double maxSaturation = Integer.parseInt(tfMaxSaturation.getText()) / 100d;
        final int numOfBrightnesses = Integer.parseInt(tfNumOfBrightnesses.getText());
        final double minBrightness = Integer.parseInt(tfMinBrightness.getText()) / 100d;
        final double maxBrightness = Integer.parseInt(tfMaxBrightness.getText()) / 100d;
        ColorPaletteGenerator.generate(numOfHues, numOfSaturations, minSaturation, maxSaturation,
                numOfBrightnesses, minBrightness, maxBrightness, canvas, 32);
    }

    @FXML
    void save(ActionEvent event) {
        ImageUtil.saveToFile(canvas);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        UnaryOperator<TextFormatter.Change> numOfHSBFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("([1-9][0-9]*)?")) {
                if (newText.length() <= 2)
                    return change;
            }
            return null;
        };

        tfNumOfHues.setTextFormatter(new TextFormatter<>(numOfHSBFilter));
        tfNumOfSaturations.setTextFormatter(new TextFormatter<>(numOfHSBFilter));
        tfNumOfBrightnesses.setTextFormatter(new TextFormatter<>(numOfHSBFilter));

        UnaryOperator<TextFormatter.Change> minMaxSBFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("([0-9]*)?")) {
                if (newText.length() <= 2)
                    return change;
                if (Integer.parseInt(newText) == 100)
                    return change;
            }
            return null;
        };

        tfMinSaturation.setTextFormatter(new TextFormatter<>(minMaxSBFilter));
        tfMaxSaturation.setTextFormatter(new TextFormatter<>(minMaxSBFilter));
        tfMinBrightness.setTextFormatter(new TextFormatter<>(minMaxSBFilter));
        tfMaxBrightness.setTextFormatter(new TextFormatter<>(minMaxSBFilter));
    }
}
