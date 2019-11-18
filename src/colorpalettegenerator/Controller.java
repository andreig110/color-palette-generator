package colorpalettegenerator;

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

public class Controller {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="canvas1"
    private Canvas canvas1; // Value injected by FXMLLoader

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
        canvas1.setWidth(HUE_COUNT * SIZE);
        canvas1.setHeight(SATURATION_COUNT * SIZE);
        GraphicsContext gc = canvas1.getGraphicsContext2D();
        for (int i = 0; i < HUE_COUNT; ++i) {
            for (int j = 0; j < SATURATION_COUNT; ++j) {
                gc.setFill(Color.hsb(i * HUE_STEP, (j + 1) * SATURATION_STEP, 0.8));
                gc.fillRect(i * SIZE, j * SIZE, SIZE, SIZE);
            }
        }
    }

    @FXML
    void save(ActionEvent event) {
        WritableImage writableImage = new WritableImage((int) canvas1.getWidth(), (int) canvas1.getHeight());
        canvas1.snapshot(null, writableImage);

        File file = new File("palette.png");
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(writableImage, null), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        };
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert canvas1 != null : "fx:id=\"canvas1\" was not injected: check your FXML file 'sample.fxml'.";
    }
}
