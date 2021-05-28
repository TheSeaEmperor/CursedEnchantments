package com.emperornasch.cursedenchantments.enchantments;

import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class CurseSummoning extends BasicEnchantment{
    public CurseSummoning(String registryName) {
        super(registryName, Rarity.COMMON, EnchantmentType.ARMOR_HEAD, new EquipmentSlotType[]
                {EquipmentSlotType.HEAD});
    }

    @Override
    public boolean isCurse() { return true; }
}
