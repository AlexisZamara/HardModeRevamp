package ratatoskr.hardModeRevamp.enchantments.inventory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;

import ratatoskr.hardModeRevamp.enchantments.Enchantments;
import ratatoskr.hardModeRevamp.logger.Logging;
import ratatoskr.hardModeRevamp.utils.RConstants;

public class PrepareAnvil implements Listener {
	// entirely untested
	@EventHandler
	public void onPrepareAnvilEvent(PrepareAnvilEvent event) {
		if(event.getResult() == null) {
			return;
		}
		if(event.getInventory().getItem(0) == null || event.getInventory().getItem(1) == null) {
			return;
		}
		Logging.logError("item slot 0: " + event.getInventory().getItem(0).getType().toString(), 0);
		Logging.logError("item slot 1: " + event.getInventory().getItem(1).getType().toString(), 0);
		Logging.logError("item slot 2: " + event.getInventory().getItem(2).getType().toString(), 0);
		
		if(!isItemAllowed(event.getInventory().getItem(0).getType()) || !isItemAllowed(event.getInventory().getItem(1).getType())) {
			return;
		}
		
		if(!hasEnchants(event.getInventory().getItem(0))) {
			if(event.getInventory().getItem(1).getType() != Material.BOOK) {
				return;
			}
		}
		if(!hasEnchants(event.getInventory().getItem(1))) {
			return;
		}
		
		Map<Enchantment, Integer> mergedEnchants = mergeEnchantments(event.getInventory().getItem(0), event.getInventory().getItem(1));
		Logging.logError("enchant result: " + mergedEnchants.entrySet().toString(), 0);
		
		ItemStack result = event.getResult();
		
		for(Map.Entry<Enchantment, Integer> ench : event.getResult().getEnchantments().entrySet()) {
			result.removeEnchantment(ench.getKey());
		}
		result.addEnchantments(mergedEnchants);
		event.setResult(result);
	}
	
	private static boolean isItemAllowed(Material item) {
		if(item == Material.BOOK || Arrays.asList(RConstants.ANVIL_ITEMS).contains(item)) {
			return true;
		}
		return false;
	}
	
	private static boolean hasEnchants(ItemStack item) {
		if(item.getEnchantments().size() > 0) {
			return true;
		}
		return false;
	}
	
	private static Map<Enchantment, Integer> mergeEnchantments(ItemStack firstItem, ItemStack secondItem) { // replace maps with ItemStack
		Map<Enchantment, Integer> firstSlot = firstItem.getEnchantments();
		Map<Enchantment, Integer> secondSlot = secondItem.getEnchantments();
		Map<Enchantment, Integer> merged = new HashMap<Enchantment, Integer>();
		
		for(Enchantment baseEnch : firstSlot.keySet()) {
			
			if(secondSlot.keySet().contains(baseEnch)) { // this may require testing
				Logging.logError("baseEnch is present in secondItem", 0);
				if(firstSlot.get(baseEnch) > secondSlot.get(baseEnch)) {
					merged.put(baseEnch, firstSlot.get(baseEnch));
				}
				else if(firstSlot.get(baseEnch) < secondSlot.get(baseEnch)) {
					merged.put(baseEnch, secondSlot.get(baseEnch));
				}
				else {
					if(Enchantments.valueOf(baseEnch.toString()).getMaxLevel().length + 1 > firstSlot.get(baseEnch)) {
						merged.put(baseEnch, firstSlot.get(baseEnch) + 1);
					}
					else {
						merged.put(baseEnch, firstSlot.get(baseEnch));
					}
				}
			}
			else {
				Logging.logError("baseEnch is not present in secondItem", 0);
				merged.put(baseEnch, firstSlot.get(baseEnch));
			}
		}
		for(Enchantment extraEnch : secondSlot.keySet()) {
			if(!merged.keySet().contains(extraEnch)) {
				Logging.logError("extraEnch is not present on merged", 0);
				if(!isConflicting(extraEnch, merged, firstItem)) {
					merged.put(extraEnch, secondSlot.get(extraEnch));
				}
			}
		}
		return merged;
	}
	
	private static boolean isConflicting(Enchantment enchantment, Map<Enchantment, Integer> merged, ItemStack firstItem) {
		Enchantment[] compatible = getCompatible(firstItem.getType());
		if(!Arrays.asList(compatible).contains(enchantment)) {
			return true;
		}
		if(merged.keySet().contains(enchantment)) {
			return true;
		}
		return false;
	}
	
	private static Enchantment[] getCompatible(Material item) {
		if(Arrays.asList(RConstants.ENCHANT_ARMOR).contains(item)) {
			return RConstants.ARMOR_ENCHANTMENTS;
		}
		else if(Arrays.asList(RConstants.ENCHANT_AXE).contains(item)
				|| Arrays.asList(RConstants.ENCHANT_HOE).contains(item)
				|| Arrays.asList(RConstants.ENCHANT_PICKAXE).contains(item)
				|| Arrays.asList(RConstants.ENCHANT_SHOVEL).contains(item)) {
			return Stream.concat(Arrays.stream(RConstants.TOOL_ENCHANTMENTS), Arrays.stream(RConstants.ANVIL_TOOL_ENCHANTMENTS)).toArray(Enchantment[]::new);
		}
		else if(Arrays.asList(RConstants.ENCHANT_BOOTS).contains(item)) {
			return RConstants.BOOT_ENCHANTMENTS;
		}
		else if(Arrays.asList(RConstants.ENCHANT_HELMET).contains(item)) {
			return RConstants.HELMET_ENCHANTMENTS;
		}
		else if(Arrays.asList(RConstants.ENCHANT_SWORD).contains(item)) {
			return RConstants.SWORD_ENCHANTMENTS;
		}
		else if(item == Material.BOW) {
			return RConstants.BOW_ENCHANTMENTS;
		}
		else if(item == Material.CROSSBOW) {
			return RConstants.CROSSBOW_ENCHANTMENTS;
		}
		else if(item == Material.TRIDENT) {
			return RConstants.TRIDENT_ENCHANTMENTS;
		}
		else if(item == Material.FISHING_ROD) {
			return RConstants.FISHING_ROD_ENCHANTMENTS;
		}
		else if(item == Material.BOOK) {
			return RConstants.BOOK_ENCHANTMENTS;
		}
		else {
			return RConstants.OTHERS_ENCHANTMENTS;
		}
	}
}
