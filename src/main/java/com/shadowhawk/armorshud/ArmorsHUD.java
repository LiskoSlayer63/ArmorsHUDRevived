package com.shadowhawk.armorshud;

import org.apache.logging.log4j.LogManager;

import com.shadowhawk.armorshud.config.ArmorsHUDConfig;
import com.shadowhawk.armorshud.config.ConfigHelper;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * Main class containing the backbone of the Armors HUD Revived mod
 * 
 * @author Zachary Cook
 */
@Mod(ArmorsHUD.MOD_ID)
@Mod.EventBusSubscriber(modid = ArmorsHUD.MOD_ID)
public class ArmorsHUD
{
	public static ArmorsHUD instance;
	public ArmorsHUDRenderer renderer = new ArmorsHUDRenderer();
	
	public static final String MOD_ID = "armorshud";
	public static final String MOD_NAME = "Armors HUD Revived";
	public static final String MOD_VERSION = "1.0.3";
	
	public static KeyBinding cycleLocation;
	public static KeyBinding toggleArmors;

	public ArmorsHUD() 
	{
		if (instance != null)
			throw new RuntimeException("Double instantiation of " + MOD_NAME);
		instance = this;

		Logger.init(LogManager.getLogger(MOD_ID));
		Logger.enableDebug(ArmorsHUDConfig.DEBUG);
		
		ModLoadingContext.get().registerConfig(Type.COMMON, ConfigHelper.SPEC);
	    
	    DistExecutor.runWhenOn(Dist.CLIENT, ()->()-> {
	    	FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
	    });
	}
	
	public void init(FMLClientSetupEvent event) 
	{
		Logger.debug("========= P R E  I N I T =========");

		toggleArmors = new KeyBinding(MOD_ID + ".key.toggle", 0, MOD_NAME);
		cycleLocation = new KeyBinding(MOD_ID + ".key.cycle", 0, MOD_NAME);
		
		ClientRegistry.registerKeyBinding(toggleArmors);
		ClientRegistry.registerKeyBinding(cycleLocation);
	}
}
