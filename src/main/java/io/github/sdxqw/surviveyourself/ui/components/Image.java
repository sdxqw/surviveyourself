package io.github.sdxqw.surviveyourself.ui.components;

import io.github.sdxqw.surviveyourself.handling.ResourceLocation;
import io.github.sdxqw.surviveyourself.utils.Utils;
import lombok.Getter;

import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.util.Objects;

import static org.lwjgl.nanovg.NanoVG.*;

public class Image {
    private final long nvg;
    @Getter
    private final ResourceLocation location;

    public Image(long nvg, ResourceLocation location) {
        this.nvg = nvg;
        this.location = location;
        ByteBuffer imageBuffer;
        try {
            imageBuffer = Utils.readFile(Paths.get(Objects.requireNonNull(Utils.class.getResource(location.getPath())).toURI()));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        location.setId(nvgCreateImageMem(nvg, NVG_IMAGE_GENERATE_MIPMAPS, imageBuffer));
    }

    public void render(float x, float y, float width, float height, int alpha) {
        Utils.drawImage(nvg, location, x, y, width, height, alpha);
    }

    public void cleanup() {
        nvgDeleteImage(nvg, location.getId());
    }
}
