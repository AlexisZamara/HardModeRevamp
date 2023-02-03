package ratatoskr.hardModeRevamp.stamina;

public enum FoodType {
	
	APPLE(4, 3.0),
	BAKED_POTATO(5, 4.0),
	BEEF(2, 2.0),
	BEETROOT(1, 1.0),
	BEETROOT_SOUP(8, 6.0),
	BREAD(5, 4.0),
	CAKE(2, 7.0),
	CARROT(3, 3.0),
	CHICKEN(0, 0.0),
	CHORUS_FRUIT(4, 5.0),
	COD(2, 0.0),
	COOKED_BEEF(8, 5.0),
	COOKED_CHICKEN(5, 5.0),
	COOKED_COD(5, 5.0),
	COOKED_MUTTON(6, 5.0),
	COOKED_PORKCHOP(8, 5.0),
	COOKED_RABBIT(5, 5.0),
	COOKED_SALMON(6, 5.0),
	COOKIE(3, 3.0),
	DRIED_KELP(3, 2.0),
	ENCHANTED_GOLDEN_APPLE(12, 4.0),
	GOLDEN_APPLE(8, 4.0),
	GOLDEN_CARROT(8, 4.0),
	HONEY_BOTTLE(5, 6.0),
	MELON_SLICE(2, 2.0),
	MUSHROOM_STEW(8, 6.0),
	MUTTON(2, 0.0),
	POISONOUS_POTATO(0, 0.0),
	PORKCHOP(3, 2.0),
	POTATO(1, 1.0),
	PUFFERFISH(0, 0.0),
	PUMPKIN_PIE(8, 5.0),
	RABBIT(3, 2.0),
	RABBIT_STEW(9, 6.0),
	ROTTEN_FLESH(4, 0.0),
	SPIDER_EYE(2, 0.0),
	SUSPICIOUS_STEW(8, 6.0),
	SWEET_BERRIES(3, 3.0),
	TROPICAL_FISH(1, 1.0);
	
	
	private FoodType(Integer food, Double healing) {
		this.food = food;
		this.healing = healing;
	}
	
	private Integer food;
	private Double healing;
	
	public Integer getFood() {
		return food;
	}
	
	public Double getHealing() {
		return healing;
	}
}
