package io.github.sdxqw.surviveyourself.ui.basics;

public interface IScreen {
    void initScreen(long nvg, long window);

    void clearScreen(long nvg, long window);

    interface IRenderable {
        void render(long nvg, long window);
        void update(long nvg, long window, float deltaTime);
    }
}
