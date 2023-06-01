package io.github.sdxqw.surviveyourself;

import io.github.sdxqw.surviveyourself.font.FontManager;
import io.github.sdxqw.surviveyourself.handling.InputManager;
import io.github.sdxqw.surviveyourself.handling.ResourceManager;
import io.github.sdxqw.surviveyourself.lwjgl.LWJGL;
import io.github.sdxqw.surviveyourself.ui.basics.IScreen;
import io.github.sdxqw.surviveyourself.ui.menus.MainMenu;
import io.github.sdxqw.surviveyourself.world.World;
import lombok.Getter;
import org.lwjgl.glfw.GLFW;

@Getter
public class Core implements LWJGL {
    @Getter
    private static final Core theCore = new Core();

    private IScreen theCurrentScreen;
    private ResourceManager theResourceManager;
    private World theWorld;
    private InputManager theInputManager;
    private FontManager theFontManager;

    @Override
    public void start(long nvg, long window) {
        theResourceManager = new ResourceManager(nvg);
        theFontManager = new FontManager(nvg);
        theInputManager = InputManager.getInstance();
        displayScreen(new MainMenu(), nvg, window);
        setGLFWCallbacks(nvg, window, theInputManager);
    }

    @Override
    public void update(long nvg, long window, float deltaTime) {
        if (theCurrentScreen != null) {
            ((IScreen.IRenderable) theCurrentScreen).update(nvg, window, deltaTime);
        }

        if (theWorld != null) {
            theWorld.update(nvg, window, deltaTime);
        }
    }

    @Override
    public void render(long nvg, long window) {
        if (theCurrentScreen != null) {
            ((IScreen.IRenderable) theCurrentScreen).render(nvg, window);
        }

        if (theWorld != null) {
            theWorld.render(nvg, window);
        }
    }

    @Override
    public void stop(long nvg, long window) {
        if (theCurrentScreen != null) {
            theCurrentScreen.clearScreen(nvg, window);
        }

        if (theWorld != null) {
            theWorld.clearWorld(nvg, window);
        }
    }

    public void displayScreen(IScreen.IRenderable uiScreen, long nvg, long window) {
        if (uiScreen instanceof World) {
            theWorld = (World) uiScreen;
            theWorld.initWorld();
            theCurrentScreen = null;
        } else {
            ((IScreen) uiScreen).initScreen(nvg, window);
            theCurrentScreen = (IScreen) uiScreen;
        }
    }

    private void setGLFWCallbacks(long nvg, long window, InputManager inputManager) {
        GLFW.glfwSetKeyCallback(window, inputManager.createKeyCallback(nvg));
        GLFW.glfwSetCursorPosCallback(window, inputManager.createMouseMoveCallback());
        GLFW.glfwSetMouseButtonCallback(window, inputManager.createMouseButtonCallback(nvg));
    }
}
