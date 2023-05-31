package io.github.sdxqw.surviveyourself.world.entity;

import io.github.sdxqw.surviveyourself.handling.InputHandler;
import io.github.sdxqw.surviveyourself.handling.ResourceLocation;
import io.github.sdxqw.surviveyourself.ui.basics.InputManager;
import io.github.sdxqw.surviveyourself.ui.basics.UiScreen;
import io.github.sdxqw.surviveyourself.ui.components.Image;
import io.github.sdxqw.surviveyourself.ui.components.ImageAnimation;
import io.github.sdxqw.surviveyourself.utils.Utils;
import lombok.Getter;
import org.lwjgl.glfw.GLFW;

@Getter
public class Player implements InputHandler, UiScreen.Component {

    private final float speed = 1.2f;
    private ImageAnimation playerSkin;
    private float x = Utils.width >> 1;
    private float y = Utils.height >> 1;
    private boolean moveUp = false;
    private boolean moveLeft = false;
    private boolean moveDown = false;
    private boolean moveRight = false;
    private int lastAnimationStartIndex = 0;

    @Override
    public void init(long nvg, long window) {
        InputManager.getInstance().registerKeyHandler(GLFW.GLFW_KEY_W, this);
        InputManager.getInstance().registerKeyHandler(GLFW.GLFW_KEY_A, this);
        InputManager.getInstance().registerKeyHandler(GLFW.GLFW_KEY_S, this);
        InputManager.getInstance().registerKeyHandler(GLFW.GLFW_KEY_D, this);
        this.playerSkin = new ImageAnimation(0.3f);
        this.playerSkin.addFrame(
                new Image(nvg, new ResourceLocation("/textures/player/forward/fw1.png")),
                new Image(nvg, new ResourceLocation("/textures/player/forward/fw2.png")),
                new Image(nvg, new ResourceLocation("/textures/player/forward/fw3.png")),
                new Image(nvg, new ResourceLocation("/textures/player/backwards/bw1.png")),
                new Image(nvg, new ResourceLocation("/textures/player/backwards/bw2.png")),
                new Image(nvg, new ResourceLocation("/textures/player/backwards/bw3.png")),
                new Image(nvg, new ResourceLocation("/textures/player/left/lf1.png")),
                new Image(nvg, new ResourceLocation("/textures/player/left/lf2.png")),
                new Image(nvg, new ResourceLocation("/textures/player/left/lf3.png")),
                new Image(nvg, new ResourceLocation("/textures/player/rigth/rt1.png")),
                new Image(nvg, new ResourceLocation("/textures/player/rigth/rt3.png")),
                new Image(nvg, new ResourceLocation("/textures/player/rigth/rt2.png"))
        );
    }

    @Override
    public void render(long nvg, long window) {
        playerSkin.render(x, y, 120, 120, 255);
    }

    public void collision(int x, int y, int width, int height) {
        // Check if the player's bounding box intersects with the collision object
        if (this.x < x + width && this.x + 120 > x && this.y < y + height && this.y + 120 > y) {
            // If there's a collision, stop the player from moving in the direction of the collision
            float dx = this.x - x;
            float dy = this.y - y;
            float dw = width - 120;
            float dh = height - 120;
            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0 && dx > dw) {
                    moveLeft = false;
                } else if (dx < 0 && -dx > dw) {
                    moveRight = false;
                }
            } else {
                if (dy > 0 && dy > dh) {
                    moveUp = false;
                } else if (dy < 0 && -dy > dh) {
                    moveDown = false;
                }
            }
        }
    }

    @Override
    public void update(long nvg, long window, float deltaTime) {
        // Calculate the change in position based on the elapsed time and player speed
        float dx = 0;
        float dy = 0;
        if (moveUp) {
            dy -= speed;
        }
        if (moveDown) {
            dy += speed;
        }
        if (moveLeft) {
            dx -= speed;
        }
        if (moveRight) {
            dx += speed;
        }
        // Only update the position if the player is actually moving
        if (dx != 0 || dy != 0) {
            playerSkin.update(deltaTime);
            x += dx;
            y += dy;

            // Prevent player from going off the edges of the screen
            if (x < 0) {
                x = 0;
            }
            if (x + 50 > Utils.width) {
                x = Utils.width - 120;
            }
            if (y < 0) {
                y = 0;
            }
            if (y + 50 > Utils.height) {
                y = Utils.height - 120;
            }
        }
    }

    @Override
    public void cleanup(long nvg, long window) {
        playerSkin.cleanup();
    }

    @Override
    public void handleKeyPress(long nvg, long window, int keyCode) {
        if (keyCode == GLFW.GLFW_KEY_W) {
            if (!moveUp) {
                playerSkin.setAnimationRange(4, 5);
                lastAnimationStartIndex = 3;
            }
            moveUp = true;
        } else if (keyCode == GLFW.GLFW_KEY_A) {
            if (!moveLeft) {
                playerSkin.setAnimationRange(7, 8);
                lastAnimationStartIndex = 6;
            }
            moveLeft = true;
        } else if (keyCode == GLFW.GLFW_KEY_S) {
            if (!moveDown) {
                playerSkin.setAnimationRange(1, 2);
                lastAnimationStartIndex = 0;
            }
            moveDown = true;
        } else if (keyCode == GLFW.GLFW_KEY_D) {
            if (!moveRight) {
                playerSkin.setAnimationRange(10, 11);
                lastAnimationStartIndex = 9;
            }
            moveRight = true;
        }
    }

    @Override
    public void handleKeyRelease(long nvg, long window, int keyCode) {
        if (keyCode == GLFW.GLFW_KEY_W) {
            moveUp = false;
        } else if (keyCode == GLFW.GLFW_KEY_A) {
            moveLeft = false;
        } else if (keyCode == GLFW.GLFW_KEY_S) {
            moveDown = false;
        } else if (keyCode == GLFW.GLFW_KEY_D) {
            moveRight = false;
        }

        // If all movement keys are released, reset the animation
        if (!moveUp && !moveLeft && !moveDown && !moveRight) {
            playerSkin.setAnimationRange(lastAnimationStartIndex, lastAnimationStartIndex + 2);
        }
    }

    @Override
    public void handleMouseMove(long nvg, long window, double xpos, double ypos) {

    }

    @Override
    public void handleMouseButton(long nvg, long window, int button, int action, int mods) {

    }
}
