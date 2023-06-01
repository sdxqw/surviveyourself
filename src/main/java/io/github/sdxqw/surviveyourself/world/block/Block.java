package io.github.sdxqw.surviveyourself.world.block;

import io.github.sdxqw.surviveyourself.Core;
import io.github.sdxqw.surviveyourself.gui.basics.IComponent;
import io.github.sdxqw.surviveyourself.gui.components.Image;
import io.github.sdxqw.surviveyourself.handling.ResourceLocation;
import lombok.Getter;

@Getter
public class Block implements IComponent {

    private final Image blockImage;

    private final float x;
    private final float y;

    private final float width;
    private final float height;

    public Block(float x, float y, float width, float height, Material.Type material) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        this.blockImage = new Image(Core.getTheCore().getTheResourceManager(), new ResourceLocation("/textures/blocks/" + material.getName() + ".png"));
    }

    @Override
    public void render(long nvg, long window) {
        blockImage.render(x, y, width, height);
    }

    @Override
    public void cleanup(long nvg, long window) {
        blockImage.cleanup();
    }
}
