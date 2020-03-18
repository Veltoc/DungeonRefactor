package dungeon;

class CharacterCreator {
    static Hero createHero(String hero) {
        int hp;
        double blockChance;
        String attackType;
        String warcry;
        Special special;
        
        switch (hero) {
            case "Sorceress":
                hp = 75;
                blockChance = .3;
                attackType = "normal";
                warcry = "casts a fireball at";
                special = new SpecialCureWounds(25, 50);
                break;
            case "Thief":
                hp = 75;
                blockChance = .5;
                attackType = "fast";
                warcry = "throws a dagger at";
                special = new SpecialBackstab();
                break;
            case "Warrior":
                hp = 125;
                blockChance = .2;
                attackType = "heavy";
                warcry = "swings a mighty sword at";
                special = new SpecialCrushingBlow(.4);
                break;
            default:
                System.out.println("Unknown hero!");
                return null;
        }
        
        return new Hero(hp, blockChance,
            attackType, warcry, special);
    }

    static Monster createMonster(String monster) {
        String name;
        int hp;
        double healChance;
        int minHeal;
        int maxHeal;
        String attackType;
        String warcry;
        
        switch (monster) {
            case "Gremlin":
                name = "Gnarltooth the Gremlin";
                hp = 70;
                healChance = .4;
                minHeal = 20;
                maxHeal = 40;
                attackType = "fast";
                warcry = "jabs his kris at";
                break;
            case "Ogre":
                name = "Oscar the Ogre";
                hp = 200;
                healChance = .1;
                minHeal = 30;
                maxHeal = 50;
                attackType = "heavy";
                warcry = "slowly swings a club toward";
                break;
            case "Skeleton":
                name = "Sargath the Skeleton";
                hp = 100;
                healChance = .3;
                minHeal = 30;
                maxHeal = 50;
                attackType = "normal";
                warcry = "slices his rusty blade at";
                break;
            default:
                System.out.println("Unknown monster!");
                return null;
        }
        
        return new Monster(name, hp,
            healChance, minHeal, maxHeal,
            attackType, warcry);
    }
}
