package net.lmor.botanicalextramachinery.blocks.blockMachines.mechanicalAlfheimMarket;

import de.melanx.botanicalmachinery.blocks.base.BotanicalBlock;
import io.github.noeppi_noeppi.libx.block.RotationShape;
import io.github.noeppi_noeppi.libx.mod.ModX;
import net.lmor.botanicalextramachinery.ModBlocks;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalAlfheimMarket.ContainerAlfheimMarketUltimate;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalAlfheimMarket.ScreenAlfheimMarketBase;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalAlfheimMarket.ScreenAlfheimMarketUltimate;
import net.lmor.botanicalextramachinery.blocks.tesr.mechanicalAlfheimMarket.RenderAlpheimMarketBase;
import net.lmor.botanicalextramachinery.blocks.tesr.mechanicalAlfheimMarket.RenderAlpheimMarketUltimate;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalAlfheimMarket.BlockEntityAlfheimMarketUltimate;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
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

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class BlockAlfheimMarketUltimate extends BotanicalBlock<BlockEntityAlfheimMarketUltimate, ContainerAlfheimMarketUltimate> {
    public static final RotationShape SHAPE;

    public BlockAlfheimMarketUltimate(ModX mod, Class<BlockEntityAlfheimMarketUltimate> teClass, MenuType<ContainerAlfheimMarketUltimate> menu) {
        super(mod, teClass, menu, false, true);
    }

    @OnlyIn(Dist.CLIENT)
    public void registerClient(ResourceLocation id, Consumer<Runnable> defer) {
        super.registerClient(id, defer);
        MenuScreens.register(ModBlocks.ultimateAlfheimMarket.menu, ScreenAlfheimMarketUltimate::new);
        BlockEntityRenderers.register(this.getBlockEntityType(), (context) -> {
            return new RenderAlpheimMarketUltimate();
        });
    }

    @Nonnull
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return SHAPE.getShape(state.getValue(BlockStateProperties.HORIZONTAL_FACING));
    }

    @Override
    public void onRemove(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()){
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof BlockEntityAlfheimMarketUltimate){
                ((BlockEntityAlfheimMarketUltimate) blockEntity).drops();
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    static {
        SHAPE = new RotationShape(Shapes.or(BotanicalBlock.FRAME_SHAPE, new VoxelShape[]{box(4.4, 1.0, 8.8, 11.6, 13.0, 11.2), box(0.0, 0.0, 8.8, 0.0, 0.0, 11.2), box(3.2, 0.0, 3.6, 6.8, 7.4, 7.2), box(8.8, 0.0, 3.6, 12.4, 7.4, 7.2)}));
    }
}
