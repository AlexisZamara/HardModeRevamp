package ratatoskr.hardModeRevamp.items;

import ratatoskr.hardModeRevamp.logger.Logging;
import ratatoskr.hardModeRevamp.utils.RConstants;

import java.util.Arrays;
import java.util.UUID;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ItemEventHandler implements Listener {
	// KNOWN ISSUES:
	// addAttributeModifier even with Operation.ADD_NUMBER does not actually ADD to the BASE VALUE, it SETS the value for all slots (Main, Off, Hat, Chest, Leg, Feet)
	// FIX:
	// either find a way to ADD to the default attribute
	
	@EventHandler
	public void onCraftItem(CraftItemEvent event) {
		if(Arrays.asList(RConstants.REWORKED_SWORDS).contains(event.getRecipe().getResult().getType())) {			
			AttributeModifier newMod = getItemAttributes(event.getRecipe().getResult(), -1.0);
			
			ItemStack sword = event.getRecipe().getResult();
			ItemMeta swordMeta = sword.getItemMeta();
			swordMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, newMod);
			
			swordMeta.getPersistentDataContainer().set(RConstants.PERSISTENT_KEY, PersistentDataType.STRING, RConstants.PERSISTENT_REWORKED_STRING);
			
			sword.setItemMeta(swordMeta);
		}
		else if(Arrays.asList(RConstants.REWORKED_AXES).contains(event.getRecipe().getResult().getType())) {
			AttributeModifier newMod = getItemAttributes(event.getRecipe().getResult(), -7.0);
			
			ItemStack axe = event.getRecipe().getResult();
			ItemMeta axeMeta = axe.getItemMeta();
			axeMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, newMod);
			
			axeMeta.getPersistentDataContainer().set(RConstants.PERSISTENT_KEY, PersistentDataType.STRING, RConstants.PERSISTENT_REWORKED_STRING);
			
			axe.setItemMeta(axeMeta);
		}
		else if(Arrays.asList(RConstants.REWORKED_GOLDEN_ARMOR).contains(event.getRecipe().getResult().getType())) {
			AttributeModifier newMod = getItemAttributes(event.getRecipe().getResult(), 1.5);
			
			ItemStack armor = event.getRecipe().getResult();
			ItemMeta armorMeta = armor.getItemMeta();
			armorMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, newMod);
			
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
		
		ItemMeta meta = updateMeta(item);
		if(meta == null) {
			Logging.logError("ENTITY DROP ITEM IS NOT GOLDEN_ARMOR", 2);
			return;
		}
		item.setItemMeta(meta);
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
		ItemMeta meta = updateMeta(item);
		if(meta == null) {
			Logging.logError("ENTITY PICKUP ITEM IS NOT GOLDEN_ARMOR", 2);
			return;
		}
		item.setItemMeta(meta);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if(event.getInventory().getType() == InventoryType.CREATIVE || event.getWhoClicked().getGameMode() != GameMode.SURVIVAL) {
			return;
		}
		
		ItemStack item = event.getCurrentItem();
		if(item == null || item.getType() == Material.AIR) {
			return;
		}
		if(!Arrays.asList(RConstants.REWORKED_ITEMS).contains(item.getType())) {
			return;
		}
		PersistentDataContainer data = item.getItemMeta().getPersistentDataContainer();
		if(data.has(RConstants.PERSISTENT_KEY, PersistentDataType.STRING)) {
			return;
		}
		
		ItemMeta meta = updateMeta(item);
		if(meta == null) {
			Logging.logError("INVENTORY CLICK ITEM IS NOT GOLDEN_ARMOR", 2);
			return;
		}
		item.setItemMeta(meta);
	}
	
	private AttributeModifier getItemAttributes(ItemStack item, Double mod) {
		String modName = ItemType.valueOf(item.getType().toString()).getName();
		Double modAmt = ItemType.valueOf(item.getType().toString()).getModifier() + mod;
		EquipmentSlot modSlot = ItemType.valueOf(item.getType().toString()).getSlot();
		
		return new AttributeModifier(UUID.randomUUID(), modName, modAmt, Operation.ADD_NUMBER, modSlot);
	}
	
	private ItemMeta updateMeta(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		
		if(Arrays.asList(RConstants.REWORKED_AXES).contains(item.getType())) {
			AttributeModifier newMod = getItemAttributes(item, -7.0);
			meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, newMod);
		}
		else if(Arrays.asList(RConstants.REWORKED_SWORDS).contains(item.getType())) {
			AttributeModifier newMod = getItemAttributes(item, -1.0);
			meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, newMod);
		}
		else {
			AttributeModifier newMod = getItemAttributes(item, 1.5);
			meta.addAttributeModifier(Attribute.GENERIC_ARMOR, newMod);
		}
		
		meta.getPersistentDataContainer().set(RConstants.PERSISTENT_KEY, PersistentDataType.STRING, RConstants.PERSISTENT_REWORKED_STRING);
		
		return meta;
	}
}
