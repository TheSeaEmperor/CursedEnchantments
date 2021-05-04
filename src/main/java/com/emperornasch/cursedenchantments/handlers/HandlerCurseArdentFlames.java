package com.emperornasch.cursedenchantments.handlers;

import com.emperornasch.cursedenchantments.CursedEnchantments;
import com.emperornasch.cursedenchantments.enchantments.CurseArdentFlames;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent.WorldTickEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;

import javax.swing.text.html.parser.Entity;
import java.util.List;

import static com.emperornasch.cursedenchantments.setup.ModEnchantments.CURSE_ARDENTFLAMES;

public class HandlerCurseArdentFlames {
    public static void handlerPlayerTick(PlayerTickEvent event)
    {
        World worldInstance = event.player.level;
        LivingEntity player = event.player;
        List<LivingEntity> mobList;

        ItemStack playerBoots = player.getItemBySlot(EquipmentSlotType.FEET);
        int enchLvl = EnchantmentHelper.getItemEnchantmentLevel(CURSE_ARDENTFLAMES, playerBoots);
        if(enchLvl == 0) return;
        mobList = worldInstance.getNearbyEntities(LivingEntity.class, EntityPredicate.DEFAULT, player,
                player.getBoundingBox().inflate(5, 3, 5));
        if(mobList.size() != 0)
        {
            //EffectInstance effectInstance = new EffectInstance(Effects.)
            for(LivingEntity mob: mobList) {
                mob.setSecondsOnFire(5);
            }
        }


        //CursedEnchantments.LOGGER.info("There are " + mobList.size() + " mobs in the area.");
    }
}
