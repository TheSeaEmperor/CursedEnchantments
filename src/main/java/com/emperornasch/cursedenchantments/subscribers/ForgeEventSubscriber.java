package com.emperornasch.cursedenchantments.subscribers;

import com.emperornasch.cursedenchantments.CursedEnchantments;
import com.emperornasch.cursedenchantments.handlers.HandlerCurseArdentFlames;
import com.emperornasch.cursedenchantments.handlers.HandlerCurseBinding;
import com.emperornasch.cursedenchantments.handlers.HandlerCurseSummoning;
import net.minecraft.command.arguments.NBTCompoundTagArgument;
import net.minecraft.command.arguments.NBTTagArgument;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import sun.security.util.Debug;

import java.util.logging.LogManager;
import java.util.logging.Logger;


@EventBusSubscriber(modid = CursedEnchantments.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public class ForgeEventSubscriber {

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public static void onPlayerTick(PlayerTickEvent event) {
        PlayerEntity player = event.player;

        HandlerCurseBinding.handlerPlayerTick(event);
        HandlerCurseArdentFlames.handlerPlayerTick(event);
        HandlerCurseSummoning.handlerPlayerTick(event);
    }

    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public static void onPlayerContainer(PlayerContainerEvent event)
    {
        HandlerCurseBinding.handlerPlayerContainer(event);
    }

}
