package io.meltingscales.amethystshards.util;

import io.meltingscales.amethystshards.AmethystShardsMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
  public static class Items {
    private static TagKey<Item> tag(String name) {
      return ItemTags.create(new ResourceLocation(AmethystShardsMod.MOD_ID, name));
    }

    public static TagKey<Item> forgeTag(String name) {
      return ItemTags.create(new ResourceLocation("forge", name));
    }
  }

  public static class Blocks {

    // Referred to by /resources/data/amethystshards/tags/blocks/metal_detector_valuables.json
    public static final TagKey<Block> METAL_DETECTOR_VALUABLES = tag("metal_detector_valuables");

    public static TagKey<Block> tag(String name) {
      return BlockTags.create(new ResourceLocation(AmethystShardsMod.MOD_ID, name));
    }

    public static TagKey<Block> forgeTag(String name) {
      return BlockTags.create(new ResourceLocation("forge", name));
    }
  }
}
