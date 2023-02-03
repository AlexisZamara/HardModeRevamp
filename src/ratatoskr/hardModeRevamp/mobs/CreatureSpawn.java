package ratatoskr.hardModeRevamp.mobs;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
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
			((Creeper) event.getEntity()).setExplosionRadius(((Creeper) event.getEntity()).getExplosionRadius() + 1); // for testing purposes, increase this value to 10
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
			AttributeInstance reinforcement = event.getEntity().getAttribute(Attribute.ZOMBIE_SPAWN_REINFORCEMENTS);
			AttributeModifier add = new AttributeModifier("ZOMBIE_REINFORCEMENT_MODIFIER", 0.05, AttributeModifier.Operation.ADD_NUMBER);
			reinforcement.addModifier(add);
		}
		
		Double health = Random.RandomDouble(event.getEntity().getMaxHealth() * 0.85, event.getEntity().getMaxHealth() * 1.2);
		event.getEntity().setMaxHealth(health);
		event.getEntity().setHealth(health);
	}
}
