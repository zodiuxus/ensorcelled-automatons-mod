package com.zodiuxus.ensorcelledautomatons.registry;

import com.zodiuxus.ensorcelledautomatons.Utils;
import com.zodiuxus.ensorcelledautomatons.blocks.TableTuringBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class ModBlockEntity {

    public static final BlockEntityType<TableTuringBlockEntity> TABLE_TURING = Registry.register(Registry.BLOCK_ENTITY_TYPE,
            Utils.getID("table_turing_entity"), BlockEntityType.Builder.create(TableTuringBlockEntity::new, ModBlocks.TABLE_TURING).build(null));

}
