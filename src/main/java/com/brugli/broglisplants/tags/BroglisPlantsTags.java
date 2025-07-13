package com.brugli.broglisplants.tags;

import com.brugli.broglisplants.BroglisPlants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class BroglisPlantsTags {

    public static class Items {

        public static final TagKey<Item> PITCHER_PLANT_FOOD = tag("pitcher_plant_food");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(BroglisPlants.MODID, name));
        }
    }
}
