package com.github.wolfiewaffle.lifedrain.proxy;

import java.io.File;

import com.github.wolfiewaffle.lifedrain.ConfigHandler;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e) {
		ConfigHandler.init(new File(e.getModConfigurationDirectory(), "lifedrain.cfg"));
	}

	public void init(FMLInitializationEvent e) {

	}

	public void postInit(FMLPostInitializationEvent e) {

	}
}
