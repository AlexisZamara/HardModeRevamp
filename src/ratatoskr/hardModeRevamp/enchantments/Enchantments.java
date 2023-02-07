package ratatoskr.hardModeRevamp.enchantments;

import org.bukkit.enchantments.Enchantment;

public enum Enchantments {
	
	ARROW_DAMAGE(Enchantment.ARROW_DAMAGE, 10, null, new Integer[] {1, 11, 21, 31, 41}, new Integer[] {16, 26, 36, 46, 56}, false),
	ARROW_FIRE(Enchantment.ARROW_FIRE, 2, new Enchantment[] {Enchantment.ARROW_INFINITE, Enchantment.MENDING, Enchantment.DURABILITY}, new Integer[] {20}, new Integer[] {50}, false),
	ARROW_INFINITE(Enchantment.ARROW_INFINITE, 1, new Enchantment[] {Enchantment.ARROW_FIRE, Enchantment.MENDING, Enchantment.DURABILITY}, new Integer[] {20}, new Integer[] {50}, false),
	ARROW_KNOCKBACK(Enchantment.ARROW_KNOCKBACK, 2, null, new Integer[] {12, 32}, new Integer[] {37, 57}, false),
	BINDING_CURSE(Enchantment.BINDING_CURSE, 1, null, new Integer[] {1}, new Integer[] {99}, true),
	CHANNELING(Enchantment.CHANNELING, 1, new Enchantment[] {Enchantment.RIPTIDE}, new Integer[] {25}, new Integer[] {50}, false),
	DAMAGE_ALL(Enchantment.DAMAGE_ALL, 10, new Enchantment[] {Enchantment.DAMAGE_ARTHROPODS, Enchantment.DAMAGE_UNDEAD, Enchantment.MENDING, Enchantment.DURABILITY}, new Integer[] {1, 12, 23, 34, 45}, new Integer[] {21, 32, 43, 54, 65}, false),
	DAMAGE_ARTHROPODS(Enchantment.DAMAGE_ARTHROPODS, 5, new Enchantment[] {Enchantment.DAMAGE_ALL, Enchantment.DAMAGE_UNDEAD, Enchantment.MENDING}, new Integer[] {5, 13, 21, 29, 37}, new Integer[] {25, 33, 41, 49, 57}, false),
	DAMAGE_UNDEAD(Enchantment.DAMAGE_UNDEAD, 5, new Enchantment[] {Enchantment.DAMAGE_ALL, Enchantment.DAMAGE_ARTHROPODS, Enchantment.MENDING}, new Integer[] {5, 13, 21, 29, 37}, new Integer[] {25, 33, 41, 49, 57}, false),
	DEPTH_STRIDER(Enchantment.DEPTH_STRIDER, 2, new Enchantment[] {Enchantment.FROST_WALKER, Enchantment.SOUL_SPEED}, new Integer[] {10, 20, 30}, new Integer[] {25, 35, 45}, false),
	DIG_SPEED(Enchantment.DIG_SPEED, 10, new Enchantment[] {Enchantment.MENDING}, new Integer[] {1, 11, 21, 31, 41}, new Integer[] {51, 61, 71, 81, 91}, false),
	DURABILITY(Enchantment.DURABILITY, 5, new Enchantment[] {Enchantment.ARROW_FIRE, Enchantment.ARROW_INFINITE, Enchantment.DAMAGE_ALL, Enchantment.FIRE_ASPECT, Enchantment.IMPALING, Enchantment.LOOT_BONUS_MOBS, 
			Enchantment.MULTISHOT, Enchantment.PIERCING, Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_EXPLOSIONS, Enchantment.PROTECTION_FIRE, Enchantment.SILK_TOUCH
			},  new Integer[] {5, 13, 21}, new Integer[] {55, 63, 71}, false),
	FIRE_ASPECT(Enchantment.FIRE_ASPECT, 2, new Enchantment[] {Enchantment.MENDING, Enchantment.DURABILITY}, new Integer[] {10, 30}, new Integer[] {60, 80}, false),
	FROST_WALKER(Enchantment.FROST_WALKER, 2, new Enchantment[] {Enchantment.DEPTH_STRIDER, Enchantment.SOUL_SPEED}, new Integer[] {10, 20}, new Integer[] {25, 35}, false),
	IMPALING(Enchantment.IMPALING, 2, new Enchantment[] {Enchantment.MENDING, Enchantment.DURABILITY}, new Integer[] {1, 9, 17, 25, 33}, new Integer[] {21, 29, 37, 45, 53}, false),
	KNOCKBACK(Enchantment.KNOCKBACK, 5, null, new Integer[] {5, 25}, new Integer[] {55, 75}, false),
	LOOT_BONUS_BLOCKS(Enchantment.LOOT_BONUS_BLOCKS, 0, null, null, null, false),
	LOOT_BONUS_MOBS(Enchantment.LOOT_BONUS_MOBS, 2, new Enchantment[] {Enchantment.MENDING, Enchantment.DURABILITY}, new Integer[] {15, 24, 33}, new Integer[] {65, 74, 83}, false),
	LOYALTY(Enchantment.LOYALTY, 5, new Enchantment[] {Enchantment.RIPTIDE}, new Integer[] {12, 19, 26}, new Integer[] {50, 50, 50}, false),
	LUCK(Enchantment.LUCK, 2, new Enchantment[] {Enchantment.MENDING}, new Integer[] {15, 24, 33}, new Integer[] {24, 74, 83}, false),
	LURE(Enchantment.LURE, 2, null, new Integer[] {15, 24, 33}, new Integer[] {24, 74, 83}, false),
	MENDING(Enchantment.MENDING, 2, new Enchantment[] {Enchantment.ARROW_FIRE, Enchantment.ARROW_INFINITE, Enchantment.DAMAGE_ALL, Enchantment.DAMAGE_ARTHROPODS, Enchantment.DAMAGE_UNDEAD, Enchantment.DIG_SPEED,
			Enchantment.FIRE_ASPECT, Enchantment.IMPALING, Enchantment.LOOT_BONUS_MOBS, Enchantment.LUCK, Enchantment.MULTISHOT, Enchantment.PIERCING, Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_EXPLOSIONS,
			Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_PROJECTILE, Enchantment.SILK_TOUCH, Enchantment.SWEEPING_EDGE
			}, new Integer[] {30}, new Integer[] {75}, true),
	MULTISHOT(Enchantment.MULTISHOT, 2, new Enchantment[] {Enchantment.MENDING, Enchantment.DURABILITY, Enchantment.PIERCING}, new Integer[] {20}, new Integer[] {50}, false),
	OXYGEN(Enchantment.OXYGEN, 2, null, new Integer[] {10, 20, 30}, new Integer[] {40, 50, 60}, false),
	PIERCING(Enchantment.PIERCING, 10, new Enchantment[] {Enchantment.MENDING, Enchantment.DURABILITY, Enchantment.MULTISHOT}, new Integer[] {1, 11, 21, 31}, new Integer[] {50, 50, 50, 50}, false),
	PROTECTION_ENVIRONMENTAL(Enchantment.PROTECTION_ENVIRONMENTAL, 10, new Enchantment[] {Enchantment.PROTECTION_EXPLOSIONS, Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_PROJECTILE, Enchantment.MENDING, Enchantment.DURABILITY}, new Integer[] {1, 12, 23, 34}, new Integer[] {12, 23, 34, 45}, false),
	PROTECTION_EXPLOSIONS(Enchantment.PROTECTION_EXPLOSIONS, 2, new Enchantment[] {Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_PROJECTILE, Enchantment.MENDING, Enchantment.DURABILITY}, new Integer[] {5, 13, 21, 29}, new Integer[] {13, 21, 29, 37}, false),
	PROTECTION_FALL(Enchantment.PROTECTION_FALL, 5, null, new Integer[] {5, 11, 17, 23}, new Integer[] {11, 17, 23, 29}, false),
	PROTECTION_FIRE(Enchantment.PROTECTION_FIRE, 5, new Enchantment[] {Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_EXPLOSIONS, Enchantment.PROTECTION_PROJECTILE, Enchantment.MENDING, Enchantment.DURABILITY}, new Integer[] {10, 18, 26, 34}, new Integer[] {18, 26, 34, 42}, false),
	PROTECTION_PROJECTILE(Enchantment.PROTECTION_PROJECTILE, 5, new Enchantment[] {Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_EXPLOSIONS, Enchantment.PROTECTION_FIRE, Enchantment.MENDING, Enchantment.DURABILITY}, new Integer[] {3, 9, 15, 21}, new Integer[] {9, 15, 21, 27}, false),
	QUICK_CHARGE(Enchantment.QUICK_CHARGE, 5, null, new Integer[] {12, 32, 52}, new Integer[] {50, 50, 50}, false),
	RIPTIDE(Enchantment.RIPTIDE, 2, new Enchantment[] {Enchantment.CHANNELING, Enchantment.LOYALTY}, new Integer[] {17, 24, 31}, new Integer[] {50, 50, 50}, false),
	SILK_TOUCH(Enchantment.SILK_TOUCH, 1, new Enchantment[] {Enchantment.MENDING, Enchantment.DURABILITY}, new Integer[] {15}, new Integer[] {65}, false),
	SOUL_SPEED(Enchantment.SOUL_SPEED, 1, new Enchantment[] {Enchantment.DEPTH_STRIDER, Enchantment.FROST_WALKER}, new Integer[] {10, 20, 30}, new Integer[] {25, 35, 45}, false),
	SWEEPING_EDGE(Enchantment.SWEEPING_EDGE, 2, new Enchantment[] {Enchantment.MENDING}, new Integer[] {5, 14, 23}, new Integer[] {20, 29, 38}, false),
	THORNS(Enchantment.THORNS, 1, null, new Integer[] {10, 30, 50}, new Integer[] {60, 70, 80}, false),
	VANISHING_CURSE(Enchantment.VANISHING_CURSE, 1, null, new Integer[] {1}, new Integer[] {99}, true),
	WATER_WORKER(Enchantment.WATER_WORKER, 2, null, new Integer[] {1}, new Integer[] {41}, false);
	
	
	private Enchantments(Enchantment enchantment, Integer weight, Enchantment[] conflicts, Integer[] minLevelValue, Integer[] maxLevelValue, boolean treasure) {
		this.enchantment = enchantment;
		this.weight = weight;
		this.conflicts = conflicts;
		this.minLevelValue = minLevelValue;
		this.maxLevelValue = maxLevelValue;
		this.treasure = treasure;
	}
	
	private Enchantment enchantment;
	private Integer weight; // values provided here: https://minecraft.fandom.com/wiki/Enchanting_mechanics#Step_three_%E2%80%93_Select_a_set_of_enchantments_from_the_list
	private Enchantment[] conflicts; // vanilla table here: https://minecraft.fandom.com/wiki/Enchanting#Summary_of_enchantments_by_item
	private Integer[] minLevelValue; // values provided here: https://minecraft.fandom.com/wiki/Enchanting/Levels
	private Integer[] maxLevelValue;
	private boolean treasure;
	
	public Enchantment getEnchantment() {
		return enchantment;
	}
	
	public Integer getWeight() {
		return weight;
	}
	
	public Enchantment[] getConflicts() {
		return conflicts;
	}
	
	public Integer[] getMinLevel() {
		return minLevelValue;
	}
	
	public Integer[] getMaxLevel() {
		return maxLevelValue;
	}
	
	public boolean isTreasure() {
		return treasure;
	}
}