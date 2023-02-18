package ratatoskr.hardModeRevamp.stamina;

public enum FoodType {
	
	APPLE(3, 4.0),
	BAKED_POTATO(5, 4.0),
	BEEF(1, 0.0),
	BEETROOT(2, 4.0),
	BEETROOT_SOUP(6, 8.0),
	BREAD(4, 4.0),
	CAKE(3, 10.0),
	CARROT(3, 3.0),
	CHICKEN(1, 0.0),
	CHORUS_FRUIT(3, 3.0),
	COD(2, 2.0),
	COOKED_BEEF(7, 6.0),
	COOKED_CHICKEN(5, 5.0),
	COOKED_COD(5, 5.0),
	COOKED_MUTTON(6, 6.0),
	COOKED_PORKCHOP(7, 7.0),
	COOKED_RABBIT(5, 5.0),
	COOKED_SALMON(5, 5.0),
	COOKIE(3, 1.0),
	DRIED_KELP(2, 2.0),
	ENCHANTED_GOLDEN_APPLE(7, 6.0),
	GOLDEN_APPLE(5, 4.0),
	GOLDEN_CARROT(5, 8.0),
	HONEY_BOTTLE(4, 4.0),
	MELON_SLICE(2, 3.0),
	MUSHROOM_STEW(6, 8.0),
	MUTTON(1, 0.0),
	POISONOUS_POTATO(1, 0.0),
	PORKCHOP(1, 0.0),
	POTATO(1, 2.0),
	PUFFERFISH(2, 0.0),
	PUMPKIN_PIE(8, 8.0),
	RABBIT(1, 0.0),
	RABBIT_STEW(7, 9.0),
	ROTTEN_FLESH(3, 3.0),
	SALMON(2, 2.0),
	SPIDER_EYE(1, 0.0),
	SUSPICIOUS_STEW(5, 7.0),
	SWEET_BERRIES(2, 3.0),
	TROPICAL_FISH(3, 1.0);
	
	
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
