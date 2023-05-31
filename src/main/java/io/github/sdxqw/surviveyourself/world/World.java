package io.github.sdxqw.surviveyourself.world;

import io.github.sdxqw.surviveyourself.world.block.Block;
import io.github.sdxqw.surviveyourself.world.block.Material;
import io.github.sdxqw.surviveyourself.world.entity.Player;
import io.github.sdxqw.surviveyourself.ui.basics.UiScreen;

public class World extends UiScreen {

    private Player player;
    private Block block;

    @Override
    public void initScreen(long nvg, long window) {
        this.addComponent(player = new Player(), block = new Block(nvg, 100, 120, 80, 32, Material.Type.BOAT));
        super.initScreen(nvg, window);
    }

    @Override
    public void updateScreen(long nvg, long window, float deltaTime) {
        player.collision(block.getX(), block.getY(), block.getWidth(), block.getHeight());
        super.updateScreen(nvg, window, deltaTime);
    }
}
