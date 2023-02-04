package ratatoskr.hardModeRevamp.mobs;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import ratatoskr.hardModeRevamp.Main;
import ratatoskr.hardModeRevamp.logger.Logging;

public class EntityExplode implements Listener {
	private Plugin plugin = Main.getPlugin();
	
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent event) {
		if(plugin.getConfig().getBoolean("anticreeper.enabled") && event.getEntityType() == EntityType.CREEPER) {
			if(plugin.getConfig().getBoolean("anticreeper.placedonly")) {
				for (Block b : new ArrayList<Block>(event.blockList())) {
					Logging.logError(b.getType().toString(), 0);
					if(b.hasMetadata("ppb")) {
						event.getLocation().getWorld().dropItem(b.getLocation(), new ItemStack(b.getType()));
						b.setType(Material.AIR);
						event.blockList().remove(b);
					}
				}
			}
			else {
				event.blockList().clear();
			}
		}
	}
}
