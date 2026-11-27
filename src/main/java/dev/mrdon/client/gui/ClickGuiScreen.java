package dev.mrdon.client.gui;

import dev.mrdon.client.module.Module;
import dev.mrdon.client.module.ModuleManager;
import net.minecraft.client.gui.Click;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

/** Simple panel: click a row to toggle that module. */
public final class ClickGuiScreen extends Screen {
    private static final int X = 20, Y = 20, W = 140, ROW = 16;

    public ClickGuiScreen() { super(Text.literal("Prevy Lite")); }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        ctx.fill(X, Y, X + W, Y + ROW, 0xFF14202B);
        ctx.drawTextWithShadow(textRenderer, "Prevy Lite", X + 4, Y + 4, 0xFF55FFFF);
        int y = Y + ROW;
        for (Module m : ModuleManager.all()) {
            boolean hover = mouseX >= X && mouseX < X + W && mouseY >= y && mouseY < y + ROW;
            ctx.fill(X, y, X + W, y + ROW, hover ? 0xE0283846 : 0xE00B1219);
            ctx.drawTextWithShadow(textRenderer, m.name, X + 4, y + 4, m.isEnabled() ? 0xFF55FF55 : 0xFFAAAAAA);
            y += ROW;
        }
    }

    @Override
    public boolean mouseClicked(Click click, boolean doubled) {
        if (click.button() == 0 && click.x() >= X && click.x() < X + W) {
            int y = Y + ROW;
            for (Module m : ModuleManager.all()) {
                if (click.y() >= y && click.y() < y + ROW) { m.toggle(); return true; }
                y += ROW;
            }
        }
        return super.mouseClicked(click, doubled);
    }

    @Override public boolean shouldPause() { return false; }
}
