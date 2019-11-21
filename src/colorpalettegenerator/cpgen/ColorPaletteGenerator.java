package colorpalettegenerator.cpgen;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ColorPaletteGenerator {

    public static void generate(int numOfHues, int numOfSaturations, double minSaturation, double maxSaturation,
                                int numOfBrightnesses, double minBrightness, double maxBrightness,
                                Canvas canvas, int paletteColorSize) {
        final double hueStep = 360d / numOfHues;
        final double saturationStep = (maxSaturation - minSaturation) / (numOfSaturations + 1);
        final double brightnessStep = (maxBrightness - minBrightness) / (numOfBrightnesses + 1);

        canvas.setWidth(numOfHues * paletteColorSize);
        canvas.setHeight(numOfSaturations * numOfBrightnesses * paletteColorSize);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        for (int i = 0; i < numOfHues; ++i) {
            for (int j = 0; j < numOfSaturations; ++j) {
                for (int k = 0; k  < numOfBrightnesses; ++k) {
                    gc.setFill(Color.hsb(i * hueStep, minSaturation + (j + 1) * saturationStep, minBrightness + (k + 1) * brightnessStep));
                    gc.fillRect(i * paletteColorSize, (j * numOfBrightnesses + k) * paletteColorSize, paletteColorSize, paletteColorSize);
                }
            }
        }
    }

}
