package io.github.sdxqw.surviveyourself.gui.components;

import io.github.sdxqw.surviveyourself.Core;
import io.github.sdxqw.surviveyourself.gui.basics.IComponent;
import io.github.sdxqw.surviveyourself.handling.InputManager;
import io.github.sdxqw.surviveyourself.handling.ResourceLocation;
import io.github.sdxqw.surviveyourself.world.World;
import org.lwjgl.glfw.GLFW;

public class PlayButton implements InputManager.MouseHandler, IComponent, IComponent.Updatable {
    private final float x;
    private final float y;
    private final float width;
    private final float height;
    private ImageAnimation playButtonImage;

    public PlayButton(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        initButton();
    }

    private void initButton() {
        this.playButtonImage = new ImageAnimation(Core.getTheCore().getTheResourceManager(), 0.5f);
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
            Core.getTheCore().displayScreen(new World(), nvg, window);
        }

        if (xPos >= x && xPos <= x + width && yPos >= y && yPos <= y + height) {
            playButtonImage.setAnimationRange(0, 1);
        } else {
            playButtonImage.setCurrentFrameIndex(0);
        }
    }
}
