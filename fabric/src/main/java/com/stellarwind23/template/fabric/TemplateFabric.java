package com.stellarwind23.template.fabric;

import com.stellarwind23.template.init.TemplateInit;
import net.fabricmc.api.ModInitializer;

public final class TemplateFabric implements ModInitializer {
    @Override
    public void onInitialize() {

        TemplateInit.init();
    }
}
