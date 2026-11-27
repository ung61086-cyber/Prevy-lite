package dev.mrdon.client.module;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import java.util.ArrayList;
import java.util.List;

/** Draws FPS, coordinates and the list of enabled modules. Client-side display only. */
public final class HudModule extends Module {
    public HudModule() { super("HUD", "FPS, coordinates, module list"); setEnabled(true); }

    public void render(DrawContext ctx, MinecraftClient mc) {
        if (!isEnabled() || mc.player == null || mc.options.hudHidden) return;
        List<String> lines = new ArrayList<>();
        lines.add("FPS: " + mc.getCurrentFps());
        lines.add(String.format("XYZ: %.1f %.1f %.1f", mc.player.getX(), mc.player.getY(), mc.player.getZ()));
        int y = 4;
        for (String s : lines) { ctx.drawTextWithShadow(mc.textRenderer, s, 4, y, 0xFFFFFFFF); y += 10; }
        int ry = 4;
        for (Module m : ModuleManager.all()) {
            if (!m.isEnabled() || m == this) continue;
            int w = mc.textRenderer.getWidth(m.name);
            ctx.drawTextWithShadow(mc.textRenderer, m.name, ctx.getScaledWindowWidth() - w - 4, ry, 0xFF55FFFF);
            ry += 10;
        }
    }
}
