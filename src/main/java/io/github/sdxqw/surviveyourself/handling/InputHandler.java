package io.github.sdxqw.surviveyourself.handling;

public interface InputHandler {
    void handleKeyPress(long nvg, long window, int keyCode);

    void handleKeyRelease(long nvg, long window, int keyCode);

    void handleMouseMove(long nvg, long window, double xpos, double ypos);

    void handleMouseButton(long nvg, long window, int button, int action, int mods);
}
