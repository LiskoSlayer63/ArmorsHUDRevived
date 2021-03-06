package com.shadowhawk.armorshud.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

/**
 * This holds the Client & Server Configs and the Client & Server ConfigSpecs.
 * It can be merged into the main ExampleModConfig class, but is separate because of personal preference and to keep the code organised
 *
 * @author Cadiboo
 */
public final class ConfigHelper {

	public static final ForgeConfigSpec SPEC;
	static final Config CONFIG;
	
	private static ModConfig modConfig;
	
	static 
	{
		final Pair<Config, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Config::new);
		CONFIG = specPair.getLeft();
		SPEC = specPair.getRight();
	}
	
	public static void save()
	{
		CONFIG.save(modConfig);
	}
	
	public static void load()
	{
		CONFIG.load();
	}
	
	public static void load(final ModConfig config)
	{
		modConfig = config;
		load();
	}
}