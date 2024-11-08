package net.lmor.botanicalextramachinery.entities.manaSpark;

import net.lmor.botanicalextramachinery.ExtraMachineryEntities;
import net.lmor.botanicalextramachinery.ExtraMachineryItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;


public class EntitySaffronManaSpark extends EntityManaSparkPattern {

    public EntitySaffronManaSpark(EntityType<?> entityEntityType, Level level) {
        super(entityEntityType, level);
        this.TRANSFER_RATE = 100000;
    }

    public EntitySaffronManaSpark(Level level){
        this(ExtraMachineryEntities.SAFFRON_SPARK, level);
    }

    @Override
    protected Item getSparkItem() {
        return ExtraMachineryItems.saffronSpark;
    }
}