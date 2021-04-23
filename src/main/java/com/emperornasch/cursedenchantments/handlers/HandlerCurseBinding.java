package com.emperornasch.cursedenchantments.handlers;

import com.emperornasch.cursedenchantments.CursedEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.NonNullList;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;

import java.util.List;

public class HandlerCurseBinding {

    private static EquipmentSlotType armor[] = {
            EquipmentSlotType.HEAD,
            EquipmentSlotType.CHEST,
            EquipmentSlotType.LEGS,
            EquipmentSlotType.FEET
    };
    public static void handlerPlayerTick(PlayerTickEvent event) {
        PlayerEntity player = event.player;
        NonNullList<ItemStack> playerInv = player.inventory.items;
        if(player instanceof PlayerEntity)
        {
            for(ItemStack stack: playerInv)
            {
                if(EnchantmentHelper.hasBindingCurse(stack))
                {
                    for(EquipmentSlotType slotType: armor)
                    {
                        if(stack.canEquip(slotType, player))
                        {
                            Equip(player, stack, slotType);
                            break;
                        }
                    }
                }
            }
        }
    }

    private static void Equip(PlayerEntity player, ItemStack stack, EquipmentSlotType slotType) {

        if(player.getItemBySlot(slotType).isEmpty()) {
            ItemStack temp = stack.copy();
            player.setItemSlot(slotType, temp);
            player.inventory.removeItem(stack);
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

    public static void handlerPlayerContainer(PlayerContainerEvent event)
    {
        PlayerEntity player = event.getPlayer();
        ItemStack slotItem;

        if(player instanceof PlayerEntity)
        {
            if(event.getContainer() instanceof ChestContainer)
            {
                ChestContainer container = (ChestContainer) event.getContainer();
                List<Slot> containerSlots = container.slots;

                for(int i = 0; i < containerSlots.size(); i++)
                {
                    Slot currentSlot = containerSlots.get(i);

                    if(currentSlot.hasItem())
                    {
                        slotItem = currentSlot.getItem();

                        if(EnchantmentHelper.hasBindingCurse(slotItem))
                        {
                            for(EquipmentSlotType slotType: armor)
                            {
                                if(slotItem.canEquip(slotType, player))
                                {
                                    if(!EnchantmentHelper.hasBindingCurse(player.getItemBySlot(slotType)))
                                    {
                                        ItemStack item = slotItem.copy();
                                        currentSlot.set(ItemStack.EMPTY);
                                        player.addItem(item);
                                        Equip(player, item, slotType);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
