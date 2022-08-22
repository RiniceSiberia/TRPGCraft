package windmillbroken.trpgcraft.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.client.gui.widget.ForgeSlider;
import org.jetbrains.annotations.NotNull;
import windmillbroken.trpgcraft.bean.description.Description;
import windmillbroken.trpgcraft.capability.api.DescriptionCapability;
import windmillbroken.trpgcraft.capability.api.SkillCapability;
import windmillbroken.trpgcraft.capability.api.StandAttributeCapability;
import windmillbroken.trpgcraft.capability.impl.SkillCapabilityImpl;
import windmillbroken.trpgcraft.capability.provider.EntitySkillProvider;
import windmillbroken.trpgcraft.capability.provider.PlayerSkillProvider;
import windmillbroken.trpgcraft.bean.dice.Dice;
import windmillbroken.trpgcraft.bean.skill.SkillGraph;
import windmillbroken.trpgcraft.bean.description.SexEnum;
import windmillbroken.trpgcraft.util.TextUtil;
import windmillbroken.trpgcraft.util.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-07-29-8:42
 * @Message: Have a good time!  :)
 **/
public class CharacterSheetGui extends Screen {

    EditBox editBox;

    Button button;
    //按钮
    ResourceLocation background = new ResourceLocation(Utils.MOD_ID,"textures/gui/uibg.png");
    //背景贴图
    Component component;

    ForgeSlider forgeSlider;

    //Forge的滑动条

    Description description;
    //个人信息
    SkillGraph skillGraph;
    //技能图
    Integer professionSkillPoint;
    //职业点
    Integer hobbySkillPoint;
    //兴趣点
    Integer growSkillPoint;
    //成长点
    int str = 0;
    //力量
    int con = 0;
    //体质
    int siz = 0;
    //体型
    int dex = 0;
    //敏捷
    int app = 0;
    //魅力
    int inte = 0;
    //智力
    int pow = 0;
    //意志
    int edu = 0;
    //教育
    double luck = 0;
    //幸运
    double maxHealth = 0;
    //最大生命值
    int mp = 0;
    //魔力值
    int mpLimit = 0;
    //魔力上限
    int sanity = 0;
    //san值
    int build = 0;
    //体格
    Component db;

    public CharacterSheetGui(Component titleIn, Entity entity) {
        super(titleIn);
        this.component = titleIn;
        if (entity.getCapability(PlayerSkillProvider.DESCRIPTION_CAPABILITY).isPresent()){
            //描述
            var d = entity.getCapability(PlayerSkillProvider.DESCRIPTION_CAPABILITY).resolve();
            this.description = d.map(DescriptionCapability::getDescription).isPresent() ?
                    d.map(DescriptionCapability::getDescription).get() : new Description();
        }
        if (entity.getCapability(EntitySkillProvider.SKILL_CAPABILITY).resolve().isPresent()) {
            //技能
            var skills = entity.getCapability(PlayerSkillProvider.SKILL_CAPABILITY).resolve();
            this.skillGraph = skills.map(SkillCapability::getSkillGraph).isPresent() ?
                    skills.map(SkillCapability::getSkillGraph).get() : SkillGraph.createFullSkillGraph();
            this.professionSkillPoint = skills.map(s -> s.getPoint(SkillCapabilityImpl.PROFESSION)).isPresent() ?
                    skills.map(s -> s.getPoint(SkillCapabilityImpl.PROFESSION)).get() : 0;
            this.hobbySkillPoint = skills.map(s -> s.getPoint(SkillCapabilityImpl.HOBBY)).isPresent() ?
                    skills.map(s -> s.getPoint(SkillCapabilityImpl.HOBBY)).get() : 0;
            this.growSkillPoint = skills.map(s -> s.getPoint(SkillCapabilityImpl.GROW)).isPresent() ?
                    skills.map(s -> s.getPoint(SkillCapabilityImpl.GROW)).get() : 0;
        }
        if (entity.getCapability(EntitySkillProvider.STAND_ATTRIBUTE_CAPABILITY).resolve().isPresent()){
            var attributes = entity.getCapability(PlayerSkillProvider.STAND_ATTRIBUTE_CAPABILITY).resolve();
            this.str = attributes.map(StandAttributeCapability::getStr).isPresent() ?
                    attributes.map(StandAttributeCapability::getStr).get() : 0;
            this.con = attributes.map(StandAttributeCapability::getCon).isPresent() ?
                    attributes.map(StandAttributeCapability::getCon).get() : 0;
            this.siz = attributes.map(StandAttributeCapability::getSiz).isPresent() ?
                    attributes.map(StandAttributeCapability::getSiz).get() : 0;
            this.dex = attributes.map(StandAttributeCapability::getDex).isPresent() ?
                    attributes.map(StandAttributeCapability::getDex).get() : 0;
            this.app = attributes.map(StandAttributeCapability::getApp).isPresent() ?
                    attributes.map(StandAttributeCapability::getApp).get() : 0;
            this.inte = attributes.map(StandAttributeCapability::getInte).isPresent() ?
                    attributes.map(StandAttributeCapability::getInte).get() : 0;
            this.pow = attributes.map(StandAttributeCapability::getPow).isPresent() ?
                    attributes.map(StandAttributeCapability::getPow).get() : 0;
            this.edu = attributes.map(StandAttributeCapability::getEdu).isPresent() ?
                    attributes.map(StandAttributeCapability::getEdu).get() : 0;
            if (entity instanceof LivingEntity livingEntity){
                if (livingEntity.getAttributes().hasAttribute(Attributes.LUCK)){
                    this.luck = livingEntity.getAttributeValue(Attributes.LUCK);
                }
                if (livingEntity.getAttributes().hasAttribute(Attributes.MAX_HEALTH)){
                    this.maxHealth = livingEntity.getAttributeValue(Attributes.MAX_HEALTH);
                }
            }
            this.mp = attributes.map(StandAttributeCapability::getMp).isPresent() ?
                    attributes.map(StandAttributeCapability::getMp).get() : 0;
            this.mpLimit = attributes.map(StandAttributeCapability::getMpLimit).isPresent() ?
                    attributes.map(StandAttributeCapability::getMpLimit).get() : 0;
            this.sanity = attributes.map(StandAttributeCapability::getSanity).isPresent() ?
                    attributes.map(StandAttributeCapability::getSanity).get() : 0;
            this.build = attributes.map(StandAttributeCapability::getBuild).isPresent() ?
                    attributes.map(StandAttributeCapability::getBuild).get() : -3;
            this.db = attributes.map(StandAttributeCapability::getDb).isPresent() ?
                    TextUtil.diceListToComponent(attributes.map(StandAttributeCapability::getDb).get()) : new TextComponent("");


        }
    }


    @Override
    public void init(){
        this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
        this.editBox = new EditBox(
                this.font,
                this.width/2-100,
                80,
                200,
                20,
                new TextComponent("editBox"));
        this.addWidget(this.editBox);

        this.button = new Button(
                this.width / 2 - 100,
                110,
                200,
                20,
                new TextComponent("button"),
                (button) -> {

                },
                (button,matrix,x,y) -> {

                });

        this.addWidget(this.button);


        this.forgeSlider = new ForgeSlider(
                this.width / 2 - 100,
                140,
                200,
                20,
                new TextComponent("-"),
                //前缀
                new TextComponent("+"),
                //后缀
                0.0D,

                100.0D,

                50.0D,
                1.0D,
                //步长，设0就行
                1,
                //精准度，每次拖动的数
                true
                //是否绘制文字
        );
        this.addRenderableWidget(this.forgeSlider);

        super.init();
    }



    @Override
    public void render(@NotNull PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, this.background);
        int width = 300;
        int height = 200;
        int textureWidth = width;
        int textureHeight = height;
        blit(matrixStack,this.width / 2 - 150, 20, 0, 0, width, height, textureWidth, textureHeight);
        drawCenteredString(matrixStack,this.font,this.component,this.width / 2, 30, 0xeb0505);
        this.editBox.render(matrixStack,mouseX,mouseY,partialTicks);
        this.button.render(matrixStack,mouseX,mouseY,partialTicks);
        this.forgeSlider.render(matrixStack,mouseX,mouseY,partialTicks);
        super.render(matrixStack,mouseX, mouseY, partialTicks);
    }
}
