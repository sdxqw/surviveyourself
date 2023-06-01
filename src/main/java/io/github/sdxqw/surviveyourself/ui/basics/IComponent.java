package io.github.sdxqw.surviveyourself.ui.basics;

public interface IComponent {

    void render(long nvg, long window);

    void cleanup(long nvg, long window);

    interface Updatable {
        void update(long nvg, long window, float deltaTime);
    }
}
