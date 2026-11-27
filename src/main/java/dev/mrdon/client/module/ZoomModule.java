package dev.mrdon.client.module;

import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

/** Hold C to zoom by lowering the FOV option; the original FOV is restored on release. */
public final class ZoomModule extends Module {
    private static final int ZOOM_FOV = 30;
    private Integer saved;

    public ZoomModule() { super("Zoom", "Hold C to zoom"); setEnabled(true); }

    @Override public void onTick(MinecraftClient mc) {
        boolean down = GLFW.glfwGetKey(mc.getWindow().getHandle(), GLFW.GLFW_KEY_C) == GLFW.GLFW_PRESS
                && mc.currentScreen == null;
        if (down && saved == null) {
            saved = mc.options.getFov().getValue();
            mc.options.getFov().setValue(ZOOM_FOV);
        } else if (!down && saved != null) {
            restore(mc);
        }
    }

    @Override protected void onDisable() { restore(MinecraftClient.getInstance()); }

    private void restore(MinecraftClient mc) {
        if (saved != null) { mc.options.getFov().setValue(saved); saved = null; }
    }
}
