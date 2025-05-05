package io.meltingscales.amethystshards.datagen;

import io.meltingscales.amethystshards.AmethystShardsMod;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AmethystShardsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

  @SubscribeEvent
  public static void gatherData(GatherDataEvent event) {
    DataGenerator generator = event.getGenerator();
    PackOutput packOutput = generator.getPackOutput();
    ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
    CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

    /*
     * Add our recipes from the `ModRecipeProvider` class.
     */
    generator.addProvider(event.includeServer(), new ModRecipeProvider(packOutput));

    /*
     * Add our block drops/loot tables.
     */
    generator.addProvider(event.includeServer(), ModLootTableProvider.create(packOutput));

    /*
     * Add our tag generators.
     */
    generator.addProvider(
        event.includeServer(),
        new ModBlockTagGenerator(packOutput, lookupProvider, existingFileHelper));

    /*
     * Add our models.
     */
    generator.addProvider(
        event.includeClient(), new ModItemModelProvider(packOutput, existingFileHelper));

    /*
     * Add our block states.
     */
    generator.addProvider(
        event.includeClient(), new ModBlockStateProvider(packOutput, existingFileHelper));
  }
}
