package ratatoskr.hardModeRevamp.stamina;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import ratatoskr.hardModeRevamp.utils.RConstants;

public class ConsumeFood implements Listener {
	
	@EventHandler
	public void onPlayerConsumeFood(PlayerItemConsumeEvent event) {
		if(event.getItem().getType() == Material.CHORUS_FRUIT || event.getItem().getType() == Material.SUSPICIOUS_STEW) {
			return;
		}
		
		if(Arrays.asList(RConstants.FOOD_LIST).contains(event.getItem().getType())) {
			Integer foodBonus = FoodType.valueOf(event.getItem().getType().toString()).getFood();
			
			event.setCancelled(true);
			
			if(event.getPlayer().getFoodLevel() < 20) {
				Integer food = Math.min(20, event.getPlayer().getFoodLevel() + foodBonus);
				event.getPlayer().setFoodLevel(food);
				event.getPlayer().setSaturation(0.0f);
			}
			if(event.getPlayer().getHealth() < 20.0) {
				setHealthRegen(event.getPlayer(), event.getItem().getType());
			}
			
			if(Arrays.asList(RConstants.BUFF_FOOD_LIST).contains(event.getItem().getType())) {
				applyFoodBuff(event.getPlayer(), event.getItem().getType());
			}
			else if(Arrays.asList(RConstants.POISON_FOOD_LIST).contains(event.getItem().getType())) {
				applyFoodPoison(event.getPlayer(), event.getItem().getType());
			}
			else if(Arrays.asList(RConstants.RAW_FOOD_LIST).contains(event.getItem().getType())) {
				eatRawFood(event.getPlayer());
			}
			
			if(event.getItem().getType() == event.getPlayer().getInventory().getItemInMainHand().getType()) {
				event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
			}
			else {
				event.getPlayer().getInventory().getItemInOffHand().setAmount(event.getPlayer().getInventory().getItemInOffHand().getAmount() - 1);
			}
		}
	}
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onPlayerHungerChange(FoodLevelChangeEvent event) {
		if(event.isCancelled()) {
			return;
		}
		if(event.getEntity().getType() != EntityType.PLAYER) {
			return;
		}
		if(event.getItem() == null) {
			return;
		}
		
		if(event.getItem().getType() == Material.CHORUS_FRUIT) {
			event.setCancelled(true);
			event.getEntity().setSaturation(0.0f);
			event.getEntity().setFoodLevel(Math.min(20, event.getEntity().getFoodLevel() + FoodType.CHORUS_FRUIT.getFood()));
			setHealthRegen((Player) event.getEntity(), Material.CHORUS_FRUIT);
		}
		else if(event.getItem().getType() == Material.SUSPICIOUS_STEW) {
			event.setCancelled(true);
			event.getEntity().setSaturation(0.0f);
			event.getEntity().setFoodLevel(Math.min(20, event.getEntity().getFoodLevel() + FoodType.SUSPICIOUS_STEW.getFood()));
			setHealthRegen((Player) event.getEntity(), Material.SUSPICIOUS_STEW);
		}
	}
	
	private void setHealthRegen(Player player, Material food) {
		if(player.hasPotionEffect(PotionEffectType.REGENERATION)) {
			player.removePotionEffect(PotionEffectType.REGENERATION);
		}
		Double healthBonus = FoodType.valueOf(food.toString()).getHealing();
		Double health = Math.min(healthBonus, 20.0 - player.getHealth());
		
		Integer regenRate = 12; // ticks per point @ Regen 3
		Integer regenDuration = regenRate * health.intValue();
		
		PotionEffect regen = new PotionEffect(PotionEffectType.REGENERATION, regenDuration, 2, false, false, false);
		player.addPotionEffect(regen);
	}
	
	private void applyFoodBuff(Player player, Material food) {
		if(food == Material.ENCHANTED_GOLDEN_APPLE) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 2400, 3));
			player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 6000, 0));
			player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 6000, 0));
			player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 600, 3));
		}
		else if(food == Material.GOLDEN_APPLE) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 2400, 0));
			player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1));
		}
		else if(food == Material.GOLDEN_CARROT) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 600, 0));
		}
		else if(food == Material.HONEY_BOTTLE) {
			player.removePotionEffect(PotionEffectType.POISON);
		}
	}
	
	private void applyFoodPoison(Player player, Material food) {
		if(food == Material.POISONOUS_POTATO) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0, false, false));
		}
		else if(food == Material.PUFFERFISH) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 1200, 1, false, false));
			player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 0, false, true, true));
		}
		else if(food == Material.ROTTEN_FLESH) {
			Double rng = Math.random();
			if(rng < 0.2) {
				return;
			}
			else {
				if(rng < 0.5) {
					player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100, 2, false, false, false));
					rng = Math.random();
					if(rng > 0.85) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0, false, false, false));
					}
				}
				else {
					player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0, false, false, false));
					rng = Math.random();
					if(rng > 0.85) {
						player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 100, 2, false, false, false));
					}
				}
			}
		}
		else if(food == Material.SPIDER_EYE) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0, false, false, false));
		}
	}
	
	private void eatRawFood(Player player) {
		Double rng = Math.random();
		if(rng < 0.2) {
			return;
		}
		player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 140, 0, false, false, false));
	}
}
