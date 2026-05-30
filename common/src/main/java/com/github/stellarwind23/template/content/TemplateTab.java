package com.github.stellarwind23.template.content;

import com.github.stellarwind23.template.init.TemplateInit;
import com.github.stellarwind23.template.util.DeferredItem;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class TemplateTab {

    protected static DeferredRegister<CreativeModeTab> TABS;

    public static RegistrySupplier<CreativeModeTab> TAB;

    public static void init(){

        TABS = DeferredRegister.create(TemplateInit.MOD_ID, Registries.CREATIVE_MODE_TAB);

        //Register stuff here ▼▼▼

        TAB = registerTab(CreativeTabRegistry.create(Component.translatable("tab." + TemplateInit.MOD_ID + ".main"), () -> new ItemStack(new DeferredItem<>(TemplateItems.TEMPLATE_BLOCK))));

        //Register stuff here ▲▲▲

        TABS.register();
    }

    private static RegistrySupplier<CreativeModeTab> registerTab(CreativeModeTab tab) {
        return TABS.register("main", () -> tab);
    }
}
