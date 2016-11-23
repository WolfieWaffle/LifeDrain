package com.github.wolfiewaffle.lifedrain;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ConfigHandler {
	private static Configuration config;

	// Config options
	public static boolean doEmptyDamage;
	public static boolean isAmountPercent;
	public static boolean doesOrbMultiply;
	public static int amountDrained;
	public static int maxAmountDrained;
	public static int drainRate;

	public static void init(File file) {
		config = new Configuration(file);
		syncConfig();
		config.load();
	}

	// Event to create config file
	public static void syncConfig() {

		// Get config options
		doEmptyDamage = config.getBoolean("doEmptyDamage", Configuration.CATEGORY_GENERAL, true,
				"Do players get damaged if they have 0 LP (after having more than 0 in their network)? The main feature of the mod.");
		isAmountPercent = config.getBoolean("isAmountPercent", Configuration.CATEGORY_GENERAL, true,
				"If true, this percentage of your LP will be drained each time. For example, if you had 100 LP this was true, and drainAmount was 3, 3 LP would be drained. If false a set amount of LP is drained each time.");
		doesOrbMultiply = config.getBoolean("doesOrbMultiply", Configuration.CATEGORY_GENERAL, true,
				"If true, the amount of LP drained each time is multiplied by your current orb tier. If false it will always be amountDrained.");
		amountDrained = config.getInt("amountDrained", Configuration.CATEGORY_GENERAL, 1, 0, Integer.MAX_VALUE,
				"The amount of LP drained each LP drain tick. If isAmountPercent is true, this is a percentage of your total network LP. If isAmountPercent is false, this is a static value that will be drained each time. Works well with mods that can control the flow of LP into your network.");
		maxAmountDrained = config.getInt("maxAmountDrained", Configuration.CATEGORY_GENERAL, 500, 1, Integer.MAX_VALUE,
				"The max amount of LP drained each LP drain tick.");
		drainRate = config.getInt("drainRate", Configuration.CATEGORY_GENERAL, 40, 1, Integer.MAX_VALUE,
				"Every drainRate ticks, amountDrained LP is drained from your network. There are 20 ticks in a second.");

		config.save();
	}

	// For config
	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		System.out.println("Config Changed");
		if (event.getModID().equals(Main.MOD_ID)) {
			syncConfig();
		}
	}

	public static Configuration getConfig() {
		return config;
	}
}