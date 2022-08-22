package windmillbroken.trpgcraft.event;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import windmillbroken.trpgcraft.capability.api.SkillCapability;
import windmillbroken.trpgcraft.capability.api.StandAttributeCapability;
import windmillbroken.trpgcraft.capability.provider.EntitySkillProvider;
import windmillbroken.trpgcraft.capability.provider.PlayerSkillProvider;
import windmillbroken.trpgcraft.bean.dice.DiceImpl;
import windmillbroken.trpgcraft.entity.PuppetEntity;
import windmillbroken.trpgcraft.item.weapons.DiceColdWeapon;
import windmillbroken.trpgcraft.bean.dice.Dice;
import windmillbroken.trpgcraft.bean.dice.DiceTypeEnum;
import windmillbroken.trpgcraft.util.AttributeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-07-29-14:50
 * @Message: Have a good time!  :)
 **/
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class TrpgCraftAttackEvent {

    @SubscribeEvent
    public static void changeDiceWeaponAttack(ItemAttributeModifierEvent event){
        ItemStack itemStack = event.getItemStack();
        if(itemStack.getItem() instanceof DiceColdWeapon){
        }

    }

    @SubscribeEvent
    public static void flushDiceWeaponAttack(AttackEntityEvent event){
        ItemStack itemStack = event.getPlayer().getItemInHand(InteractionHand.MAIN_HAND);
        if(itemStack.getItem() instanceof DiceColdWeapon){

        }


    }

    @SubscribeEvent
    public static void hurtEntity(LivingHurtEvent event){
        float amount = 0;
        List<Dice> db = new ArrayList<>();
        LivingEntity entityBeAttack = event.getEntityLiving();
        DamageSource dmg = event.getSource();
        if (dmg instanceof EntityDamageSource edmg
                && !(edmg instanceof IndirectEntityDamageSource)
                && edmg.getEntity() instanceof LivingEntity attacker
                && getAttributeCap(attacker).isPresent()){
            //伤害加深处理
            if (getAttributeCap(attacker).map(StandAttributeCapability::getStr).orElse(0) > 0
                    && getAttributeCap(attacker).map(StandAttributeCapability::getSiz).orElse(0) > 0
            ){
                int str = getAttributeCap(attacker).map(StandAttributeCapability::getStr).orElse(0);
                int siz = getAttributeCap(attacker).map(StandAttributeCapability::getSiz).orElse(0);
                db = AttributeUtils.buildToDb(AttributeUtils.getBuild(str, siz));
            }
            ItemStack stack = attacker.getItemInHand(InteractionHand.MAIN_HAND);
            //近战武器处理/玩家空手基础伤害
            if (stack.getItem() instanceof DiceColdWeapon weapon){
                if (weapon.getDamageListSize(stack)>0){
                    amount += weapon.rollDamage(stack);
                    amount += (int)(db.stream().mapToInt(Dice::roll).sum()*weapon.getDbRatio());
                }
            }else if (stack.isEmpty() && attacker instanceof Player){
                Dice dice = new DiceImpl(3, DiceTypeEnum.NORMAL);
                amount += dice.roll();
                amount += db.stream().mapToInt(Dice::roll).sum();
            }
            event.setAmount(amount);
        }
    }
    private static Optional<StandAttributeCapability> getAttributeCap(LivingEntity entity){
        if (entity instanceof Player player){
            return player.getCapability(PlayerSkillProvider.STAND_ATTRIBUTE_CAPABILITY).resolve();
        }else if (entity instanceof PuppetEntity){
            return entity.getCapability(EntitySkillProvider.STAND_ATTRIBUTE_CAPABILITY).resolve();
        }
        return Optional.empty();
    }

    private static Optional<SkillCapability> getSkillCap(LivingEntity entity){
        if (entity instanceof Player player){
            return player.getCapability(PlayerSkillProvider.SKILL_CAPABILITY).resolve();
        }else if (entity instanceof PuppetEntity){
            return entity.getCapability(EntitySkillProvider.SKILL_CAPABILITY).resolve();
        }
        return Optional.empty();
    }
}
