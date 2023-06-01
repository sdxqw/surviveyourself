package io.github.sdxqw.surviveyourself.font;

import io.github.sdxqw.surviveyourself.utils.Utils;
import org.lwjgl.BufferUtils;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NanoVG;

import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * The Font class provides functionality for loading and drawing fonts using NanoVG.
 */
public class Font {

    private long nvg = 0L;

    /**
     * Initializes a font with the given name and font file.
     *
     * @param nvg      the NanoVG context
     * @param name     the name of the font
     * @param fontName the name of the font file
     */
    public void init(long nvg, String name, String fontName) throws URISyntaxException {
        this.nvg = nvg;
        ByteBuffer fontBuffer = Utils.readFile(Paths.get(Objects.requireNonNull(Utils.class.getResource("/textures/fonts/" + fontName + ".ttf")).toURI()));
        NanoVG.nvgCreateFontMem(nvg, name, fontBuffer, true);
    }

    /**
     * Draws the specified text with the given font, size, and color at the specified position.
     *
     * @param text     the text to draw
     * @param x        the x-coordinate of the starting position
     * @param y        the y-coordinate of the starting position
     * @param font     the name of the font to use
     * @param fontSize the size of the font to use
     * @param color    the color of the text to draw
     */
    public void drawText(String text, float x, float y, String font, float fontSize, NVGColor color) {
        NanoVG.nvgFontFace(nvg, font);
        NanoVG.nvgFontSize(nvg, fontSize);
        NanoVG.nvgFillColor(nvg, color);
        NanoVG.nvgText(nvg, x, y, text);
    }

    /**
     * Returns the width of the specified text when drawn with the given font and size.
     *
     * @param text     the text to measure
     * @param font     the name of the font to use
     * @param fontSize the size of the font to use
     * @return the width of the text in pixels
     */
    public float measureTextWidth(String text, String font, float fontSize) {
        NanoVG.nvgFontFace(nvg, font);
        NanoVG.nvgFontSize(nvg, fontSize);
        return NanoVG.nvgTextBounds(nvg, 0f, 0f, text, (FloatBuffer) null);
    }

    /**
     * Returns the height of the specified text when drawn with the given font and size.
     *
     * @param font the name of the font to use
     * @return the height of the font in pixels
     */
    public float getTextHeight(String font) {
        NanoVG.nvgFontFace(nvg, font);
        FloatBuffer lineh = BufferUtils.createFloatBuffer(1);
        NanoVG.nvgTextMetrics(nvg, null, null, lineh);
        return lineh.get(0);
    }
}
