package com.shadowhawk.armorshud;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.client.event.ConfigChangedEvent.OnConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

@Mod.EventBusSubscriber(modid = ArmorsHUDRevived.MOD_ID)
public class ClientEvents 
{
	@SubscribeEvent
	public static void onConfigChanged(final OnConfigChangedEvent event) 
	{
		if (event.getModID().equals(ArmorsHUDRevived.MOD_ID))
			ArmorsHUDConfig.sync();
	}
	
	@SubscribeEvent
	public static void onRenderGameOverlay(RenderGameOverlayEvent.Post event) 
	{
		if (event.isCanceled() || !Minecraft.isGuiEnabled() || !event.getType().equals(ElementType.ALL)) return;

		ArmorsHUDRevived instance = ArmorsHUDRevived.instance;
		ScaledResolution resolution = event.getResolution();

		instance.renderer.render(resolution.getScaledWidth(), resolution.getScaledHeight());
	}
	
	@SubscribeEvent
	public static void onKeyInput(KeyInputEvent event)
	{
		Minecraft minecraft = Minecraft.getMinecraft();
		
		if (event.isCanceled() || minecraft.isGamePaused() || !Minecraft.isGuiEnabled() || !minecraft.inGameHasFocus) return;
		
		boolean sync = false;
		
		if (ArmorsHUDRevived.toggleArmors.isPressed())
		{
			ArmorsHUDConfig.enabled = !ArmorsHUDConfig.enabled;
			sync = true;
		}
		
		if (ArmorsHUDRevived.cycleLocation.isPressed())
		{
			ArmorsHUDConfig.location.cycleLocation();
			sync = true;
		}
		
		if (sync)
			ArmorsHUDConfig.sync();
	}
}