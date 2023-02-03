package ratatoskr.hardModeRevamp.utils;

import ratatoskr.hardModeRevamp.Main;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public class RConstants {
	public static final String PERSISTENT_REWORKED_STRING = "reworked";
	public static final String BREWING_XP_STRING = "brew_xp";
	public static final String MERCHANT_LOADED_STRING = "inventory_set";
	public static final String PIGLIN_GOLD_PICKUP = "piglin_pickup";
	public static final String PERSISTENT_KEY_STRING = "Ratatoskr-Reworked";
	public static final NamespacedKey PERSISTENT_KEY = new NamespacedKey(Main.getPlugin(),PERSISTENT_KEY_STRING);
	
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
}
