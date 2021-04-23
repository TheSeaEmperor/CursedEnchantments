package com.emperornasch.cursedenchantments.setup;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;

public class ModItems {
    public static final RegistryObject<Item> TEST_OBJECT = Registration.ITEMS.register("test_object", () ->
            new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));

    static void register() {}
}
