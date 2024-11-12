package net.lmor.botanicalextramachinery.blocks.screens.mechanicalRunicAltar;

import de.melanx.botanicalmachinery.helper.GhostItemRenderer;
import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalRunicAltar.ContainerRunicAltarUltimate;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalRunicAltar.BlockEntityRunicAltarUltimate;
import net.lmor.botanicalextramachinery.core.LibResources;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.common.block.BotaniaBlocks;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;

public class ScreenRunicAltarUltimate extends ExtraScreenBase<ContainerRunicAltarUltimate> {

    BlockEntityRunicAltarUltimate blockEntity = (BlockEntityRunicAltarUltimate)((ContainerRunicAltarUltimate)this.menu).getBlockEntity();

    public ScreenRunicAltarUltimate(ContainerRunicAltarUltimate menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 27, 120);

        this.imageWidth = 184;
        this.imageHeight = 216;

        Map<Integer, int[]> livingrock = new HashMap<>();
        Map<Integer, int[]> upgrades = new HashMap<>();

        livingrock.put(0, new int[] {66, 94});
        livingrock.put(1, new int[] {84, 94});
        livingrock.put(2, new int[] {102, 94});
        upgrades.put(3, new int[] {27, 94});
        upgrades.put(4, new int[] {140, 94});

        this.runicAltarSlotInfo.setCoord(livingrock, upgrades);

        blockEntity = this.menu.getBlockEntity();
    }

    protected void renderBg(@Nonnull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(guiGraphics, LibResources.ULTIMATE_MECHANICAL_RUNIC_ALTAR_GUI);
        this.drawLabelText(guiGraphics);

        for (int i = 0; i < 3; i++){
            if (blockEntity.getInventory().getStackInSlot(i).isEmpty() && this.minecraft != null) {
                GhostItemRenderer.renderGhostItem(new ItemStack(BotaniaBlocks.livingrock), guiGraphics, this.leftPos + 66 + i * 18, this.topPos + 94);
            }
        }

        if (blockEntity.getInventory().getStackInSlot(3).isEmpty() && this.minecraft != null) {
            GhostItemRenderer.renderGhostItem(blockEntity.getUpgrades(), guiGraphics, this.leftPos + 27, this.topPos + 94);
        }

        if (blockEntity.getInventory().getStackInSlot(4).isEmpty() && this.minecraft != null) {
            GhostItemRenderer.renderGhostItem(blockEntity.getUpgrades(), guiGraphics, this.leftPos + 140, this.topPos + 94);
        }

        this.runicAltarSlotInfo.renderHoveredToolTip(guiGraphics, mouseX, mouseY, blockEntity.getInventory(), new boolean[]{true, true});

        if (blockEntity.getProgress() > 0) {
            float pct = Math.min((float)blockEntity.getProgress() / (float)blockEntity.getMaxProgress(), 1.0F);
            guiGraphics.blit(LibResources.ULTIMATE_MECHANICAL_RUNIC_ALTAR_GUI, this.leftPos + 87, this.topPos + 31, this.imageWidth, 0, Math.round(11.0F * pct), 37);
        }
    }

    private void drawLabelText(GuiGraphics guiGraphics){
        Component titleText = Component.translatable("block.botanicalextramachinery.ultimate_runic_altar");
        float scale = calculateOptimalScale(titleText, this.imageWidth - 20);
        guiGraphics.pose().pushPose();
        guiGraphics.pose().scale(scale, scale, scale);
        guiGraphics.drawString(
                this.font,
                titleText,
                (int)((leftPos + imageWidth / 2 - this.font.width(titleText) * scale / 2) / scale),
                (int)((topPos + 5) / scale),
                0x00, false
        );
        guiGraphics.pose().popPose();
    }

    private float calculateOptimalScale(Component text, int maxWidth) {
        int textWidth = this.font.width(text);
        if (textWidth <= maxWidth) {
            return 1.0f;
        }
        return (float) maxWidth / textWidth;
    }
}
