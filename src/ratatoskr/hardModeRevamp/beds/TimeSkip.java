package ratatoskr.hardModeRevamp.beds;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.TimeSkipEvent;
import org.bukkit.event.world.TimeSkipEvent.SkipReason;

public class TimeSkip implements Listener {
	
	@EventHandler
	public void onTimeSkip(TimeSkipEvent event) {
		if(event.getSkipReason() == SkipReason.NIGHT_SKIP) {
			event.setCancelled(true);
		}
	}

}
