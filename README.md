# HardModeRevamp
(WIP) a Minecraft plugin meant to bring back some difficulty


## Features (finished)
- Creeper explosions destroy shields when blocking
- Creeper explosions larger
- Endermen can no longer pick up blocks
- God apples craftable again
- Piglin bartering rebalanced
- Phantoms disabled
- Potion brewing grants XP
- Sleeping disabled
- Villager trades are universal

## Features (untested)
- Beds spawn the player in a 128-block radius on the surface
- Creeper explosions always drop blocks that a player placed
- Hostile mobs have randomized speed and health values

## Features (currently broken)
- Elytra flight disabled in the Overworld and Nether
  - current issue: spamming elytra glides causes the player character to become stuck in the glide animation with no movement speed
- Farming gives XP
- Breaking crops with a hoe replants the crop
- Weapons rebalance
  - current issue: weapons currently have negative damage values (setAttributeModifier issue)
- Gold armor rebalance:
  - current issue: armor values are low (setAttributeModifier issue)
- Griefing logs:
  - current issue: any block placed by a player and broken by any player will be added to the logs (getMetadata issue)
- Stamina Rework:
  - players have infinite stamina
  - players cannot eat while at maximum stamina

## Features (planned?)
- Taking damage while regenerating health suspends or cancels health regeneration for a few seconds
- Enchantment overhaul
- Adding curses at the enchantment table


## Changelog
#### Player Changes
- hunger bar changed into a stamina bar
  - health regeneration is no longer tied to hunger
  - a player can run until the stamina bar is empty
  - eating food regenerates stamina and health
- elytra flight disabled outside of The End
- players now gain experience from farming
  - breaking crops with a hoe grants 1-2 points of experience
- payers now gain experience from brewing
  - taking a potion from a brewing stand grands 4-6 points of experience

#### Item Changes
- breaking a crop with a hoe automatically replants it using one of the dropped seeds
- god apples can be crafted using an Apple surrounded by 8 Gold Blocks

#### Gameplay Changes
- creeper explosions always cause blocks placed by a player to drop when destroyed 
- night can no longer be skipped
- respawning at a bed now spawns the player on the surface within a 128-block radius

#### Hostile Mob Changes
- creepers now destroy a shield if the player blocks the explosion
- creeper explosion radius increased by 1
- endermen can no longer pick up blocks
- phantoms removed from the game
- spiders:
  - speed now a random value between 0.28 and 0.33
- zombies:
  - speed now a random value between 0.22 and 0.245
  - zombie reinforcement rate increased by 0.05
- all hostile mobs:
  - health values now randomized between 0.85 and 1.2 times their default value when spawned
  
#### Passive Mob Changes
- Piglin bartering now no longer offers items which would normally require grind
- Villagers now all offer the same trades in an unlimited quantity


### Piglin Barter Table
(to be added soon)

### Villager Trade Table
(to be added soon)
