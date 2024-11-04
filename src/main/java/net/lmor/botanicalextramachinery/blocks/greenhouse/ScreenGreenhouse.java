package net.lmor.botanicalextramachinery.blocks.greenhouse;

import com.mojang.blaze3d.vertex.PoseStack;
import net.lmor.botanicalextramachinery.blocks.base.ExtraScreenBase;
import net.lmor.botanicalextramachinery.core.LibResources;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

public class ScreenGreenhouse extends ExtraScreenBase<ContainerGreenhouse> {

    BlockEntityGreenhouse blockEntity;

    public ScreenGreenhouse(ContainerGreenhouse menu, Inventory inventory, Component title) {
        super(menu, inventory, title, 27, 121);

        this.imageWidth = 184;
        this.imageHeight = 235;

        blockEntity = (BlockEntityGreenhouse)((ContainerGreenhouse)this.menu).getBlockEntity();
    }

    @OnlyIn(Dist.CLIENT)
    protected void renderBg(@Nonnull PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.drawDefaultGuiBackgroundLayer(poseStack, LibResources.GREENHOUSE_GUI);
    }
}
