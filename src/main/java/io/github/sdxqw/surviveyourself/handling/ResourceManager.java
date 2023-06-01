package io.github.sdxqw.surviveyourself.handling;

import io.github.sdxqw.surviveyourself.utils.Utils;
import lombok.Getter;

import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.lwjgl.nanovg.NanoVG.nvgCreateImageMem;
import static org.lwjgl.nanovg.NanoVG.nvgDeleteImage;

public class ResourceManager {
    private static final int NVG_IMAGE_GENERATE_MIPMAPS = 1;
    @Getter
    private final long nvg;
    private final Map<String, Integer> loadedImages;

    public ResourceManager(long nvg) {
        this.nvg = nvg;
        this.loadedImages = new HashMap<>();
    }

    public int loadImage(String imagePath) {
        if (loadedImages.containsKey(imagePath)) {
            return loadedImages.get(imagePath);
        }

        try {
            Path resourcePath = Paths.get(Objects.requireNonNull(Utils.class.getResource(imagePath)).toURI());
            ByteBuffer imageBuffer = Utils.readFile(resourcePath);
            int imageId = nvgCreateImageMem(nvg, NVG_IMAGE_GENERATE_MIPMAPS, imageBuffer);
            loadedImages.put(imagePath, imageId);
            return imageId;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load image: " + imagePath, e);
        }
    }

    public void deleteImage(int imageId) {
        nvgDeleteImage(nvg, imageId);
    }
}
