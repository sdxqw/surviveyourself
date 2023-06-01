package io.github.sdxqw.surviveyourself.ui.basics;

public interface Component {

    void render(long nvg, long window);

    void update(long nvg, long window, float deltaTime);

    void cleanup(long nvg, long window);
}
