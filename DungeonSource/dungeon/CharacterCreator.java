package dungeon;

public class CharacterCreator {
    public static Hero createHero(String hero) {
        int hp;
        double blockChance;
        String attackType;
        String warcry;
        Special special;
        
        switch (hero) {
            case "Alchemist":
                hp = 100;
                blockChance = .4;
                attackType = "fast";
                warcry = "throws a bomb near";
                special = new SpecialExplosion(.3, 60, 80);
                break;
            case "Duelist":
                hp = 80;
                blockChance = .4;
                attackType = "normal";
                warcry = "slashes a rapier at";
                special = new SpecialQuickAttack();
                break;
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
                warcry = "swings a mighty sword toward";
                special = new SpecialCrushingBlow(.4);
                break;
            default:
                System.out.println("Unknown hero!");
                return null;
        }
        
        return new Hero(hp, blockChance,
            attackType, warcry, special);
    }

    public static Monster createMonster(String monster) {
        String name;
        int hp;
        double healChance;
        int minHeal;
        int maxHeal;
        String attackType;
        String warcry;
        
        switch (monster) {
            case "Drow":
                name = "Drizzt the Drow";
                hp = 80;
                healChance = .3;
                minHeal = 25;
                maxHeal = 45;
                attackType = "fast";
                warcry = "fires an arrow at";
                break;
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
            case "Slime":
                name = "Jerry the Slime";
                hp = 100;
                healChance = 1;
                minHeal = 10;
                maxHeal = 30;
                attackType = "normal";
                warcry = "swallows";
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
