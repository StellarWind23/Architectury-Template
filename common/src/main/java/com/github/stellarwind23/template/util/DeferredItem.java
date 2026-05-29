package com.github.stellarwind23.template.util;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.NotNull;

public record DeferredItem<T extends Item>(RegistrySupplier<T> supplier) implements ItemLike {

    @Override
    public @NotNull Item asItem() {
        return supplier.get();
    }
}