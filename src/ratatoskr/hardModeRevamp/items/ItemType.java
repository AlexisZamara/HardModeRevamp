package ratatoskr.hardModeRevamp.items;

import org.bukkit.attribute.Attribute;
import org.bukkit.inventory.EquipmentSlot;

public enum ItemType {
	
	WOODEN_SWORD("generic.attack_damage", 4.0, EquipmentSlot.HAND, Attribute.GENERIC_ATTACK_DAMAGE),
	STONE_SWORD("generic.attack_damage", 5.0, EquipmentSlot.HAND, Attribute.GENERIC_ATTACK_DAMAGE),
	GOLDEN_SWORD("generic.attack_damage", 4.0, EquipmentSlot.HAND, Attribute.GENERIC_ATTACK_DAMAGE),
	IRON_SWORD("generic.attack_damage", 6.0, EquipmentSlot.HAND, Attribute.GENERIC_ATTACK_DAMAGE),
	DIAMOND_SWORD("generic.attack_damage", 7.0, EquipmentSlot.HAND, Attribute.GENERIC_ATTACK_DAMAGE),
	NETHERITE_SWORD("generic.attack_damage", 8.0, EquipmentSlot.HAND, Attribute.GENERIC_ATTACK_DAMAGE),
	
	WOODEN_AXE("generic.attack_damage", 0.0, EquipmentSlot.HAND, Attribute.GENERIC_ATTACK_DAMAGE),
	STONE_AXE("generic.attack_damage", 0.0, EquipmentSlot.HAND, Attribute.GENERIC_ATTACK_DAMAGE),
	GOLDEN_AXE("generic.attack_damage", 0.0, EquipmentSlot.HAND, Attribute.GENERIC_ATTACK_DAMAGE),
	IRON_AXE("generic.attack_damage", 0.0, EquipmentSlot.HAND, Attribute.GENERIC_ATTACK_DAMAGE),
	DIAMOND_AXE("generic.attack_damage", 0.0, EquipmentSlot.HAND, Attribute.GENERIC_ATTACK_DAMAGE),
	NETHERITE_AXE("generic.attack_damage", 0.0, EquipmentSlot.HAND, Attribute.GENERIC_ATTACK_DAMAGE),
	
	GOLDEN_BOOTS("generic.armor", 1.0, EquipmentSlot.FEET, Attribute.GENERIC_ARMOR),
	GOLDEN_CHESTPLATE("generic.armor", 5.0, EquipmentSlot.CHEST, Attribute.GENERIC_ARMOR),
	GOLDEN_HELMET("generic.armor", 2.0, EquipmentSlot.HEAD, Attribute.GENERIC_ARMOR),
	GOLDEN_LEGGINGS("generic.armor", 3.0, EquipmentSlot.LEGS, Attribute.GENERIC_ARMOR);
	
	private ItemType(String name, Double modifier, EquipmentSlot slot, Attribute attribute) {
		this.name = name;
		this.modifier = modifier;
		this.slot = slot;
		this.attribute = attribute;
	}
	
	private String name;
	private Double modifier;
	private EquipmentSlot slot;
	private Attribute attribute;
	
	public String getName() {
		return name;
	}
	
	public Double getModifier() {
		return modifier;
	}
	
	public EquipmentSlot getSlot() {
		return slot;
	}
	
	public Attribute getAttribute() {
		return attribute;
	}
}