# HardModeRevamp
(WIP) a Minecraft plugin meant to bring back some difficulty
#### v0.2.2 - rebuilding the enchantment system

## Features (finished)
- Bed respawns altered
- Creeper explosions destroy shields when blocking
- Creeper explosions drop player placed blocks
- Creeper explosions larger
- Elytra flight disabled in the Overworld and Nether
- Endermen can no longer pick up blocks
- Food bar now functions as a stamina bar
- God apples craftable again
- Health no longer affected by food bar, eating provides a quick burst of health regeneration
- Hostile mobs have randomized speed and health values
- Hostile mob xp increased
- Piglin bartering rebalanced
- Phantoms disabled
- Potion brewing grants XP
- Sleeping disabled
- Villager trades are universal
- Weapons, Tools and Gold Armor values rebalanced

## Features (untested)
- Log players destroying another player's block

## Known Issues:
- Elytra flight:
  - spamming elytra glides causes the player character to become stuck in the glide animation with no movement speed
- Healing:
  - Eating food with right click while looking at a block has a delay of at least one tick.
- Stamina:
  - player sprint ends when food bar reaches 6/20

## Features (planned?)
- Taking damage while regenerating health suspends or cancels health regeneration for a few seconds
- Enchantment overhaul
- Adding curses at the enchantment table
- Reworked loot chest balance by lowering odds of Diamonds and Diamond gear in chests (or completely removing them)


## Changelog
#### Player Changes
- hunger bar changed into a stamina bar
  - health regeneration is no longer tied to hunger
  - a player can run until the stamina bar is empty
  - eating food regenerates stamina and health
- elytra flight disabled outside of The End
- players now gain experience from farming
  - breaking crops with a hoe grants 1-2 points of experience
  - breaking pumpkins and melons with an axe grants 1-2 points of experience
- payers now gain experience from brewing
  - taking a potion from a brewing stand grands 4-6 points of experience

#### Item Changes
- breaking a crop with a hoe automatically replants it using one of the dropped seeds
- god apples can be crafted using shaped recipe:
  - A = Apple / G = Gold Block
    - G G G
    - G A G
    - G G G
- gold armor rebalanced:
  - gold chestplate armor value increased from 5 to 6.5
  - gold leggings armor value increased from 3 to 4.5
  - gold boots armor value increased from 1 to 2.5
- weapon damage rebalanced:
  - axe damage returned to 1.8.1 values (base fist damage +1, total of one heart of damage)
  - sword damage reduced by 1 (or half a heart)
  
#### Gameplay Changes
- creeper explosions always cause blocks placed by a player to drop when destroyed 
  - the dropped items may still be destroyed by a different creeper explosion
- night can no longer be skipped
- respawning at a bed now spawns the player on the surface within a 128-block radius around the bed's location

### Enchantment Changes
- Fortune removed from the game
- Mending removed from the game
- Unbreaking made incompatible with more enchantments

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
  - zombie reinforcement rate increased by 0.05
  
#### Passive Mob Changes
- Piglin bartering table replaced
- Villagers now all offer the same trades, trade table replaced


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
| EMERALD | EXPERIENCE BOTTLE | 1:1 |
| EMERALD | ITEM FRAME | 1:16 |
| EMERALD | NAME TAG | 1:3 |
| EMERALD | PAINTING | 1:16 |
| EMERALD | SCUTE | 1:1 |
| EMERALD | SUSPICIOUS STEW | 1:1 |

### Reworked Enchantment Table
full table of changes will be added after the enchantment rework is complete
