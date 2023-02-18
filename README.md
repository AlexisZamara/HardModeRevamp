# HardModeRevamp
a 1.16.5 Minecraft plugin to make things just a bit more difficult and make grinding a part of the game again

#### Release v1.0.0

## Full Changes
#### Enchantment Changes
- Fortune removed from the game
- Mending removed from the game
- Unbreaking now harder to obtain at the Enchanting Table
  - minimum enchanting level increased to 13/25/37 from 8/13/21
  - enchanting books is now the only way to obtain Unbreaking III from the enchanting table

#### Hostile Mob Changes
- all hostile mobs:
  - health values now randomized ([0.85 to 1.2] * base health value)
  - xp dropped increased by a random (int) between 1 and 5
- creepers:
  - explosion now always destroys a player's shield if blocking
  - explosion radius increased by 1
- endermen can no longer pick up blocks
- phantoms removed from the game
- spiders:
  - speed now a random value between 0.28 and 0.33
- zombies:
  - speed now a random value between 0.22 and 0.245

#### Item Changes
- breaking a crop with a hoe automatically replants it using one of the dropped seeds
- god apples can once again be crafted using shaped recipe:
  - A = Apple / G = Gold Block
    - G G G
    - G A G
    - G G G
- gold armor rebalanced:
  - gold chestplate armor value increased from 5 to 6.5
  - gold leggings armor value increased from 3 to 4.5
  - gold boots armor value increased from 1 to 2.5
- weapon damage rebalanced:
  - axe damage returned to pre-1.9 values (base fist damage +1, for a total of one heart of damage)
  - sword damage reduced by 1 (or half a heart)
  
#### Loot Table Changes
- diamonds removed from loot chests outside The End dimension
- enchanted items obtained from fishing have an 87.5% chance of having a curse
- enchanted items obtained from end city chests have a 5% chance of having a curse
- phantom membranes now generate in loot chests in The End dimension (0-6)

#### Gameplay Changes
- creeper explosions always cause blocks placed by a player to drop when destroyed 
  - the dropped items may still be destroyed by a different creeper explosion
- night can no longer be skipped
- respawning at a bed now spawns the player on the surface within a 128-block radius around the bed's location
  
#### Passive Mob Changes
- piglin bartering table replaced
- villagers now all offer the same trades, trade table replaced

#### Player Changes
- healing changes
  - eating food now applies a regeneration effect based on the type of food
  - all foods regenerate health at the same rate but each food regenerates a different amount
  - taking damage from a mob or player prevents food from giving the regeneration effect for 3 seconds
- hunger bar changed into a stamina bar
  - health regeneration is no longer tied to hunger
  - eating food recovers stamina and health at different rates
- elytra flight disabled outside of The End
- players now gain experience from farming
  - breaking crops with a hoe grants 1-2 points of experience
  - breaking pumpkins and melons with an axe grants 1-2 points of experience
- payers now gain experience from brewing
  - taking a potion from a brewing stand grands 4-6 points of experience


### Piglin Barter Table
| ITEM  | AMOUNT | ODDS |
| ----- | ------ | ---- |
| Iron Boots (Soul Speed) | 1 | 1 / 225 |
| Splash Potion (Fire Res) | 1 | 1 / 225 |
| Potion (Fire Res) | 1 | 1 / 225 |
| Lava Bucket | 1 | 2 / 225 |
| Chainmail (random piece) | 1 | 10 / 225 |
| Gold Nugget | 1-8 | 11 / 225 |
| Water Bottle | 1 | 11 / 225 |
| Fire Charge | 1-2 | 16 / 225 |
| Charcoal | 1-12 | 21 / 225 |
| Dyes | 1-3 | 21 / 225 |
| Spectral Arrow | 4-9 | 25 / 225 |
| Crying Obsidian | 1-4 | 35 / 225 |
| Leather | 1-3 | 35 / 225 |
| Gravel | 3-8 | 35 / 225 |

### Villager Trade Table
| OFFER | RECEIVE | RATIO |
| ----- | ------- |:-----:|
| DIAMOND | EMERALD | 1:1 |
| IRON BLOCK | EMERALD | 3:1 |
| REDSTONE BLOCK | EMERALD | 11:1 |
| COAL BLOCK | EMERALD | 33:1 |
| EMERALD | BELL | 1:4 |
| EMERALD, GLASS BOTTLE | EXPERIENCE BOTTLE | 1,1:1 |
| EMERALD | ITEM FRAME | 1:16 |
| EMERALD | LAPIS LAZULI | 1:1 |
| EMERALD, LEATHER | NAME TAG | 1,3:3 |
| EMERALD | PAINTING | 1:16 |
| EMERALD | SCUTE | 1:1 |
| EMERALD | SUSPICIOUS STEW | 1:1 |

### Food Table
| FOOD | HEALTH | STAMINA |
| ---- |:------:|:-------:|
| APPLE | 4 | 3 |
| BAKED POTATO | 3 | 3 |
| BEETROOT | 4 | 2 |
| BEETROOT SOUP | 8 | 6 |
| BREAD | 4 | 4 |
| CAKE (SLICE) | 10 | 3 |
| CARROT | 3 | 3 |
| CHORUS FRUIT | 3 | 3 |
| COOKED CHICKEN | 5 | 5 |
| COOKED COD | 5 | 5 |
| COOKED MUTTON | 6 | 6 |
| COOKED PORKCHOP | 7 | 7 |
| COOKED RABBIT | 5 | 5 |
| COOKED SALMON | 5 | 5 |
| COOKIE | 1 | 3 |
| DRIED KELP | 2 | 2 |
| ENCHANTED GOLDEN APPLE | 6 | 7 |
| GOLDEN APPLE | 4 | 5 |
| GOLDEN CARROT | 8 | 5 |
| HONEY BOTTLE | 4 | 4 |
| MELON SLICE | 3 | 2 |
| MUSHROOW STEW | 8 | 6 |
| POISONOUS POTATO | 0 | 1 |
| POTATO | 2 | 1 |
| PUFFERFISH | 0 | 2 |
| PUMPKIN PIE | 8 | 8 |
| RABBIT STEW | 9 | 7 |
| RAW BEEF | 0 | 1 |
| RAW CHICKEN | 0 | 1 |
| RAW COD | 2 | 2 |
| RAW MUTTON | 0 | 1 |
| RAW PORKCHOP | 0 | 1 |
| RAW RABBIT | 0 | 1 |
| RAW SALMON | 2 | 2 |
| ROTTEN FLESH | 3 | 3 |
| SPIDER EYE | 0 | 1 |
| STEAK | 6 | 7 |
| SUSPICIOUS STEW | 7 | 5 |
| SWEET BERRIES | 3 | 2 |
| TROPICAL FISH | 1 | 3 |

NOTE: one heart of healing is 2 points of health, the same applies to hunger.

## Known Issues:
- Elytra flight:
  - spamming elytra glides causes the player character to become stuck in the glide animation with no movement speed
- Healing:
  - Eating food with right click while looking at a block has a delay of at least one tick.
- Stamina:
  - player sprint ends when food bar reaches 6/20
