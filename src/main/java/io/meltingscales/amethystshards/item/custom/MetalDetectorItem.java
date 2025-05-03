package io.meltingscales.amethystshards.item.custom;

import java.util.List;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class MetalDetectorItem extends Item {
  public MetalDetectorItem(Properties properties) {
    super(properties);
  }

  @Override
  public InteractionResult useOn(UseOnContext pContext) {

    if (!pContext.getLevel().isClientSide()) {

      BlockPos positionClicked = pContext.getClickedPos();
      Player player = pContext.getPlayer();

      boolean foundBlock = false;

      for (int i = 0; i <= positionClicked.getY() + 64; i++) {
        BlockState blockState = pContext.getLevel().getBlockState(positionClicked.below(i));

        // check if the block is a valuable ore
        if (isValuableBlock(blockState)) {
          outputValuableCoordinates(positionClicked.below(i), player, blockState.getBlock());
          foundBlock = true;
          break;
        }
      }

      // there was no valuable block found
      if (!foundBlock) {
        outputNoValuableFound(player);
      }
    }

    // use up 1 durability regardless of success
    pContext
        .getItemInHand()
        .hurtAndBreak(
            1,
            pContext.getPlayer(),
            (player) -> player.broadcastBreakEvent(pContext.getPlayer().getUsedItemHand()));

    return InteractionResult.SUCCESS;
  }

  private void outputNoValuableFound(Player player) {
    player.sendSystemMessage(
        Component.translatable("item.amethystshards.metal_detector.no_valuables"));
  }

  @Override
  public void appendHoverText(
      ItemStack pStack,
      @Nullable Level pLevel,
      List<Component> pTooltipComponents,
      TooltipFlag pIsAdvanced) {

    if (Screen.hasShiftDown()) {
      pTooltipComponents.add(
          Component.translatable("tooltip.amethystshards.metal_detector.tooltip.shift"));
    } else {
      pTooltipComponents.add(
          Component.translatable("tooltip.amethystshards.metal_detector.tooltip"));
    }

    super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
  }

  private void outputValuableCoordinates(BlockPos below, Player player, Block block) {

    player.sendSystemMessage(
        Component.empty()
            .append(
                "Valuable [%s] found at > %d %d %d <, or %d blocks below."
                    .formatted(
                        I18n.get(block.getDescriptionId()),
                        below.getX(),
                        below.getY(),
                        below.getZ(),
                        (player.getBlockY() - below.getY()))));
  }

  private boolean isValuableBlock(BlockState blockState) {
    return blockState.is(Blocks.IRON_ORE)
        || blockState.is(Blocks.DEEPSLATE_IRON_ORE)
        || blockState.is(Blocks.DIAMOND_ORE);
  }
}
