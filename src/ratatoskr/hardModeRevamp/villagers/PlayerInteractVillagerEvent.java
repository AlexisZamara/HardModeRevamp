package ratatoskr.hardModeRevamp.villagers;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Merchant;
import org.bukkit.persistence.PersistentDataType;

import ratatoskr.hardModeRevamp.utils.RConstants;

public class PlayerInteractVillagerEvent implements Listener {
	
	@EventHandler
	public void onPlayerInteractVillagerEvent(PlayerInteractEntityEvent event) {
		if(event.getRightClicked().getType() != EntityType.VILLAGER || !(event.getRightClicked() instanceof Merchant)) {
			return;
		}
		
		Villager villager = (Villager) event.getRightClicked();
		if(!villager.getPersistentDataContainer().has(RConstants.PERSISTENT_KEY, PersistentDataType.STRING)) {
			TradesManager.loadTrades(villager);
		}
	}
}
