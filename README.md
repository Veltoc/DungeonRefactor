# DungeonRefactor

issues:
- fix asking to continue after winning?
- 

refactored:
- (V) Refactor #1: Each class (except thief) overrode attack just to print a line, pushed that up and passed the line at construction to DungeonCharacter to call.
- (V) Refactor #2: each hero overrode BattleChoices with the only difference being a print with the name of the special and the method the special was. Made the methods general and override a special method in Hero, passed the special name and pushed up the battleChoices to hero.
- (V) Refactor #3: everything was made public, most could be private or package private. Indecent Exposure code smell 
- (M) Refactor #4: DungeonCharacter implements Comparable and compareTo, but compareTo is never used - removed Comparable implementation
- (V) Refactor #5: Keyboard can be replaced with the Scanner utility, also fixes the issue with continue not working on enter
- (M) Refactor #6: Improved user experience (not continuing turn when monster is dead; not asking to quit after winning; formatting)
- (M) Refactor #7: Create classes for special abilities (Strategy 'em up)
- (M) Refactor #8: Create builder for monsters and heroes