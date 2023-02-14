package ratatoskr.hardModeRevamp.loot;

import java.util.Random;

import org.bukkit.block.Block;
import org.bukkit.block.Container;
import org.bukkit.entity.Entity;
import org.bukkit.entity.minecart.StorageMinecart;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.loot.LootContext;
import org.bukkit.loot.Lootable;

public class ChestLoot {
	
	// currently untested
	public static void populateChestLoot(Block block) {
		if(!isNewLootChest(block)) {
			return;
		}
		Lootable loot = (Lootable) block.getState();
		loot.getLootTable().populateLoot(new Random(), new LootContext.Builder(block.getLocation()).build());
		Inventory inv = ((Container) block.getState()).getInventory();
		for(int i = 0; i < inv.getSize(); i++) {
			ItemStack item = inv.getItem(i);
			if(item == null) {
				continue;
			}
			ItemMeta meta = item.getItemMeta();
			if(meta == null || !meta.hasEnchants()) {
				continue;
			}
			// TODO: check enchantments for illegal enchants
			// TODO: check chest location: if in the End, 5% chance of item having a curse, otherwise remove all curses
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
		// TODO: generate loot
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
