package com.github.stellarwind23.template.util;

import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public record MItem<T extends Item>(String modelType, Function<Item.Properties, T> itemConstructor, Optional<Item.Properties> feedInProps) {

    public static final List<MItem<?>> MOD_ITEMS = new ArrayList<>();
    public static final HashMap<MItem<?>, Item> REGISTERED_MOD_ITEMS = new HashMap<>();

    public MItem {
        MOD_ITEMS.add(this);
    }
}
