package ratatoskr.hardModeRevamp.logger;

import java.text.SimpleDateFormat;
import java.util.Date;

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
				String log = new SimpleDateFormat("yy.MM.dd.HH.mm.ss").format(new Date()) + ": " + event.getPlayer().getName() + " has broken " +  event.getBlock().getType().toString() + " at coordinates: " + event.getBlock().getLocation().toString() + " ---- PPB METADATA: " + event.getBlock().getMetadata("ppb").toString();
				Main.log(log, "grieflogs.txt");
			}
		}
	}
}
