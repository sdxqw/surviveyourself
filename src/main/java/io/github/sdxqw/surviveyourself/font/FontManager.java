package io.github.sdxqw.surviveyourself.font;

import lombok.SneakyThrows;
import org.lwjgl.nanovg.NVGColor;

/**
 * The FontManager class is responsible for managing fonts used in the application.
 * It provides methods to load fonts, draw text using the loaded fonts, and measure the width of text.
 */
public class FontManager {
    private final String ROBOTO = "roboto";
    private final Font font = new Font();

    /**
     * Initializes the Roboto font by loading it from a file.
     * This method should be called once at the start of the application.
     *
     * @param nvg the NanoVG context
     */
    @SneakyThrows
    public FontManager(long nvg) {
        font.init(nvg, ROBOTO, "8bit");
    }

    /**
     * Draws text using the Roboto font.
     *
     * @param text  the text to draw
     * @param x     the x-coordinate of the text position
     * @param y     the y-coordinate of the text position
     * @param size  the size of the text
     * @param color the color of the text
     */
    public void drawRobotoText(String text, float x, float y, float size, NVGColor color) {
        font.drawText(text, x, y, ROBOTO, size * 2, color);
    }

    /**
     * Measures the width of text using a specified font and font size.
     *
     * @param text     the text to measure
     * @param name     the name of the font to use
     * @param fontSize the size of the font to use
     * @return the width of the text
     */
    public float getTextWidth(String text, String name, float fontSize) {
        return font.measureTextWidth(text, name, fontSize);
    }

    /**
     * Returns the height of the specified text when drawn with the given font and size.
     *
     * @param name the name of the font to use
     * @return the height of the font in pixels
     */
    public float getTextHeight(String name) {
        return font.getTextHeight(name);
    }
}
