# Description
A turn based game where two players take turns in trying to sink all the ships of the other player

# Board Layout
The empty board is a 10x10 grid that looks as follows
```
    1 2 3 4 5 6 7 8 9 10
A   ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ 
B   ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
C   ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
D   ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
E   ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
F   ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
G   ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
H   ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
I   ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
J   ~ ~ ~ ~ ~ ~ ~ ~ ~ ~
```

# Types of Ships
There are 5 types of ships
1. Aircraft Carrier (5 cells)
2. Battleship (4 cells)
3. Submarine (3 cells)
4. Cruiser (2 cells)
5. Destroyer (2 cells)

# Gameplay
1. Player 1 places all ships on the field by specifying their position. Filled slots are marked on the grid as O
2. Player 2 follows the same steps
3. Once all the ships are placed. Each player takes one turn to fire a shot. The player can see the empty board and their only own board
4. If a shop hits, the cell is marked with X, otherwise with M
5. Once a whole ship has been sunk, a message is displayed specifying that a ship has been sunk
6. Once all the ships of a player have been sunk, the game ends


