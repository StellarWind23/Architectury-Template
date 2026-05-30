package com.github.stellarwind23.template.util;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.*;
import java.util.function.Function;

public record MBlock<T extends Block>(String modelType, Function<BlockBehaviour.Properties, T> blockConstructor, Optional<BlockBehaviour.Properties> feedInProps) {

    public static final List<MBlock<?>> MOD_BLOCKS = new ArrayList<>();
    public static final HashMap<MBlock<?>, Block> REGISTERED_MOD_BLOCKS = new HashMap<>();

    public MBlock {
        MOD_BLOCKS.add(this);
    }
}