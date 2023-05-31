package io.github.sdxqw.surviveyourself.ui.components;

import io.github.sdxqw.surviveyourself.handling.InputHandler;
import io.github.sdxqw.surviveyourself.handling.ResourceLocation;
import io.github.sdxqw.surviveyourself.ui.basics.InputManager;
import io.github.sdxqw.surviveyourself.ui.basics.UiScreen;

public class PlayButton implements UiScreen.Component, InputHandler {
    private ImageAnimation playButtonImage;

    private final int x;
    private final int y;

    private final int width;
    private final int height;

    public PlayButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void init(long nvg, long window) {
        InputManager.getInstance().registerMouseHandler(this);
        this.playButtonImage = new ImageAnimation(0.5f);
        this.playButtonImage.addFrame(
                new Image(nvg, new ResourceLocation("/textures/ui/button/play_button1.png")),
                new Image(nvg, new ResourceLocation("/textures/ui/button/play_button2.png")));
    }

    @Override
    public void render(long nvg, long window) {
        playButtonImage.render(x, y, width, height, 255);
    }

    @Override
    public void update(long nvg, long window, float deltaTime) {

    }

    @Override
    public void cleanup(long nvg, long window) {
        playButtonImage.cleanup();
    }

    @Override
    public void handleKeyPress(long nvg, long window, int keyCode) {

    }

    @Override
    public void handleKeyRelease(long nvg, long window, int keyCode) {

    }

    @Override
    public void handleMouseMove(long nvg, long window, double xpos, double ypos) {
        if (xpos >= x && xpos <= x + width && ypos >= y && ypos <= y + height) {
            handleMouseButton(nvg, window, 0, 0, 0);
        }
    }

    @Override
    public void handleMouseButton(long nvg, long window, int button, int action, int mods) {
        if (button == 0) {
            System.out.println("test");
        }
    }
}
