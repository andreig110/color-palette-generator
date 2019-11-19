package colorpalettegenerator.ui.main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import javax.imageio.ImageIO;

public class MainController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="canvas"
    private Canvas canvas; // Value injected by FXMLLoader

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
        final int HUE_COUNT = 8;
        final double HUE_STEP = 360d / HUE_COUNT;
        final int SATURATION_COUNT = 4;
        final double SATURATION_STEP = 0.8d / SATURATION_COUNT;
        final int SIZE = 32;
        canvas.setWidth(HUE_COUNT * SIZE);
        canvas.setHeight(SATURATION_COUNT * SIZE);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for (int i = 0; i < HUE_COUNT; ++i) {
            for (int j = 0; j < SATURATION_COUNT; ++j) {
                gc.setFill(Color.hsb(i * HUE_STEP, (j + 1) * SATURATION_STEP, 0.8));
                gc.fillRect(i * SIZE, j * SIZE, SIZE, SIZE);
            }
        }
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
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'main.fxml'.";
    }
}
