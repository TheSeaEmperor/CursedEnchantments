package com.emperornasch.cursedenchantments.enchantments;

import com.emperornasch.cursedenchantments.interfaces.IEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class BasicEnchantment extends Enchantment implements IEnchantment {

    String registryName;

    protected BasicEnchantment(String registryName, Rarity rarityIn, EnchantmentType typeIn, EquipmentSlotType[] slots)
    {
        super(rarityIn, typeIn, slots);
        this.registryName = registryName;
    }

    @Override
    public String getRegistry()
    {
        return registryName;
    }
}
