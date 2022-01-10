package com.zodiuxus.ensorcelledautomatons.registry;

import com.zodiuxus.ensorcelledautomatons.blocks.TableTuringBlockEntityRenderer;

public class BlockEntityRendererRegistry {
    public static void Register() {
        net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry.INSTANCE.register(ModBlockEntity.TABLE_TURING, TableTuringBlockEntityRenderer::new);
    }
}
