package net.lmor.botanicalextramachinery.blocks.blockMachines.mechanicalRunicAltar;

import net.lmor.botanicalextramachinery.ModBlocks;
import net.lmor.botanicalextramachinery.blocks.base.ExtraBotanicalBlock;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalRunicAltar.ContainerRunicAltarBase;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalRunicAltar.ScreenRunicAltarBase;
import net.lmor.botanicalextramachinery.blocks.tesr.mechanicalRunicAltar.RendererRunicAltarBase;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalRunicAltar.BlockEntityRunicAltarBase;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.block.RotationShape;
import org.moddingx.libx.mod.ModX;
import org.moddingx.libx.registration.SetupContext;

import javax.annotation.Nonnull;

public class BlockRunicAltarBase extends ExtraBotanicalBlock<BlockEntityRunicAltarBase, ContainerRunicAltarBase> {
    public static final RotationShape SHAPE;

    public BlockRunicAltarBase(ModX mod, Class<BlockEntityRunicAltarBase> teClass, MenuType<ContainerRunicAltarBase> menu) {
        super(mod, teClass, menu, false, true);
    }

    @OnlyIn(Dist.CLIENT)
    public void registerClient(SetupContext ctx) {
        super.registerClient(ctx);
        MenuScreens.register(ModBlocks.baseRunicAltar.menu, ScreenRunicAltarBase::new);
        BlockEntityRenderers.register(this.getBlockEntityType(), (context) -> {
            return new RendererRunicAltarBase();
        });
    }

    @Nonnull
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return SHAPE.getShape(state.getValue(BlockStateProperties.HORIZONTAL_FACING));
    }

    static {
        SHAPE = new RotationShape(Shapes.or(ExtraBotanicalBlock.FRAME_SHAPE, box(2.0, 5.0, 2.0, 14.0, 9.0, 14.0), box(6.0, 3.0, 6.0, 10.0, 5.0, 10.0), box(4.0, 1.0, 4.0, 12.0, 3.0, 12.0)));
    }
}
