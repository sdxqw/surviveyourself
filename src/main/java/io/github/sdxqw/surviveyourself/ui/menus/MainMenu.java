package io.github.sdxqw.surviveyourself.ui.menus;

import io.github.sdxqw.surviveyourself.Core;
import io.github.sdxqw.surviveyourself.handling.InputHandler;
import io.github.sdxqw.surviveyourself.ui.basics.InputManager;
import io.github.sdxqw.surviveyourself.ui.basics.UiScreen;
import io.github.sdxqw.surviveyourself.ui.components.PlayButton;
import io.github.sdxqw.surviveyourself.world.World;
import org.lwjgl.glfw.GLFW;

public class MainMenu extends UiScreen implements InputHandler {

    @Override
    public void initScreen(long nvg, long window) {
        InputManager.getInstance().registerKeyHandler(GLFW.GLFW_KEY_ESCAPE,this);
        this.addComponent(new PlayButton(100, 100, 60, 40));
        super.initScreen(nvg, window);
    }

    @Override
    public void handleKeyPress(long nvg, long window, int keyCode) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {;
            Core.getTheCore().displayUiScreen(nvg, window, new World());
        }
    }

    @Override
    public void handleKeyRelease(long nvg, long window, int keyCode) {}

    @Override
    public void handleMouseMove(long nvg, long window, double xpos, double ypos) {

    }

    @Override
    public void handleMouseButton(long nvg, long window, int button, int action, int mods) {

    }
}
