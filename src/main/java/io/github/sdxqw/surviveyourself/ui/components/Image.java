package io.github.sdxqw.surviveyourself.ui.components;

import io.github.sdxqw.surviveyourself.handling.ResourceLocation;
import io.github.sdxqw.surviveyourself.handling.ResourceManager;
import io.github.sdxqw.surviveyourself.utils.Utils;
import lombok.Getter;

public class Image {
    private final ResourceManager resourceManager;
    @Getter
    private final ResourceLocation location;

    public Image(ResourceManager resourceManager, ResourceLocation location) {
        this.resourceManager = resourceManager;
        this.location = location;
        location.setId(resourceManager.loadImage(location.getPath()));
    }

    public void render(float x, float y, float width, float height) {
        Utils.drawImage(resourceManager.getNvg(), location, x, y, width, height, 255);
    }

    public void cleanup() {
        resourceManager.deleteImage(location.getId());
    }
}
