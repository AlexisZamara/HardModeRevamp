package ratatoskr.hardModeRevamp.logger;

import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;

import ratatoskr.hardModeRevamp.Main;

public class GriefLogging implements Listener {
	private Plugin plugin = Main.getPlugin();
	
	public void onBlockBroken(BlockBreakEvent event) {
		if(plugin.getConfig().getBoolean("grieflogs.enabled")) {
			FixedMetadataValue playerMetadata = new FixedMetadataValue(plugin, event.getPlayer().getName());
			if(event.getBlock().hasMetadata("ppb") && event.getBlock().getMetadata("ppb") != playerMetadata) {
				String log = event.getPlayer().getName() + " has broken " +  event.getBlock().getType().toString() + " at coordinates: " + event.getBlock().getLocation().toString() + " ---- PPB METADATA: " + event.getBlock().getMetadata("ppb").toString();
				Main.log(log, "grieflog.txt");
			}
		}
	}
}
