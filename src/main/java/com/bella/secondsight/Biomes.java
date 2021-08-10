package com.bella.secondsight;

import net.minecraft.block.Blocks;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BiomeAdditionsSound;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class Biomes {
    public static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> VOID_REMNANTS_BUILDER = SurfaceBuilder.DEFAULT.withConfig(
        new TernarySurfaceConfig(
            BlockDefinitions.VOID_GRASS.getDefaultState(),
            Blocks.SOUL_SAND.getDefaultState(),
            Blocks.GRAVEL.getDefaultState()
        )
    );

    public static final Biome VOID_REMNANTS = createVoidRemnants();

    private static Biome createVoidRemnants() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        spawnSettings.creatureSpawnProbability(0);

        GenerationSettings.Builder generatorSettings = new GenerationSettings.Builder();
        generatorSettings.surfaceBuilder(VOID_REMNANTS_BUILDER);

        return (new Biome.Builder())
            .precipitation(Biome.Precipitation.NONE)
            .category(Biome.Category.NONE)
            .depth(0.1f)
            .scale(0.1f)
            .temperature(0.0f)
            .downfall(0.0f)
            .effects(new BiomeEffects.Builder()
                .fogColor(0)
                .waterColor(0)
                .waterFogColor(0)
                .skyColor(0)
                .particleConfig(new BiomeParticleConfig(ParticleTypes.WHITE_ASH, 0.01f))
                .loopSound(new SoundEvent(new Identifier("minecraft", "ambient.crimson_forest.loop")))
                .additionsSound(new BiomeAdditionsSound(new SoundEvent(new Identifier("minecraft", "ambient.crimson_forest.additions")), 0.0111f))
                .music(new MusicSound(new SoundEvent(new Identifier("minecraft", "music.nether.crimson_forest")), 0, 0, true))
                .moodSound(new BiomeMoodSound(new SoundEvent(new Identifier("minecraft", "ambient.crimson_forest.mood")), 6000, 8, 2.0f))
                .build()
            )
            .spawnSettings(spawnSettings.build())
            .generationSettings(generatorSettings.build())
            .build();
    }

    public static final RegistryKey<Biome> VOID_REMNANTS_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("secondsight", "void_remnants"));
}
