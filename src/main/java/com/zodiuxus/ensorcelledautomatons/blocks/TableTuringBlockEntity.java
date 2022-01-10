package com.zodiuxus.ensorcelledautomatons.blocks;

import com.zodiuxus.ensorcelledautomatons.guis.InventoryHandler;
import com.zodiuxus.ensorcelledautomatons.registry.ModBlockEntity;
import com.zodiuxus.ensorcelledautomatons.registry.ModItems;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.collection.DefaultedList;

public class TableTuringBlockEntity extends BlockEntity implements InventoryHandler, BlockEntityClientSerializable {

    private DefaultedList<ItemStack> inventory;
    private ItemStack storedGolem = ItemStack.EMPTY;
    private double rotationSpeed = 1;
    private double offset = 0;
    private boolean crafting = false;

    public TableTuringBlockEntity() {
        super(ModBlockEntity.TABLE_TURING);
        inventory = DefaultedList.ofSize(size(), ItemStack.EMPTY);
    }

    @Override
    public DefaultedList<ItemStack> getInventory() {
        return this.inventory;
    }

    //get from NBT Tag
    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        Inventories.fromTag(tag, this.inventory);
        this.storedGolem = ItemStack.fromTag(tag.getCompound("golem"));
        this.crafting = tag.getBoolean("isCrafting");
        this.rotationSpeed = tag.getDouble("rotationSpeed");
    }

    //set to NBT Tag
    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        Inventories.toTag(tag, this.inventory);
        CompoundTag itemTag = new CompoundTag();
        this.storedGolem.toTag(itemTag);
        tag.put("golem", itemTag);
        tag.putDouble("rotationSpeed", this.rotationSpeed);
        tag.putBoolean("isCrafting", this.crafting);
        return tag;
    }

    public void setGolem(ItemStack stack) {
        if (stack.getItem() == ModItems.GOLEM) {
            this.storedGolem = stack.copy();
            this.storedGolem.setCount(1);
        }
    }

    public void setRotationSpeed (double rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public double getRotationSpeed () {
        return this.rotationSpeed;
    }

    public void removeGolem() {
        this.storedGolem = ItemStack.EMPTY;
    }

    public void setOffset(double offset) {
        this.offset = offset;
    }

    public double getOffset() {
        return this.offset;
    }

    public void setCraftingBool(boolean crafting) {
        this.crafting = crafting;
    }

    public boolean getCraftingBool() {
        return this.crafting;
    }

    public ItemStack getGolemStack() {
        return this.storedGolem;
    }

    public boolean hasGolem() {
        return this.storedGolem.getItem() == ModItems.GOLEM;
    }

    @Override
    public int size() {
        return 6;
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        if (this.world.getBlockEntity(this.pos) != this) {
            return false;
        }
        return player.squaredDistanceTo(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64;
    }

    @Override
    public void fromClientTag(CompoundTag tag) {
        Inventories.fromTag(tag, this.inventory);
        this.rotationSpeed = tag.getDouble("rotationSpeed");
        this.crafting = tag.getBoolean("isCrafting");
        this.storedGolem = ItemStack.fromTag(tag.getCompound("golem"));
    }

    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
        Inventories.toTag(tag, this.inventory);
        CompoundTag itemTag = new CompoundTag();
        this.storedGolem.toTag(itemTag);
        tag.put("golem", itemTag);
        tag.putDouble("rotationSpeed", this.rotationSpeed);
        tag.putBoolean("isCrafting", this.crafting);
        return tag;
    }
}
