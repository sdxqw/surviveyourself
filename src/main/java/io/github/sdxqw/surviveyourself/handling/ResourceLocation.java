package io.github.sdxqw.surviveyourself.handling;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public final class ResourceLocation {
    private final String path;
    private int id;

    public ResourceLocation(@NonNull String path) {
        this.path = path;
    }
}
