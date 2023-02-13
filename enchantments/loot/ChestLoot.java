package ratatoskr.hardModeRevamp.enchantments.loot;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.minecart.StorageMinecart;
import org.bukkit.loot.Lootable;

public class ChestLoot {
	private static boolean isNewLootChest(Block block) {
		if(block.getState() instanceof Lootable) {
			if(((Lootable) block.getState()).getLootTable() != null) {
				return true;
			}
		}
		return false;
	}
	
	public static void populateChestLoot(Block block) {
		if(!isNewLootChest(block)) {
			return;
		}
		Lootable loot = (Lootable) block.getState();
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
	
	public static void populateChestMinecartLoot(Entity entity) {
		if(!isNewLootCart(entity)) {
			return;
		}
		Lootable loot = (Lootable) entity;
		// TODO: generate loot
	}
}
