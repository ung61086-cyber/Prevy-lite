package dev.mrdon.client.module;

import net.minecraft.client.MinecraftClient;
import java.util.List;

public final class ModuleManager {
    private static final List<Module> MODULES = List.of(new HudModule(), new FullbrightModule(), new ZoomModule());

    private ModuleManager() {}

    public static List<Module> all() { return MODULES; }

    public static void tick(MinecraftClient mc) {
        for (Module m : MODULES) {
            if (m.isEnabled()) m.onTick(mc);
        }
    }
}
