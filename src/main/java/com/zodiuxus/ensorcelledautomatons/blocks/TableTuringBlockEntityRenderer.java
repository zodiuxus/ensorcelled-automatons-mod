package com.zodiuxus.ensorcelledautomatons.blocks;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.item.ItemStack;

public class TableTuringBlockEntityRenderer extends BlockEntityRenderer<TableTuringBlockEntity> {

    private float countdown = 1000;
    public TableTuringBlockEntityRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(TableTuringBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        entity.setOffset(entity.getOffset() + tickDelta * 0.064D);
        ItemStack golem = entity.getGolemStack();
        matrices.push();

        matrices.translate(0.5D, 1 + Math.sin(entity.getOffset()) / 6D, 0.5D);

        if (entity.getCraftingBool()) {
            entity.setRotationSpeed(entity.getRotationSpeed() + tickDelta*0.000443342123d);
            double test = ((entity.getWorld().getTime() * ((entity.getRotationSpeed()*entity.getRotationSpeed())*0.6d))%360d);
            matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((float) test));
            countdown -= tickDelta;
            if (countdown <= 0) {
                entity.setRotationSpeed(1f);
                entity.setCraftingBool(false);
                countdown = 1000;
            }
        }
        else {
            matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((int) ((entity.getWorld().getTime() + tickDelta))));
        }

        MinecraftClient.getInstance().getItemRenderer().renderItem(golem, ModelTransformation.Mode.GROUND, light, overlay, matrices, vertexConsumers);
        matrices.pop();
    }
}
