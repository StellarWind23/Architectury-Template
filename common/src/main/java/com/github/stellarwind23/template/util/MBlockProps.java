package com.github.stellarwind23.template.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;

@SuppressWarnings("unused")
public class MBlockProps {

    public Function<BlockState, MapColor> mapColor = (blockState) -> MapColor.NONE;
    boolean hasCollision = true;
    public SoundType soundType;
    public ToIntFunction<BlockState> lightEmission;
    public float explosionResistance = 0;
    public float destroyTime = 0;
    public boolean requiresCorrectToolForDrops;
    public boolean isRandomlyTicking;
    public float friction;
    public float speedFactor;
    public float jumpFactor;
    @Nullable public ResourceKey<Block> id;
    public boolean canOcclude;
    public boolean isAir;
    public boolean ignitedByLava;
    boolean forceSolidOn;
    boolean forceSolidOff;
    public PushReaction pushReaction;
    boolean spawnTerrainParticles;
    public NoteBlockInstrument instrument;
    public BlockBehaviour.StateArgumentPredicate<EntityType<?>> isValidSpawn;
    public BlockBehaviour.StatePredicate isRedstoneConductor;
    public BlockBehaviour.StatePredicate isSuffocating;
    public BlockBehaviour.StatePredicate isViewBlocking;
    public BlockBehaviour.StatePredicate hasPostProcess;
    public BlockBehaviour.StatePredicate emissiveRendering;
    public boolean dynamicShape;
    public List<FeatureFlag> requiredFeatures;

    @SuppressWarnings("deprecation")
    public MBlockProps() {
        this.soundType = SoundType.STONE;
        this.lightEmission = (blockState) -> 0;
        this.friction = 0.6F;
        this.speedFactor = 1.0F;
        this.jumpFactor = 1.0F;
        this.canOcclude = true;
        this.pushReaction = PushReaction.NORMAL;
        this.spawnTerrainParticles = true;
        this.instrument = NoteBlockInstrument.HARP;
        this.isValidSpawn = (blockState, blockGetter, blockPos, entityType) -> blockState.isFaceSturdy(blockGetter, blockPos, Direction.UP) && blockState.getLightEmission() < 14;
        this.isRedstoneConductor = BlockBehaviour.BlockStateBase::isCollisionShapeFullBlock;
        this.isSuffocating = (blockState, blockGetter, blockPos) -> blockState.blocksMotion() && blockState.isCollisionShapeFullBlock(blockGetter, blockPos);
        this.isViewBlocking = this.isSuffocating;
        this.hasPostProcess = (blockState, blockGetter, blockPos) -> false;
        this.emissiveRendering = (blockState, blockGetter, blockPos) -> false;
        this.requiredFeatures = List.of(FeatureFlags.VANILLA);
    }

    @SuppressWarnings("deprecation")
    public BlockBehaviour.Properties getCopy() {
        BlockBehaviour.Properties copy = BlockBehaviour.Properties.of();

        // Basic properties
        copy.mapColor(this.mapColor);
        copy.sound(this.soundType);
        copy.lightLevel(this.lightEmission);
        copy.friction(this.friction);
        copy.speedFactor(this.speedFactor);
        copy.jumpFactor(this.jumpFactor);
        copy.pushReaction(this.pushReaction);
        copy.instrument(this.instrument);
        copy.requiredFeatures(this.requiredFeatures.toArray(new FeatureFlag[0]));
        copy.strength(this.destroyTime, this.explosionResistance);

        // Collision / occlusion
        if (!this.hasCollision) copy.noCollision();
        if (!this.canOcclude) copy.noOcclusion();
        if (this.forceSolidOn) copy.forceSolidOn();
        if (this.forceSolidOff) copy.forceSolidOff();

        // Special flags
        if (this.requiresCorrectToolForDrops) copy.requiresCorrectToolForDrops();
        if (this.isRandomlyTicking) copy.randomTicks();
        if (this.isAir) copy.air();
        if (this.ignitedByLava) copy.ignitedByLava();
        if (this.dynamicShape) copy.dynamicShape();

        // Terrain / visuals
        if (!this.spawnTerrainParticles) copy.noTerrainParticles();
        copy.isValidSpawn(this.isValidSpawn);
        copy.isRedstoneConductor(this.isRedstoneConductor);
        copy.isSuffocating(this.isSuffocating);
        copy.isViewBlocking(this.isViewBlocking);
        copy.hasPostProcess(this.hasPostProcess);
        copy.emissiveRendering(this.emissiveRendering);

        return copy;
    }

    public static boolean always(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, EntityType<?> entityType) {return true;}
    public static boolean always(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) { return true; }
    public static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, EntityType<?> entityType) {return false;}
    public static boolean never(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) { return false; }

    public MBlockProps mapColor(DyeColor dyeColor) {
        this.mapColor = (blockState) -> dyeColor.getMapColor();
        return this;
    }

    public MBlockProps mapColor(MapColor mapColor) {
        this.mapColor = (blockState) -> mapColor;
        return this;
    }

    public MBlockProps instrument(NoteBlockInstrument noteBlockInstrument) {
        this.instrument = noteBlockInstrument;
        return this;
    }

    public MBlockProps mapColor(Function<BlockState, MapColor> function) {
        this.mapColor = function;
        return this;
    }

    public MBlockProps noCollision() {
        this.hasCollision = false;
        this.canOcclude = false;
        return this;
    }

    public MBlockProps noOcclusion() {
        this.canOcclude = false;
        return this;
    }

    public MBlockProps friction(float f) {
        this.friction = f;
        return this;
    }

    public MBlockProps speedFactor(float f) {
        this.speedFactor = f;
        return this;
    }

    public MBlockProps jumpFactor(float f) {
        this.jumpFactor = f;
        return this;
    }

    public MBlockProps sound(SoundType soundType) {
        this.soundType = soundType;
        return this;
    }

    public MBlockProps lightLevel(ToIntFunction<BlockState> toIntFunction) {
        this.lightEmission = toIntFunction;
        return this;
    }

    public MBlockProps strength(float f, float g) {
        return this.destroyTime(f).explosionResistance(g);
    }

    public MBlockProps instabreak() {
        return this.strength(0.0F);
    }

    public MBlockProps strength(float f) {
        this.strength(f, f);
        return this;
    }

    public MBlockProps strength(StrPair strPair) {
        this.strength(strPair.Strength1(), strPair.Strength2());
        return this;
    }

    public MBlockProps randomTicks() {
        this.isRandomlyTicking = true;
        return this;
    }

    public MBlockProps dynamicShape() {
        this.dynamicShape = true;
        return this;
    }

    public MBlockProps ignitedByLava() {
        this.ignitedByLava = true;
        return this;
    }

    public MBlockProps forceSolidOn() {
        this.forceSolidOn = true;
        return this;
    }

    public MBlockProps forceSolidOff() {
        this.forceSolidOff = true;
        return this;
    }

    public MBlockProps pushReaction(PushReaction pushReaction) {
        this.pushReaction = pushReaction;
        return this;
    }

    public MBlockProps air() {
        this.isAir = true;
        return this;
    }

    public MBlockProps isValidSpawn(BlockBehaviour.StateArgumentPredicate<EntityType<?>> stateArgumentPredicate) {
        this.isValidSpawn = stateArgumentPredicate;
        return this;
    }

    public MBlockProps isRedstoneConductor(BlockBehaviour.StatePredicate statePredicate) {
        this.isRedstoneConductor = statePredicate;
        return this;
    }

    public MBlockProps isSuffocating(BlockBehaviour.StatePredicate statePredicate) {
        this.isSuffocating = statePredicate;
        return this;
    }

    public MBlockProps isViewBlocking(BlockBehaviour.StatePredicate statePredicate) {
        this.isViewBlocking = statePredicate;
        return this;
    }

    public MBlockProps hasPostProcess(BlockBehaviour.StatePredicate statePredicate) {
        this.hasPostProcess = statePredicate;
        return this;
    }

    public MBlockProps emissiveRendering(BlockBehaviour.StatePredicate statePredicate) {
        this.emissiveRendering = statePredicate;
        return this;
    }

    public MBlockProps requiresCorrectToolForDrops() {
        this.requiresCorrectToolForDrops = true;
        return this;
    }

    public MBlockProps destroyTime(float f) {
        this.destroyTime = f;
        return this;
    }

    public MBlockProps explosionResistance(float f) {
        this.explosionResistance = Math.max(0.0F, f);
        return this;
    }
}