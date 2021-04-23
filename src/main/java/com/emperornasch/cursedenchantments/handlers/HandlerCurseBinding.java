package com.emperornasch.cursedenchantments.handlers;

import com.emperornasch.cursedenchantments.CursedEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.NonNullList;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;

public class HandlerCurseBinding {

    private static EquipmentSlotType armor[] = {
            EquipmentSlotType.HEAD,
            EquipmentSlotType.CHEST,
            EquipmentSlotType.LEGS,
            EquipmentSlotType.FEET
    };
    public static void handlerPlayerTick(PlayerTickEvent event) {
        PlayerEntity player = event.player;
        NonNullList<ItemStack> inv = player.inventory.items;
        if(player instanceof PlayerEntity)
        {
            for(ItemStack stack: player.inventory.items)
            {
                if(EnchantmentHelper.hasBindingCurse(stack))
                {
                    for(EquipmentSlotType slotType: armor)
                    {
                        if(stack.canEquip(slotType, player))
                        {
                            if(player.getItemBySlot(slotType).isEmpty()) {
                                ItemStack temp = stack.copy();
                                player.setItemSlot(slotType, temp);
                                player.inventory.removeItem(stack);
                                break;


                            }
                            else
                            {
                                if(!EnchantmentHelper.hasBindingCurse(player.getItemBySlot(slotType)))
                                {
                                    ItemStack nonBoundArmor = player.getItemBySlot(slotType);
                                    ItemStack temp = stack.copy();
                                    player.setItemSlot(slotType, temp);
                                    player.addItem(nonBoundArmor);
                                    player.inventory.removeItem(stack);
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}
