package io.github.sdxqw.surviveyourself.utils;

import io.github.sdxqw.surviveyourself.handling.ResourceLocation;
import org.lwjgl.nanovg.NVGPaint;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static org.lwjgl.nanovg.NanoVG.*;

public class Utils {
    public static int width = 1280;
    public static int height = 820;


    public static ByteBuffer readFile(Path path) {
        try (FileChannel fc = FileChannel.open(path, StandardOpenOption.READ)) {
            if (fc.size() == 0) throw new RuntimeException("File is empty: " + path);
            ByteBuffer buffer = ByteBuffer.allocateDirect(Math.toIntExact(fc.size()));
            if (fc.read(buffer) == -1) throw new IOException("Failed to read from file: " + path);
            buffer.flip();
            return buffer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void drawImage(long nvg, ResourceLocation location, float x, float y, float width, float height, int alpha) {
        nvgBeginPath(nvg);
        NVGPaint paint = nvgImagePattern(nvg, x, y, width, height, 0.0F, location.getId(), alpha / 255.0F, NVGPaint.create());
        nvgRect(nvg, x, y, width, height);
        nvgFillPaint(nvg, paint);
        nvgFill(nvg);
    }
}
