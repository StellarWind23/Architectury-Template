package com.stellarwind23.template.neoforge;

import com.stellarwind23.template.Template;
import net.neoforged.fml.common.Mod;

@Mod(Template.MOD_ID)
public final class TemplateNeoForge {
    public TemplateNeoForge() {
        // Run our common setup.
        Template.init();
    }
}
