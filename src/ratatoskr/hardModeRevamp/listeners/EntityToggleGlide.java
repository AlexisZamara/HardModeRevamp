package ratatoskr.hardModeRevamp.listeners;

import org.bukkit.World.Environment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.plugin.Plugin;

import ratatoskr.hardModeRevamp.Main;

public class EntityToggleGlide implements Listener {
	private Plugin plugin = Main.getPlugin();
	
	// KNOWN ISSUE:
	// attempting to glide repeatedly locks the player into a gliding state
	
	@EventHandler
	public void onEntityToggleGlide(EntityToggleGlideEvent event) {
		if(event.getEntity().getWorld().getEnvironment() == Environment.NORMAL && plugin.getConfig().getBoolean("noelytra.overworld")) {
			event.setCancelled(true);
		}
		else if (event.getEntity().getWorld().getEnvironment() == Environment.NETHER && plugin.getConfig().getBoolean("noelytra.nether")) {
			event.setCancelled(true);
		}
		else if(event.getEntity().getWorld().getEnvironment() == Environment.THE_END && plugin.getConfig().getBoolean("noelytra.end")) {
			event.setCancelled(true);
		}
	}
}
