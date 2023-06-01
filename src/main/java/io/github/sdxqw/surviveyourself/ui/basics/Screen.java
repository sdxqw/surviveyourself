package io.github.sdxqw.surviveyourself.ui.basics;

public interface Screen {
    void initScreen(long nvg, long window);

    void drawScreen(long nvg, long window);

    void updateScreen(long nvg, long window, float deltaTime);

    void clearScreen(long nvg, long window);
}
