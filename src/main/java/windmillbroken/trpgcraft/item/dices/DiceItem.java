package windmillbroken.trpgcraft.item.dices;

import net.minecraft.Util;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import windmillbroken.trpgcraft.capability.api.DiceCapability;
import windmillbroken.trpgcraft.capability.provider.DiceProvider;
import windmillbroken.trpgcraft.bean.dice.Dice;
import windmillbroken.trpgcraft.bean.dice.DiceImpl;
import windmillbroken.trpgcraft.bean.dice.DiceTypeEnum;
import windmillbroken.trpgcraft.sound.TrpgCraftSounds;
import windmillbroken.trpgcraft.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static windmillbroken.trpgcraft.capability.provider.DiceProvider.DICE_CAPABILITY;


/**
 *
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-07-08-22:16
 * @Message: 骰子的爹
 **/
public class DiceItem extends Item{


    public DiceItem(Item.Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new DiceProvider();

    }

    public Optional<DiceCapability> getCap(ItemStack stack){
        return stack.getCapability(DICE_CAPABILITY).resolve();
    }

    public int roll(ItemStack stack){
        return getCap(stack).map(DiceCapability::roll).orElse(0);
    }

    public void setDice(ItemStack stack, Dice dice){
        List<Dice> diceList = new ArrayList<>();
        diceList.add(dice);
        getCap(stack).ifPresent(cap->cap.setDice(diceList));
    }

    public Dice getDice(ItemStack stack){
        return getCap(stack).map(cap->cap.getDice().get(0)).orElse(null);
    }

    public DiceTypeEnum getDiceType(ItemStack stack){
        return getCap(stack).map(cap->cap.getDice().get(0).getType()).orElse(null);
    }

    public int getDiceValue(ItemStack stack){
        return getCap(stack).map(cap->cap.getDice().get(0).getDiceValue()).orElse(0);
    }

    public Dice popDice(ItemStack stack){
        return getCap(stack).map(cap->cap.popDice()).orElse(null);
    }

    public int getDiceValueMax(ItemStack stack){
        return getCap(stack).map(DiceCapability::getDiceValueMax).orElse(0);
    }
    public int getDiceValueMin(ItemStack stack){
        return getCap(stack).map(DiceCapability::getDiceValueMin).orElse(0);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, @NotNull InteractionHand interactionHand) {
        MinecraftServer server = world.getServer();
        ItemStack stack = player.getItemInHand(interactionHand);
        world.playSound((Player)null, player.getX(), player.getY(), player.getZ(),
                TrpgCraftSounds.ROLL.get(), SoundSource.NEUTRAL,
                0.7F, 1.0F);
        //4.0F / (world.getRandom().nextFloat() * 1.0F + 7.5F

        int roll = roll(stack);
        Component component =new TranslatableComponent(Utils.MESSAGE + ".roll",roll);
        if (world.isClientSide()){
            //只在客户端处理不在多人world
            if(server != null){
                server.getPlayerList().broadcastMessage(component, ChatType.CHAT, Util.NIL_UUID);
            }else {
                player.sendMessage(component,Util.NIL_UUID);
            }
        }
        return super.use(world, player, interactionHand);
    }


    @Override
    public InteractionResult useOn(UseOnContext useOnContext) {
        return super.useOn(useOnContext);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level world,List<Component> components,TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, world, components, tooltipFlag);
        if (this.getDiceType(itemStack) == DiceTypeEnum.CONSTANT){
            components.add(new TranslatableComponent(Utils.ITEM + "dice.static.desc",this.getDiceValueMax(itemStack)));
        }else {
            components.add(new TranslatableComponent(Utils.ITEM + "dice.desc", this.getDiceValueMin(itemStack), this.getDiceValueMax(itemStack)));
        }
    }

    @Override
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> list) {
        if (!this.allowdedIn(tab)) {
            return;
        }
        final var defaultStack = getDefaultInstance();
        for (DiceEnum diceEnum : DiceEnum.values()) {
            var stack = new ItemStack(this);
            var cap = stack.getCapability(DICE_CAPABILITY).resolve();
            cap.ifPresent(c -> {
                Dice dice = new DiceImpl(diceEnum.getDiceValue(), diceEnum.getDiceTypeEnum());
                List<Dice> diceList = new ArrayList<>();
                diceList.add(dice);
                c.setDice(diceList);
                list.add(stack);
            });
        }
//        final ItemStack defaultStack = getDefaultInstance();
//        list.add(defaultStack);
//        final var filled = defaultStack.copy();
//        final var cap = filled.getCapability(DICE_CAPABILITY).resolve();
//        cap.ifPresent(c -> {
//            c.addDice(this.dice);
//            list.add(filled);
//        });
    }
}
