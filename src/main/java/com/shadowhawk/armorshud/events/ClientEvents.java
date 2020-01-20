package com.shadowhawk.armorshud.events;

import com.shadowhawk.armorshud.ArmorsHUD;
import com.shadowhawk.armorshud.config.ArmorsHUDConfig;
import com.shadowhawk.armorshud.config.ConfigHelper;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ArmorsHUD.MOD_ID)
public class ClientEvents 
{
	@SubscribeEvent
	public static void onRenderGameOverlay(RenderGameOverlayEvent.Post event) 
	{
		if (event.isCanceled() || !Minecraft.isGuiEnabled() || !event.getType().equals(ElementType.ALL)) return;

		Minecraft minecraft = Minecraft.getInstance();
		ArmorsHUD instance = ArmorsHUD.instance;

		instance.renderer.render(minecraft);
	}
	
	@SubscribeEvent
	public static void onClientTick(ClientTickEvent event)
	{
		Minecraft minecraft = Minecraft.getInstance();
		
		if (event.isCanceled() || minecraft == null || !event.phase.equals(Phase.END) || minecraft.isGamePaused() || !Minecraft.isGuiEnabled() || !minecraft.isGameFocused()) return;
		
		boolean sync = false;
		
		if (ArmorsHUD.toggleArmors.isPressed())
		{
			ArmorsHUDConfig.enabled = !ArmorsHUDConfig.enabled;
			sync = true;
		}
		
		if (ArmorsHUD.cycleLocation.isPressed())
		{
			ArmorsHUDConfig.location.cycleLocation();
			sync = true;
		}
		
		if (sync)
			ConfigHelper.save();
	}
}