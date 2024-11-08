package net.lmor.botanicalextramachinery.data;

import net.lmor.botanicalextramachinery.ExtraMachinery;
import net.lmor.botanicalextramachinery.ExtraMachineryBlocks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.moddingx.libx.annotation.data.Datagen;
import org.moddingx.libx.datagen.provider.BlockStateProviderBase;

@Datagen
public class BlockStates extends BlockStateProviderBase {
    public BlockStates(DataGenerator gen, ExistingFileHelper helper) {
        super(ExtraMachinery.getInstance(), gen, helper);
    }

    protected void setup() {
        this.manualModel(ExtraMachineryBlocks.malachiteDragonstoneBlock);
        this.manualModel(ExtraMachineryBlocks.saffronDragonstoneBlock);
        this.manualModel(ExtraMachineryBlocks.shadowDragonstoneBlock);
        this.manualModel(ExtraMachineryBlocks.crimsonDragonstoneBlock);

        this.manualModel(ExtraMachineryBlocks.baseAlfheimMarket);
        this.manualModel(ExtraMachineryBlocks.upgradedAlfheimMarket);
        this.manualModel(ExtraMachineryBlocks.advancedAlfheimMarket);
        this.manualModel(ExtraMachineryBlocks.ultimateAlfheimMarket);

        this.manualModel(ExtraMachineryBlocks.baseApothecary);
        this.manualModel(ExtraMachineryBlocks.upgradedApothecary);
        this.manualModel(ExtraMachineryBlocks.advancedApothecary);
        this.manualModel(ExtraMachineryBlocks.ultimateApothecary);

        this.manualModel(ExtraMachineryBlocks.baseDaisy);
        this.manualModel(ExtraMachineryBlocks.upgradedDaisy);
        this.manualModel(ExtraMachineryBlocks.advancedDaisy);
        this.manualModel(ExtraMachineryBlocks.ultimateDaisy);

        this.manualModel(ExtraMachineryBlocks.baseManaPool);
        this.manualModel(ExtraMachineryBlocks.upgradedManaPool);
        this.manualModel(ExtraMachineryBlocks.advancedManaPool);
        this.manualModel(ExtraMachineryBlocks.ultimateManaPool);

        this.manualModel(ExtraMachineryBlocks.baseRunicAltar);
        this.manualModel(ExtraMachineryBlocks.upgradedRunicAltar);
        this.manualModel(ExtraMachineryBlocks.advancedRunicAltar);
        this.manualModel(ExtraMachineryBlocks.ultimateRunicAltar);

        this.manualModel(ExtraMachineryBlocks.baseIndustrialAgglomerationFactory);
        this.manualModel(ExtraMachineryBlocks.upgradedIndustrialAgglomerationFactory);
        this.manualModel(ExtraMachineryBlocks.advancedIndustrialAgglomerationFactory);
        this.manualModel(ExtraMachineryBlocks.ultimateIndustrialAgglomerationFactory);

        this.manualModel(ExtraMachineryBlocks.baseOrechid);
        this.manualModel(ExtraMachineryBlocks.upgradedOrechid);
        this.manualModel(ExtraMachineryBlocks.advancedOrechid);
        this.manualModel(ExtraMachineryBlocks.ultimateOrechid);

        this.manualModel(ExtraMachineryBlocks.greenhouse);
    }
}
