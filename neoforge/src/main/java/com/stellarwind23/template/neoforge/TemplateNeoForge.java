package com.stellarwind23.template.neoforge;

import com.stellarwind23.template.init.TemplateInit;
import net.neoforged.fml.common.Mod;

@Mod(TemplateInit.MOD_ID)
public final class TemplateNeoForge {
    public TemplateNeoForge() {
        // Run our common setup.
        TemplateInit.init();
    }
}
