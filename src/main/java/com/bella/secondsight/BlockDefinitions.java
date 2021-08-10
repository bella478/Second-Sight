package com.bella.secondsight;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;

public class BlockDefinitions {
    public static final Block VOID_GRASS = new Block(
            FabricBlockSettings.of(Material.SOIL)
                    .strength(0.6f)
                    .resistance(0.6f)
                    .luminance(0).sounds(BlockSoundGroup.GRASS)
                    .breakByTool(FabricToolTags.SHOVELS)
    );
}
