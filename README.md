# DungeonRefactor

issues:
- fix public access accross the board
- comparable is unused in dungeon character (compareTo unused and doesn't work)
- continue doesn't work if you just press enter
- remove unused in keyboard. (maybe just remove/replace?)
- fix asking to continue after winning?
- 

refactored:
- (V) Refactor #1: Each class (except thief) overrode attack just to print a line, pushed that up and passed the line at construction to DungeonCharacter to call.
- (V) Refactor #2: each hero overrode BattleChoices with the only difference being a print with the name of the special and the method the special was. Made the methods general and override a special method in Hero, passed the special name and pushed up the battleChoices to hero.
-
-
