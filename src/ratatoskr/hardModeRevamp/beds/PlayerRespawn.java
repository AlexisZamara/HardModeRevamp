package ratatoskr.hardModeRevamp.beds;

import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import ratatoskr.hardModeRevamp.logger.Logging;
import ratatoskr.hardModeRevamp.utils.RConstants;
import ratatoskr.hardModeRevamp.utils.Random;

public class PlayerRespawn implements Listener {
	
	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		if(!event.isBedSpawn()) {
			return;
		}
		
		Location bedLoc = event.getRespawnLocation();
		
		for(int i = 0; i < 16; i++) {
			Double rngX = Random.RandomDouble(-128.0, 128.0);
			Double rngZ = Random.RandomDouble(-128.0, 128.0);
			
			Location respawnLoc = new Location(bedLoc.getWorld(), bedLoc.getX() + rngX, bedLoc.getY(), bedLoc.getZ() + rngZ, (float)Math.random() * 360, 0);
			Integer highestY = respawnLoc.getWorld().getHighestBlockYAt(respawnLoc);
			respawnLoc.setY(highestY + 1.5);
			
			Block bottomBlock = respawnLoc.getBlock().getRelative(BlockFace.DOWN);
			
			if(respawnLoc.getBlock().getType() != Material.AIR) {
				Logging.logError("cannot respawn " + event.getPlayer().getName() + " at " + respawnLoc.toString() + " ---- Attempt " + i + "/16", 0);
				continue;
			}
			if(bottomBlock.isLiquid() || bottomBlock.isPassable()) {
				Logging.logError("cannot respawn " + event.getPlayer().getName() + " at " + respawnLoc.toString() + " ---- Attempt " + i + "/16", 0);
				continue;
			}
			if(bottomBlock.getType() == null || Arrays.asList(RConstants.UNSAFE_BLOCKS).contains(bottomBlock.getType())) {
				Logging.logError("cannot respawn " + event.getPlayer().getName() + " at " + respawnLoc.toString() + " ---- Attempt " + i + "/16", 0);
				continue;
			}

			event.setRespawnLocation(respawnLoc);
			Logging.logError(event.getPlayer().getName() + " respawn point set to " + respawnLoc.toString() + " on attempt " + i + "/16", 0);
			break;
		}
	}
}
