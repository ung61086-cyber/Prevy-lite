package dev.mrdon.client.module;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

/** Client-side night vision for rendering only; the server is never told about it. */
public final class FullbrightModule extends Module {
    public FullbrightModule() { super("Fullbright", "See clearly in the dark"); }

    @Override public void onTick(MinecraftClient mc) {
        if (mc.player != null) {
            mc.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 400, 0, false, false, false));
        }
    }

    @Override protected void onDisable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player != null) mc.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
    }
}
