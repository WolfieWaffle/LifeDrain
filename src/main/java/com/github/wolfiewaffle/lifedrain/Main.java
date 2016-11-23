package com.github.wolfiewaffle.lifedrain;

import java.io.File;

import com.github.wolfiewaffle.lifedrain.proxy.CommonProxy;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.MOD_ID, version = Main.VERSION, name = "Life Drain", dependencies = "required-after:BloodMagic;after:guideapi", guiFactory = "com.github.wolfiewaffle.lifedrain.ConfigGuiFactory")
public class Main {
	public static final String MOD_ID = "lifedrain";
	public static final String VERSION = "1.0";

	public static Configuration configFile;

	@Mod.Instance("lifedrain")
	public static Main instance;

	public static int myConfigInteger = 32;
	public static String myConfigString = "Hello!";
	public static boolean myConfigBool = false;

	@SidedProxy(clientSide = "com.github.wolfiewaffle.lifedrain.proxy.ClientProxy", serverSide = "com.github.wolfiewaffle.lifedrain.proxy.ServerProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		proxy.preInit(e);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void init(FMLInitializationEvent e) {
		FMLCommonHandler.instance().bus().register(instance);
		proxy.init(e);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}
}
