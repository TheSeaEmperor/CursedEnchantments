package com.emperornasch.cursedenchantments.enchantments;

import com.emperornasch.cursedenchantments.CursedEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentType;
import net.minecraft.inventory.EquipmentSlotType;

public class CurseArdentFlames extends BasicEnchantment
{
    public CurseArdentFlames(String registryName)
    {
        super(registryName, Rarity.RARE, EnchantmentType.ARMOR, new EquipmentSlotType[]
                {EquipmentSlotType.FEET});
    }

    @Override
    public boolean isCurse()
    {
        return true;
    }
}
