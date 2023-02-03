package ratatoskr.hardModeRevamp.items;

import ratatoskr.hardModeRevamp.utils.RConstants;

import java.util.Arrays;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ItemEventHandler implements Listener {
	// KNOWN ISSUES:
	// addAttributeModifier even with Operation.ADD_NUMBER does not actually ADD to the BASE VALUE, it SETS the value for all slots (Main, Off, Hat, Chest, Leg, Feet)
	// FIX:
	// either find a way to ADD to the default attribute
	// OR configure each item type individually 
	
	@EventHandler
	public void onCraftItem(CraftItemEvent event) {
		if(Arrays.asList(RConstants.REWORKED_SWORDS).contains(event.getRecipe().getResult().getType())) {
			AttributeModifier attackMod = new AttributeModifier(UUID.randomUUID(), "generic.attack_damage", -1.0, AttributeModifier.Operation.ADD_NUMBER);
			
			ItemStack sword = event.getRecipe().getResult();
			ItemMeta swordMeta = sword.getItemMeta();
			swordMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, attackMod);
			
			swordMeta.getPersistentDataContainer().set(RConstants.PERSISTENT_KEY, PersistentDataType.STRING, RConstants.PERSISTENT_REWORKED_STRING);
			
			sword.setItemMeta(swordMeta);
		}
		else if(Arrays.asList(RConstants.REWORKED_AXES).contains(event.getRecipe().getResult().getType())) {
			AttributeModifier attackMod = new AttributeModifier(UUID.randomUUID(), "generic.attack_damage", -7.0, AttributeModifier.Operation.ADD_NUMBER);
			
			ItemStack axe = event.getRecipe().getResult();
			ItemMeta axeMeta = axe.getItemMeta();
			axeMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, attackMod);
			
			axeMeta.getPersistentDataContainer().set(RConstants.PERSISTENT_KEY, PersistentDataType.STRING, RConstants.PERSISTENT_REWORKED_STRING);
			
			axe.setItemMeta(axeMeta);
		}
		else if(Arrays.asList(RConstants.REWORKED_GOLDEN_ARMOR).contains(event.getRecipe().getResult().getType())) {
			AttributeModifier armorMod = new AttributeModifier(UUID.randomUUID(), "generic.armor", 1.5, AttributeModifier.Operation.ADD_NUMBER);
			
			ItemStack armor = event.getRecipe().getResult();
			ItemMeta armorMeta = armor.getItemMeta();
			armorMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, armorMod);
			
			armorMeta.getPersistentDataContainer().set(RConstants.PERSISTENT_KEY, PersistentDataType.STRING, RConstants.PERSISTENT_REWORKED_STRING);
			
			armor.setItemMeta(armorMeta);
		}
	}
	
	@EventHandler
	public void onEntityDropItem(EntityDropItemEvent event) {
		ItemStack item = event.getItemDrop().getItemStack();
		if(!Arrays.asList(RConstants.REWORKED_ITEMS).contains(item.getType())) {
			return;
		}
		PersistentDataContainer data = item.getItemMeta().getPersistentDataContainer();
		if(data.has(RConstants.PERSISTENT_KEY, PersistentDataType.STRING)) {
			return;
		}
		
		item.setItemMeta(updateMeta(item));
	}
	
	@EventHandler
	public void onEntityPickupItem(EntityPickupItemEvent event) {
		ItemStack item = event.getItem().getItemStack();
		if(!Arrays.asList(RConstants.REWORKED_ITEMS).contains(item.getType())) {
			return;
		}
		PersistentDataContainer data = item.getItemMeta().getPersistentDataContainer();
		if(data.has(RConstants.PERSISTENT_KEY, PersistentDataType.STRING)) {
			return;
		}
		
		item.setItemMeta(updateMeta(item));
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if(event.getInventory().getType() == InventoryType.CREATIVE) {
			return;
		}
		
		ItemStack item = event.getCurrentItem();
		if(item.getType() == Material.AIR) {
			return;
		}
		if(!Arrays.asList(RConstants.REWORKED_ITEMS).contains(item.getType())) {
			return;
		}
		PersistentDataContainer data = item.getItemMeta().getPersistentDataContainer();
		if(data.has(RConstants.PERSISTENT_KEY, PersistentDataType.STRING)) {
			return;
		}
		
		item.setItemMeta(updateMeta(item));
	}
	
	private ItemMeta updateMeta(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		
		if(Arrays.asList(RConstants.REWORKED_AXES).contains(item.getType())) {
			AttributeModifier attackMod = new AttributeModifier(UUID.randomUUID(), "generic.attack_damage", -7.0, AttributeModifier.Operation.ADD_NUMBER);
			meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, attackMod);
		}
		else if(Arrays.asList(RConstants.REWORKED_GOLDEN_ARMOR).contains(item.getType())) {
			AttributeModifier armorMod = new AttributeModifier(UUID.randomUUID(), "generic.armor", 1.5, AttributeModifier.Operation.ADD_NUMBER);
			meta.addAttributeModifier(Attribute.GENERIC_ARMOR, armorMod);
		}
		else {
			AttributeModifier attackMod = new AttributeModifier(UUID.randomUUID(), "generic.attack_damage", -1.0, AttributeModifier.Operation.ADD_NUMBER);
			meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, attackMod);
		}
		
		meta.getPersistentDataContainer().set(RConstants.PERSISTENT_KEY, PersistentDataType.STRING, RConstants.PERSISTENT_REWORKED_STRING);
		
		return meta;
	}
}
