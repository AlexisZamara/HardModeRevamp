package ratatoskr.hardModeRevamp.piglins;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import ratatoskr.hardModeRevamp.utils.RConstants;

public class PiglinHandler implements Listener {
	
	@EventHandler (priority = EventPriority.HIGH)
	public void onPiglinPickupItem(EntityPickupItemEvent event) {
		if(event.getEntityType() != EntityType.PIGLIN || event.getItem().getItemStack().getType() != Material.GOLD_INGOT) {
			return;
		}
		
		LivingEntity piglin = event.getEntity();
		piglin.getPersistentDataContainer().set(RConstants.PERSISTENT_KEY, PersistentDataType.STRING, RConstants.PIGLIN_GOLD_PICKUP);
	}
	
	@EventHandler
	public void onPiglinDropItem(EntityDropItemEvent event) {
		if(event.getEntityType() != EntityType.PIGLIN || !event.getEntity().getPersistentDataContainer().has(RConstants.PERSISTENT_KEY, PersistentDataType.STRING)) {
			return;
		}
		
		Entity piglin = event.getEntity();
		piglin.getPersistentDataContainer().remove(RConstants.PERSISTENT_KEY);
		
		Double rng = 100.000 - (Math.random() * 100.0);
		Item droppedItem = event.getItemDrop();
		ItemStack newItem = PiglinBarters.barterResult(rng);
		
		droppedItem.setItemStack(newItem);
		// event.getItemDrop().setItemStack(newItem);
	}
}
