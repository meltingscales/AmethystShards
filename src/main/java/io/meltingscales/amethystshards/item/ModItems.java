package io.meltingscales.amethystshards.item;

import io.meltingscales.amethystshards.AmethystShards;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

  public static final DeferredRegister<Item> ITEMS =
      DeferredRegister.create(ForgeRegistries.ITEMS, AmethystShards.MOD_ID);

  public static final RegistryObject<Item> ALEXANDRITE =
      ITEMS.register(
          "alexandrite", () -> new Item(new Item.Properties().stacksTo(12).fireResistant()));

  public static final RegistryObject<Item> RAW_ALEXANDRITE =
      ITEMS.register(
          "raw_alexandrite", () -> new Item(new Item.Properties().stacksTo(12).fireResistant()));

  public static void register(IEventBus eventBus) {
    ITEMS.register(eventBus);
  }
}
