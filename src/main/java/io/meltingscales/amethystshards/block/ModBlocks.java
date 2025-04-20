package io.meltingscales.amethystshards.block;

import io.meltingscales.amethystshards.AmethystShards;
import io.meltingscales.amethystshards.item.ModItems;
import java.util.function.Supplier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
  public static final DeferredRegister<Block> BLOCKS =
      DeferredRegister.create(ForgeRegistries.BLOCKS, AmethystShards.MOD_ID);

  public static final RegistryObject<Block> ALEXANDRITE_BLOCK =
      registerBlock(
          "alexandrite_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

  public static final RegistryObject<Block> RAW_ALEXANDRITE_BLOCK =
      registerBlock(
          "raw_alexandrite_block",
          () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK)));

  public static final RegistryObject<Block> ALEXANDRITE_ORE =
      registerBlock(
          "alexandrite_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));

  public static final RegistryObject<Block> DEEPSLATE_ALEXANDRITE_ORE =
      registerBlock(
          "deepslate_alexandrite_ore",
          () -> new Block(BlockBehaviour.Properties.copy(ALEXANDRITE_ORE.get())));

  public static final RegistryObject<Block> END_STONE_ALEXANDRITE_ORE =
      registerBlock(
          "end_stone_alexandrite_ore",
          () -> new Block(BlockBehaviour.Properties.copy(ALEXANDRITE_ORE.get())));
  public static final RegistryObject<Block> NETHER_ALEXANDRITE_ORE =
      registerBlock(
          "nether_alexandrite_ore",
          () -> new Block(BlockBehaviour.Properties.copy(ALEXANDRITE_ORE.get())));

  private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
    RegistryObject<T> toReturn = BLOCKS.register(name, block);
    registerBlockItem(name, toReturn);
    return toReturn;
  }

  public static <T extends Block> RegistryObject<Item> registerBlockItem(
      String name, RegistryObject<T> block) {
    return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
  }

  public static void register(IEventBus eventBus) {

    BLOCKS.register(eventBus);
  }
}
