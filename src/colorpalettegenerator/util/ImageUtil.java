/**
 * A Utility Class to Save an Image to a File
 */

package colorpalettegenerator.util;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtil {

    public static void saveToFile(Canvas canvas) {
        // Ask the user for the file name
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an image file name");
        fileChooser.setInitialFileName("palette");
        FileChooser.ExtensionFilter pngExt = new FileChooser.ExtensionFilter("PNG Files", "*.png");
        fileChooser.getExtensionFilters().add(pngExt);

        File outputFile = fileChooser.showSaveDialog(null);
        if (outputFile == null)
            return;

        WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        canvas.snapshot(null, writableImage);

        BufferedImage bImage = SwingFXUtils.fromFXImage(writableImage, null);
        BufferedImage bImageNoAlpha = new BufferedImage(bImage.getWidth(), bImage.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        bImageNoAlpha.getGraphics().drawImage(bImage, 0, 0, null);

        try {
            ImageIO.write(bImageNoAlpha, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        };
    }

}
