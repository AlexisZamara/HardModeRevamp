package ratatoskr.hardModeRevamp.listeners;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import ratatoskr.hardModeRevamp.Main;
import ratatoskr.hardModeRevamp.utils.RConstants;
import ratatoskr.hardModeRevamp.utils.Random;

public class InventoryClick implements Listener {
	Plugin plugin = Main.getPlugin();
	
	public InventoryAction[] takeActions = {InventoryAction.PICKUP_ALL, InventoryAction.MOVE_TO_OTHER_INVENTORY, InventoryAction.SWAP_WITH_CURSOR};
	public InventoryAction[] placeActions = {InventoryAction.PLACE_ALL, InventoryAction.MOVE_TO_OTHER_INVENTORY, InventoryAction.SWAP_WITH_CURSOR};
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if(plugin.getConfig().getBoolean("brewingxp.enabled")) {
			if(Arrays.asList(takeActions).contains(event.getAction()) && event.getClickedInventory().getType() == InventoryType.BREWING && event.getCurrentItem().getType() == Material.POTION) {
				if(!event.getCurrentItem().getItemMeta().getPersistentDataContainer().has(RConstants.PERSISTENT_KEY, PersistentDataType.STRING)) {
					ItemMeta meta = event.getCurrentItem().getItemMeta();
					meta.getPersistentDataContainer().set(RConstants.PERSISTENT_KEY, PersistentDataType.STRING, RConstants.BREWING_XP_STRING);
					event.getCurrentItem().setItemMeta(meta);
					
					ExperienceOrb expOrb = event.getClickedInventory().getLocation().getWorld().spawn(event.getClickedInventory().getLocation(), ExperienceOrb.class);
					List<Integer> values = plugin.getConfig().getIntegerList("brewingxp.brewxp");
					expOrb.setExperience(Random.RandomInt(values.get(0), values.get(1)));
				}
			}
			else if(Arrays.asList(placeActions).contains(event.getAction()) && event.getCurrentItem().getType() == Material.POTION && (event.getClickedInventory().getType() == InventoryType.BREWING || event.getClickedInventory().getType() == InventoryType.PLAYER)) {
				if(!event.getCurrentItem().getItemMeta().getPersistentDataContainer().has(RConstants.PERSISTENT_KEY, PersistentDataType.STRING)) {
					ItemMeta meta = event.getCurrentItem().getItemMeta();
					meta.getPersistentDataContainer().set(RConstants.PERSISTENT_KEY, PersistentDataType.STRING, RConstants.BREWING_XP_STRING);
					event.getCurrentItem().setItemMeta(meta);
				}
			}
		}
	}
}
