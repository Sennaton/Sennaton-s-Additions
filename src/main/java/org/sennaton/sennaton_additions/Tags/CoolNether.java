package org.sennaton.sennaton_additions.Tags;

import com.mojang.datafixers.types.templates.Tag;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import org.sennaton.sennaton_additions.Sennaton_Additions;

public class CoolNether {

        public static final TagKey<Biome> IS_COOL_NETHER =tag("is_cool_nether");

        private static TagKey<Biome> create(String pName) {
            return TagKey.create(Registries.BIOME, new ResourceLocation(pName));
        }

        public static  TagKey<Biome> tag(String name){
        return TagKey.create(Registries.BIOME, (new ResourceLocation(Sennaton_Additions.MODID, name)));

        }
}