package io.meltingscales.amethystshards.block.custom;

import io.meltingscales.amethystshards.AmethystShardsMod;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class SoundBlock extends Block {

  public SoundBlock(Properties pProperties) {
    super(pProperties);
  }

  @Override
  public InteractionResult use(
      BlockState pState,
      Level pLevel,
      BlockPos pPos,
      Player pPlayer,
      InteractionHand pHand,
      BlockHitResult pHit) {
    AmethystShardsMod.LOGGER.info("hello there :)");

    if (!pLevel.isClientSide && pHand == InteractionHand.MAIN_HAND) {

      if (pPlayer.isCrouching()) {
        pLevel.playSound(
            null, pPos, SoundEvents.NOTE_BLOCK_BANJO.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
        return InteractionResult.SUCCESS;
      } else {

        pLevel.playSound(
            null, pPos, SoundEvents.NOTE_BLOCK_COW_BELL.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
        return InteractionResult.CONSUME;
      }
    }

    return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
  }

  @Override
  public void appendHoverText(
      ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag) {

    pTooltip.add(Component.translatable("tooltip.amethystshards.sound_block.tooltip"));

    super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
  }

  @Override
  public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {

    pLevel.playSound(
        pEntity, pPos, SoundEvents.NOTE_BLOCK_BIT.get(), SoundSource.BLOCKS, 1.0F, 1.0F);

    super.stepOn(pLevel, pPos, pState, pEntity);
  }
}
