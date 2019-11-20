package colorpalettegenerator.ui.main;

import colorpalettegenerator.cpgen.ColorPaletteGenerator;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.io.File;
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

    @FXML // fx:id="tfNumOfBrightnesses"
    private TextField tfNumOfBrightnesses;

    @FXML
    void about(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Color palette generator");
        alert.setHeaderText("Color palette generator");
        alert.setContentText("A program that generate color palettes.");
        alert.showAndWait();
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
        final int numOfBrightnesses = Integer.parseInt(tfNumOfBrightnesses.getText());
        ColorPaletteGenerator.generate(numOfHues, numOfSaturations, numOfBrightnesses, canvas, 32);
    }

    @FXML
    void save(ActionEvent event) {
        WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        canvas.snapshot(null, writableImage);

        File file = new File("palette.png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        };
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("([1-9][0-9]*)?")) {
                if (newText.length() <= 2)
                    return change;
            }
            return null;
        };

        tfNumOfHues.setTextFormatter(new TextFormatter<>(integerFilter));
        tfNumOfSaturations.setTextFormatter(new TextFormatter<>(integerFilter));
        tfNumOfBrightnesses.setTextFormatter(new TextFormatter<>(integerFilter));
    }
}
