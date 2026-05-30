package com.github.stellarwind23.template.content;

import com.github.stellarwind23.template.init.TemplateInit;
import com.github.stellarwind23.template.util.MItem;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import java.util.Objects;
import java.util.Optional;

public class TemplateItems {

    protected static DeferredRegister<Item> ITEMS;

    public static RegistrySupplier<Item> TEMPLATE_ITEM;
    public static RegistrySupplier<BlockItem> TEMPLATE_BLOCK;

    public static void init() {

        ITEMS = DeferredRegister.create(TemplateInit.MOD_ID, Registries.ITEM);

        //Register stuff here ▼▼▼

        TEMPLATE_BLOCK = registerItem("template_block", new MItem<>("block", props -> new BlockItem(TemplateBlocks.TEMPLATE_BLOCK.get(), props), Optional.empty()));
        TEMPLATE_ITEM = registerItem("template_item", new MItem<>("simple", Item::new, Optional.empty()));

        //Register stuff here ▲▲▲

        ITEMS.register();
    }

    @SuppressWarnings("UnstableApiUsage")
    private static <T extends Item> RegistrySupplier<T> registerItem(String name, MItem<T> mItem) {
        Identifier id = Identifier.fromNamespaceAndPath(TemplateInit.MOD_ID, name);
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, id);

        T item;
        if(mItem.feedInProps().isPresent()) {
            item = mItem.itemConstructor().apply(mItem.feedInProps().orElseThrow().setId(key).arch$tab(TemplateTab.TAB));
        } else {
            item = mItem.itemConstructor().apply(new Item.Properties().setId(key).arch$tab(TemplateTab.TAB));
        }

        //Generate model if not block
        if(!mItem.modelType().equals("block")) MItem.REGISTERED_MOD_ITEMS.put(mItem, item);

        return ITEMS.register(id, () -> item);
    }
}
