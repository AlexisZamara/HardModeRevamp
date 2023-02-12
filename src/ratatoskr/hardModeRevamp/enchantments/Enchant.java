package ratatoskr.hardModeRevamp.enchantments;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import ratatoskr.hardModeRevamp.logger.Logging;
import ratatoskr.hardModeRevamp.utils.RConstants;
import ratatoskr.hardModeRevamp.utils.Random;

public class Enchant {
	
	// decided not to replace enchantments in the GUI and instead opted to directly reroll enchantments after choosing. 
	// Players will just be informed that Fortune is always converted into Silk Touch 
	public static Map<Enchantment, Integer> enchantAtTable(Enchantment baseEnchant, Integer baseEnchantPower, Integer levelCost, Material item) {
		HashMap<Enchantment, Integer> enchantList = new HashMap<Enchantment, Integer>();
		
		if(baseEnchant == Enchantment.LOOT_BONUS_BLOCKS) {
			baseEnchant = Enchantment.SILK_TOUCH;
			baseEnchantPower = 1;
			
		}
		enchantList.put(baseEnchant, baseEnchantPower);
		
		Integer enchantability = findEnchantability(item);
		Integer adjustedEnchantLevel = calcAdjustedEnchantLevel(levelCost, enchantability);
		
		Map<Enchantment, Integer> availableEnchants = getEnchantsPower(item, adjustedEnchantLevel, false);
		availableEnchants = removeConflictingEnchants(availableEnchants, baseEnchant);
		Logging.logError(baseEnchant.toString(), 1);
		Logging.logError(enchantList.entrySet().toString(), 0);
		if(availableEnchants == null || availableEnchants.isEmpty()) {
			return enchantList;
		}
		
		Integer totalWeight = getTotalEnchantmentsWeight(availableEnchants, false);
		
		Double a = Math.random();
		Double tempAdjusted = adjustedEnchantLevel.doubleValue();
		Double odds = ((tempAdjusted + 1) / 50);
		Enchantment nextEnchant = null;
		
		while(a < odds) {
			nextEnchant = getRandomEnchantment(availableEnchants, totalWeight); // somehow this can return null?
			enchantList.put(nextEnchant, availableEnchants.get(nextEnchant));
			
			availableEnchants = removeConflictingEnchants(availableEnchants, nextEnchant);
			if(availableEnchants == null || availableEnchants.isEmpty()) {
				break;
			}
			
			totalWeight = getTotalEnchantmentsWeight(availableEnchants, false);
			
			tempAdjusted = tempAdjusted / 2;
			a = Math.random();
			odds = (double) ((tempAdjusted + 1) / 50);
		}
		Logging.logError(baseEnchant.toString(), 1);
		Logging.logError(enchantList.entrySet().toString(), 0);
		return enchantList;
	}
	
	@SuppressWarnings("deprecation")
	private static Enchantment getRandomEnchantment(Map<Enchantment, Integer> enchants, Integer totalWeight) {
		Integer W = Random.RandomInt(0, totalWeight);
		for(Map.Entry<Enchantment, Integer> e : enchants.entrySet()) { // this loop does not work
			W = W - Enchantments.valueOf(e.getKey().getName().toString()).getWeight(); // 
			if(W < 0) { // this appears to never go below 0 which should not be possible
				// possible explanations:
				// W is calculated incorrectly and therefore can never be lower than 0 even after a full loop
				// Map.Entry iteration does not work and should be replaced with iterating over keys?
				return e.getKey();
			}
		}
		return null;
	}
	
	private static Integer calcAdjustedEnchantLevel(Integer cost, Integer enchantability) {
		return (int) Math.rint((cost + Random.RandomInt(0, Math.floorDiv(enchantability, 4)) + Random.RandomInt(0, Math.floorDiv(enchantability, 4))  + 1) * Random.RandomDouble(0.85, 1.15));
	}
	
	@SuppressWarnings("deprecation")
	private static Map<Enchantment, Integer> getEnchantsPower(Material item, Integer adjustedEnchValue, boolean treasure) {
		HashMap<Enchantment, Integer> enchantPower = new HashMap<Enchantment, Integer>();
		Enchantment[] enchantList = findEnchantmentList(item);
		for(Enchantment e : enchantList) {
			Enchantments enchant = Enchantments.valueOf(e.getName().toString());
			Integer level = 0;
			if(!treasure && enchant.isTreasure()) {
				continue;
			}
			for(int i = 0; i < enchant.getMinLevel().length; i++) {
				if(adjustedEnchValue > enchant.getMinLevel()[i] && adjustedEnchValue < enchant.getMaxLevel()[i]) {
					level = i+1;
				}
			}
			
			if(level < 1) {
				continue;
			}
			enchantPower.put(e, level);
		}
		
		if(enchantPower.isEmpty()) {
			return null;
		}
		
		return enchantPower;
	}
	
	private static Map<Enchantment, Integer> removeConflictingEnchants(Map<Enchantment, Integer> enchantmentList, Enchantment enchantment) {
		Enchantment[] conflicting = getConflicting(enchantment);
		if(conflicting == null) {
			return enchantmentList;
		}
		
		for(Iterator<Map.Entry<Enchantment, Integer>> it = enchantmentList.entrySet().iterator(); it.hasNext();) {
			Map.Entry<Enchantment, Integer> entry = it.next();
			if(entry.getKey() == enchantment || Arrays.asList(conflicting).contains(entry.getKey())) {
				it.remove();
			}
		}
		
		return enchantmentList;
	}
	
	@SuppressWarnings("deprecation")
	private static Enchantment[] getConflicting(Enchantment enchantment) {
		if(Enchantments.valueOf(enchantment.getName().toString()).getConflicts() == null) {
			return null;
		}
		return Enchantments.valueOf(enchantment.getName().toString()).getConflicts();
	}
	
	@SuppressWarnings("deprecation")
	private static Integer getTotalEnchantmentsWeight(Map<Enchantment, Integer> enchants, boolean treasure) {
		Integer total = 0;
		for(Map.Entry<Enchantment, Integer> e : enchants.entrySet()) {
			if(!treasure && Enchantments.valueOf(e.getKey().getName().toString()).isTreasure()) {
				continue;
			}
			total += Enchantments.valueOf(e.getKey().getName().toString()).getWeight();
		}
		return total;
	}
	
	private static Integer findEnchantability(Material item) {
		Integer enchantability = 1;
		
		if(Arrays.asList(RConstants.ENCHANT_MATERIAL_WOOD).contains(item) ||
				Arrays.asList(RConstants.ENCHANT_MATERIAL_LEATHER).contains(item) ||
				Arrays.asList(RConstants.ENCHANT_MATERIAL_NETHERITE).contains(item)) {
			enchantability = 15;
		}
		else if(Arrays.asList(RConstants.ENCHANT_MATERIAL_STONE).contains(item)) {
			enchantability = 5;
		}
		else if (Arrays.asList(RConstants.ENCHANT_MATERIAL_CHAINMAIL).contains(item)) {
			enchantability = 12;
		}
		else if(Arrays.asList(RConstants.ENCHANT_MATERIAL_IRON_ARMOR).contains(item)) {
			enchantability = 9;
		}
		else if(Arrays.asList(RConstants.ENCHANT_MATERIAL_IRON_TOOL).contains(item)) {
			enchantability = 14;
		}
		else if(Arrays.asList(RConstants.ENCHANT_MATERIAL_GOLDEN_ARMOR).contains(item)) {
			enchantability = 25;
		}
		else if(Arrays.asList(RConstants.ENCHANT_MATERIAL_GOLDEN_TOOL).contains(item)) {
			enchantability = 22;
		}
		else if(Arrays.asList(RConstants.ENCHANT_MATERIAL_DIAMOND).contains(item)) {
			enchantability = 10;
		}
		else if(item == Material.TURTLE_HELMET) {
			enchantability = 9;
		}
		
		return enchantability;
	}
	
	private static Enchantment[] findEnchantmentList(Material item) {
		Enchantment[] enchantList = RConstants.OTHERS_ENCHANTMENTS;
		
		if(Arrays.asList(RConstants.ENCHANT_SWORD).contains(item)) {
			enchantList = RConstants.SWORD_ENCHANTMENTS;
		}
		else if(Arrays.asList(RConstants.ENCHANT_AXE).contains(item) ||
				Arrays.asList(RConstants.ENCHANT_HOE).contains(item) ||
				Arrays.asList(RConstants.ENCHANT_PICKAXE).contains(item) ||
				Arrays.asList(RConstants.ENCHANT_SHOVEL).contains(item)) {
			enchantList = RConstants.TOOL_ENCHANTMENTS;
		}
		else if(Arrays.asList(RConstants.ENCHANT_ARMOR).contains(item)) {
			enchantList = RConstants.ARMOR_ENCHANTMENTS;
		}
		else if(Arrays.asList(RConstants.ENCHANT_HELMET).contains(item)) {
			enchantList = RConstants.HELMET_ENCHANTMENTS;
		}
		else if(Arrays.asList(RConstants.ENCHANT_BOOTS).contains(item)) {
			enchantList = RConstants.BOOT_ENCHANTMENTS;
		}
		else if(item == Material.BOW) {
			enchantList = RConstants.BOW_ENCHANTMENTS;
		}
		else if(item == Material.CROSSBOW) {
			enchantList = RConstants.CROSSBOW_ENCHANTMENTS;
		}
		else if(item == Material.TRIDENT) {
			enchantList = RConstants.TRIDENT_ENCHANTMENTS;
		}
		else if(item == Material.FISHING_ROD) {
			enchantList = RConstants.FISHING_ROD_ENCHANTMENTS;
		}
		
		return enchantList;
	}
}
