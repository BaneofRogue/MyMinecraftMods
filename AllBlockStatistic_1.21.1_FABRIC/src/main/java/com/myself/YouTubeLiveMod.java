package com.myself;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.world.GameMode;

public class YouTubeLiveMod implements ModInitializer {

	@Override
	public void onInitialize() {
		PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, entity) -> {
			if(player instanceof ServerPlayerEntity serverPlayer){
				if(player.getServer() != null && player.getServer().isSingleplayer()){
					if(serverPlayer.interactionManager.getGameMode() == GameMode.SURVIVAL){
						if(player.getMainHandStack().isEmpty()){
							Block block = state.getBlock();
							if(state.getHardness(world, pos) >= 1.5f){
								serverPlayer.incrementStat(Stats.MINED.getOrCreateStat(block));
							}
						}
					}
				}
			}
		});
	}
}