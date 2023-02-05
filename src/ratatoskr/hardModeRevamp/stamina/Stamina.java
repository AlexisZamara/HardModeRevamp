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

import ratatoskr.hardModeRevamp.logger.Logging;

public class Stamina implements Listener {
	
	// KNOWN ISSUES:
	// PotionEffect.HUNGER does NOT drain HUNGER bar as it should
	// test eating cake for stamina
	
	@EventHandler
	public void onPlayerSprint(PlayerToggleSprintEvent event) {
		if(event.getPlayer().getFoodLevel() > 0 && !(event.getPlayer().isSprinting())) {
			event.getPlayer().setSprinting(true);
			event.setCancelled(true);
		}
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onPlayerHungerChange(FoodLevelChangeEvent event) {
		event.getEntity().setSaturation(0.0f);
		
		if(event.isCancelled()) {
			return;
		}
		if(event.getEntity().hasPotionEffect(PotionEffectType.HUNGER)) {
			Logging.logError("hunger effect", 0);
			return; // potential issue: other effects will eat away at the player's food bar due to not canceling hunger ticks while under this effect
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
		if(event.getExhaustionReason() == ExhaustionReason.SPRINT) {
			event.setExhaustion(0.13f); // 0.13f
		}
		else if(event.getExhaustionReason() == ExhaustionReason.JUMP) {
			event.setExhaustion(0.08f); // 0.08f
		}
		else {
			event.setExhaustion(0.2f); // 0.18f
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
