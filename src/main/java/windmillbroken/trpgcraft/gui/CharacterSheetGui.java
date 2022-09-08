package windmillbroken.trpgcraft.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.apache.commons.logging.impl.Log4JLogger;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;
import windmillbroken.trpgcraft.bean.description.Description;
import windmillbroken.trpgcraft.bean.skill.SkillGraph;
import windmillbroken.trpgcraft.util.AttributeUtils;
import windmillbroken.trpgcraft.util.Utils;

import java.util.ArrayList;
import java.util.List;

import static windmillbroken.trpgcraft.gui.CharacterSheetMenu.*;

/**
 *
 * 使用IDEA编写
 *
 * @Author: DUELIST
 * @Time: 2022-07-29-8:42
 * @Message: Have a good time!  :)
 **/
@OnlyIn(Dist.CLIENT)
public class CharacterSheetGui extends AbstractContainerScreen<CharacterSheetMenu> {

//    EditBox editBox;

    Button[] attributeButtons;
    //按钮组，0-8为属性。第二个维度为按钮款式。
    ResourceLocation background = new ResourceLocation(Utils.MOD_ID,"textures/gui/character_sheet_ui.png");
    //背景贴图
    Component component;
    //title

//    ForgeSlider forgeSlider;

    //Forge的滑动条
    private static final int BACKGROUND_REAL_WIDTH = 300;
    private static final int BACKGROUND_REAL_HEIGHT = 240;


    private static final int BACKGROUND_RENDER_WIDTH = 239;
    private static final int BACKGROUND_RENDER_HEIGHT = 240;

//    private static final int SKILL_SCROLLER_WIDTH = 16;
//    private static final int SKILL_SCROLLER_HEIGHT = 24;

//    private static final int SKILL_SCROLLER_START_FIX_X = 571;
//    private static final int SKILL_SCROLLER_START_FIX_Y = 36;
//
//    private static final int SKILL_SCROLLER_RANGE_Y = 111;




    private static final int ATT_BUTTON_NUM = 9;
    //按钮数量
    private static final int ATT_BUTTON_ROW = 3;
    private static final int ATT_BUTTON_COL = ATT_BUTTON_NUM / ATT_BUTTON_ROW;
    private static final int ATT_BUTTON_TYPE = 3;






    private boolean scrolling;
    //滚动条是否在点击中
    private float scrollOffs;
    //上下滚动条的y轴坐标
    private int startIndex;
    //技能的开始坐标


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



    public CharacterSheetGui(CharacterSheetMenu menu, Inventory inv, Component titleIn) {
        super(menu,inv,titleIn);
        this.component = titleIn;
        this.scrollOffs = 0.0F;
        this.startIndex = 0;
        inventoryLabelX += 24;
        inventoryLabelY += 73;
        this.imageWidth = BACKGROUND_RENDER_WIDTH;
        this.imageHeight = BACKGROUND_RENDER_HEIGHT;
//
//        if (entity.getCapability(PlayerSkillProvider.DESCRIPTION_CAPABILITY).isPresent()){
//            //描述
//            var d = entity.getCapability(PlayerSkillProvider.DESCRIPTION_CAPABILITY).resolve();
//            this.description = d.map(DescriptionCapability::getDescription).isPresent() ?
//                    d.map(DescriptionCapability::getDescription).get() : new Description();
//        }
//        if (entity.getCapability(EntitySkillProvider.SKILL_CAPABILITY).resolve().isPresent()) {
//            //技能
//            var skills = entity.getCapability(PlayerSkillProvider.SKILL_CAPABILITY).resolve();
//            this.skillGraph = skills.map(SkillCapability::getSkillGraph).isPresent() ?
//                    skills.map(SkillCapability::getSkillGraph).get() : SkillGraph.createFullSkillGraph();
//            this.professionSkillPoint = skills.map(s -> s.getPoint(SkillCapabilityImpl.PROFESSION)).isPresent() ?
//                    skills.map(s -> s.getPoint(SkillCapabilityImpl.PROFESSION)).get() : 0;
//            this.hobbySkillPoint = skills.map(s -> s.getPoint(SkillCapabilityImpl.HOBBY)).isPresent() ?
//                    skills.map(s -> s.getPoint(SkillCapabilityImpl.HOBBY)).get() : 0;
//            this.growSkillPoint = skills.map(s -> s.getPoint(SkillCapabilityImpl.GROW)).isPresent() ?
//                    skills.map(s -> s.getPoint(SkillCapabilityImpl.GROW)).get() : 0;
//        }
//        if (entity.getCapability(EntitySkillProvider.STAND_ATTRIBUTE_CAPABILITY).resolve().isPresent()){
//            var attributes = entity.getCapability(PlayerSkillProvider.STAND_ATTRIBUTE_CAPABILITY).resolve();
//            this.str = attributes.map(StandAttributeCapability::getStr).isPresent() ?
//                    attributes.map(StandAttributeCapability::getStr).get() : 0;
//            this.con = attributes.map(StandAttributeCapability::getCon).isPresent() ?
//                    attributes.map(StandAttributeCapability::getCon).get() : 0;
//            this.siz = attributes.map(StandAttributeCapability::getSiz).isPresent() ?
//                    attributes.map(StandAttributeCapability::getSiz).get() : 0;
//            this.dex = attributes.map(StandAttributeCapability::getDex).isPresent() ?
//                    attributes.map(StandAttributeCapability::getDex).get() : 0;
//            this.app = attributes.map(StandAttributeCapability::getApp).isPresent() ?
//                    attributes.map(StandAttributeCapability::getApp).get() : 0;
//            this.inte = attributes.map(StandAttributeCapability::getInte).isPresent() ?
//                    attributes.map(StandAttributeCapability::getInte).get() : 0;
//            this.pow = attributes.map(StandAttributeCapability::getPow).isPresent() ?
//                    attributes.map(StandAttributeCapability::getPow).get() : 0;
//            this.edu = attributes.map(StandAttributeCapability::getEdu).isPresent() ?
//                    attributes.map(StandAttributeCapability::getEdu).get() : 0;
//            if (entity instanceof LivingEntity livingEntity){
//                if (livingEntity.getAttributes().hasAttribute(Attributes.LUCK)){
//                    this.luck = livingEntity.getAttributeValue(Attributes.LUCK);
//                }
//                if (livingEntity.getAttributes().hasAttribute(Attributes.MAX_HEALTH)){
//                    this.maxHealth = livingEntity.getAttributeValue(Attributes.MAX_HEALTH);
//                }
//            }
//            this.mp = attributes.map(StandAttributeCapability::getMp).isPresent() ?
//                    attributes.map(StandAttributeCapability::getMp).get() : 0;
//            this.mpLimit = attributes.map(StandAttributeCapability::getMpLimit).isPresent() ?
//                    attributes.map(StandAttributeCapability::getMpLimit).get() : 0;
//            this.sanity = attributes.map(StandAttributeCapability::getSanity).isPresent() ?
//                    attributes.map(StandAttributeCapability::getSanity).get() : 0;
//            this.build = attributes.map(StandAttributeCapability::getBuild).isPresent() ?
//                    attributes.map(StandAttributeCapability::getBuild).get() : -3;
//            this.db = attributes.map(StandAttributeCapability::getDb).isPresent() ?
//                    TextUtil.diceListToComponent(attributes.map(StandAttributeCapability::getDb).get()) : new TextComponent("");


//        }
    }


    @Override
    public void init(){
        this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
//        this.editBox = new EditBox(
//                this.font,
//                this.width/2-100,
//                80,
//                200,
//                20,
//                new TextComponent("editBox"));
//        this.addWidget(this.editBox);

        //x坐标，y坐标，长，宽，内部文字，触发事件，悬浮文字
        //居中 = (x坐标-长度)/2

        this.attributeButtons = new Button[ATT_BUTTON_NUM];
        for (int row = 0; row < ATT_BUTTON_ROW; row++){
            for (int col = 0;col <ATT_BUTTON_COL; col++){
                int width = SLOT_WIDTH;
                int height = SLOT_HEIGHT;
                int xPos = this.leftPos + ATT_SLOT_FIX_X + row * ATT_BUTTON_PADDING_X - SLOT_WIDTH-2;
                int yPos = this.topPos + ATT_SLOT_FIX_Y + col * ATT_BUTTON_PADDING_Y -1;
                int attIndex = row  * ATT_BUTTON_COL + col;
                Component component = new TextComponent(String.valueOf(this.menu.getAttValueByIndex(attIndex)));
                if (this.menu.isFull(attIndex)){
                    component = new TranslatableComponent(Utils.MOD_ID + ".attribute.roll");
                }
                Component tipA = new TranslatableComponent(Utils.tips("attribute.roll.a"));
                Component tipB = new TranslatableComponent(Utils.tips("attribute.roll.b"));
                Component tipC = new TranslatableComponent(Utils.tips("attribute.roll.c"));
                //提醒文字
                List<Component> tips = new ArrayList<>();
                tips.add(tipA);
                tips.add(tipB);
                tips.add(tipC);
                this.attributeButtons[attIndex] = new Button(
                        xPos,
                        yPos,
                        width,
                        height,
                        component,
                        (button) -> {

                        },
                        (button, matrixStack, i, j) -> {
                            this.renderComponentTooltip(matrixStack, tips, i, j);
                        }
                ){
                    @Override
                    public boolean mouseClicked(double mouseX, double mouseY, int button) {
                        boolean flag = false;
                        if (mouseX >= this.x && mouseX < this.x + this.width && mouseY >= this.y && mouseY < this.y + this.height) {
                            if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT){
                                //左键
                                menu.buttonAction(attIndex,0);
                            }else if (button == GLFW.GLFW_MOUSE_BUTTON_RIGHT){
                                //右键
                                menu.buttonAction(attIndex,1);
                            }else if (button == GLFW.GLFW_MOUSE_BUTTON_MIDDLE){
                                //中键
                                menu.buttonAction(attIndex,2);
                            }
                            flag = true;
                            this.onPress();
                        }
                        return flag;
                    }


                };
                this.addWidget(this.attributeButtons[attIndex]);

            }
        }


//        this.forgeSlider = new ForgeSlider(
//                this.width / 2 - 100,
//                140,
//                200,
//                20,
//                new TextComponent("-"),
//                //前缀
//                new TextComponent("+"),
//                //后缀
//                0.0D,
//
//                100.0D,
//
//                50.0D,
//                1.0D,
//                //步长，设0就行
//                1,
//                //精准度，每次拖动的数
//                true
//                //是否绘制文字
//        );
//        this.addRenderableWidget(this.forgeSlider);

        super.init();
    }



//    @Override
//    public void render(@NotNull PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
//        this.renderBackground(matrixStack);
//        RenderSystem.setShader(GameRenderer::getPositionTexShader);
//        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
//        RenderSystem.setShaderTexture(0, this.background);
//        int width = 300;
//        int height = 200;
//        int textureWidth = width;
//        int textureHeight = height;
//        blit(matrixStack,this.width / 2 - 150, 20, 0, 0, width, height, textureWidth, textureHeight);
//        drawCenteredString(matrixStack,this.font,this.component,this.width / 2, 30, 0xeb0505);
//        this.editBox.render(matrixStack,mouseX,mouseY,partialTicks);
//        this.button.render(matrixStack,mouseX,mouseY,partialTicks);
//        this.forgeSlider.render(matrixStack,mouseX,mouseY,partialTicks);
//        super.render(matrixStack,mouseX, mouseY, partialTicks);
//    }

    @Override
    public void render(@NotNull PoseStack transform, int mouseX, int mouseY, float partialTicks) {
        super.render(transform, mouseX, mouseY, partialTicks);
        //标题渲染
        Component c1 = new TranslatableComponent("attribute."+Utils.MOD_ID + ".point.total",
                this.menu.getTotalSkillValue(true));
        Component c2 = new TranslatableComponent("attribute."+Utils.MOD_ID + ".point.total.not_luck",
                this.menu.getTotalSkillValue(false));
        this.font.draw(transform,c1.getString(),
                this.leftPos +8, this.topPos + 20, 4210752);
        this.font.draw(transform,c2.getString(),
                this.leftPos +46, this.topPos + 30, 4210752);
        for (int row =0; row < ATT_BUTTON_ROW;row++){
            //属性检定按钮渲染
            for (int col = 0; col < ATT_BUTTON_COL;col++){
                int attIndex = row * ATT_BUTTON_COL + col;
                this.font.draw(transform,AttributeUtils.getAttComponentByIndex(attIndex).getString(),
                        this.leftPos + ATT_SLOT_FIX_X + row * ATT_BUTTON_PADDING_X - SLOT_WIDTH*2-4,
                        this.topPos + ATT_SLOT_FIX_Y + col * ATT_BUTTON_PADDING_Y+3,
                        4210752);
                this.attributeButtons[attIndex].render(transform,mouseX,mouseY,partialTicks);
            }
        }
        this.init();
        //不知道为什么，但是加了这个init就不会炸了
    }



    @Override
    protected void renderBg(@NotNull PoseStack transform, float partialTicks, int x, int y) {
        //背景渲染
        this.renderBackground(transform);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, background);
        int i = this.leftPos;
        int j = this.topPos;
        //左上角的位置坐标
        blit(transform, i, j, 0, 0, BACKGROUND_RENDER_WIDTH, BACKGROUND_RENDER_HEIGHT, BACKGROUND_REAL_WIDTH, BACKGROUND_RENDER_HEIGHT);
        //前倆是图片本身长宽，imageWidth和imageHeight是展示区域长宽
        this.renderSkillScroller(transform, x, y);
    }

    public void renderSkillScroller(PoseStack transform, int x, int y){
//        int i = (int) (this.leftPos + scrollOffs);
//        //x坐标修正
//        int j = this.topPos + SKILL_SCROLLER_START_FIX_Y;
//        //y坐标修正
//        int u = BACKGROUND_RENDER_WIDTH;
//        int v = 0;
//        if (mouseClicked(x,y,GLFW.GLFW_MOUSE_BUTTON_LEFT)){
//            v += SKILL_SCROLLER_HEIGHT;
//        }
//        blit(transform,i,j,u,v,
//                SKILL_SCROLLER_WIDTH,SKILL_SCROLLER_HEIGHT,
//                SKILL_SCROLLER_WIDTH,SKILL_SCROLLER_HEIGHT);
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scroll) {
        //滚轮
        return super.mouseScrolled(mouseX, mouseY, scroll);
    }

//    @Override
//    public boolean mouseClicked(double mouseX, double mouseY, int button) {
//        this.scrolling = false;
//
//        int x = this.leftPos + SKILL_SCROLLER_START_FIX_X;
//        int y = (int) (this.topPos + SKILL_SCROLLER_START_FIX_Y + scrollOffs);
//        if (mouseX >= x && mouseX <= x + SKILL_SCROLLER_WIDTH
//        && mouseY >= y && mouseY <= y + SKILL_SCROLLER_HEIGHT){
//            this.scrolling = true;
//        }
//        return super.mouseClicked(mouseX, mouseY, button);
//    }

    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
//        //拖动
//        if (this.scrolling && this.isSkillScrollBarActive()){
//            if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
//                //鼠标左键拖
//                int upLimit = this.topPos + SKILL_SCROLLER_START_FIX_Y;
//                this.scrollOffs = Mth.clamp(
//                        upLimit,
//                        upLimit+SKILL_SCROLLER_RANGE_Y,
//                        (float) mouseY);
//                //scrollOffs的范围: 36-147
//                //如果有n(N>5)个技能，就将技能n-5等分
//                //每挪动range/(n-5)个像素，Index++
//                this.startIndex =
//                        (int) ((scrollOffs - upLimit)/(this.menu.getSkillNum()-5));
//            }
////            else if (button == GLFW.GLFW_MOUSE_BUTTON_RIGHT) {
////                //鼠标右键拖
////            }
//        }
        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }



    private boolean isSkillScrollBarActive() {
        return this.menu.getSkillNum() > 5;
    }
}
