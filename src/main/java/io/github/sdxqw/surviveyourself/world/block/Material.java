package io.github.sdxqw.surviveyourself.world.block;

import lombok.Getter;

public class Material {
    @Getter
    public enum Type {

        DIRT("dirt"), BOAT("Boat");

        final String name;

        Type(String name) {
            this.name = name;
        }
    }
}
