package com.github.stellarwind23.template.util;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.Optional;
import java.util.function.Function;

public record MBlock<T extends Block>(Function<BlockBehaviour.Properties, T> blockConstructor, Optional<BlockBehaviour.Properties> feedInProps) {

    public static int blockCount = 0;

    public MBlock {
        blockCount += 1;
    }
}