package com.github.stellarwind23.template.fabric.datagen;

import com.github.stellarwind23.template.fabric.datagen.provider.MEnglishUSLangProvider;
import com.github.stellarwind23.template.fabric.datagen.provider.MModelProvider;
import com.github.stellarwind23.template.fabric.datagen.provider.MParticleProvider;
import com.github.stellarwind23.template.init.TemplateInit;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.jspecify.annotations.NonNull;

public class TemplateFabricDatagen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(@NonNull FabricDataGenerator fabricDataGenerator) {
        //Create
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        //Add Providers
        pack.addProvider(MModelProvider::new);
        pack.addProvider(MEnglishUSLangProvider::new);
        pack.addProvider((FabricDataGenerator.Pack.Factory<MParticleProvider>) output -> new MParticleProvider(output, TemplateInit.MOD_ID));
    }
}
