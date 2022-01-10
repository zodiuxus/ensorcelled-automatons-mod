package com.zodiuxus.ensorcelledautomatons.registry;

import com.zodiuxus.ensorcelledautomatons.EnsorcelledAutomatons;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    // Item IDs and their groups go below this
    public static final Item TOOL_AUTOMATON = new Item(new Item.Settings().group(ItemGroup.TOOLS));
    public static final Item GOLEM = new Item(new Item.Settings().group(ItemGroup.COMBAT));

    // Block IDs and their groups go below this
    public static final BlockItem TABLE_TURING = new BlockItem(ModBlocks.TABLE_TURING, new Item.Settings().group(ItemGroup.DECORATIONS));

    // I really wish I could remove the need to manually add every single item with a single loop
    // but I'm a lazy bumfuck

    // Item registering
    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(EnsorcelledAutomatons.MOD_ID, "tool_automaton"), TOOL_AUTOMATON);
        Registry.register(Registry.ITEM, new Identifier(EnsorcelledAutomatons.MOD_ID, "golem"), GOLEM);
        Registry.register(Registry.ITEM, new Identifier(EnsorcelledAutomatons.MOD_ID, "table_turing"), TABLE_TURING);
    }
}
