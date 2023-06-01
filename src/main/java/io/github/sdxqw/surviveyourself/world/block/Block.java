package io.github.sdxqw.surviveyourself.world.block;

import io.github.sdxqw.surviveyourself.Core;
import io.github.sdxqw.surviveyourself.handling.ResourceLocation;
import io.github.sdxqw.surviveyourself.ui.basics.Component;
import io.github.sdxqw.surviveyourself.ui.components.Image;
import lombok.Getter;

@Getter
public class Block implements Component {

    private final Image blockImage;

    private final int x;
    private final int y;

    private final int width;
    private final int height;

    public Block(int x, int y, int width, int height, Material.Type material) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.blockImage = new Image(Core.getTheCore().getResourceManager(), new ResourceLocation("/textures/blocks/" + material.getName() + ".png"));
    }

    @Override
    public void render(long nvg, long window) {
        blockImage.render(x, y, width, height, 255);
    }

    @Override
    public void update(long nvg, long window, float deltaTime) {

    }

    @Override
    public void cleanup(long nvg, long window) {
        blockImage.cleanup();
    }
}
