package io.meltingscales.amethystshards.datagen.loot;

import io.meltingscales.amethystshards.block.ModBlocks;
import io.meltingscales.amethystshards.item.ModItems;
import java.util.Set;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockLootTables extends BlockLootSubProvider {

  public ModBlockLootTables() {
    super(Set.of(), FeatureFlags.REGISTRY.allFlags());
  }

  /**
   * Every time we add a new block, we need to add an entry here so its loot table is set up
   * properly.
   */
  @Override
  protected void generate() {
    this.dropSelf(ModBlocks.ALEXANDRITE_BLOCK.get());
    this.dropSelf(ModBlocks.RAW_ALEXANDRITE_BLOCK.get());
    this.dropSelf(ModBlocks.SOUND_BLOCK.get());

    // Note: Check out the createOreDrop method's implementation. There are neat adjacent methods.
    this.add(
        ModBlocks.ALEXANDRITE_ORE.get(),
        block -> createOreDrop(ModBlocks.ALEXANDRITE_ORE.get(), ModItems.RAW_ALEXANDRITE.get()));

    this.add(
        ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get(),
        block ->
            createOreDrop(
                ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get(), ModItems.RAW_ALEXANDRITE.get()));

    this.add(
        ModBlocks.END_STONE_ALEXANDRITE_ORE.get(),
        block ->
            createOreDrop(
                ModBlocks.END_STONE_ALEXANDRITE_ORE.get(), ModItems.RAW_ALEXANDRITE.get()));

    this.add(
        ModBlocks.NETHER_ALEXANDRITE_ORE.get(),
        block ->
            createOreDrop(ModBlocks.NETHER_ALEXANDRITE_ORE.get(), ModItems.RAW_ALEXANDRITE.get()));
  }

  /**
   * Take all the blocks we've registered in our {@link
   * net.minecraftforge.registries.DeferredRegister} inside the class {@link ModBlocks} and go
   * through all of them, and make them "known blocks".
   */
  @Override
  protected Iterable<Block> getKnownBlocks() {
    return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
  }
}
