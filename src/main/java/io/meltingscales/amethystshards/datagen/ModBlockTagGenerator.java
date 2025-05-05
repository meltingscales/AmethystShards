package io.meltingscales.amethystshards.datagen;

import io.meltingscales.amethystshards.AmethystShardsMod;
import io.meltingscales.amethystshards.block.ModBlocks;
import io.meltingscales.amethystshards.util.ModTags;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ModBlockTagGenerator extends BlockTagsProvider {
  public ModBlockTagGenerator(
      PackOutput output,
      CompletableFuture<HolderLookup.Provider> lookupProvider,
      @Nullable ExistingFileHelper existingFileHelper) {
    super(output, lookupProvider, AmethystShardsMod.MOD_ID, existingFileHelper);
  }

  @Override
  protected void addTags(HolderLookup.Provider pProvider) {

    /*
     * Tags Alexandrite ore with the Forge "Ores" tag.
     */
    this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES)
        .add(ModBlocks.ALEXANDRITE_ORE.get())
        .addTag(Tags.Blocks.ORES);
  }

  @Override
  public String getName() {
    return super.getName();
  }
}
