package com.github.stellarwind23.template.content;

import com.github.stellarwind23.template.client.object.MSimpleParticleType;
import com.github.stellarwind23.template.init.TemplateInit;
import com.github.stellarwind23.template.util.MParticle;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;

public class TemplateParticles {

    protected static DeferredRegister<ParticleType<?>> PARTICLE_TYPES;

    public static RegistrySupplier<MSimpleParticleType> TEMPLATE_PARTICLE;

    public static void init() {
        PARTICLE_TYPES = DeferredRegister.create(TemplateInit.MOD_ID, Registries.PARTICLE_TYPE);

        //Register stuff here ▼▼▼

        TEMPLATE_PARTICLE = registerParticle(new MParticle<>("template_particle", () -> new MSimpleParticleType(false)));

        //Register stuff here ▲▲▲

        PARTICLE_TYPES.register();
    }

    private static <T extends ParticleType<?>>RegistrySupplier<T> registerParticle(MParticle<T> mParticle) {
        return PARTICLE_TYPES.register(mParticle.name(), mParticle.particleSupplier());
    }
}
