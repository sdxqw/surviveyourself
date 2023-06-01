package io.github.sdxqw.surviveyourself.gui.menus;

import io.github.sdxqw.surviveyourself.Core;
import io.github.sdxqw.surviveyourself.gui.basics.IScreen;
import io.github.sdxqw.surviveyourself.gui.components.PlayButton;
import io.github.sdxqw.surviveyourself.handling.InputManager;
import io.github.sdxqw.surviveyourself.utils.Utils;
import org.lwjgl.glfw.GLFW;

public class MainMenu implements InputManager.MouseHandler, IScreen, IScreen.Renderable {

    private PlayButton playButton;

    @Override
    public void initScreen(long nvg, long window) {
        Core.getTheCore().getTheInputManager().registerMouseHandler(this);
        playButton = new PlayButton(Utils.width >> 1, Utils.height >> 1, 120, 80);
    }

    @Override
    public void render(long nvg, long window) {
        playButton.render(nvg, window);
        Core.getTheCore().getTheFontManager().drawRobotoText("ciao", Utils.width >> 1, Utils.height >> 1, 15f, Utils.color(1f, 1f, 1f, 1f));
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
