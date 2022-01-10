package com.zodiuxus.ensorcelledautomatons.blocks;

import com.zodiuxus.ensorcelledautomatons.EnsorcelledAutomatons;
import com.zodiuxus.ensorcelledautomatons.guis.TableTuringScreenHandler;
import com.zodiuxus.ensorcelledautomatons.registry.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TableTuringBlock extends BlockWithEntity {

    private static final Text TITLE = new TranslatableText("container." + EnsorcelledAutomatons.MOD_ID + ".table_turing");

    public TableTuringBlock(Settings settings) {
        super(settings);
    }

    public static final VoxelShape bottom = VoxelShapes.cuboid(0 / 16f, 0 / 16f, 0 / 16f, 16 / 16f, 1 / 16f, 16 / 16f);
    public static final VoxelShape top = VoxelShapes.cuboid(2.5 / 16f, 9.4 / 16f, 2.5 / 16f, 13.5 / 16f, 10.7 / 16f, 13.5 / 16f);
    public static final VoxelShape rod = VoxelShapes.cuboid(7.5 / 16f, 1 / 16f, 7.5 / 16f, 8.8 / 16f, 10 / 16f, 8.8 / 16f);
    public static final VoxelShape end = VoxelShapes.union(bottom, top, rod).simplify();

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return end;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack heldItem = player.getStackInHand(hand);
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (!world.isClient && blockEntity instanceof TableTuringBlockEntity) {
            TableTuringBlockEntity tableBlockEntity = ((TableTuringBlockEntity) blockEntity);
            DefaultedList<ItemStack> crinj = tableBlockEntity.getInventory();
            if (heldItem.getItem() == ModItems.GOLEM && !tableBlockEntity.hasGolem()) {
                tableBlockEntity.setGolem(heldItem);
                heldItem.decrement(1);
                tableBlockEntity.markDirty();
                tableBlockEntity.sync();
                return ActionResult.SUCCESS;
            }
            else if (tableBlockEntity.hasGolem() && heldItem.isEmpty()) {
                player.setStackInHand(hand, tableBlockEntity.getGolemStack());
                tableBlockEntity.removeGolem();
                tableBlockEntity.markDirty();
                tableBlockEntity.sync();
                return ActionResult.SUCCESS;
            }

            else if (tableBlockEntity.hasGolem() && heldItem.getItem() == ModItems.TOOL_AUTOMATON && crinj.get(0).getItem() == Items.IRON_INGOT) {
                tableBlockEntity.setRotationSpeed(0.1f);
                tableBlockEntity.setCraftingBool(true);
                tableBlockEntity.markDirty();
                tableBlockEntity.sync();
                return ActionResult.SUCCESS;
            }
        }
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }
        NamedScreenHandlerFactory factory = state.createScreenHandlerFactory(world, pos);
        player.openHandledScreen(factory);
        return ActionResult.CONSUME;
    }

    @Nullable
    @Override
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return new SimpleNamedScreenHandlerFactory((syncId, playerInventory, player) -> new TableTuringScreenHandler(syncId, playerInventory, (TableTuringBlockEntity) world.getBlockEntity(pos)), TITLE);
    }

    //if block is broken, drop shit from inventory
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity entity = world.getBlockEntity(pos);
            if (entity instanceof TableTuringBlockEntity) {
                ItemScatterer.spawn(world, pos, (TableTuringBlockEntity)entity);
                Block.dropStack(world, pos, ((TableTuringBlockEntity) entity).getGolemStack());

            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public @Nullable
    BlockEntity createBlockEntity(BlockView world) {
        return new TableTuringBlockEntity();
    }

}