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
	private Double armorMod = 1.5;
	private Double axeMod = 1.0;
	private Double swordMod = -2.0;
	
	private Attribute armorAttr = Attribute.GENERIC_ARMOR;
	private Attribute weaponAttr = Attribute.GENERIC_ATTACK_DAMAGE;
	
	@EventHandler
	public void onCraftItem(CraftItemEvent event) {
		if(Arrays.asList(RConstants.REWORKED_SWORDS).contains(event.getRecipe().getResult().getType())) {			
			AttributeModifier newMod = getItemAttributes(event.getRecipe().getResult(), swordMod);
			
			ItemStack sword = event.getRecipe().getResult();
			ItemMeta swordMeta = sword.getItemMeta();
			swordMeta.addAttributeModifier(weaponAttr, newMod);
			
			swordMeta.getPersistentDataContainer().set(RConstants.PERSISTENT_KEY, PersistentDataType.STRING, RConstants.PERSISTENT_REWORKED_STRING);
			
			sword.setItemMeta(swordMeta);
		}
		else if(Arrays.asList(RConstants.REWORKED_AXES).contains(event.getRecipe().getResult().getType())) {
			AttributeModifier newMod = getItemAttributes(event.getRecipe().getResult(), axeMod);
			
			ItemStack axe = event.getRecipe().getResult();
			ItemMeta axeMeta = axe.getItemMeta();
			axeMeta.addAttributeModifier(weaponAttr, newMod);
			
			axeMeta.getPersistentDataContainer().set(RConstants.PERSISTENT_KEY, PersistentDataType.STRING, RConstants.PERSISTENT_REWORKED_STRING);
			
			axe.setItemMeta(axeMeta);
		}
		else if(Arrays.asList(RConstants.REWORKED_GOLDEN_ARMOR).contains(event.getRecipe().getResult().getType())) {
			AttributeModifier newMod = getItemAttributes(event.getRecipe().getResult(), armorMod);
			
			ItemStack armor = event.getRecipe().getResult();
			ItemMeta armorMeta = armor.getItemMeta();
			armorMeta.addAttributeModifier(armorAttr, newMod);
			
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
			Logging.logError("ENTITY DROP ITEM IS NOT GOLDEN_ARMOR: " + item.getType(), 2);
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
			Logging.logError("ENTITY PICKUP ITEM IS NOT GOLDEN_ARMOR: " + item.getType(), 2);
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
			Logging.logError("INVENTORY CLICK ITEM IS NOT GOLDEN_ARMOR: " + item.getType(), 2);
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
			AttributeModifier newMod = getItemAttributes(item, axeMod);
			meta.addAttributeModifier(weaponAttr, newMod);
		}
		else if(Arrays.asList(RConstants.REWORKED_SWORDS).contains(item.getType())) {
			AttributeModifier newMod = getItemAttributes(item, swordMod);
			meta.addAttributeModifier(weaponAttr, newMod);
		}
		else {
			AttributeModifier newMod = getItemAttributes(item, armorMod);
			meta.addAttributeModifier(armorAttr, newMod);
		}
		
		meta.getPersistentDataContainer().set(RConstants.PERSISTENT_KEY, PersistentDataType.STRING, RConstants.PERSISTENT_REWORKED_STRING);
		
		return meta;
	}
}
