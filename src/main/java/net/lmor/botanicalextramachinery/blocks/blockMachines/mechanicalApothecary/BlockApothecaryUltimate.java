package net.lmor.botanicalextramachinery.blocks.blockMachines.mechanicalApothecary;

import de.melanx.botanicalmachinery.blocks.base.BotanicalBlock;
import net.lmor.botanicalextramachinery.ExtraMachineryBlocks;
import net.lmor.botanicalextramachinery.blocks.containers.mechanicalApothecary.ContainerApothecaryUltimate;
import net.lmor.botanicalextramachinery.blocks.screens.mechanicalApothecary.ScreenApothecaryUltimate;
import net.lmor.botanicalextramachinery.blocks.tesr.mechanicalApothecary.RenderApothecaryUltimate;
import net.lmor.botanicalextramachinery.blocks.tiles.mechanicalApothecary.BlockEntityApothecaryUltimate;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import org.jetbrains.annotations.NotNull;
import org.moddingx.libx.base.tile.BlockEntityBase;
import org.moddingx.libx.base.tile.MenuBlockBE;
import org.moddingx.libx.block.RotationShape;
import org.moddingx.libx.mod.ModX;
import org.moddingx.libx.registration.SetupContext;
import org.moddingx.libx.render.ItemStackRenderer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Consumer;

public class BlockApothecaryUltimate extends MenuBlockBE<BlockEntityApothecaryUltimate, ContainerApothecaryUltimate> {
    public static final RotationShape SHAPE;

    public BlockApothecaryUltimate(ModX mod, Class<BlockEntityApothecaryUltimate> teClass, MenuType<ContainerApothecaryUltimate> menu) {
        super(mod, teClass, menu, Properties.of(Material.STONE).strength(2.0F, 10.0F).dynamicShape(), new Item.Properties());
    }

    @OnlyIn(Dist.CLIENT)
    public void registerClient(SetupContext ctx) {
        ItemStackRenderer.addRenderBlock(this.getBlockEntityType(), true);
        MenuScreens.register(ExtraMachineryBlocks.ultimateApothecary.menu, ScreenApothecaryUltimate::new);
        BlockEntityRenderers.register(this.getBlockEntityType(), (context) -> {
            return new RenderApothecaryUltimate();
        });
    }

    @OnlyIn(Dist.CLIENT)
    public void initializeItemClient(@Nonnull Consumer<IClientItemExtensions> consumer) {
        consumer.accept(ItemStackRenderer.createProperties());
    }

    @Nonnull
    public InteractionResult use(@Nonnull BlockState state, Level level, @Nonnull BlockPos pos, @Nonnull Player player, @Nonnull InteractionHand hand, @Nonnull BlockHitResult hit) {
        if (!level.isClientSide) {
            BlockEntity tile = level.getBlockEntity(pos);
            ItemStack held = player.getMainHandItem();
            FluidActionResult fluidActionResult = FluidUtil.tryEmptyContainer(held, (IFluidHandler)tile.getCapability(ForgeCapabilities.FLUID_HANDLER, (Direction)null).orElse((IFluidHandler) null), 1000, player, true);
            if (fluidActionResult.isSuccess()) {
                if (tile instanceof BlockEntityBase) {
                    ((BlockEntityBase)tile).setDispatchable();
                }

                if (!player.isCreative()) {
                    player.setItemInHand(hand, fluidActionResult.getResult());
                }

                return InteractionResult.SUCCESS;
            }

            super.use(state, level, pos, player, hand, hit);
        }

        return InteractionResult.SUCCESS;
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return (BlockState)this.defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{BlockStateProperties.HORIZONTAL_FACING});
    }

    public int getLightBlock(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos) {
        return 0;
    }

    public boolean propagatesSkylightDown(@Nonnull BlockState state, @Nonnull BlockGetter reader, @Nonnull BlockPos pos) {
        return true;
    }

    public boolean useShapeForLightOcclusion(@Nonnull BlockState state) {
        return true;
    }

    @Nonnull
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter level, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        return SHAPE.getShape((Direction)state.getValue(BlockStateProperties.HORIZONTAL_FACING));
    }

    @Override
    public void onRemove(@NotNull BlockState state, @NotNull Level level, @NotNull BlockPos pos, @NotNull BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()){
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof BlockEntityApothecaryUltimate){
                ((BlockEntityApothecaryUltimate) blockEntity).drops();
            }
        }
        super.onRemove(state, level, pos, newState, isMoving);
    }

    protected boolean shouldDropInventory(Level level, BlockPos pos, BlockState state) {
        return false;
    }

    static {
        SHAPE = new RotationShape(Shapes.or(BotanicalBlock.FRAME_SHAPE, new VoxelShape[]{box(0.0, 0.0, 0.0, 16.0, 1.0, 16.0), box(0.0, 1.0, 0.0, 1.0, 15.0, 1.0), box(15.0, 1.0, 0.0, 16.0, 15.0, 1.0), box(0.0, 1.0, 15.0, 1.0, 15.0, 16.0), box(15.0, 1.0, 15.0, 16.0, 15.0, 16.0), box(0.0, 15.0, 1.0, 1.0, 16.0, 15.0), box(15.0, 15.0, 1.0, 16.0, 16.0, 15.0), box(0.0, 15.0, 0.0, 16.0, 16.0, 1.0), box(0.0, 15.0, 15.0, 16.0, 16.0, 16.0), box(12.0, 10.0, 4.0, 13.0, 13.0, 12.0), box(3.0, 1.0, 3.0, 13.0, 2.0, 13.0), box(5.0, 2.0, 5.0, 11.0, 9.0, 11.0), box(3.0, 9.0, 3.0, 13.0, 10.0, 13.0), box(3.0, 10.0, 12.0, 13.0, 13.0, 13.0), box(3.0, 10.0, 3.0, 13.0, 13.0, 4.0), box(3.0, 10.0, 4.0, 4.0, 13.0, 12.0)}));
    }
}
