package io.github.sdxqw.surviveyourself.ui.basics;

import io.github.sdxqw.surviveyourself.handling.InputHandler;
import lombok.Getter;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputManager {
    @Getter
    private static final InputManager instance = new InputManager();

    private final Map<Integer, List<InputHandler>> keyHandlers;
    private final List<InputHandler> mouseHandlers;

    public InputManager() {
        keyHandlers = new HashMap<>();
        mouseHandlers = new ArrayList<>();
    }

    public void registerKeyHandler(int keyCode, InputHandler inputHandler) {
        List<InputHandler> handlers = keyHandlers.computeIfAbsent(keyCode, k -> new ArrayList<>());
        handlers.add(inputHandler);
    }

    public void registerMouseHandler(InputHandler inputHandler) {
        mouseHandlers.add(inputHandler);
    }

    public GLFWKeyCallback createKeyCallback(long nvg) {
        return new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (action == GLFW.GLFW_PRESS) {
                    handleKeyPress(nvg, window, key);
                } else if (action == GLFW.GLFW_RELEASE) {
                    handleKeyRelease(nvg, window, key);
                }
            }
        };
    }

    public GLFWCursorPosCallback createMouseMoveCallback(long nvg) {
        return new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                handleMouseMove(nvg, window, xpos, ypos);
            }
        };
    }

    public GLFWMouseButtonCallback createMouseButtonCallback(long nvg) {
        return new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                handleMouseButton(nvg, window, button, action, mods);
            }
        };
    }

    private void handleKeyPress(long nvg, long window, int keyCode) {
        List<InputHandler> handlers = keyHandlers.get(keyCode);
        if (handlers != null) {
            for (InputHandler handler : handlers) {
                handler.handleKeyPress(nvg, window, keyCode);
            }
        }
    }

    private void handleKeyRelease(long nvg, long window, int keyCode) {
        List<InputHandler> handlers = keyHandlers.get(keyCode);
        if (handlers != null) {
            for (InputHandler handler : handlers) {
                handler.handleKeyRelease(nvg, window, keyCode);
            }
        }
    }

    private void handleMouseMove(long nvg, long window, double xpos, double ypos) {
        for (InputHandler handler : mouseHandlers) {
            handler.handleMouseMove(nvg, window, xpos, ypos);
        }
    }

    private void handleMouseButton(long nvg, long window, int button, int action, int mods) {
        for (InputHandler handler : mouseHandlers) {
            handler.handleMouseButton(nvg, window, button, action, mods);
        }
    }
}