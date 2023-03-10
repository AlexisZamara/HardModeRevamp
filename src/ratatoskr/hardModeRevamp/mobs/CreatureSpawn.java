package ratatoskr.hardModeRevamp.mobs;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import ratatoskr.hardModeRevamp.utils.Random;

public class CreatureSpawn implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		if(event.getEntityType() == EntityType.CREEPER) {	
			((Creeper) event.getEntity()).setExplosionRadius(((Creeper) event.getEntity()).getExplosionRadius() + 1);
		}
		else if(event.getEntityType() == EntityType.PHANTOM) {
			event.setCancelled(true);
			return;
		}
		else if(event.getEntityType() == EntityType.SPIDER) {
			AttributeInstance speed = event.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
			speed.setBaseValue(Random.RandomDouble(0.28, 0.33));
		}
		else if(event.getEntityType() == EntityType.ZOMBIE) {
			AttributeInstance speed = event.getEntity().getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
			speed.setBaseValue(Random.RandomDouble(0.22, 0.245));
		}
		
		Double health = Random.RandomDouble(event.getEntity().getMaxHealth() * 0.85, event.getEntity().getMaxHealth() * 1.2);
		event.getEntity().setMaxHealth(health);
		event.getEntity().setHealth(health);
	}
}
