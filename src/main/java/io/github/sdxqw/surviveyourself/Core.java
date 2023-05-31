package io.github.sdxqw.surviveyourself;

import io.github.sdxqw.surviveyourself.lwjgl.LWJGL;
import io.github.sdxqw.surviveyourself.ui.basics.InputManager;
import io.github.sdxqw.surviveyourself.ui.basics.UiScreen;
import io.github.sdxqw.surviveyourself.ui.menus.MainMenu;
import lombok.Getter;
import org.lwjgl.glfw.GLFW;

@Getter
public class Core implements LWJGL {
    @Getter
    private static final Core theCore = new Core();

    private UiScreen currentScreen;

    @Override
    public void start(long nvg, long window) {
        GLFW.glfwSetKeyCallback(window, InputManager.getInstance().createKeyCallback(nvg));
        GLFW.glfwSetCursorPosCallback(window, InputManager.getInstance().createMouseMoveCallback(nvg));
        GLFW.glfwSetMouseButtonCallback(window, InputManager.getInstance().createMouseButtonCallback(nvg));
        displayUiScreen(nvg, window, new MainMenu());
    }

    @Override
    public void update(long nvg, long window, float deltaTime) {
        currentScreen.updateScreen(nvg, window, deltaTime);
    }

    @Override
    public void render(long nvg, long window) {
        currentScreen.drawScreen(nvg, window);
    }

    @Override
    public void stop(long nvg, long window) {
        currentScreen.clearScreen(nvg, window);
    }

    public void displayUiScreen(long nvg, long window, UiScreen uiScreen) {
        uiScreen.initScreen(nvg, window);
        this.currentScreen = uiScreen;
    }
}
