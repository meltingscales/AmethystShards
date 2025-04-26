package io.meltingscales.amethystshards.item.custom;

import javax.annotation.Nullable;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;

public class FuelItem extends Item {

  private int burnTime = 0;

  public FuelItem(Properties pProperties, int burnTime) {
    super(pProperties);
    this.burnTime = burnTime;
  }

  public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
    return this.burnTime;
  }
}
