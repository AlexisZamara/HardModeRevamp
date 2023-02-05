package ratatoskr.hardModeRevamp.stamina;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityExhaustionEvent.ExhaustionReason;
import org.bukkit.event.entity.EntityExhaustionEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffectType;


public class Stamina implements Listener {

	@EventHandler
	public void onPlayerGainExhaustion(EntityExhaustionEvent event) {
		if(event.getEntityType() != EntityType.PLAYER) {
			return;
		}
		if(!((Player) event.getEntity()).isOnline()) {
			return;
		}
		if(event.getExhaustionReason() != ExhaustionReason.SPRINT || event.getExhaustionReason() != ExhaustionReason.JUMP_SPRINT) {
			event.setCancelled(true);
			return;
		}
		
		if(event.getExhaustionReason() == ExhaustionReason.SPRINT) {
			event.setExhaustion(0.13f); // sprint duration:
		}
		if(event.getExhaustionReason() == ExhaustionReason.JUMP_SPRINT) {
			event.setExhaustion(0.2f); // jump-sprint duration:
		}
	}
	
	@EventHandler
	public void onPlayerFoodLevelChange(FoodLevelChangeEvent event) {
		if(event.isCancelled()) {
			return;
		}
		if(event.getEntityType() != EntityType.PLAYER) {
			return;
		}
		if(event.getEntity().hasPotionEffect(PotionEffectType.HUNGER)) {
			return;
		}
		if(((Player) event.getEntity()).isSprinting()) {
			return;
		}
		
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onPlayerSpawn(PlayerRespawnEvent event) {
		event.getPlayer().setSaturation(0.0f);
	}
	
	// disable health regeneration and damage from hunger
	@EventHandler
	public void onPlayerHealthRegen(EntityRegainHealthEvent event) {
		if(event.getRegainReason() == RegainReason.SATIATED) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onPlayerHealthDrain(EntityDamageEvent event) {
		if(event.getCause() == DamageCause.STARVATION) {
			event.setCancelled(true);
		}
	}
}
