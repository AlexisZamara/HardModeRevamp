package ratatoskr.hardModeRevamp.stamina;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import ratatoskr.hardModeRevamp.Main;
import ratatoskr.hardModeRevamp.utils.RConstants;

public class CombatFatigue implements Listener {
	Plugin plugin = Main.getPlugin();
	
	@EventHandler
	public void onPlayerDamageByEntity(EntityDamageByEntityEvent event) {
		if(event.getEntity().getType() == EntityType.PLAYER) {
			Player player = (Player) event.getEntity();
			if(player.hasMetadata(RConstants.PERSISTENT_COMBAT_FATIGUE_STRING)) {
				for(MetadataValue meta : player.getMetadata(RConstants.PERSISTENT_COMBAT_FATIGUE_STRING)) {
					if(meta.getOwningPlugin() == plugin) {
						return;
					}
				}
			}
			
			player.setMetadata(RConstants.PERSISTENT_COMBAT_FATIGUE_STRING, new FixedMetadataValue(plugin, "r-combat-fatigue"));
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				public void run() {
					player.removeMetadata(RConstants.PERSISTENT_COMBAT_FATIGUE_STRING, plugin);
				}
			}, 65L);
		}
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		if(!event.getPlayer().hasMetadata(RConstants.PERSISTENT_COMBAT_FATIGUE_STRING)) {
			return;
		}
		boolean hasMeta = false;
		for(MetadataValue meta : event.getPlayer().getMetadata(RConstants.PERSISTENT_COMBAT_FATIGUE_STRING)) {
			if(meta.getOwningPlugin() == plugin) {
				hasMeta = true;
			}
		}
		if(!hasMeta) {
			return;
		}
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				event.getPlayer().removeMetadata(RConstants.PERSISTENT_COMBAT_FATIGUE_STRING, plugin);
			}
		}, 100L);
	}
}
