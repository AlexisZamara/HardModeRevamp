package ratatoskr.hardModeRevamp.piglins;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import ratatoskr.hardModeRevamp.utils.Random;

public class PiglinBarters {
	public static ItemStack barterResult(Double rng) {
		ItemStack droppedItem = null;
		Integer quantity = 1;
		
		if(rng > 84.444) {
			quantity = Random.RandomInt(3, 8);
			droppedItem = new ItemStack(Material.GRAVEL, quantity);
		}
		else if(rng > 68.888) {
			quantity = Random.RandomInt(1, 3);
			droppedItem = new ItemStack(Material.LEATHER, quantity);
		}
		else if(rng > 53.332) {
			quantity = Random.RandomInt(1, 4);
			droppedItem = new ItemStack(Material.CRYING_OBSIDIAN, quantity);
		}
		else if(rng > 42.221) {
			quantity = Random.RandomInt(4, 9);
			droppedItem = new ItemStack(Material.SPECTRAL_ARROW, quantity);
		}
		else if(rng > 32.888) {
			quantity = Random.RandomInt(1, 3);
			Integer color = Random.RandomInt(0, 3);
			switch(color) {
			case 0:
				droppedItem = new ItemStack(Material.GRAY_DYE, quantity);
				break;
			case 1:
				droppedItem = new ItemStack(Material.ORANGE_DYE, quantity);
				break;
			case 2:
				droppedItem = new ItemStack(Material.RED_DYE, quantity);
				break;
			case 3:
				droppedItem = new ItemStack(Material.YELLOW_DYE, quantity);
				break;
			}
		}
		else if(rng > 23.555) {
			quantity = Random.RandomInt(1, 12);
			droppedItem = new ItemStack(Material.CHARCOAL, quantity);
		}
		else if(rng > 16.444) {
			quantity = Random.RandomInt(1, 2);
			droppedItem = new ItemStack(Material.FIRE_CHARGE, quantity);
		}
		else if(rng > 11.555) {
			droppedItem = new ItemStack(Material.POTION, quantity);
			PotionMeta meta = (PotionMeta) droppedItem.getItemMeta();
			meta.setBasePotionData(new PotionData(PotionType.WATER));
			droppedItem.setItemMeta(meta);
		}
		else if(rng > 6.666) {
			quantity = Random.RandomInt(1, 8);
			droppedItem = new ItemStack(Material.GOLD_NUGGET, quantity);
		}
		else if(rng > 2.222) {
			Integer piece = Random.RandomInt(0, 3);
			switch(piece) {
			case 0:
				droppedItem = new ItemStack(Material.CHAINMAIL_BOOTS, quantity);
				break;
			case 1:
				droppedItem = new ItemStack(Material.CHAINMAIL_CHESTPLATE, quantity);
				break;
			case 2:
				droppedItem = new ItemStack(Material.CHAINMAIL_HELMET, quantity);
				break;
			case 3:
				droppedItem = new ItemStack(Material.CHAINMAIL_LEGGINGS, quantity);
				break;
			}
		}
		else if(rng > 1.333) {
			droppedItem = new ItemStack(Material.LAVA_BUCKET, quantity);
		}
		else if(rng > 0.888) {
			droppedItem = new ItemStack(Material.POTION, quantity);
			PotionMeta meta = (PotionMeta) droppedItem.getItemMeta();
			meta.setBasePotionData(new PotionData(PotionType.FIRE_RESISTANCE));
			droppedItem.setItemMeta(meta);
		}
		else if(rng > 0.444) {
			droppedItem = new ItemStack(Material.SPLASH_POTION, quantity);
			PotionMeta meta = (PotionMeta) droppedItem.getItemMeta();
			meta.setBasePotionData(new PotionData(PotionType.FIRE_RESISTANCE));
			droppedItem.setItemMeta(meta);
		}
		else {
			droppedItem = new ItemStack(Material.IRON_BOOTS, quantity);
			Integer level = Random.RandomInt(0, 2);
			droppedItem.addEnchantment(Enchantment.SOUL_SPEED, level);
		}
		
		return droppedItem;
	}
}
