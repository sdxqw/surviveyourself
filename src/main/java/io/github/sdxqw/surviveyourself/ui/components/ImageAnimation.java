package io.github.sdxqw.surviveyourself.ui.components;

import io.github.sdxqw.surviveyourself.handling.ResourceLocation;
import io.github.sdxqw.surviveyourself.handling.ResourceManager;
import io.github.sdxqw.surviveyourself.utils.Utils;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class ImageAnimation {
    private final List<ResourceLocation> frameLocations;
    private final ResourceManager resourceManager;
    private final float frameDuration;
    @Setter
    private int currentFrameIndex;
    private int startFrameIndex;
    private int endFrameIndex;
    private float elapsedFrameTime;

    public ImageAnimation(ResourceManager resourceManager, float frameDuration) {
        this.resourceManager = resourceManager;
        this.frameLocations = new ArrayList<>();
        this.frameDuration = frameDuration;
        this.currentFrameIndex = 0;
        this.startFrameIndex = 0;
        this.endFrameIndex = 0;
        this.elapsedFrameTime = 0;

    }

    public void addFrame(ResourceLocation... frameLocation) {
        frameLocations.addAll(List.of(frameLocation));
    }

    public void update(float deltaTime) {
        if (frameLocations.isEmpty()) {
            return;
        }

        elapsedFrameTime += deltaTime;
        if (elapsedFrameTime >= frameDuration) {
            currentFrameIndex++;
            if (currentFrameIndex > endFrameIndex) {
                currentFrameIndex = startFrameIndex;
            }
            elapsedFrameTime = 0;
        }
    }

    public void render(float x, float y, float width, float height, int alpha) {
        if (frameLocations.isEmpty()) {
            return;
        }
        ResourceLocation frameLocation = frameLocations.get(currentFrameIndex);
        frameLocation.setId(resourceManager.loadImage(frameLocation.getPath()));
        Utils.drawImage(resourceManager.getNvg(), frameLocation, x, y, width, height, alpha);
    }

    public void setAnimationRange(int startFrameIndex, int endFrameIndex) {
        if (startFrameIndex < 0 || endFrameIndex >= frameLocations.size() || startFrameIndex > endFrameIndex) {
            throw new IllegalArgumentException("Invalid animation range");
        }

        this.startFrameIndex = startFrameIndex;
        this.endFrameIndex = endFrameIndex;
        this.currentFrameIndex = startFrameIndex;
    }

    public void cleanup() {
        frameLocations.forEach(e -> resourceManager.deleteImage(e.getId()));
        frameLocations.clear();
    }
}
