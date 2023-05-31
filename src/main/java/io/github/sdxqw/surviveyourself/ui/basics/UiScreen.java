package io.github.sdxqw.surviveyourself.ui.basics;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class UiScreen {
    @Getter
    private final List<Component> componentsList = new ArrayList<>();

    public void addComponent(Component... component) {
        componentsList.addAll(List.of(component));
    }

    public void initScreen(long nvg, long window) {
        componentsList.forEach(component -> component.init(nvg, window));
    }

    public void drawScreen(long nvg, long window) {
        componentsList.forEach(component -> component.render(nvg, window));
    }

    public void updateScreen(long nvg, long window, float deltaTime) {
        componentsList.forEach(component -> component.update(nvg, window, deltaTime));
    }

    public void clearScreen(long nvg, long window) {
        componentsList.forEach(component -> component.cleanup(nvg, window));
    }

    public interface Component {
        void init(long nvg, long window);

        void render(long nvg, long window);

        void update(long nvg, long window, float deltaTime);

        void cleanup(long nvg, long window);
    }
}
