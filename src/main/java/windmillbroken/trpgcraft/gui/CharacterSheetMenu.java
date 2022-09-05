package windmillbroken.trpgcraft.gui;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import windmillbroken.trpgcraft.bean.dice.DiceImpl;
import windmillbroken.trpgcraft.bean.dice.DiceTypeEnum;
import windmillbroken.trpgcraft.bean.skill.CheckResultEnum;
import windmillbroken.trpgcraft.capability.provider.DiceProvider;
import windmillbroken.trpgcraft.capability.provider.EntitySkillProvider;
import windmillbroken.trpgcraft.entity.PuppetEntity;
import windmillbroken.trpgcraft.event.ContainerTypeRegistry;
import windmillbroken.trpgcraft.item.dices.DiceItem;
import windmillbroken.trpgcraft.util.AttributeUtils;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-08-23-11:16
 * @Message: Have a good time!  :)
 **/
public class CharacterSheetMenu extends AbstractContainerMenu {

    public static final int STR_SLOT = 0;
    public static final int CON_SLOT = 1;
    public static final int SIZ_SLOT = 2;
    public static final int DEX_SLOT = 3;
    public static final int APP_SLOT = 4;
    public static final int INTE_SLOT = 5;
    public static final int POW_SLOT = 6;
    public static final int EDU_SLOT = 7;
    public static final int LUCK_SLOT = 8;
    public static final int SKILL_ROLL_SLOT = 9;

    private static final int INV_SLOT_START = 2;
    private static final int INV_SLOT_END = 29;
    private static final int USE_ROW_SLOT_START = 29;
    private static final int USE_ROW_SLOT_END = 38;
    //用户背包相关 不理

    public static final int SLOT_COUNT = 9;
    //存储的槽量
    public static final int ATTRIBUTE_DATA_COUNT = 9;
    //存储的状态数据量

    protected static final int SLOT_WIDTH = 18;
    protected static final int SLOT_HEIGHT = 18;
    //槽的大小

    protected static final int ATT_SLOT_FIX_X = 56;
    //填充槽在x轴的起始位置基于中心点的修正
    protected static final int ATT_SLOT_FIX_Y = 50;
    //填充槽在y轴的起始位置基于中心点的修正

    protected static final int ATT_BUTTON_PADDING_X = 48;
    //按钮的x轴间距
    protected static final int ATT_BUTTON_PADDING_Y = 18;
    //按钮的y轴间距



    //方块实体
    private final ContainerData attRollData;
    //存放骰子丢出来的结果

    protected final Level level;
    //所在世界

    protected final Entity entity;


    final Slot strSlot;
    //力量槽
    final Slot conSlot;
    //体质槽
    final Slot sizSlot;
    //体型槽
    final Slot dexSlot;
    //敏捷槽
    final Slot appSlot;
    //外貌槽
    final Slot intSlot;
    //智力槽
    final Slot powSlot;
    //意志槽
    final Slot eduSlot;
    //教育槽
    final Slot luckSlot;
    //幸运槽



    public CharacterSheetMenu(int id, Inventory inv){
        this(id, inv,
                new SimpleContainer(SLOT_COUNT),
                new SimpleContainerData(ATTRIBUTE_DATA_COUNT),
                new CompoundTag());
    }
    public static CharacterSheetMenu fromNetwork(int windowId, Inventory inv,FriendlyByteBuf buf){
        CompoundTag tag = new CompoundTag();
        if (buf != null){
            tag = buf.readNbt();
        }
        return new CharacterSheetMenu(windowId,
                inv,
                new SimpleContainer(SLOT_COUNT),
                new SimpleContainerData(ATTRIBUTE_DATA_COUNT),
                tag);
    }
    public CharacterSheetMenu(int id,
                              Inventory inventory,
                              SimpleContainer container,
                              SimpleContainerData attData,
                              CompoundTag tag)
    {
        super(ContainerTypeRegistry.CHARACTER_SHEET_MENU.get(), id);
        //继承父类
        checkContainerSize(container, SLOT_COUNT);
        //检查container的大小
        checkContainerDataCount(attData, ATTRIBUTE_DATA_COUNT);
        //检查data的大小

        this.level = inventory.player.level;
        //世界获取

        if (tag != null){
            int type = tag.getInt("entity_type");
            if (type == 0){
                this.entity = this.level.getPlayerByUUID(tag.getUUID("uuid"));
            }else if (type == 1){
                double a = tag.getDouble("min_x");
                double b = tag.getDouble("min_y");
                double c = tag.getDouble("min_z");
                double d = tag.getDouble("max_x");
                double e = tag.getDouble("max_y");
                double f = tag.getDouble("max_z");
                AABB aabb = new AABB(a,b,c,d,e,f);
                List<PuppetEntity> entities = this.level.getEntitiesOfClass(PuppetEntity.class,aabb);
                this.entity = Objects.requireNonNullElseGet(entities.get(0), () -> inventory.player);
            }else {
                this.entity = inventory.player;
                //获取实体
            }
        }else {
            this.entity = inventory.player;
        }


        var rCap = this.entity.getCapability(EntitySkillProvider.STAND_ATTRIBUTE_CAPABILITY).resolve();
        if (rCap.isPresent()) {
            attData.set(STR_SLOT,rCap.get().getStr());
            attData.set(CON_SLOT,rCap.get().getCon());
            attData.set(SIZ_SLOT,rCap.get().getSiz());
            attData.set(DEX_SLOT,rCap.get().getDex());
            attData.set(APP_SLOT,rCap.get().getApp());
            attData.set(INTE_SLOT,rCap.get().getInte());
            attData.set(POW_SLOT,rCap.get().getPow());
            attData.set(EDU_SLOT,rCap.get().getEdu());
            attData.set(LUCK_SLOT,rCap.get().getLuck());
        }


        this.attRollData = attData;
        //数据存放地,0是能量，1x2y3z

        this.strSlot = this.addSlot(new Slot(container, STR_SLOT,
                ATT_SLOT_FIX_X + 0 * ATT_BUTTON_PADDING_X,
                ATT_SLOT_FIX_Y + 0 * ATT_BUTTON_PADDING_Y) {
            //定义输入槽属性，新建了一个Slot，里面包含:container(内容物，上面为空)，指定的槽位ID，x和y轴间距
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                //判定这个槽能不能放指定的物品
                //此处来判定是否是六面骰
                return CharacterSheetMenu.mayPlace(itemStack);
            }

            @Override
            public int getMaxStackSize() {
                //最高可放置的上限
                return 3;
            }
        });

        this.conSlot = this.addSlot(new Slot(container, CON_SLOT,
                ATT_SLOT_FIX_X + 0 * ATT_BUTTON_PADDING_X,
                ATT_SLOT_FIX_Y + 1 * ATT_BUTTON_PADDING_Y) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return CharacterSheetMenu.mayPlace(itemStack);
            }

            @Override
            public int getMaxStackSize() {
                return 3;
            }
        });

        this.sizSlot = this.addSlot(new Slot(container, SIZ_SLOT,
                ATT_SLOT_FIX_X + 0 * ATT_BUTTON_PADDING_X,
                ATT_SLOT_FIX_Y + 2 * ATT_BUTTON_PADDING_Y) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return CharacterSheetMenu.mayPlace(itemStack);
            }

            @Override
            public int getMaxStackSize() {
                return 3;
            }
        });

        this.dexSlot = this.addSlot(new Slot(container, DEX_SLOT,
                ATT_SLOT_FIX_X + 1 * ATT_BUTTON_PADDING_X,
                ATT_SLOT_FIX_Y + 0 * ATT_BUTTON_PADDING_Y) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return CharacterSheetMenu.mayPlace(itemStack);
            }

            @Override
            public int getMaxStackSize() {
                return 3;
            }
        });

        this.appSlot = this.addSlot(new Slot(container, APP_SLOT,
                ATT_SLOT_FIX_X + 1 * ATT_BUTTON_PADDING_X,
                ATT_SLOT_FIX_Y + 1 * ATT_BUTTON_PADDING_Y) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return CharacterSheetMenu.mayPlace(itemStack);
            }

            @Override
            public int getMaxStackSize() {
                return 3;
            }
        });

        this.intSlot = this.addSlot(new Slot(container, INTE_SLOT,
                ATT_SLOT_FIX_X + 1 * ATT_BUTTON_PADDING_X,
                ATT_SLOT_FIX_Y + 2 * ATT_BUTTON_PADDING_Y) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return CharacterSheetMenu.mayPlace(itemStack);
            }

            @Override
            public int getMaxStackSize() {
                return 3;
            }
        });

        this.powSlot = this.addSlot(new Slot(container, POW_SLOT,
                ATT_SLOT_FIX_X + 2 * ATT_BUTTON_PADDING_X,
                ATT_SLOT_FIX_Y + 0 * ATT_BUTTON_PADDING_Y) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return CharacterSheetMenu.mayPlace(itemStack);
            }

            @Override
            public int getMaxStackSize() {
                return 3;
            }
        });

        this.eduSlot = this.addSlot(new Slot(container, EDU_SLOT,
                ATT_SLOT_FIX_X + 2 * ATT_BUTTON_PADDING_X,
                ATT_SLOT_FIX_Y + 1 * ATT_BUTTON_PADDING_Y) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return CharacterSheetMenu.mayPlace(itemStack);
            }

            @Override
            public int getMaxStackSize() {
                return 3;
            }
        });

        this.luckSlot = this.addSlot(new Slot(container, LUCK_SLOT,
                ATT_SLOT_FIX_X + 2 * ATT_BUTTON_PADDING_X,
                ATT_SLOT_FIX_Y + 2 * ATT_BUTTON_PADDING_Y) {
            @Override
            public boolean mayPlace(@NotNull ItemStack itemStack) {
                return CharacterSheetMenu.mayPlace(itemStack);
            }

            @Override
            public int getMaxStackSize() {
                return 3;
            }
        });

        this.addDataSlots(attData);

        for(int i = 0; i < 3; ++i) {
            //背包层渲染
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 9 + j * 18, 137 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inventory, k, 9 + k * 18, 195));
        }





    }

    public static boolean mayPlace(ItemStack itemStack){
        return itemStack.getItem() instanceof DiceItem diceItem
                && (DiceItem.getDiceValue(itemStack) == 6
                || DiceItem.getDiceType(itemStack).equals(DiceTypeEnum.CONSTANT));
    }

    @Override @NotNull
    public MenuType<?> getType() {
        return ContainerTypeRegistry.CHARACTER_SHEET_MENU.get();
    }


    @Override
    public boolean clickMenuButton(@NotNull Player player, int index) {
        //点击按钮
        //需要有九个按钮，根据按钮index来判定按钮的编号
//        if (index > slots.size() - 1 || index < 0){
//            //不在范围内的index，选择将所有槽位全部重置
//            for (Slot slot : slots){
//                if (this.isFull(slot)){
//                    this.setAtt(this.slots.get(index),player);
//                    this.broadcastChanges();
//                    return true;
//                }
//            }
//        }
//        if(this.isFull(this.slots.get(index))) {
//            //判断可以点击按钮的条件
//            //对应槽是填满的
//            this.setAtt(this.slots.get(index),player);
//            //设置对应的属性
//            this.broadcastChanges();
//            return true;
//        }
        return true;
    }

    @Override @NotNull
    public ItemStack quickMoveStack(@NotNull Player player, int index) {
        //shift移动物品
        ItemStack itemstack = ItemStack.EMPTY;
        //准备一个空的stack
        Slot slot = this.slots.get(index);
        //获取背包物品对应的slot
        if (slot.hasItem()) {
            //非空
            ItemStack itemstack1 = slot.getItem();
            //获取背包物品所在的itemStack
            itemstack = itemstack1.copy();
            //复制一份itemstack，作为移动物品的记录
            if (index != CON_SLOT && index != STR_SLOT) {
                //且目标不是ui槽
                if (CharacterSheetMenu.mayPlace(itemstack1) && itemstack1.getCount() == 3) {
                    //如果目标能拿来充电
                    if (!this.moveItemStackTo(itemstack1, STR_SLOT, STR_SLOT + 1, false)) {
                        //尝试移动物品，不行就empty
                        return ItemStack.EMPTY;
                    }
                } else if (index >= INV_SLOT_START && index < INV_SLOT_END) {
                    //如果index在背包栏里面
                    if (!this.moveItemStackTo(itemstack1, USE_ROW_SLOT_START, USE_ROW_SLOT_END, false)) {
                        //尝试移动物品，不行就empty
                        return ItemStack.EMPTY;
                    }
                } else if (index >= USE_ROW_SLOT_START
                        && index < USE_ROW_SLOT_END
                        && !this.moveItemStackTo(itemstack1, INV_SLOT_START, INV_SLOT_END, false)) {
                    //如果目录是手持栏里面
                    //尝试移动物品，不行就empty
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, INV_SLOT_START, USE_ROW_SLOT_END, false)) {
                //目标是ui槽，移动到物品栏和手持栏里
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                //目标空，返回空
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(player, itemstack1);
        }

        return itemstack;
    }

    public boolean isFull(Slot slot){
        return slot.getItem().getCount() == slot.getMaxStackSize();
    }

    public boolean buttonAction(int attIndex,int degree){
        if(this.isFull(this.slots.get(attIndex))) {
            //判断可以点击按钮的条件
            //对应槽是填满的
            this.setAtt(this.slots.get(attIndex),this.entity);
            //设置对应的属性
            this.broadcastChanges();
            return true;
        }else if (this.entity.getCapability(EntitySkillProvider.STAND_ATTRIBUTE_CAPABILITY).resolve().isPresent()){
            var cap = this.entity.getCapability(EntitySkillProvider.STAND_ATTRIBUTE_CAPABILITY).resolve().get();
            int successValue = cap.getSkillValueByIndex(attIndex);
            CheckResultEnum result = CheckResultEnum.skillCheck(
                    new DiceImpl(100,DiceTypeEnum.NORMAL),
                    AttributeUtils.getAttComponentByIndex(attIndex),
                    successValue,
                    this.level,
                    this.entity);

            return true;
        }
        return false;
    }


    public void setAtt(Slot slot,Entity entity) {
        if (slot.getItem().getCapability(DiceProvider.DICE_CAPABILITY).resolve().isEmpty()) {
            //没有能力直接返回
            return;
        }
        //根据属性槽里的骰子设置对应属性的数值
        DiceItem diceItem = (DiceItem) slot.getItem().getItem();
        DiceTypeEnum type = DiceItem.getDiceType(slot.getItem());

        int rollNum = 0;
        for (int i = 0; i < slot.getItem().getCount(); i++) {
            rollNum += slot.getItem().getCapability(DiceProvider.DICE_CAPABILITY).resolve().get().roll();
        }
            rollNum *= 5;
        if (rollNum > 0) {
            //清空骰子
            slot.getItem().shrink(slot.getItem().getCount());
        }
        final int f = rollNum;
        this.attRollData.set(slot.index, rollNum);
        entity.getCapability(EntitySkillProvider.STAND_ATTRIBUTE_CAPABILITY).resolve().ifPresentOrElse(
                (a) -> {
                    if (slot.index == STR_SLOT){
                        a.setStr(f);
                    }else if (slot.index == CON_SLOT){
                        a.setCon(f);
                    }else if (slot.index == SIZ_SLOT){
                        a.setSiz(f);
                    }else if (slot.index == DEX_SLOT){
                        a.setDex(f);
                    }else if (slot.index == APP_SLOT){
                        a.setApp(f);
                    }else if (slot.index == INTE_SLOT){
                        a.setInte(f);
                    }else if (slot.index == POW_SLOT){
                        a.setPow(f);
                    }else if (slot.index == EDU_SLOT){
                        a.setEdu(f);
                    }else if (slot.index == LUCK_SLOT){
                        a.setLuck(f);
                    }
                    setAttValueByIndex(slot.index,f);
                },
                () -> {}
        );
    }

    public int getAttValueByIndex(int index){
        return this.attRollData.get(index);
    }
    public boolean setAttValueByIndex(int index,int value){
        this.attRollData.set(index,value);
        return true;
    }


    @Override
    public boolean stillValid(@NotNull Player playerIn) {
        //判断是否打开gui，用来判定距离
        return true;
    }

    public int getSkillNum(){
        AtomicInteger n = new AtomicInteger();
        this.entity.getCapability(EntitySkillProvider.SKILL_CAPABILITY).resolve().ifPresentOrElse(
                (a) -> n.set(a.getSkillGraph().getHasStudiedSkill().size()),
                () -> {}
        );
        return n.get();
    }
}
