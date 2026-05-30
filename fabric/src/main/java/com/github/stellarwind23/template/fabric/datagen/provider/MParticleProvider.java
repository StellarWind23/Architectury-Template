package com.github.stellarwind23.template.fabric.datagen.provider;

import com.github.stellarwind23.template.util.MParticle;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import org.jspecify.annotations.NonNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MParticleProvider implements DataProvider {

    private final FabricDataOutput output;
    private final String modid;
    private final List<String> particles = new ArrayList<>();

    public MParticleProvider(FabricDataOutput output, String modid) {
        this.output = output;
        this.modid = modid;
    }

    public void init() {
        for(MParticle<?> mParticle : MParticle.MOD_PARTICLES) {
            generateParticle(mParticle.name());
        }
    }

    @Override
    public @NonNull CompletableFuture<?> run(@NonNull CachedOutput cachedOutput) {
        init();

        List<CompletableFuture<?>> futures = new ArrayList<>();

        for(String particle : particles) {
            Path path = output.getOutputFolder().resolve("assets/particles/" + modid + "/" + particle + ".json");

            JsonObject json = new JsonObject();
            JsonArray textures = new JsonArray();

            textures.add(modid + ":" + particle);
            json.add("textures", textures);

            futures.add(DataProvider.saveStable(cachedOutput, json, path));
        }

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @Override
    public @NonNull String getName() {
        return "Particles";
    }

    public void generateParticle(String name) {
        particles.add(name);
    }
}
