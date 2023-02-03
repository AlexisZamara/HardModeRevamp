package ratatoskr.hardModeRevamp.stamina;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExhaustionEvent;
import org.bukkit.event.entity.EntityExhaustionEvent.ExhaustionReason;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.potion.PotionEffectType;

public class Stamina implements Listener {
	
	// KNOWN ISSUES:
	// stamina does not appear to go down
	// player cannot eat food unless stamina is lower than maximum, even when damaged
	
	// SOLUTION:
	// on item right click (?) EventHandler for food logic?
	
	@EventHandler
	public void onPlayerSprint(PlayerToggleSprintEvent event) {
		if(event.getPlayer().getFoodLevel() > 0 && !(event.getPlayer().isSprinting())) {
			event.getPlayer().setSprinting(true);
		}
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onPlayerHungerChange(FoodLevelChangeEvent event) {
		if(event.isCancelled()) {
			return;
		}
		if(event.getEntity().hasPotionEffect(PotionEffectType.HUNGER)) {
			return; // potential issue: other effects will eat away at the player's food bar due to not cancelling hunger ticks while under this effect
		}
		// food level only changes when eating or sprinting
		if(event.getItem() == null && !((Player) event.getEntity()).isSprinting()) {
			event.setCancelled(true);
		}
		
		// stop player sprint at food level 0
		if(event.getItem() == null && ((Player) event.getEntity()).isSprinting() && event.getEntity().getFoodLevel() <= 0) {
			event.getEntity().setFoodLevel(0);
			((Player) event.getEntity()).setSprinting(false);
			event.setCancelled(true);
		}
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onPlayerExhaustionGain(EntityExhaustionEvent event) {
		if(event.getExhaustionReason() != ExhaustionReason.JUMP && event.getExhaustionReason() != ExhaustionReason.JUMP_SPRINT && event.getExhaustionReason() != ExhaustionReason.SPRINT) {
			event.setCancelled(true);
			return;
		}
		// needed testing: whether setExhaustion sets the TOTAL exhaustion value OR adds to it
		// testing method: if the sprint lasts forever regardless of the float value, then setExhaustion is the TOTAL
		// otherwise tweak it as needed to achieve 105 sprint-jump duration and 150 sprint duration
		if(event.getExhaustionReason() == ExhaustionReason.SPRINT) {
			event.setExhaustion(0.5f); // 0.02f
		}
		else if(event.getExhaustionReason() == ExhaustionReason.JUMP) {
			event.setExhaustion(0.5f); // 0.01f
		}
		else {
			event.setExhaustion(0.5f); // 0.05f
		}
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onPlayerHealthRegen(EntityRegainHealthEvent event) {
		if(event.getRegainReason() == RegainReason.SATIATED) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onPlayerHealthDrain(EntityDamageEvent event) {
		if(event.getCause() == DamageCause.STARVATION) {
			event.setCancelled(true);
		}
	}
}
