package ratatoskr.hardModeRevamp.loot;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerFish implements Listener {
	
	@EventHandler
	public void onPlayerFish(PlayerFishEvent event) {
		if(event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
			Item item = (Item) event.getCaught();
			ItemMeta meta = item.getItemStack().getItemMeta();
			
			if(!meta.hasEnchants() && item.getItemStack().getType() != Material.ENCHANTED_BOOK) {
				return;
			}
			
			Integer remove = item.getItemStack().removeEnchantment(Enchantment.MENDING);
			if(remove > 0) {
				item.getItemStack().addUnsafeEnchantment(Enchantment.DURABILITY, 3);
			}
			remove = item.getItemStack().removeEnchantment(Enchantment.LOOT_BONUS_BLOCKS);
				if(remove > 0) {
					item.getItemStack().addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);
			}
			if(Math.random() < 0.875) {
				if(!item.getItemStack().containsEnchantment(Enchantment.BINDING_CURSE) && !item.getItemStack().containsEnchantment(Enchantment.VANISHING_CURSE)) {
					item.getItemStack().addUnsafeEnchantment(Enchantment.VANISHING_CURSE, 1);
				}
			}
		}
	}
}
