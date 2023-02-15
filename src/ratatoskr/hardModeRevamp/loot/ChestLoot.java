package ratatoskr.hardModeRevamp.loot;

import java.util.HashMap;
import java.util.Map;
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
	
	// currently untested
	@SuppressWarnings("deprecation")
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
					Logging.logError("adding phantom membrane to end chest", 0);
					item = new ItemStack(Material.PHANTOM_MEMBRANE, membrane);
					membrane--;
				}
				continue;
			}
			if(item.getType() == Material.DIAMOND && block.getLocation().getWorld().getEnvironment() != Environment.THE_END) {
				Logging.logError("removing diamond in chest in dimension: " + block.getLocation().getWorld().getEnvironment(), 0);
				item = null;
				continue;
			}
			ItemMeta meta = item.getItemMeta();
			if(meta == null || !meta.hasEnchants()) {
				Logging.logError("skipping " + item.getType() + ": unenchanted", 0);
				continue;
			}
			
			Map<Enchantment, Integer> newEnchants = new HashMap<Enchantment, Integer>();
			Double curse = Math.random();
			
			for(Map.Entry<Enchantment, Integer> entry : meta.getEnchants().entrySet()) {
				if(entry.getKey().getName() == Enchantment.LOOT_BONUS_BLOCKS.getName()) {
					newEnchants.put(Enchantment.SILK_TOUCH, 1);
					meta.removeEnchant(entry.getKey());
					continue;
				}
				if(entry.getKey().getName() == Enchantment.MENDING.getName()) {
					Logging.logError("removing Memding from " + item.getType().toString(), 0);
					newEnchants.put(Enchantment.DURABILITY, 3);
					meta.removeEnchant(entry.getKey());
					continue;
				}
				if(entry.getKey().getName() == Enchantment.BINDING_CURSE.getName() || entry.getKey().getName() == Enchantment.VANISHING_CURSE.getName()) {
					if(curse > 0.05 && block.getLocation().getWorld().getEnvironment() == Environment.THE_END) {
						Logging.logError("removing " + entry.getKey().getName() + " from " + item.getType().toString(), 0);
						meta.removeEnchant(entry.getKey());
						continue;
					}
				}
				newEnchants.put(entry.getKey(), entry.getValue());
				meta.removeEnchant(entry.getKey());
			}
			Logging.logError("new enchantments for " + item.getType().toString() + " = " + newEnchants.entrySet().toString(), 0);
			item.setItemMeta(meta);
			item.addUnsafeEnchantments(newEnchants);
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
			if(meta == null || !meta.hasEnchants()) {
				continue;
			}
			
			Map<Enchantment, Integer> newEnchants = new HashMap<Enchantment, Integer>();
			
			for(Map.Entry<Enchantment, Integer> entry : meta.getEnchants().entrySet()) {
				if(entry.getKey().getName() == Enchantment.LOOT_BONUS_BLOCKS.getName()) {
					newEnchants.put(Enchantment.SILK_TOUCH, 1);
					meta.removeEnchant(entry.getKey());
					continue;
				}
				if(entry.getKey().getName() == Enchantment.MENDING.getName()) {
					Logging.logError("removing Memding from " + item.getType().toString(), 0);
					newEnchants.put(Enchantment.DURABILITY, 3);
					meta.removeEnchant(entry.getKey());
					continue;
				}
				newEnchants.put(entry.getKey(), entry.getValue());
				meta.removeEnchant(entry.getKey());
			}
			Logging.logError("new enchantments for " + item.getType().toString() + " = " + newEnchants.entrySet().toString(), 0);
			item.setItemMeta(meta);
			item.addUnsafeEnchantments(newEnchants);
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
