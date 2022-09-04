package windmillbroken.trpgcraft.item.tools;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import windmillbroken.trpgcraft.capability.provider.EntitySkillProvider;
import windmillbroken.trpgcraft.capability.provider.PlayerSkillProvider;
import windmillbroken.trpgcraft.entity.PuppetEntity;
import windmillbroken.trpgcraft.gui.CharacterSheetGui;
import windmillbroken.trpgcraft.gui.CharacterSheetMenu;
import windmillbroken.trpgcraft.util.Utils;

import java.util.UUID;

/**
 * @author duelist
 */
public class CharacterSheet extends Item {
    public CharacterSheet(Item.Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand interactionHand) {
        MinecraftServer server = world.getServer();
//        if (world.isClientSide()){
            openSheetGui(player,player,world);
            if(server != null){
                //多人模式
                }else {
            }
//        }
        return super.use(world, player, interactionHand);
    }


    public static void openSheetGui(Player player,Entity entity,Level level){
//        if (entity.getCapability(EntitySkillProvider.STAND_ATTRIBUTE_CAPABILITY).resolve().isPresent()
//        && entity.getCapability(EntitySkillProvider.SKILL_CAPABILITY).resolve().isPresent()){
//            CompoundTag c = entity.getCapability(EntitySkillProvider.STAND_ATTRIBUTE_CAPABILITY).resolve().get().serializeNBT();
//            CompoundTag d = entity.getCapability(EntitySkillProvider.SKILL_CAPABILITY).resolve().get().serializeNBT();
//            CompoundTag e = new CompoundTag();
//            if (entity instanceof Player p
//            && p.getCapability(PlayerSkillProvider.DESCRIPTION_CAPABILITY).resolve().isPresent()){
//                e = p.getCapability(PlayerSkillProvider.DESCRIPTION_CAPABILITY).resolve().get().serializeNBT();
//            }
//            CompoundTag nbt = new CompoundTag();
//            nbt.put("att",c);
//            nbt.put("skill",d);
//            nbt.put("des",e);


        CompoundTag nbt = new CompoundTag();
        if (entity instanceof Player player1){
            nbt.putInt("entity_type",0);
            UUID uuid = entity.getUUID();
            nbt.putUUID("uuid",uuid);
        }else{
            if (entity instanceof PuppetEntity puppetEntity){
                nbt.putInt("entity_type",1);
            }else {
                nbt.putInt("entity_type",-1);
            }
            AABB aabb = entity.getBoundingBoxForCulling();
            nbt.putDouble("min_x",aabb.minX);
            nbt.putDouble("min_y",aabb.minY);
            nbt.putDouble("min_z",aabb.minZ);
            nbt.putDouble("max_x",aabb.maxX);
            nbt.putDouble("max_y",aabb.maxY);
            nbt.putDouble("max_z",aabb.maxZ);
        }
        if (!level.isClientSide()){
//            if (player instanceof ServerPlayer serverPlayer){
                //判定是否是服务端
                MenuProvider container = new MenuProvider() {
                    @Override
                    public Component getDisplayName() {
                        return new TranslatableComponent(Utils.MOD_ID + ".sheet.title", entity.getName().getString());
                    }

                    @Nullable
                    @Override
                    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity) {
                        return new CharacterSheetMenu(
                                windowId,
                                playerInventory,
                                new SimpleContainer(CharacterSheetMenu.SLOT_COUNT),
                                new SimpleContainerData(CharacterSheetMenu.ATTRIBUTE_DATA_COUNT),
                                nbt);
                    }
                };
                NetworkHooks.openGui((ServerPlayer) player, container,
                        buf -> buf.writeNbt(nbt));
                player.openMenu(container);
//            }else if (player instanceof LocalPlayer localPlayer){
//                MenuProvider container = new SimpleMenuProvider(
//                        (windowId, playerInventory, playerEntity) ->
//                                new CharacterSheetMenu(windowId, playerInventory,
//                                        new SimpleContainer(CharacterSheetMenu.SLOT_COUNT),
//                                        new SimpleContainerData(CharacterSheetMenu.ATTRIBUTE_DATA_COUNT),
//                                        nbt),
//                        new TranslatableComponent( Utils.MOD_ID + ".sheet.title", entity.getName().getString()));
//                player.openMenu(container);
//
//            }
        }
        //        Minecraft.getInstance().setScreen(new CharacterSheetGui(
//                , player.getInventory(), new TranslatableComponent( Utils.MOD_ID + ".sheet.title", entity.getName().getString()),entity), entity
//                );
//        }
    }
}
