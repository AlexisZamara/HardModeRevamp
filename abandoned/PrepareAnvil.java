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
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import ratatoskr.hardModeRevamp.enchantments.Enchantments;
import ratatoskr.hardModeRevamp.logger.Logging;
import ratatoskr.hardModeRevamp.utils.RConstants;

public class PrepareAnvil implements Listener {
	// these seems to be no way to access the list of enchantments present on a book while in this inventory?
	// meta.getEnchants() returns an empty Map, item.getEnchantments() also returns an empty Map, attempting to convert the ItemMeta to EnchantmentStorageMeta causes:
	// java.lang.ClassCastException: class org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack cannot be cast to class org.bukkit.inventory.meta.EnchantmentStorageMeta
	// (org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack and org.bukkit.inventory.meta.EnchantmentStorageMeta are in unnamed module of loader 'app')
	// stumped on this one
	// TODO: fix enchanted books by hiding the enchantments in the item meta for this purpose only?
	// OTHER ISSUE THAT POPPED UP:
	// event.getResult() == null skips over the option to input unsafe enchants on tools which is one of the key ideas for the anvil rework
	
	
	/*/ ABANDONED /*/
	@EventHandler
	public void onPrepareAnvilEvent(PrepareAnvilEvent event) {
		if(event.getResult() == null) {
			return;
		}
		if(event.getInventory().getItem(0) == null || event.getInventory().getItem(1) == null) {
			return;
		}
		if(event.getInventory().getItem(0).getType() == Material.ENCHANTED_BOOK && event.getInventory().getItem(1).getType() == Material.ENCHANTED_BOOK) {
			return;
		}
		
		if(!isItemAllowed(event.getInventory().getItem(0).getType()) || !isItemAllowed(event.getInventory().getItem(1).getType())) {
			Logging.logError("one of these is not allowed", 2);
			return;
		}
		
		ItemStack firstItem = event.getInventory().getItem(0);
		ItemStack secondItem = event.getInventory().getItem(1);
		ItemStack result = event.getResult();
		
		if(!hasEnchants(firstItem)) {
			if(secondItem.getType() != Material.ENCHANTED_BOOK) {
				Logging.logError("second item is not a book and first item is not enchanted", 0);
				return;
			}
		}
		if(!hasEnchants(secondItem)) {
			Logging.logError("second item has no enchants", 0);
			return;
		}
		
		Map<Enchantment, Integer> mergedEnchants = mergeEnchantments(firstItem, secondItem); // handle enchantments for item + book
		Logging.logError("enchant result: " + mergedEnchants.entrySet().toString(), 0);
		
		if(mergedEnchants.keySet() != result.getEnchantments().keySet()) {
			for(Map.Entry<Enchantment, Integer> ench : result.getEnchantments().entrySet()) {
				result.removeEnchantment(ench.getKey());
			}
			
			result.addUnsafeEnchantments(mergedEnchants);
			event.setResult(result);
		}
	}
	
	private static boolean isItemAllowed(Material item) {
		if(item == Material.ENCHANTED_BOOK || Arrays.asList(RConstants.ANVIL_ITEMS).contains(item)) {
			return true;
		}
		return false;
	}
	
	private static boolean hasEnchants(ItemStack item) {
		if(item.getType() == Material.ENCHANTED_BOOK) {
			return true;
		}
		Logging.logError(item.getType().toString() + " " + String.valueOf(item.getEnchantments().size()), 0);
		if(item.getEnchantments().size() > 0) {
			return true;
		}
		return false;
	}
	
	private static Map<Enchantment, Integer> mergeEnchantments(ItemStack firstItem, ItemStack secondItem) {
		ItemMeta firstMeta = firstItem.getItemMeta();
		ItemMeta secondMeta = secondItem.getItemMeta();
		Map<Enchantment, Integer> firstSlot = firstMeta.getEnchants();
		Map<Enchantment, Integer> secondSlot = secondMeta.getEnchants(); // this does not work for books for some ungodly reason
		Map<Enchantment, Integer> merged = new HashMap<Enchantment, Integer>();
		
		for(Enchantment baseEnch : firstSlot.keySet()) {
			if(Enchantments.valueOf(baseEnch.getName().toString()).getWeight() == 0) {
				Logging.logError("illegal enchant: " + baseEnch.toString() + ", removing from item", 0);
				continue;
			}
			
			if(secondSlot.keySet().contains(baseEnch)) {
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
		else if(item == Material.ENCHANTED_BOOK) {
			return RConstants.BOOK_ENCHANTMENTS;
		}
		else {
			return RConstants.OTHERS_ENCHANTMENTS;
		}
	}
}
