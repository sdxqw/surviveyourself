package io.github.sdxqw.surviveyourself.world;

import io.github.sdxqw.surviveyourself.Core;
import io.github.sdxqw.surviveyourself.gui.basics.IScreen;
import io.github.sdxqw.surviveyourself.gui.menus.MainMenu;
import io.github.sdxqw.surviveyourself.handling.InputManager;
import io.github.sdxqw.surviveyourself.world.block.Block;
import io.github.sdxqw.surviveyourself.world.block.Material;
import io.github.sdxqw.surviveyourself.world.entity.Player;
import org.lwjgl.glfw.GLFW;

public class World implements IScreen.Renderable, InputManager.KeyboardHandler {

    private Player player;
    private Block block;

    public void initWorld() {
        Core.getTheCore().getTheInputManager().registerKeyHandler(GLFW.GLFW_KEY_ESCAPE, this);
        Core.getTheCore().getTheInputManager().registerKeyHandler(GLFW.GLFW_PRESS, this);
        player = new Player();
        block = new Block(100, 100, 80, 32, Material.Type.BOAT);
    }

    @Override
    public void render(long nvg, long window) {
        block.render(nvg, window);
        player.render(nvg, window);
    }

    @Override
    public void update(long nvg, long window, float deltaTime) {
        player.update(nvg, window, deltaTime);
        player.collision(block.getX(), block.getY(), block.getWidth(), block.getHeight());
    }

    public void clearWorld(long nvg, long window) {
        player.cleanup(nvg, window);
        block.cleanup(nvg, window);
    }

    @Override
    public void handleKeyEvent(long nvg, long window, int keyCode, int action) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_PRESS) {
            Core.getTheCore().displayScreen(new MainMenu(), nvg, window);
        }
    }
}
