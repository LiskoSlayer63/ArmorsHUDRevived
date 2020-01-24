package com.shadowhawk.armorshud;

import com.shadowhawk.armorshud.config.ArmorsHUDConfig;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.world.GameType;

public class ArmorsHUDRenderer {
	
	/**
	 * An enum of locations for the overlay to render at on-screen:
	 * <pre>
	 * BOTTOM_RIGHT: 0
	 * BOTTOM_LEFT: 1
	 * TOP_CENTER: 2
	 * </pre>
	 */
	public enum Location
	{
		BOTTOM_LEFT(1),
		BOTTOM_RIGHT(0),
		TOP_CENTER(2);
		
		public final static int locations = 3;
		private int value;

        /**
         * instantiates the enums
         * @param newValue
         */
		Location(final int newValue) {
            value = newValue;
        }
        
        /**
         * Returns a processed location based on screen width and index slot
         * @param screenWidth	width of the minecraft screen
         * @param i				index of the armor slot being processed
         * @return				the location the item should be rendered
         */
		public int processX(int screenWidth, int i) {
        	if(value == 0) {
        		return screenWidth - 20;
        	} else if(value == 1) {
        		return 5;
        	} else if(value == 2) {
        		//return screenWidth/2+(((10*(3-i))-15)*2)-10;
        		return screenWidth/2 - 20*(i-1);
        	} else {
        		return -1;
        	}
        }
		
		/**
		 * Returns a processed location based on screen height and index slot
		 * @param screenHeight	height of the minecraft screen
         * @param i				index of the armor slot being processed
         * @return				the location the item should be rendered
		 */
        public int processY(int screenHeight, int i) {
        	if(value == 0 || value ==1) {
        		return screenHeight - 40 - i * 20;
        	} else if(value == 2) {
        		return 5;
        	} else {
        		return -1;
        	}
        }
        
        public void cycleLocation()
    	{
    		value = (value + 1) % Location.locations;
    	}
        
		@Override
		public String toString()
		{
			if(value == 0)
			{
				return "gui.armorshudrevived.config.bottomright";
			} else if (value == 1) {
				return "gui.armorshudrevived.config.bottomleft";
			} else if (value == 2) {
				return "gui.armorshudrevived.config.topcenter";
			} else {
				return "Internal Error 001";
			}
		}
	}
	
	/**
	 * Renders the overlay based on information about the size of the Minecraft window
	 * @param screenWidth	the width of the window
	 * @param screenHeight	the height of the window
	 */
	public void render(Minecraft minecraft)
	{
    	ItemRenderer itemRenderer = minecraft.getItemRenderer();

        //This will only work if the player exists and has an inventory, and if the overlay is enabled
		//Essentially has to be loaded into a world
		if (minecraft.player != null && minecraft.player.inventory != null && ArmorsHUDConfig.enabled)
        {
            //Check to make sure armor overlay doesn't render while in spectator
			if (minecraft.world.getWorldInfo().getGameType() != GameType.SPECTATOR)
        	{
				RenderSystem.pushLightingAttributes();
                RenderSystem.enableRescaleNormal();
                RenderSystem.enableBlend();
                RenderSystem.blendFuncSeparate(770, 771, 1, 0);
                RenderHelper.enableStandardItemLighting();
                
                for (int i = 0; i < 4; ++i)
                {
                	int x = ArmorsHUDConfig.location.processX(minecraft.getMainWindow().getScaledWidth(), i);
                	int y = ArmorsHUDConfig.location.processY(minecraft.getMainWindow().getScaledHeight(), i);

                    ItemStack armorItem = minecraft.player.inventory.armorInventory.get(i);

                    if (armorItem != null)
                    {
                        itemRenderer.renderItemAndEffectIntoGUI(armorItem, x, y);
                        itemRenderer.renderItemOverlays(minecraft.fontRenderer, armorItem, x, y);
                    }
                }
               
                RenderHelper.disableStandardItemLighting();
                RenderSystem.disableRescaleNormal();
                RenderSystem.disableBlend();
				RenderSystem.popAttributes();
            }
        }
	}
}
