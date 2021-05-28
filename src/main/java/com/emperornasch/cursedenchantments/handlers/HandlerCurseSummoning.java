package com.emperornasch.cursedenchantments.handlers;

import com.emperornasch.cursedenchantments.enchantments.CurseSummoning;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.EndermiteEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.RegistryManager;

import net.minecraft.entity.Entity;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.emperornasch.cursedenchantments.setup.ModEnchantments.CURSE_SUMMONING;

public class HandlerCurseSummoning {

    public static List<EntityType<?>> monsterList = new ArrayList<>();
    public static int tickTimer = 0;
    public static int maxTime = 100;
    public static boolean cooldown = false;
    //public static List<EntityType<?>> filteredList = new ArrayList<>();

    public static void handlerPlayerTick(PlayerTickEvent event)
    {
        World world = event.player.level;
        LivingEntity player = event.player;

        ItemStack playerHelmet = player.getItemBySlot(EquipmentSlotType.HEAD);
        int enchlvl = EnchantmentHelper.getItemEnchantmentLevel(CURSE_SUMMONING, playerHelmet);
        if(enchlvl == 0){tickTimer = 0; return;}

        if(cooldown){
            tickTimer++;
            if(tickTimer == maxTime){
                cooldown = false;
                tickTimer = 0;
            }
        }

        if(!cooldown) {
            summonRandomMob(world, player);
            cooldown = true;
        }
    }

    private static void summonRandomMob(World world, LivingEntity player) {
        if(monsterList.isEmpty()){
            //monsterList.addAll(ForgeRegistries.ENTITIES.getValues());
            //Predicate<EntityType<?>> byMonster = entityType -> entityType.getCategory().equals(EntityClassification.MONSTER);
            //filteredList = monsterList.stream().filter(byMonster).collect(Collectors.toList());
            monsterList = ForgeRegistries.ENTITIES.getValues().stream().filter(entityType ->
                    entityType.getCategory().equals(EntityClassification.MONSTER)
                            && !entityType.getRegistryName().toString().contains("ender_dragon")
                            && !entityType.getRegistryName().toString().contains("wither")).collect(Collectors.toList());
            /*
            for(EntityType<?> entity : monsterList)
            {
                if(entity.getRegistryName().toString() == "ender_dragon"
                        ||entity.getRegistryName().toString() == "wither" ){
                    monsterList.remove(entity);
                }

            }*/

        }

        Entity entity = monsterList.get((int)Math.floor(Math.random()*(monsterList.size() - 1))).create(world);
        if(entity != null){
            entity.setPos(player.position().x - 1, player.position().y, player.position().z - 1);
            world.addFreshEntity(entity);
        }
    }
}
