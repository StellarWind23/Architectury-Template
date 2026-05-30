package com.github.stellarwind23.template.util;

import net.minecraft.world.item.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public record MItem<T extends Item>(String itemName, String modelType, Function<Item.Properties, T> itemConstructor, Optional<Item.Properties> feedInProps) {

    public static List<MItem<?>> MOD_ITEMS;
    public static HashMap<MItem<?>, Item> REGISTERED_MOD_ITEMS = new HashMap<>();

    public MItem {
        MOD_ITEMS.add(this);
    }
}
