package dev.mrdon.client;

import dev.mrdon.client.gui.ClickGuiScreen;
import dev.mrdon.client.module.HudModule;
import dev.mrdon.client.module.Module;
import dev.mrdon.client.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

public final class LiteClient implements ClientModInitializer {
    private boolean guiKeyWasDown;

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(mc -> {
            // Right Shift opens the ClickGUI (edge-triggered).
            boolean down = GLFW.glfwGetKey(mc.getWindow().getHandle(), GLFW.GLFW_KEY_RIGHT_SHIFT) == GLFW.GLFW_PRESS;
            if (down && !guiKeyWasDown && mc.currentScreen == null && mc.player != null) {
                mc.setScreen(new ClickGuiScreen());
            }
            guiKeyWasDown = down;
            ModuleManager.tick(mc);
        });
        HudRenderCallback.EVENT.register((ctx, tickCounter) -> {
            for (Module m : ModuleManager.all()) {
                if (m instanceof HudModule hud) hud.render(ctx, MinecraftClient.getInstance());
            }
        });
    }
}
