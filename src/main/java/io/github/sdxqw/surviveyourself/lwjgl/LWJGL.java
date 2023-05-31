package io.github.sdxqw.surviveyourself.lwjgl;

public interface LWJGL {
    void start(long nvg, long window);

    void update(long nvg, long window, float deltaTime);

    void render(long nvg, long window);

    void stop(long nvg, long window);
}
