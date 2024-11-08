package net.lmor.botanicalextramachinery.data;

import de.melanx.botanicalmachinery.core.TileTags;
import net.lmor.botanicalextramachinery.ExtraMachinery;
import net.lmor.botanicalextramachinery.ExtraMachineryBlocks;
import net.minecraft.data.DataGenerator;
import org.moddingx.libx.annotation.data.Datagen;
import org.moddingx.libx.datagen.provider.loot.BlockLootProviderBase;

@Datagen
public class LootTables extends BlockLootProviderBase {
    public LootTables(DataGenerator gen) {
        super(ExtraMachinery.getInstance(), gen);
    }

    protected void setup() {
        this.drops(ExtraMachineryBlocks.baseAlfheimMarket, this.copyNBT(TileTags.MANA));
        this.drops(ExtraMachineryBlocks.upgradedAlfheimMarket, this.copyNBT(TileTags.MANA));
        this.drops(ExtraMachineryBlocks.advancedAlfheimMarket, this.copyNBT(TileTags.MANA));
        this.drops(ExtraMachineryBlocks.ultimateAlfheimMarket, this.copyNBT(TileTags.MANA));

        this.drops(ExtraMachineryBlocks.baseIndustrialAgglomerationFactory, this.copyNBT(TileTags.MANA));
        this.drops(ExtraMachineryBlocks.upgradedIndustrialAgglomerationFactory, this.copyNBT(TileTags.MANA));
        this.drops(ExtraMachineryBlocks.advancedIndustrialAgglomerationFactory, this.copyNBT(TileTags.MANA));
        this.drops(ExtraMachineryBlocks.ultimateIndustrialAgglomerationFactory, this.copyNBT(TileTags.MANA));

        this.drops(ExtraMachineryBlocks.baseApothecary, this.copyNBT(TileTags.FLUID));
        this.drops(ExtraMachineryBlocks.upgradedApothecary, this.copyNBT(TileTags.FLUID));
        this.drops(ExtraMachineryBlocks.advancedApothecary, this.copyNBT(TileTags.FLUID));
        this.drops(ExtraMachineryBlocks.ultimateApothecary, this.copyNBT(TileTags.FLUID));

        this.drops(ExtraMachineryBlocks.baseDaisy, this.copyNBT());
        this.drops(ExtraMachineryBlocks.upgradedDaisy, this.copyNBT());
        this.drops(ExtraMachineryBlocks.advancedDaisy, this.copyNBT());
        this.drops(ExtraMachineryBlocks.ultimateDaisy, this.copyNBT());

        this.drops(ExtraMachineryBlocks.baseManaPool, this.copyNBT(TileTags.MANA));
        this.drops(ExtraMachineryBlocks.upgradedManaPool, this.copyNBT(TileTags.MANA));
        this.drops(ExtraMachineryBlocks.advancedManaPool, this.copyNBT(TileTags.MANA));
        this.drops(ExtraMachineryBlocks.ultimateManaPool, this.copyNBT(TileTags.MANA));

        this.drops(ExtraMachineryBlocks.baseRunicAltar, this.copyNBT(TileTags.MANA));
        this.drops(ExtraMachineryBlocks.upgradedRunicAltar, this.copyNBT(TileTags.MANA));
        this.drops(ExtraMachineryBlocks.advancedRunicAltar, this.copyNBT(TileTags.MANA));
        this.drops(ExtraMachineryBlocks.ultimateRunicAltar, this.copyNBT(TileTags.MANA));

        this.drops(ExtraMachineryBlocks.baseOrechid, this.copyNBT(TileTags.MANA));
        this.drops(ExtraMachineryBlocks.upgradedOrechid, this.copyNBT(TileTags.MANA));
        this.drops(ExtraMachineryBlocks.advancedOrechid, this.copyNBT(TileTags.MANA));
        this.drops(ExtraMachineryBlocks.ultimateOrechid, this.copyNBT(TileTags.MANA));

        this.drops(ExtraMachineryBlocks.greenhouse, this.copyNBT(TileTags.MANA));
    }
}