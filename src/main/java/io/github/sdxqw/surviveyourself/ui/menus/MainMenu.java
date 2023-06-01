package io.github.sdxqw.surviveyourself.ui.menus;

import io.github.sdxqw.surviveyourself.Core;
import io.github.sdxqw.surviveyourself.handling.InputManager;
import io.github.sdxqw.surviveyourself.ui.basics.IScreen;
import io.github.sdxqw.surviveyourself.ui.components.PlayButton;
import org.lwjgl.glfw.GLFW;

public class MainMenu implements InputManager.MouseHandler, IScreen, IScreen.IRenderable {

    private PlayButton playButton;

    @Override
    public void initScreen(long nvg, long window) {
        Core.getTheCore().getInputManager().registerMouseHandler(this);
        playButton = new PlayButton(100, 100, 90, 60);
    }

    @Override
    public void render(long nvg, long window) {
        playButton.render(nvg, window);
    }

    @Override
    public void update(long nvg, long window, float deltaTime) {
        playButton.update(nvg, window, deltaTime);
    }

    @Override
    public void clearScreen(long nvg, long window) {
        playButton.cleanup(nvg, window);
    }

    @Override
    public void handleMouseInput(long nvg, long window, int button, int action, double xPos, double yPos) {
        if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT && action == GLFW.GLFW_PRESS) {
            playButton.handleMouseInput(nvg, window, button, action, xPos, yPos);
        }
    }
}
