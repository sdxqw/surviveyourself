package io.github.sdxqw.surviveyourself.handling;

import lombok.Getter;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputManager {
    @Getter
    private static final InputManager instance = new InputManager();

    private final Map<Integer, List<KeyboardHandler>> keyHandlers;
    private final List<MouseHandler> mouseHandlers;

    private double mouseX;
    private double mouseY;

    public InputManager() {
        keyHandlers = new HashMap<>();
        mouseHandlers = new ArrayList<>();
    }

    public void registerKeyHandler(int keyCode, KeyboardHandler inputHandler) {
        keyHandlers.computeIfAbsent(keyCode, k -> new ArrayList<>()).add(inputHandler);
    }

    public void registerMouseHandler(MouseHandler inputHandler) {
        mouseHandlers.add(inputHandler);
    }

    public GLFWKeyCallback createKeyCallback(long nvg) {
        return new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (action == GLFW.GLFW_PRESS || action == GLFW.GLFW_RELEASE) {
                    handleKeyEvent(nvg, window, key, action);
                }
            }
        };
    }

    public GLFWCursorPosCallback createMouseMoveCallback() {
        return new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                mouseX = xpos;
                mouseY = ypos;
            }
        };
    }

    public GLFWMouseButtonCallback createMouseButtonCallback(long nvg) {
        return new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                handleMouseInput(nvg, window, button, action);
            }
        };
    }

    private void handleKeyEvent(long nvg, long window, int keyCode, int action) {
        List<KeyboardHandler> handlers = keyHandlers.get(keyCode);
        if (handlers != null) {
            for (KeyboardHandler handler : handlers) {
                handler.handleKeyEvent(nvg, window, keyCode, action);
            }
        }
    }

    private void handleMouseInput(long nvg, long window, int button, int action) {
        for (MouseHandler handler : mouseHandlers) {
            handler.handleMouseInput(nvg, window, button, action, mouseX, mouseY);
        }
    }

    public interface KeyboardHandler {
        void handleKeyEvent(long nvg, long window, int keyCode, int action);
    }

    public interface MouseHandler {
        void handleMouseInput(long nvg, long window, int button, int action, double xPos, double yPos);
    }
}
