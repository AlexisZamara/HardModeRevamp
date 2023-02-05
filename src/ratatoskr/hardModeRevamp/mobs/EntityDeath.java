package ratatoskr.hardModeRevamp.mobs;

import java.util.Arrays;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import ratatoskr.hardModeRevamp.logger.Logging;
import ratatoskr.hardModeRevamp.utils.RConstants;
import ratatoskr.hardModeRevamp.utils.Random;

public class EntityDeath implements Listener {
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		if(Arrays.asList(RConstants.HOSTILE_MOBS).contains(event.getEntityType())) {
			if(event.getDroppedExp() > 0) {
				Integer xp = event.getDroppedExp() + Random.RandomInt(1, 5);
				Logging.logError("base xp: " + event.getDroppedExp(), 0);
				Logging.logError("new xp:" + xp, 1);
				event.setDroppedExp(xp);
			}
		}
	}
}
