package com.shadowhawk.armorshud;

import com.shadowhawk.armorshud.ArmorsHUDRenderer.Location;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RequiresMcRestart;
import net.minecraftforge.common.config.ConfigManager;

@Config(modid = ArmorsHUDRevived.MOD_ID)
public class ArmorsHUDConfig 
{
	@LangKey("gui.armorshudrevived.config.enabled")
	public static boolean enabled = true;
	
	@LangKey("gui.armorshudrevived.config.location")
	public static Location location = Location.BOTTOM_RIGHT;
	

	@Comment({
	  "Enable debugging mode.",
	  "(for development use only)"
	})
	@Name("Enable Debug")
	@RequiresMcRestart
	public static boolean DEBUG = false;
	
	
	public static void sync()
	{
		ConfigManager.sync(ArmorsHUDRevived.MOD_ID, Config.Type.INSTANCE);
		
		Logger.debug("Configuration changed!");
	}
}
