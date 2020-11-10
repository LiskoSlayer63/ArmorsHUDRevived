package com.shadowhawk.armorshud.config;

import static com.shadowhawk.armorshud.ArmorsHUD.MOD_ID;

import com.shadowhawk.armorshud.ArmorsHUDRenderer.Location;
import com.shadowhawk.armorshud.utils.Logger;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.config.ModConfig;

/**
 * For configuration settings that change the behaviour of code on the LOGICAL CLIENT.
 * This can be moved to an inner class of ExampleModConfig, but is separate because of personal preference and to keep the code organised
 *
 * @author Cadiboo
 */
final class Config 
{
	final protected ForgeConfigSpec.BooleanValue enabled;
	final protected ForgeConfigSpec.EnumValue<Location> location;

	Config(final ForgeConfigSpec.Builder builder)
	{
		builder.push("general");
		
		enabled = builder
				.comment("Enables the Armors HUD Revived")
        		.translation(MOD_ID + ".config.enabled")
        		.define("enabled", true);
		location = builder
				.comment("Armors HUD location on the screen")
				.translation(MOD_ID + ".config.location")
				.defineEnum("location", Location.BOTTOM_RIGHT);
        
		builder.pop();
	}
	
	protected void save(ModConfig config)
	{
		if (config == null) return;

		config.getConfigData().set("general.enabled", ArmorsHUDConfig.enabled);
		config.getConfigData().set("general.location", ArmorsHUDConfig.location);
    	
		config.save();
		
		Logger.debug("Configuration saved!");
	}
	
	protected void load()
	{
		ArmorsHUDConfig.enabled = enabled.get();
		ArmorsHUDConfig.location = location.get();

		Logger.debug("Configuration loaded!");
	}
}
