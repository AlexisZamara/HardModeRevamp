package ratatoskr.hardModeRevamp.mobs;

import java.util.Arrays;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.plugin.Plugin;

import ratatoskr.hardModeRevamp.Main;

public class EntityChangeBlock implements Listener {
	Plugin plugin = Main.getPlugin();
	
	@EventHandler
	public void onEntityChangeBlock(EntityChangeBlockEvent event) {
		if(plugin.getConfig().getBoolean("antienderman.enabled") && event.getEntityType() == EntityType.ENDERMAN) {
			if(plugin.getConfig().getBoolean("antienderman.exceptions") && !Arrays.asList(plugin.getConfig().getList("antienderman.list").toArray()).contains(event.getBlock().getType().toString().toLowerCase())) {
				event.setCancelled(true);
			}
			else {
				event.setCancelled(true);
			}
		}
	}
}
