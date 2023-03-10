package ratatoskr.hardModeRevamp.enchantments;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import ratatoskr.hardModeRevamp.utils.RConstants;
import ratatoskr.hardModeRevamp.utils.Random;

public class Enchant {
	
	// decided not to replace enchantments in the GUI and instead opted to simply reroll enchantments after choosing. 
	// Players will just be informed that Fortune is always converted into Silk Touch and Unbreaking III converted into Unbreaking II 
	@SuppressWarnings("deprecation")
	public static Map<Enchantment, Integer> enchantAtTable(Map<Enchantment, Integer> enchantsToAdd, Enchantment baseEnchant, Integer baseEnchantPower, Integer levelCost, Material item) {
		HashMap<Enchantment, Integer> enchantList = new HashMap<Enchantment, Integer>();
		
		if(item == Material.BOOK) {
			Map<Enchantment, Integer> bookEnchants = new HashMap<Enchantment, Integer>();
			for(Iterator<Map.Entry<Enchantment, Integer>> it = enchantsToAdd.entrySet().iterator(); it.hasNext();) {
				Map.Entry<Enchantment, Integer> entry = it.next();
				if(entry.getKey().getName() == Enchantment.LOOT_BONUS_BLOCKS.getName()) {
					bookEnchants.put(Enchantment.SILK_TOUCH, 1);
				}
				else {
					bookEnchants.put(entry.getKey(), entry.getValue());
				}
			}
			return bookEnchants;
		}
		
		
		if(baseEnchant.getName() == Enchantment.LOOT_BONUS_BLOCKS.getName()) {
			baseEnchant = Enchantment.SILK_TOUCH;
			baseEnchantPower = 1;
		}
		if(baseEnchant.getName() == Enchantment.DURABILITY.getName() && baseEnchantPower > 2) {
			baseEnchantPower = 2;
		}
		enchantList.put(baseEnchant, baseEnchantPower);
		
		Integer enchantability = findEnchantability(item);
		Integer adjustedEnchantLevel = calcAdjustedEnchantLevel(levelCost, enchantability);
		
		Map<Enchantment, Integer> availableEnchants = getEnchantsPower(item, adjustedEnchantLevel, false);
		availableEnchants = removeConflictingEnchants(availableEnchants, baseEnchant, item);
		
		if(availableEnchants == null || availableEnchants.isEmpty()) {
			return enchantList;
		}
		
		Integer totalWeight = getTotalEnchantmentsWeight(availableEnchants, false);
		
		Double a = Math.random();
		Double tempAdjusted = adjustedEnchantLevel.doubleValue();
		Double odds = ((tempAdjusted + 1) / 50);
		Enchantment nextEnchant = null;
		
		// items can receive up to two enchantments at most
		if(a < odds) {
			nextEnchant = getRandomEnchantment(availableEnchants, totalWeight);
			enchantList.put(nextEnchant, availableEnchants.get(nextEnchant));
		}
		
		return enchantList;
	}
	
	@SuppressWarnings("deprecation")
	private static Enchantment getRandomEnchantment(Map<Enchantment, Integer> enchants, Integer totalWeight) {
		Integer W = (int) (Math.random() * totalWeight); // any integer in the range: [0,totalWeight)
		for(Enchantment key : enchants.keySet()) {
			W = W - Enchantments.valueOf(key.getName().toString()).getWeight();
			if(W < 0) {
				return key;
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
	
	@SuppressWarnings("deprecation")
	private static Map<Enchantment, Integer> removeConflictingEnchants(Map<Enchantment, Integer> enchantmentList, Enchantment enchantment, Material item) {
		Enchantment[] conflicting = getConflicting(enchantment);
		if(conflicting == null) {
			for(Iterator<Map.Entry<Enchantment, Integer>> it = enchantmentList.entrySet().iterator(); it.hasNext();) {
				Map.Entry<Enchantment, Integer> entry = it.next();
				if(entry.getKey().getName() == enchantment.getName()) {
					it.remove();
					break;
				}
			}
			return enchantmentList;
		}
		
		for(Iterator<Map.Entry<Enchantment, Integer>> it = enchantmentList.entrySet().iterator(); it.hasNext();) {
			Map.Entry<Enchantment, Integer> entry = it.next();
			if(entry.getKey().getName() == enchantment.getName() || Arrays.asList(conflicting).contains(entry.getKey())) {
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
		Enchantment[] enchantList = null;
		
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
