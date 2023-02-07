package ratatoskr.hardModeRevamp.enchantments.inventory;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements Listener {
	@EventHandler
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getClickedBlock().getType() == Material.ENCHANTING_TABLE) {
				
			}
			
			if(event.getClickedBlock().getType() == Material.ANVIL) {
				
			}
			
			if(event.getClickedBlock().getType() == Material.GRINDSTONE) {
				
			}
		}
	}
}
