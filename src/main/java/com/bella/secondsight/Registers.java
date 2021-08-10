package com.bella.secondsight;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.kyrptonaught.customportalapi.CustomPortalApiRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;

public class Registers {

    public static void registerBlocks() {
        Registry.register
                (Registry.BLOCK,
                        new Identifier("secondsight", "void_grass"),
                        BlockDefinitions.VOID_GRASS
                );
    }

    public static void registerItems() {
        Registry.register
                (
                        Registry.ITEM,
                        new Identifier("secondsight", "void_grass"),
                        new BlockItem(BlockDefinitions.VOID_GRASS, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS))
                );
    }

    public static void registerPortals() {
        CustomPortalApiRegistry.addPortal(Blocks.GOLD_BLOCK, new Identifier("secondsight", "void"), 234, 183, 8);
    }

    public static void registerBiomes() {
        Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new Identifier("secondsight", "void_remnants_builder"), Biomes.VOID_REMNANTS_BUILDER);
        Registry.register(BuiltinRegistries.BIOME, Biomes.VOID_REMNANTS_KEY.getValue(), Biomes.VOID_REMNANTS);
    }

    public static void registerAll() {
        registerBlocks();
        registerItems();
        registerPortals();
        registerBiomes();
    }
}
