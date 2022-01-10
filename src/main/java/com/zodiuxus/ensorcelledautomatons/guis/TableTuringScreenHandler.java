package com.zodiuxus.ensorcelledautomatons.guis;

import com.zodiuxus.ensorcelledautomatons.registry.ScreenHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class  TableTuringScreenHandler extends ScreenHandler {

    private final Inventory inventory;

    public TableTuringScreenHandler(int syncID, PlayerInventory pi) {
        this(syncID, pi, new SimpleInventory(6));
    }

    public TableTuringScreenHandler(int syncId, PlayerInventory pi, Inventory inv) {
        super(ScreenHandlerRegistry.sct, syncId);
        this.inventory = inv;
        this.addSlot(new Slot(this.inventory,0, 80, 24)); //head
        this.addSlot(new Slot(this.inventory,1, 80, 60)); //body
        this.addSlot(new Slot(this.inventory,2, 49, 43)); //left arm
        this.addSlot(new Slot(this.inventory,3, 111,43)); //right arm
        this.addSlot(new Slot(this.inventory,4, 62, 93)); //left leg
        this.addSlot(new Slot(this.inventory,5, 98, 93)); //right leg

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 3; j++) {
                this.addSlot(new Slot(pi, i+j*9+9, 8+i*18, 134+j*18));
            }
        }
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(pi, i, 8+i*18, 192));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int clickedSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(clickedSlot);
        if (slot != null && slot.hasStack()) {
            int invSize = this.inventory.size();
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            //If the clicked slot is inside the block inventory
            if (clickedSlot < invSize) {
                //Transfer it to the player
                if (!this.insertItem(originalStack, invSize, this.slots.size(), false)) {
                    return ItemStack.EMPTY;
                }
            //If the clicked slot is inside the player inventory, attempt to insert in the block inventory
            } else if (!this.insertItem(originalStack, 0, invSize - 1, false)) {
                //If we can't insert it in the block inventory, then attempt to insert from inventory to hotbar
                if (clickedSlot >= invSize && clickedSlot < this.slots.size() - 9) {
                    if (!this.insertItem(originalStack, this.slots.size() - 9, this.slots.size(), false)) {
                        return ItemStack.EMPTY;
                    }
                //Hotbar to player inventory
                } else if (clickedSlot >= this.slots.size() - 9) {
                    if (!this.insertItem(originalStack, invSize, this.slots.size() - 9, false)) {
                        return ItemStack.EMPTY;
                    }
                } else {
                    return ItemStack.EMPTY;
                }
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            if (originalStack.getCount() == newStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTakeItem(player, originalStack);
        }

        return newStack;

    }

}
