package io.github.sdxqw.surviveyourself.ui.components;

import io.github.sdxqw.surviveyourself.Core;
import io.github.sdxqw.surviveyourself.handling.ResourceLocation;
import io.github.sdxqw.surviveyourself.ui.basics.Component;
import io.github.sdxqw.surviveyourself.handling.InputManager;
import org.lwjgl.glfw.GLFW;

public class PlayButton implements InputManager.MouseHandler, Component {
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private ImageAnimation playButtonImage;

    public PlayButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        initButton();
    }

    private void initButton() {
        this.playButtonImage = new ImageAnimation(Core.getTheCore().getResourceManager(), 0.5f);
        this.playButtonImage.addFrame(
                new ResourceLocation("/textures/ui/button/play_button1.png"),
                new ResourceLocation("/textures/ui/button/play_button2.png"));
    }

    @Override
    public void render(long nvg, long window) {
        playButtonImage.render(x, y, width, height, 255);
    }

    @Override
    public void update(long nvg, long window, float deltaTime) {
        playButtonImage.update(deltaTime);
    }

    @Override
    public void cleanup(long nvg, long window) {
        playButtonImage.cleanup();
    }

    @Override
    public void handleMouseInput(long nvg, long window, int button, int action, double xPos, double yPos) {
        if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT && action == GLFW.GLFW_PRESS && xPos >= x && xPos <= x + width && yPos >= y && yPos <= y + height) {
            Core.getTheCore().displayWorldScreen();
        }

        if (xPos >= x && xPos <= x + width && yPos >= y && yPos <= y + height) {
            playButtonImage.setAnimationRange(0 ,1);
        } else {
            playButtonImage.setCurrentFrameIndex(0);
        }
    }
}
