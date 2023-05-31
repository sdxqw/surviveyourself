package io.github.sdxqw.surviveyourself.ui.components;

import java.util.ArrayList;
import java.util.List;

public class ImageAnimation {
    private final List<Image> frames;
    private final float frameDuration;
    private int currentFrameIndex;
    private int startFrameIndex;
    private int endFrameIndex;
    private float elapsedFrameTime;

    public ImageAnimation(float frameDuration) {
        this.frames = new ArrayList<>();
        this.currentFrameIndex = 0;
        this.startFrameIndex = 0;
        this.endFrameIndex = 0;
        this.frameDuration = frameDuration;
        this.elapsedFrameTime = 0;
    }

    public void addFrame(Image... image) {
        frames.addAll(List.of(image));
    }

    public void update(float deltaTime) {
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
        frames.get(currentFrameIndex).render(x, y, width, height, alpha);
    }

    public void setAnimationRange(int startFrameIndex, int endFrameIndex) {
        this.startFrameIndex = startFrameIndex;
        this.endFrameIndex = endFrameIndex;
        this.currentFrameIndex = startFrameIndex;
    }

    public void cleanup() {
        frames.forEach(Image::cleanup);
    }
}