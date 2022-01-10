package com.zodiuxus.ensorcelledautomatons.registry;

import com.zodiuxus.ensorcelledautomatons.Utils;
import com.zodiuxus.ensorcelledautomatons.blocks.TableTuringBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    // Block ID registries go below this
    public static final Block TABLE_TURING = new TableTuringBlock(FabricBlockSettings.of(Material.METAL).strength(2.0f, 50.0f).breakByTool(FabricToolTags.PICKAXES).requiresTool().sounds(BlockSoundGroup.METAL));

    // Block registering
    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, Utils.getID("table_turing"), TABLE_TURING);
    }
}
