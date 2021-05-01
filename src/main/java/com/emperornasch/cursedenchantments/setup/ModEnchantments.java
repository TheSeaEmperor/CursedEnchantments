package com.emperornasch.cursedenchantments.setup;

import com.emperornasch.cursedenchantments.CursedEnchantments;
import com.emperornasch.cursedenchantments.enchantments.CurseArdentFlames;
import com.emperornasch.cursedenchantments.utils.ModUtils;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(CursedEnchantments.MOD_ID)
public final class ModEnchantments {

    public static final Enchantment CURSE_ARDENTFLAMES = ModUtils._null();

}
