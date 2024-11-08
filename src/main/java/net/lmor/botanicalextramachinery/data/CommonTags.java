package net.lmor.botanicalextramachinery.data;

import net.lmor.botanicalextramachinery.ExtraMachineryBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.moddingx.libx.annotation.data.Datagen;
import org.moddingx.libx.datagen.provider.CommonTagsProviderBase;
import org.moddingx.libx.mod.ModX;

@Datagen
public class CommonTags extends CommonTagsProviderBase {
    public CommonTags(ModX mod, DataGenerator generator, ExistingFileHelper fileHelper) {
        super(mod, generator, fileHelper);
    }

    public void setup() {
        this.block(BlockTags.MINEABLE_WITH_PICKAXE).add(ExtraMachineryBlocks.malachiteDragonstoneBlock);
        this.block(BlockTags.MINEABLE_WITH_PICKAXE).add(ExtraMachineryBlocks.saffronDragonstoneBlock);
        this.block(BlockTags.MINEABLE_WITH_PICKAXE).add(ExtraMachineryBlocks.shadowDragonstoneBlock);
        this.block(BlockTags.MINEABLE_WITH_PICKAXE).add(ExtraMachineryBlocks.crimsonDragonstoneBlock);

        this.block(BlockTags.MINEABLE_WITH_PICKAXE).add(ExtraMachineryBlocks.malachiteIngotBlock);
        this.block(BlockTags.MINEABLE_WITH_PICKAXE).add(ExtraMachineryBlocks.saffronIngotBlock);
        this.block(BlockTags.MINEABLE_WITH_PICKAXE).add(ExtraMachineryBlocks.shadowIngotBlock);
        this.block(BlockTags.MINEABLE_WITH_PICKAXE).add(ExtraMachineryBlocks.crimsonIngotBlock);

        this.block(BlockTags.NEEDS_IRON_TOOL).add(ExtraMachineryBlocks.malachiteDragonstoneBlock);
        this.block(BlockTags.NEEDS_IRON_TOOL).add(ExtraMachineryBlocks.saffronDragonstoneBlock);
        this.block(BlockTags.NEEDS_IRON_TOOL).add(ExtraMachineryBlocks.malachiteIngotBlock);
        this.block(BlockTags.NEEDS_IRON_TOOL).add(ExtraMachineryBlocks.saffronIngotBlock);

        this.block(BlockTags.NEEDS_DIAMOND_TOOL).add(ExtraMachineryBlocks.shadowDragonstoneBlock);
        this.block(BlockTags.NEEDS_DIAMOND_TOOL).add(ExtraMachineryBlocks.crimsonDragonstoneBlock);
        this.block(BlockTags.NEEDS_DIAMOND_TOOL).add(ExtraMachineryBlocks.shadowIngotBlock);
        this.block(BlockTags.NEEDS_DIAMOND_TOOL).add(ExtraMachineryBlocks.crimsonIngotBlock);
    }
}
