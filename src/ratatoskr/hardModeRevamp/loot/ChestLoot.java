package ratatoskr.hardModeRevamp.loot;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.minecart.StorageMinecart;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.Lootable;

import ratatoskr.hardModeRevamp.logger.Logging;

public class ChestLoot {
	
	// item = null does not remove the item
	// SOLUTION? accessing the block inventory and removing the item from the slot specifically?
	// item = new ItemSteck does not add an item
	// SOLUTION? accessing the block inventory and adding an item to the slot specifically?
	// meta.hasEnchant() doesn't detect ENCHANTED_BOOK again, 
	// SOLUTION? bruteforce item.removeEnchant()
	public static void populateChestLoot(Block block) {
		if(!isNewLootChest(block)) {
			return;
		}
		Lootable loot = (Lootable) block.getState();
		loot.getLootTable().populateLoot(new Random(), new LootContext.Builder(block.getLocation()).build());
		Inventory inv = ((Container) block.getState()).getInventory();
		Integer membrane = ratatoskr.hardModeRevamp.utils.Random.RandomInt(1,3);
		
		for(int i = 0; i < inv.getSize(); i++) {
			ItemStack item = inv.getItem(i);
			if(item == null) {
				if(block.getLocation().getWorld().getEnvironment() != Environment.THE_END) {
					continue;
				}
				if(membrane > 0) {
					Logging.logError("adding " + membrane + " phantom membrane to end chest", 0);
					inv.addItem(new ItemStack(Material.PHANTOM_MEMBRANE, membrane));
					membrane--;
				}
				continue;
			}
			if(item.getType() == Material.DIAMOND && block.getLocation().getWorld().getEnvironment() != Environment.THE_END) {
				Logging.logError("removing diamond in chest in dimension: " + block.getLocation().getWorld().getEnvironment(), 0);
				inv.remove(item);
				continue;
			}
			
			ItemMeta meta = item.getItemMeta();
			if(meta == null || (!meta.hasEnchants() && item.getType() != Material.ENCHANTED_BOOK)) {
				continue;
			}
			
			Logging.logError(item.getType().toString(), 0);
			Integer remove = item.removeEnchantment(Enchantment.MENDING);
			if(remove == 0) {
				Logging.logError("Mending removed", 0);
				item.addUnsafeEnchantment(Enchantment.DURABILITY, 3);
			}
			remove = item.removeEnchantment(Enchantment.LOOT_BONUS_BLOCKS);
			if(remove == 0) {
				Logging.logError("Fortune removed", 0);
				item.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);
			}
			if(Math.random() > 0.05 && block.getLocation().getWorld().getEnvironment() == Environment.THE_END) {
				item.removeEnchantment(Enchantment.BINDING_CURSE);
				item.removeEnchantment(Enchantment.VANISHING_CURSE);
			}
		}
	}
	
	private static boolean isNewLootChest(Block block) {
		if(block.getState() instanceof Lootable) {
			if(((Lootable) block.getState()).getLootTable() != null) {
				return true;
			}
		}
		return false;
	}
	
	public static void populateChestMinecartLoot(Entity entity) {
		if(!isNewLootCart(entity)) {
			return;
		}
		Lootable loot = (Lootable) entity;
		loot.getLootTable().populateLoot(new Random(), new LootContext.Builder(entity.getLocation()).build());
		Inventory inv = ((InventoryHolder) entity).getInventory();
		
		for(int i = 0; i < inv.getSize(); i++) {
			ItemStack item = inv.getItem(i);
			if(item == null) {
				continue;
			}
			
			ItemMeta meta = item.getItemMeta();
			if(meta == null || (!meta.hasEnchants() && item.getType() != Material.ENCHANTED_BOOK)) {
				continue;
			}
			
			Logging.logError(item.getType().toString(), 0);
			Integer remove = item.removeEnchantment(Enchantment.MENDING);
			if(remove == 0) {
				Logging.logError("Mending removed", 0);
				item.addEnchantment(Enchantment.DURABILITY, 3);
			}
			remove = item.removeEnchantment(Enchantment.LOOT_BONUS_BLOCKS);
			if(remove == 0) {
				Logging.logError("Fortune removed", 0);
				item.addEnchantment(Enchantment.SILK_TOUCH, 1);
			}
		}
	}
	
	private static boolean isNewLootCart(Entity entity) {
		if(entity instanceof StorageMinecart) {
			if(((StorageMinecart) entity).getLootTable() != null) {
				return true;
			}
		}
		return false;
	}
}
