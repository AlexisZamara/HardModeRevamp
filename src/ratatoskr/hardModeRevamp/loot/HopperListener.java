package ratatoskr.hardModeRevamp.loot;

import org.bukkit.block.Chest;
import org.bukkit.entity.minecart.StorageMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;

public class HopperListener implements Listener {
	// listen for hoppers and hopper minecarts emptying chests
	@EventHandler
	public void onInventoryMoveItem(InventoryMoveItemEvent event) {
		if(event.getSource().getHolder() instanceof Chest) {
			ChestLoot.populateChestLoot(event.getSource().getLocation().getBlock());
		}
		else if(event.getSource().getHolder() instanceof StorageMinecart) {
			StorageMinecart minecart = (StorageMinecart) event.getSource().getHolder();
			ChestLoot.populateChestMinecartLoot(minecart);
		}
	}
}