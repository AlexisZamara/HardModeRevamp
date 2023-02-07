package ratatoskr.hardModeRevamp.enchantments.loot;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Hopper;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.inventory.EquipmentSlot;

public class ChestLootListener implements Listener {
	// chests
	// TODO: listener for Hopper and Hopper Minecart
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if(event.getBlock().getType() != Material.HOPPER) {
			return;
		}

		Hopper hopper = (Hopper) event.getBlock().getBlockData();
		Block[] possibleChests = new Block[3];
		possibleChests[0] = event.getBlock().getRelative(hopper.getFacing());
		possibleChests[1] = event.getBlock().getRelative(BlockFace.UP);
		possibleChests[2] = event.getBlock().getRelative(BlockFace.DOWN);
		for(int i = 0; i < possibleChests.length; i++) {
			ChestLoot.populateChestLoot(possibleChests[i]);
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		ChestLoot.populateChestLoot(event.getBlock());
	}
	
	@EventHandler
	public void onBlockExplode(EntityExplodeEvent event) {
		for(Block block : event.blockList()) {
			ChestLoot.populateChestLoot(block);
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getHand() == EquipmentSlot.OFF_HAND) {
				return;
			}
			ChestLoot.populateChestLoot(event.getClickedBlock());
		}
	}
	
	// minecart chests
	@EventHandler
	public void onVehicleDestroy(VehicleDestroyEvent event) {
		ChestLoot.populateChestMinecartLoot(event.getVehicle());
	}
	
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		ChestLoot.populateChestMinecartLoot(event.getRightClicked());
	}
}
