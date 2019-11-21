package colorpalettegenerator.cpgen;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ColorPaletteGenerator {

    public static void generate(int numOfHues, int numOfSaturations, int numOfBrightnesses, Canvas canvas, int paletteColorSize) {
        final double hueStep = 360d / numOfHues;
        final double saturationStep = 1d / (numOfSaturations + 1);
        final double brightnessStep = 1d / (numOfBrightnesses + 1);

        canvas.setWidth(numOfHues * paletteColorSize);
        canvas.setHeight(numOfSaturations * numOfBrightnesses * paletteColorSize);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        for (int i = 0; i < numOfHues; ++i) {
            for (int j = 0; j < numOfSaturations; ++j) {
                for (int k = 0; k  < numOfBrightnesses; ++k) {
                    gc.setFill(Color.hsb(i * hueStep, (j + 1) * saturationStep, (k + 1) * brightnessStep));
                    gc.fillRect(i * paletteColorSize, (j*numOfBrightnesses + k) * paletteColorSize, paletteColorSize, paletteColorSize);
                }
            }
        }
    }

}
