package io.meltingscales.amethystshards.datagen;

import io.meltingscales.amethystshards.AmethystShardsMod;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTagGenerator extends ItemTagsProvider {

  public ModItemTagGenerator(
      PackOutput packOutput,
      CompletableFuture<HolderLookup.Provider> future,
      CompletableFuture<TagLookup<Block>> tagLookupFuture,
      @Nullable ExistingFileHelper existingFileHelper) {
    super(packOutput, future, tagLookupFuture, AmethystShardsMod.MOD_ID, existingFileHelper);
  }

  @Override
  protected void addTags(HolderLookup.Provider pProvider) {
    // TODO add tags
    //    tag(ModTags.)
  }

  @Override
  public String getName() {
    return "Item Tags";
  }
}
