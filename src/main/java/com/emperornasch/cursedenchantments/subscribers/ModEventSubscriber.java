package com.emperornasch.cursedenchantments.subscribers;

import com.emperornasch.cursedenchantments.CursedEnchantments;
import com.emperornasch.cursedenchantments.enchantments.BasicEnchantment;
import com.emperornasch.cursedenchantments.enchantments.CurseArdentFlames;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;
import java.util.ArrayList;

@EventBusSubscriber(modid = CursedEnchantments.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {

    @SubscribeEvent
    public static void registerEnchantments(RegistryEvent.Register<Enchantment> event)
    {
        final IForgeRegistry<Enchantment> registry = event.getRegistry();
        ArrayList<BasicEnchantment> enchantments = new ArrayList<>();

        enchantments.add(new CurseArdentFlames("curse_ardentflames"));

        enchantments.forEach((e) ->{
            registry.register((setup(e, e.getRegistry())));
        });
    }

    @Nonnull
    private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final String name)
    {
        return setup(entry, new ResourceLocation(CursedEnchantments.MOD_ID, name));
    }

    @Nonnull
    private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final ResourceLocation registryName)
    {
        entry.setRegistryName(registryName);
        return entry;
    }
}
