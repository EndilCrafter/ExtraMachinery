package net.lmor.botanicalextramachinery.blocks.screens.mechanicalDaisy;

import com.mojang.blaze3d.systems.RenderSystem;
import de.melanx.botanicalmachinery.helper.GhostItemRenderer;
import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalDaisy.ContainerDaisyUltimate;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalDaisy.BlockEntityDaisyUltimate;
import net.lmor.botanicalextramachinery.core.LibResources;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

public class ScreenDaisyUltimate extends ExtraScreenBase<ContainerDaisyUltimate> {

    BlockEntityDaisyUltimate blockEntity;
    public ScreenDaisyUltimate(ContainerDaisyUltimate menu, Inventory inventory, Component title) {
        super(menu, inventory, title, -999, -999);

        this.imageWidth = 184;
        this.imageHeight = 217;

        this.inventoryLabelY = -9999;
        this.titleLabelY = -9999;

        this.daisySlotInfo.setCoord( new int[] {154, 106} );

        blockEntity = this.menu.getBlockEntity();
    }

    @OnlyIn(Dist.CLIENT)
    protected void renderBg(@Nonnull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        this.renderBackground(guiGraphics);

        if (blockEntity.getInventoryUpgrade() != null && blockEntity.getInventoryUpgrade().getStackInSlot(0).isEmpty() && this.minecraft != null) {
            GhostItemRenderer.renderGhostItem(blockEntity.getUpgrades(), guiGraphics, this.leftPos + 154, this.topPos + 106);
        }

        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        guiGraphics.blit(LibResources.ULTIMATE_MECHANICAL_DAISY_GUI, relX, relY, 0, 0, this.imageWidth, this.imageHeight);

        this.daisySlotInfo.renderHoveredToolTip(guiGraphics, mouseX, mouseY, blockEntity.getInventoryUpgrade());
        this.drawLabelText(guiGraphics);
    }

    private void drawLabelText(GuiGraphics guiGraphics){
        Component titleText = Component.translatable("block.botanicalextramachinery.ultimate_daisy");
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
