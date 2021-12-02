package com.theundertaker11.aaringautoabsorb;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import de.ellpeck.actuallyadditions.mod.items.ItemPotionRing;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;

@EventBusSubscriber
public class Events {

	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent event) {
		if(!event.player.isServerWorld()) return;
		
		EntityPlayer player = event.player;
		InventoryPlayer inv = player.inventory;
		for(int i=0; i < inv.getSizeInventory(); i++) {
			ItemStack blazeStack = inv.getStackInSlot(i);
			if(blazeStack.getItem() == Items.BLAZE_POWDER) {
				ItemStack baubleStack = getNextBaublePotionRing(player);
				if(!baubleStack.isEmpty()) {
					ItemPotionRing.setStoredBlaze(baubleStack, ItemPotionRing.getStoredBlaze(baubleStack) + 1);
					blazeStack.shrink(1);
					break;
				}else {
					//Find ring in inventory since no baubles slots need filling.
					ItemStack invStack = getNextInventoryPotionRing(player);
					if(!invStack.isEmpty()) {
						ItemPotionRing.setStoredBlaze(invStack, ItemPotionRing.getStoredBlaze(invStack) + 1);
						blazeStack.shrink(1);
						break;
					}
				}
			}
		}
	}
	/**
	 * Finds the first Actually Additions potion ring in your baubles slots that needs more Blaze
	 * @param player
	 * @return
	 */
	public static ItemStack getNextBaublePotionRing(EntityPlayer player) {
		IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
		for(int i=0;i<baubles.getSlots();i++) {
			ItemStack stack = baubles.getStackInSlot(i);
			if(!stack.isEmpty() && stack.getTagCompound() != null) {
				if(stack.getItem() instanceof ItemPotionRing && stack.getTagCompound().hasKey("Blaze")) {
					if(ItemPotionRing.getStoredBlaze(stack) < ItemPotionRing.MAX_BLAZE) {
						return stack;
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}
	/**
	 * Finds the first Actually Additions potion ring in your inventory that needs more Blaze
	 * @param player
	 * @return
	 */
	public static ItemStack getNextInventoryPotionRing(EntityPlayer player) {
		for(int i=0; i<player.inventory.getSizeInventory();i++) {
			ItemStack stack = player.inventory.getStackInSlot(i);
			if(!stack.isEmpty() && stack.getTagCompound() != null) {
				if(stack.getItem() instanceof ItemPotionRing && stack.getTagCompound().hasKey("Blaze")) {
					if(ItemPotionRing.getStoredBlaze(stack) < ItemPotionRing.MAX_BLAZE) {
						return stack;
					}
				}
			}
		}
		return ItemStack.EMPTY;
	}
}
