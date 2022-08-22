package windmillbroken.trpgcraft.tier;

import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import windmillbroken.trpgcraft.item.TrpgCraftItems;

import java.util.function.Supplier;

/**
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-07-02-21:18
 * @Message: Have a good time!  :)
 **/
public enum ModItemTier implements Tier{
    //设置工具等级，此处参考MUGEN分级
    PAPER(2,18,6.0F,2.0F,99,() -> {
        return Ingredient.of(Items.PAPER);
    }),
    NORMAL(3,1561,8.0F,3.0F,10,() -> {
        return Ingredient.of(TrpgCraftItems.slaaneshSword.get());
    }),
    POWER(4,1991,10.0F,4.0F,14,() -> {
        return Ingredient.of(TrpgCraftItems.slaaneshSword.get());
    }),
    FIERCE(5,2077,12.0F,6.0F,18,() -> {
        return Ingredient.of(TrpgCraftItems.slaaneshSword.get());
    }),
    INSANE(6,1024,16.0F,10.0F,22,() -> {
        return Ingredient.of(TrpgCraftItems.slaaneshSword.get());
    }),
    QUASI_GOD(7,9999,20.0F,14.0F,5,() -> {
        return Ingredient.of(TrpgCraftItems.slaaneshSword.get());
    }),
    GOD(99,32768,20.0F,1023.0F,1,() -> {
        return Ingredient.of(TrpgCraftItems.slaaneshSword.get());
    }),
    EXTRA(-10,99,-10.0F,-10.0F,0,() -> {
        return Ingredient.of(TrpgCraftItems.slaaneshSword.get());
    }),
    DICE(5,32768,0.0F,0.0F,20,() -> {
//        return Ingredient.of(DiceItems.DICE.get());
        return Ingredient.of(TrpgCraftItems.slaaneshSword.get());
    }),
    ;
    //挖掘等级
    private final int level;
    //耐久
    private final int uses;
    //挖掘速度
    private final float speed;
    //攻击伤害加成
    private final float damage;
    //附魔值，代表了武器能被附魔的能力
    private final int enchantmentValue;
    //合成
    private final LazyLoadedValue<Ingredient> repairIngredient;

    private ModItemTier(int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> ingredient) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = new LazyLoadedValue<>(ingredient);
    }

    @Override
    public int getUses() {
        return this.uses;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.damage;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

}