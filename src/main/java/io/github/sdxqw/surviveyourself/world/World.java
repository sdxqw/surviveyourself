package io.github.sdxqw.surviveyourself.world;

import io.github.sdxqw.surviveyourself.world.block.Block;
import io.github.sdxqw.surviveyourself.world.block.Material;
import io.github.sdxqw.surviveyourself.world.entity.Player;

public class World {

    private Player player;
    private Block block;

    public void initWorld() {
        player = new Player();
        block = new Block(0, 0, 80, 32, Material.Type.BOAT);
    }

    public void renderWorld(long nvg, long window) {
        block.render(nvg, window);
        player.render(nvg, window);
    }

    public void updateWorld(long nvg, long window, float deltaTime) {
        player.update(nvg, window, deltaTime);
        player.collision(block.getX(), block.getY(), block.getWidth(), block.getHeight());
    }

    public void clearWorld(long nvg, long window) {
        player.cleanup(nvg, window);
    }
}
