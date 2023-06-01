package io.github.sdxqw.surviveyourself;

import io.github.sdxqw.surviveyourself.handling.ResourceManager;
import io.github.sdxqw.surviveyourself.lwjgl.LWJGL;
import io.github.sdxqw.surviveyourself.handling.InputManager;
import io.github.sdxqw.surviveyourself.ui.basics.Screen;
import io.github.sdxqw.surviveyourself.ui.menus.MainMenu;
import io.github.sdxqw.surviveyourself.world.World;
import lombok.Getter;
import org.lwjgl.glfw.GLFW;

@Getter
public class Core implements LWJGL {
    @Getter
    private static final Core theCore = new Core();

    private Screen currentScreen;
    private ResourceManager resourceManager;
    private World theWorld;
    private final InputManager inputManager = InputManager.getInstance();

    @Override
    public void start(long nvg, long window) {
        resourceManager = new ResourceManager(nvg);
        displayUiScreen(new MainMenu(), nvg, window);
        setGLFWCallbacks(nvg, window, inputManager);
    }

    @Override
    public void update(long nvg, long window, float deltaTime) {
        if (currentScreen != null) {
            currentScreen.updateScreen(nvg, window, deltaTime);
        }

        if (theWorld != null) {
            theWorld.updateWorld(nvg, window, deltaTime);
        }
    }

    @Override
    public void render(long nvg, long window) {
        if (currentScreen != null) {
            currentScreen.drawScreen(nvg, window);
        }

        if (theWorld != null) {
            theWorld.renderWorld(nvg, window);
        }
    }

    @Override
    public void stop(long nvg, long window) {
        if (currentScreen != null) {
            currentScreen.clearScreen(nvg, window);
        }

        if (theWorld != null) {
            theWorld.clearWorld(nvg, window);
        }
    }

    public void displayUiScreen(Screen uiScreen, long nvg, long window) {
        uiScreen.initScreen(nvg, window);
        currentScreen = uiScreen;
    }

    public void displayWorldScreen() {
        theWorld = new World();
        theWorld.initWorld();

        currentScreen = null;
    }

    private void setGLFWCallbacks(long nvg, long window, InputManager inputManager) {
        GLFW.glfwSetKeyCallback(window, inputManager.createKeyCallback(nvg));
        GLFW.glfwSetCursorPosCallback(window, inputManager.createMouseMoveCallback(nvg));
        GLFW.glfwSetMouseButtonCallback(window, inputManager.createMouseButtonCallback(nvg));
    }
}
