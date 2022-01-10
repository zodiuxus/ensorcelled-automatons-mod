package com.zodiuxus.ensorcelledautomatons.guis;

import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public interface InventoryHandler extends Inventory {

    DefaultedList<ItemStack> getInventory();
    
    @Override
    default boolean isEmpty() {
        for (ItemStack stack : getInventory()) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    default ItemStack getStack(int slot) {
        return this.getInventory().get(slot);
    }

    @Override
    default ItemStack removeStack(int slot, int amount) {
        ItemStack result = Inventories.splitStack(this.getInventory(), slot, amount);
        if (!result.isEmpty()) {
            markDirty();
        }
        return result;
    }

    @Override
    default ItemStack removeStack(int slot) {
        return Inventories.removeStack(this.getInventory(), slot);
    }

    @Override
    default void setStack(int slot, ItemStack stack) {
        this.getInventory().set(slot, stack);
        if (stack.getCount() > getMaxCountPerStack()) {
            stack.setCount(getMaxCountPerStack());
        }
        markDirty();
    }

    @Override
    default void markDirty() {}

    @Override
    default void clear() {
        this.getInventory().clear();
    }


}
