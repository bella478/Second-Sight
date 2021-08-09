package com.bella.secondsight;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.kyrptonaught.customportalapi.CustomPortalApiRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BiomeAdditionsSound;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class SecondSight implements ModInitializer {

    private static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> VOID_REMNANTS_BUILDER = SurfaceBuilder.DEFAULT.withConfig(
        new TernarySurfaceConfig(
            Blocks.BASALT.getDefaultState(),
            Blocks.SOUL_SAND.getDefaultState(),
            Blocks.GRAVEL.getDefaultState()
        )
    );

    private static final Biome VOID_REMNANTS = createVoidRemnants();

    private static Biome createVoidRemnants() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        spawnSettings.creatureSpawnProbability(0);
        DefaultBiomeFeatures.addMonsters(spawnSettings, 0, 0, 0);

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
                .particleConfig(new BiomeParticleConfig(ParticleTypes.PORTAL, 0.01f))
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

    public static final Block VOID_STONE = new Block(FabricBlockSettings.of(Material.METAL).strength(4.0f));

    @Override
    public void onInitialize() {
        registerBlocks();
        registerItems();
        registerPortals();
        registerBiomes();
    }

    public void registerBlocks() {
        Registry.register
        (Registry.BLOCK,
            new Identifier("secondsight", "void_stone"),
            VOID_STONE
        );
    }

    public void registerItems() {
        Registry.register
        (
            Registry.ITEM,
            new Identifier("secondsight", "void_stone"),
            new BlockItem(VOID_STONE, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS))
        );
    }

    public void registerPortals() {
        CustomPortalApiRegistry.addPortal(Blocks.GOLD_BLOCK, new Identifier("secondsight", "void"), 234, 183, 8);
    }

    public void registerBiomes() {
        Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new Identifier("secondsight", "void_remnants_builder"), VOID_REMNANTS_BUILDER);
        Registry.register(BuiltinRegistries.BIOME, VOID_REMNANTS_KEY.getValue(), VOID_REMNANTS);
    }
}
