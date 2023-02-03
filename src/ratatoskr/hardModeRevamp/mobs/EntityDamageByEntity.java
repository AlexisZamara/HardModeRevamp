package ratatoskr.hardModeRevamp.mobs;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class EntityDamageByEntity implements Listener {
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if(event.getCause() == DamageCause.ENTITY_EXPLOSION && event.getEntityType() == EntityType.PLAYER) {
			if(Bukkit.getPlayer(event.getEntity().getUniqueId()).isBlocking()) {
				// if the player has a shield in each hand, main hand takes priority for blocking
				if(Bukkit.getPlayer(event.getEntity().getUniqueId()).getInventory().getItemInMainHand().getType() == Material.SHIELD) {
					ItemMeta shieldMeta = Bukkit.getPlayer(event.getEntity().getUniqueId()).getInventory().getItemInMainHand().getItemMeta();
					Damageable meta = (Damageable) shieldMeta;
					meta.setDamage(Bukkit.getPlayer(event.getEntity().getUniqueId()).getInventory().getItemInMainHand().getType().getMaxDurability());
					Bukkit.getPlayer(event.getEntity().getUniqueId()).getInventory().getItemInMainHand().setItemMeta((ItemMeta) meta);
				}
				else if(Bukkit.getPlayer(event.getEntity().getUniqueId()).getInventory().getItemInOffHand().getType() == Material.SHIELD) {
					ItemMeta shieldMeta = Bukkit.getPlayer(event.getEntity().getUniqueId()).getInventory().getItemInOffHand().getItemMeta();
					Damageable meta = (Damageable) shieldMeta;
					meta.setDamage(Bukkit.getPlayer(event.getEntity().getUniqueId()).getInventory().getItemInOffHand().getType().getMaxDurability());
					Bukkit.getPlayer(event.getEntity().getUniqueId()).getInventory().getItemInOffHand().setItemMeta((ItemMeta) meta);
				}
			}
		}
	}
}
