package io.github.sdxqw.surviveyourself.lwjgl;

import io.github.sdxqw.surviveyourself.Core;
import io.github.sdxqw.surviveyourself.utils.Utils;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.nanovg.NanoVGGL3;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.nanovg.NanoVG.nvgBeginFrame;
import static org.lwjgl.nanovg.NanoVG.nvgEndFrame;
import static org.lwjgl.nanovg.NanoVGGL3.nvgDelete;
import static org.lwjgl.opengl.GL.createCapabilities;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class LWJGLCore implements LWJGL {

    private long window = 0L;
    private long nvg = 0L;

    private long lastTime;
    private float deltaTime;

    public void init() {
        start(nvg, window);
        while (!glfwWindowShouldClose(window)) {
            render(nvg, window);
            update(nvg, window, deltaTime);
            glfwSwapBuffers(window);
            glfwPollEvents();
        }
        stop(nvg, window);
    }

    @Override
    public void start(long nvg, long window) {
        if (!glfwInit()) throw new RuntimeException("Unable to initialize GLFW");

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

        this.window = glfwCreateWindow(Utils.width, Utils.height, "Survive Yourself", NULL, NULL);
        window = this.window;
        if (window == NULL) {
            glfwTerminate();
            throw new IllegalArgumentException("Failed to create the GLFW window");
        }

        GLFWVidMode videoMode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        glfwSetWindowPos(window, ((videoMode != null ? videoMode.width() : 0) - Utils.width) / 2, ((videoMode != null ? videoMode.height() : 0) - Utils.height) / 2);

        glfwMakeContextCurrent(window);
        glfwSwapInterval(1);

        createCapabilities();

        this.nvg = NanoVGGL3.nvgCreate(NanoVGGL3.NVG_ANTIALIAS | NanoVGGL3.NVG_STENCIL_STROKES);
        nvg = this.nvg;
        if (nvg == NULL) {
            glfwTerminate();
            throw new RuntimeException("Failed to create NVG context");
        }

        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0.0, Utils.width, 0.0, Utils.height, 1.0, -1.0);
        glMatrixMode(GL_MODELVIEW);

        Core.getTheCore().start(nvg, window);
    }

    @Override
    public void update(long nvg, long window, float deltaTime) {
        long currentTime = System.nanoTime();
        deltaTime = (currentTime - lastTime) / 1_000_000_000.0f;
        this.deltaTime = deltaTime;
        lastTime = currentTime;
        try {
            Thread.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Core.getTheCore().update(nvg, window, deltaTime);
    }

    @Override
    public void render(long nvg, long window) {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0.2f, 0.2f, 0.2f, 0.2f);
        nvgBeginFrame(nvg, Utils.width, Utils.height, 1f);
        Core.getTheCore().render(nvg, window);
        nvgEndFrame(nvg);
    }

    @Override
    public void stop(long nvg, long window) {
        Core.getTheCore().stop(nvg, window);
        if (window != NULL) glfwDestroyWindow(window);
        if (nvg != NULL) nvgDelete(nvg);
        glfwTerminate();
    }
}
