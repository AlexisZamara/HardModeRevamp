package ratatoskr.hardModeRevamp.listeners;

import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import ratatoskr.hardModeRevamp.Main;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class BlockPlaced implements Listener {
	private Plugin plugin = Main.getPlugin();
	
	@EventHandler
	public void onBlockPlaced(BlockPlaceEvent event) {
		event.getBlock().setMetadata("ppb", new FixedMetadataValue(plugin, event.getPlayer().getName()));
	}
}
