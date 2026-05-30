package com.github.stellarwind23.template.util;

import net.minecraft.core.particles.ParticleType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public record MParticle<T extends ParticleType<?>>(String name, Supplier<T> particleSupplier) {

    public static final List<MParticle<?>> MOD_PARTICLES = new ArrayList<>();

    public MParticle {
        MOD_PARTICLES.add(this);
    }
}
