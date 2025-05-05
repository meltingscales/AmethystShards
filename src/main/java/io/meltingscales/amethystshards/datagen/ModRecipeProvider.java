package io.meltingscales.amethystshards.datagen;

import io.meltingscales.amethystshards.AmethystShardsMod;
import io.meltingscales.amethystshards.block.ModBlocks;
import io.meltingscales.amethystshards.item.ModItems;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

  /**
   * A collection of smeltable items and blocks related to Alexandrite. This list includes various
   * Alexandrite ores (overworld, deepslate, nether, and endstone variants) and raw Alexandrite.
   * These items and blocks can be used in smelting or blasting recipes to produce Alexandrite
   * items.
   */
  private List<ItemLike> ALEXANDRITE_SMELTABLES =
      List.of(
          ModItems.RAW_ALEXANDRITE.get(),
          ModBlocks.ALEXANDRITE_ORE.get(),
          ModBlocks.DEEPSLATE_ALEXANDRITE_ORE.get(),
          ModBlocks.NETHER_ALEXANDRITE_ORE.get(),
          ModBlocks.END_STONE_ALEXANDRITE_ORE.get());

  public ModRecipeProvider(PackOutput pOutput) {
    super(pOutput);
  }

  @Override
  protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {

    // Alexandrite from block crafting and advancement
    ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.ALEXANDRITE_BLOCK.get())
        .requires(ModBlocks.ALEXANDRITE_BLOCK.get(), 9)
        .unlockedBy(
            "has_alexandrite_block",
            inventoryTrigger(
                ItemPredicate.Builder.item().of(ModBlocks.ALEXANDRITE_BLOCK.get()).build()))
        .save(pWriter);

    // Craft 9 raw alexandrite into 1 alexandrite block
    nineBlockStorageRecipes(
        pWriter,
        RecipeCategory.MISC,
        ModItems.RAW_ALEXANDRITE.get(),
        RecipeCategory.MISC,
        ModBlocks.RAW_ALEXANDRITE_BLOCK.get(),
        "amethystshards:raw_alexandrite",
        "alexandrite",
        "amethystshards:raw_alexandrite_block",
        "alexandrite");

    // Smelt ANY alexandrite ore into 1 alexandrite
    oreSmelting(
        pWriter,
        ALEXANDRITE_SMELTABLES,
        RecipeCategory.MISC,
        ModItems.ALEXANDRITE.get(),
        0.25f,
        200,
        "alexandrite");

    // Blast ANY alexandrite ore into 1 alexandrite
    oreBlasting(
        pWriter,
        ALEXANDRITE_SMELTABLES,
        RecipeCategory.MISC,
        ModItems.ALEXANDRITE.get(),
        0.35f,
        100,
        "alexandrite");
  }

  protected static void oreSmelting(
      Consumer<FinishedRecipe> pFinishedRecipeConsumer,
      List<ItemLike> pIngredients,
      RecipeCategory pCategory,
      ItemLike pResult,
      float pExperience,
      int pCookingTIme,
      String pGroup) {
    oreCooking(
        pFinishedRecipeConsumer,
        RecipeSerializer.SMELTING_RECIPE,
        pIngredients,
        pCategory,
        pResult,
        pExperience,
        pCookingTIme,
        pGroup,
        "_from_smelting");
  }

  protected static void oreBlasting(
      Consumer<FinishedRecipe> pFinishedRecipeConsumer,
      List<ItemLike> pIngredients,
      RecipeCategory pCategory,
      ItemLike pResult,
      float pExperience,
      int pCookingTime,
      String pGroup) {
    oreCooking(
        pFinishedRecipeConsumer,
        RecipeSerializer.BLASTING_RECIPE,
        pIngredients,
        pCategory,
        pResult,
        pExperience,
        pCookingTime,
        pGroup,
        "_from_blasting");
  }

  protected static void oreCooking(
      Consumer<FinishedRecipe> pFinishedRecipeConsumer,
      RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer,
      List<ItemLike> pIngredients,
      RecipeCategory pCategory,
      ItemLike pResult,
      float pExperience,
      int pCookingTime,
      String pGroup,
      String pRecipeName) {
    for (ItemLike itemlike : pIngredients) {
      SimpleCookingRecipeBuilder.generic(
              Ingredient.of(itemlike),
              pCategory,
              pResult,
              pExperience,
              pCookingTime,
              pCookingSerializer)
          .group(pGroup)
          .unlockedBy(getHasName(itemlike), has(itemlike))
          .save(
              pFinishedRecipeConsumer,
              AmethystShardsMod.MOD_ID
                  + ":"
                  + getItemName(pResult)
                  + pRecipeName
                  + "_"
                  + getItemName(itemlike));
    }
  }
}
