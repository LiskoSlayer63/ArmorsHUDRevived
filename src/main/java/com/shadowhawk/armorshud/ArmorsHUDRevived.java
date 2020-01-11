package com.shadowhawk.armorshud;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Main class containing the backbone of the Armors HUD Revived mod
 * 
 * @author Zachary Cook
 */
@Mod(modid = ArmorsHUDRevived.MOD_ID, name = ArmorsHUDRevived.MOD_NAME, version = ArmorsHUDRevived.MOD_VERSION, acceptedMinecraftVersions = "[1.12.2]", clientSideOnly = true)
@Mod.EventBusSubscriber(modid = ArmorsHUDRevived.MOD_ID)
public class ArmorsHUDRevived
{
	@Mod.Instance(ArmorsHUDRevived.MOD_ID)
	public static ArmorsHUDRevived instance;
	public ArmorsHUDRenderer renderer = new ArmorsHUDRenderer();
	
	public static final String MOD_ID = "armorshudrevived";
	public static final String MOD_NAME = "Armors HUD Revived";
	public static final String MOD_VERSION = "1.0.3";
	
	public static KeyBinding cycleLocation;
	public static KeyBinding toggleArmors;

	public ArmorsHUDRevived() 
	{
		if (instance != null) {
			throw new RuntimeException("Double instantiation of " + MOD_NAME);
		} else {
			instance = this;
		}
	}
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) 
	{
		Logger.init(event.getModLog());
		Logger.enableDebug(ArmorsHUDConfig.DEBUG);
		Logger.debug("========= P R E  I N I T =========");

		toggleArmors = new KeyBinding("key.armorshudrevived.toggle", 0, MOD_NAME);
		cycleLocation = new KeyBinding("key.armorshudrevived.cycle", 0, MOD_NAME);
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) 
	{
		Logger.debug("========= I N I T =========");

		ClientRegistry.registerKeyBinding(toggleArmors);
		ClientRegistry.registerKeyBinding(cycleLocation);
	}
}
