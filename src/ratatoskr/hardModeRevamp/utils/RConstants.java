package ratatoskr.hardModeRevamp.utils;

import ratatoskr.hardModeRevamp.Main;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;

public class RConstants {
	public static final String PERSISTENT_REWORKED_STRING = "reworked";
	public static final String BREWING_XP_STRING = "brew_xp";
	public static final String MERCHANT_LOADED_STRING = "inventory_set";
	public static final String PIGLIN_GOLD_PICKUP = "piglin_pickup";
	public static final String PERSISTENT_KEY_STRING = "Ratatoskr-Reworked";
	public static final NamespacedKey PERSISTENT_KEY = new NamespacedKey(Main.getPlugin(),PERSISTENT_KEY_STRING);
	
	public static final String PERSISTENT_COMBAT_FATIGUE_STRING = "combat-fatigue";
	
	public static final Material[] REWORKED_SWORDS = {Material.WOODEN_SWORD, Material.STONE_SWORD, Material.GOLDEN_SWORD, Material.IRON_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD};
	public static final Material[] REWORKED_AXES = {Material.WOODEN_AXE, Material.STONE_AXE, Material.GOLDEN_AXE, Material.IRON_AXE, Material.DIAMOND_AXE, Material.NETHERITE_AXE};
	public static final Material[] REWORKED_GOLDEN_ARMOR = {Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS};
	
	public static final Material[] REWORKED_ITEMS = {Material.WOODEN_SWORD, Material.STONE_SWORD, Material.GOLDEN_SWORD, Material.IRON_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD,
			Material.WOODEN_AXE, Material.STONE_AXE, Material.GOLDEN_AXE, Material.IRON_AXE, Material.DIAMOND_AXE, Material.NETHERITE_AXE,
			Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS
			};
	
	public static final Material[] FOOD_LIST = {Material.APPLE, Material.BAKED_POTATO, Material.BEEF, Material.BEETROOT, Material.BEETROOT_SOUP,
			Material.BREAD, Material.CAKE, Material.CARROT, Material.CHICKEN, Material.CHORUS_FRUIT, Material.COD, Material.COOKED_BEEF, Material.COOKED_CHICKEN,
			Material.COOKED_COD, Material.COOKED_MUTTON, Material.COOKED_PORKCHOP, Material.COOKED_RABBIT, Material.COOKED_SALMON,
			Material.COOKIE, Material.DRIED_KELP, Material.ENCHANTED_GOLDEN_APPLE, Material.GOLDEN_APPLE, Material.GOLDEN_CARROT, Material.HONEY_BOTTLE, Material.MELON_SLICE,
			Material.MUSHROOM_STEW, Material.MUTTON, Material.POISONOUS_POTATO, Material.PORKCHOP, Material.POTATO, Material.PUFFERFISH, Material.PUMPKIN_PIE, Material.RABBIT,
			Material.RABBIT_STEW, Material.ROTTEN_FLESH, Material.SPIDER_EYE, Material.SUSPICIOUS_STEW, Material.SWEET_BERRIES, Material.TROPICAL_FISH
			};
	
	public static final Material[] BUFF_FOOD_LIST = {Material.ENCHANTED_GOLDEN_APPLE, Material.GOLDEN_APPLE, Material.GOLDEN_CARROT, Material.HONEY_BOTTLE};
	public static final Material[] POISON_FOOD_LIST = {Material.POISONOUS_POTATO, Material.PUFFERFISH, Material.ROTTEN_FLESH, Material.SPIDER_EYE};
	public static final Material[] RAW_FOOD_LIST = {Material.BEEF, Material.CHICKEN, Material.COD, Material.MUTTON, Material.PORKCHOP, Material.POTATO, Material.RABBIT, Material.TROPICAL_FISH};
	
	public static final Material[] UNSAFE_BLOCKS = {Material.CACTUS, Material.CAULDRON, Material.CHEST, Material.SPAWNER, 
			Material.TNT, Material.TRAPPED_CHEST, Material.DISPENSER, Material.DROPPER, Material.MAGMA_BLOCK
			};
	
	public static final EntityType[] HOSTILE_MOBS = {EntityType.BLAZE, EntityType.CAVE_SPIDER, EntityType.CREEPER, EntityType.DROWNED, EntityType.ELDER_GUARDIAN, EntityType.ENDERMAN,
			EntityType.EVOKER, EntityType.GHAST, EntityType.GUARDIAN, EntityType.HOGLIN, EntityType.HUSK, EntityType.MAGMA_CUBE, EntityType.PIGLIN, EntityType.PIGLIN_BRUTE, EntityType.PILLAGER,
			EntityType.RAVAGER, EntityType.SKELETON, EntityType.SPIDER, EntityType.STRAY, EntityType.VINDICATOR, EntityType.WITCH, EntityType.WITHER_SKELETON, EntityType.ZOGLIN,
			EntityType.ZOMBIE, EntityType.ZOMBIE_VILLAGER, EntityType.ZOMBIFIED_PIGLIN
			};
	
	public static final Material[] ENCHANT_ARMOR = {Material.LEATHER_CHESTPLATE, Material.GOLDEN_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.IRON_CHESTPLATE, Material.DIAMOND_CHESTPLATE, Material.NETHERITE_CHESTPLATE, 
			Material.LEATHER_LEGGINGS, Material.GOLDEN_LEGGINGS, Material.CHAINMAIL_LEGGINGS, Material.IRON_LEGGINGS, Material.DIAMOND_LEGGINGS, Material.NETHERITE_LEGGINGS
			};
	public static final Material[] ENCHANT_AXE = {Material.WOODEN_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.GOLDEN_AXE, Material.DIAMOND_AXE, Material.NETHERITE_AXE};
	public static final Material[] ENCHANT_BOOTS = {Material.LEATHER_BOOTS, Material.GOLDEN_BOOTS, Material.CHAINMAIL_BOOTS, Material.IRON_BOOTS, Material.DIAMOND_BOOTS, Material.NETHERITE_BOOTS};
	public static final Material[] ENCHANT_HELMET = {Material.LEATHER_HELMET, Material.GOLDEN_HELMET, Material.CHAINMAIL_HELMET, Material.IRON_HELMET, Material.DIAMOND_HELMET, Material.NETHERITE_HELMET, Material.TURTLE_HELMET};
	public static final Material[] ENCHANT_HOE = {Material.WOODEN_HOE, Material.STONE_HOE, Material.IRON_HOE, Material.GOLDEN_HOE, Material.DIAMOND_HOE, Material.NETHERITE_HOE};
	public static final Material[] ENCHANT_PICKAXE = {Material.WOODEN_PICKAXE, Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.GOLDEN_PICKAXE, Material.DIAMOND_PICKAXE, Material.NETHERITE_PICKAXE};
	public static final Material[] ENCHANT_SHOVEL = {Material.WOODEN_SHOVEL, Material.STONE_SHOVEL, Material.IRON_SHOVEL, Material.GOLDEN_SHOVEL, Material.DIAMOND_SHOVEL, Material.NETHERITE_SHOVEL};
	public static final Material[] ENCHANT_SWORD = {Material.WOODEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLDEN_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD};
	
	public static final Material[] ENCHANTABLE = {Material.LEATHER_CHESTPLATE, Material.GOLDEN_CHESTPLATE, Material.CHAINMAIL_CHESTPLATE, Material.IRON_CHESTPLATE, Material.DIAMOND_CHESTPLATE, Material.NETHERITE_CHESTPLATE, 
			Material.LEATHER_LEGGINGS, Material.GOLDEN_LEGGINGS, Material.CHAINMAIL_LEGGINGS, Material.IRON_LEGGINGS, Material.DIAMOND_LEGGINGS, Material.NETHERITE_LEGGINGS,
			Material.WOODEN_AXE, Material.STONE_AXE, Material.IRON_AXE, Material.GOLDEN_AXE, Material.DIAMOND_AXE, Material.NETHERITE_AXE,
			Material.LEATHER_BOOTS, Material.GOLDEN_BOOTS, Material.CHAINMAIL_BOOTS, Material.IRON_BOOTS, Material.DIAMOND_BOOTS, Material.NETHERITE_BOOTS,
			Material.LEATHER_HELMET, Material.GOLDEN_HELMET, Material.CHAINMAIL_HELMET, Material.IRON_HELMET, Material.DIAMOND_HELMET, Material.NETHERITE_HELMET, Material.TURTLE_HELMET,
			Material.WOODEN_HOE, Material.STONE_HOE, Material.IRON_HOE, Material.GOLDEN_HOE, Material.DIAMOND_HOE, Material.NETHERITE_HOE,
			Material.WOODEN_PICKAXE, Material.STONE_PICKAXE, Material.IRON_PICKAXE, Material.GOLDEN_PICKAXE, Material.DIAMOND_PICKAXE, Material.NETHERITE_PICKAXE,
			Material.WOODEN_SHOVEL, Material.STONE_SHOVEL, Material.IRON_SHOVEL, Material.GOLDEN_SHOVEL, Material.DIAMOND_SHOVEL, Material.NETHERITE_SHOVEL,
			Material.WOODEN_SWORD, Material.STONE_SWORD, Material.IRON_SWORD, Material.GOLDEN_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD
			};
	
	public static final Enchantment[] SWORD_ENCHANTMENTS = {Enchantment.DAMAGE_ALL, Enchantment.DAMAGE_ARTHROPODS, Enchantment.DAMAGE_UNDEAD, Enchantment.FIRE_ASPECT, Enchantment.LOOT_BONUS_MOBS, Enchantment.KNOCKBACK, Enchantment.SWEEPING_EDGE, Enchantment.DURABILITY, Enchantment.VANISHING_CURSE};
	public static final Enchantment[] TOOL_ENCHANTMENTS = {Enchantment.DIG_SPEED, Enchantment.SILK_TOUCH, Enchantment.DURABILITY, Enchantment.VANISHING_CURSE};
	public static final Enchantment[] HELMET_ENCHANTMENTS = {Enchantment.OXYGEN, Enchantment.WATER_WORKER, Enchantment.THORNS, Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_EXPLOSIONS, Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_PROJECTILE, Enchantment.DURABILITY, Enchantment.BINDING_CURSE, Enchantment.VANISHING_CURSE};
	public static final Enchantment[] ARMOR_ENCHANTMENTS = {Enchantment.THORNS, Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_EXPLOSIONS, Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_PROJECTILE, Enchantment.DURABILITY, Enchantment.BINDING_CURSE, Enchantment.VANISHING_CURSE};
	public static final Enchantment[] BOOT_ENCHANTMENTS = {Enchantment.DEPTH_STRIDER, Enchantment.FROST_WALKER, Enchantment.SOUL_SPEED, Enchantment.PROTECTION_FALL, Enchantment.THORNS, Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.PROTECTION_EXPLOSIONS, Enchantment.PROTECTION_FIRE, Enchantment.PROTECTION_PROJECTILE, Enchantment.DURABILITY, Enchantment.BINDING_CURSE, Enchantment.VANISHING_CURSE};
	public static final Enchantment[] BOW_ENCHANTMENTS = {Enchantment.ARROW_DAMAGE, Enchantment.ARROW_FIRE, Enchantment.ARROW_INFINITE, Enchantment.ARROW_KNOCKBACK, Enchantment.DURABILITY, Enchantment.VANISHING_CURSE};
	public static final Enchantment[] CROSSBOW_ENCHANTMENTS = {Enchantment.PIERCING, Enchantment.MULTISHOT, Enchantment.QUICK_CHARGE, Enchantment.DURABILITY, Enchantment.VANISHING_CURSE};
	public static final Enchantment[] TRIDENT_ENCHANTMENTS = {Enchantment.CHANNELING, Enchantment.LOYALTY, Enchantment.RIPTIDE, Enchantment.IMPALING, Enchantment.DURABILITY, Enchantment.VANISHING_CURSE};
	public static final Enchantment[] FISHING_ROD_ENCHANTMENTS = {Enchantment.LUCK, Enchantment.LURE, Enchantment.DURABILITY, Enchantment.VANISHING_CURSE};

	public static final Material[] ENCHANT_MATERIAL_WOOD = {Material.WOODEN_AXE, Material.WOODEN_HOE, Material.WOODEN_PICKAXE, Material.WOODEN_SHOVEL, Material.WOODEN_SWORD};
	public static final Material[] ENCHANT_MATERIAL_LEATHER = {Material.LEATHER_BOOTS, Material.LEATHER_CHESTPLATE, Material.LEATHER_HELMET, Material.LEATHER_LEGGINGS};
	public static final Material[] ENCHANT_MATERIAL_STONE = {Material.STONE_AXE, Material.STONE_HOE, Material.STONE_PICKAXE, Material.STONE_SHOVEL, Material.STONE_SWORD};
	public static final Material[] ENCHANT_MATERIAL_CHAINMAIL = {Material.CHAINMAIL_BOOTS, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_HELMET, Material.CHAINMAIL_LEGGINGS};
	public static final Material[] ENCHANT_MATERIAL_IRON_ARMOR = {Material.IRON_BOOTS, Material.IRON_CHESTPLATE, Material.IRON_HELMET, Material.IRON_LEGGINGS};
	public static final Material[] ENCHANT_MATERIAL_IRON_TOOL = {Material.IRON_AXE, Material.IRON_HOE, Material.IRON_PICKAXE, Material.IRON_SHOVEL, Material.IRON_SWORD};
	public static final Material[] ENCHANT_MATERIAL_GOLDEN_ARMOR = {Material.GOLDEN_BOOTS, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_HELMET, Material.GOLDEN_LEGGINGS};
	public static final Material[] ENCHANT_MATERIAL_GOLDEN_TOOL = {Material.GOLDEN_AXE, Material.GOLDEN_HOE, Material.GOLDEN_PICKAXE, Material.GOLDEN_SHOVEL, Material.GOLDEN_SWORD};
	public static final Material[] ENCHANT_MATERIAL_DIAMOND = {Material.DIAMOND_AXE, Material.DIAMOND_HOE, Material.DIAMOND_PICKAXE, Material.DIAMOND_SHOVEL, Material.DIAMOND_SWORD,
			Material.DIAMOND_BOOTS, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_HELMET, Material.DIAMOND_LEGGINGS
			};
	public static final Material[] ENCHANT_MATERIAL_NETHERITE = {Material.NETHERITE_AXE, Material.NETHERITE_HOE, Material.NETHERITE_PICKAXE, Material.NETHERITE_SHOVEL, Material.NETHERITE_SWORD,
			Material.NETHERITE_BOOTS, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_HELMET, Material.NETHERITE_LEGGINGS
			};
}