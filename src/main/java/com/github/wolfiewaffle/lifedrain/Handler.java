package com.github.wolfiewaffle.lifedrain;

import WayofTime.bloodmagic.api.saving.SoulNetwork;
import WayofTime.bloodmagic.api.util.helper.NetworkHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

// This annotation tells Forge to send this class events
@Mod.EventBusSubscriber
public class Handler {

	// This annotation and parameter catch the event
	@SubscribeEvent
	public static void tick(TickEvent.PlayerTickEvent event) {
		if (event.phase == Phase.START) { 
			// Get network
			SoulNetwork network = NetworkHelper.getSoulNetwork(event.player);

			// Debug
			//System.out.println(network.getOrbTier());
			//System.out.println(network.getCurrentEssence());

			// Drain Life
			if (network.getOrbTier() > 0 && !event.player.worldObj.isRemote) {

				// Calculate the amount of LP drained
				int amount;

				if (ConfigHandler.isAmountPercent)
					amount = (int) Math.ceil((ConfigHandler.amountDrained * 0.01) * network.getCurrentEssence());
				else 
					amount = ConfigHandler.amountDrained;

				// Multiply by orb tier
				if (ConfigHandler.doesOrbMultiply) amount = amount * network.getOrbTier(); 

				// Every 10 ticks
				if (event.player.worldObj.getTotalWorldTime() % ConfigHandler.drainRate == 0) {
					network.syphonAndDamage(network.getPlayer(), Math.min(Math.min(amount, ConfigHandler.maxAmountDrained), network.getCurrentEssence()));
					System.out.println(Math.min(Math.min(amount, ConfigHandler.maxAmountDrained), network.getCurrentEssence()));

					// Damage the player
					if (network.getCurrentEssence() == 0) {
						if (ConfigHandler.doEmptyDamage) network.hurtPlayer(event.player, 1);
					}
				}
			}
		}
	}
}
