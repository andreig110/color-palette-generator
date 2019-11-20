package colorpalettegenerator.cpgen;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ColorPaletteGenerator {

    public static void generate(int numOfHues, int numOfSaturations, int numOfBrightnesses, Canvas canvas, int paletteColorSize) {
        final double hueStep = 360d / numOfHues;
        final double saturationStep = 0.8d / numOfSaturations;
        final double brightnessStep = 1d / numOfBrightnesses;

        canvas.setWidth(numOfHues * paletteColorSize);
        canvas.setHeight(numOfSaturations * paletteColorSize);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        for (int i = 0; i < numOfHues; ++i) {
            for (int j = 0; j < numOfSaturations; ++j) {
                gc.setFill(Color.hsb(i * hueStep, (j + 1) * saturationStep, 0.8));
                gc.fillRect(i * paletteColorSize, j * paletteColorSize, paletteColorSize, paletteColorSize);
            }
        }
    }

}
