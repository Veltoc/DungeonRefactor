
class CharacterCreator {
    static Hero createHero(String hero) {
        int hp;
        int attackSpeed;
        double hitChance;
        int minDamage;
        int maxDamage;
        double blockChance;
        String warcry;
        Special special;

        switch (hero) {
            case "Sorceress":
                hp = 75;
                attackSpeed = 5;
                hitChance = .7;
                minDamage = 25;
                maxDamage = 50;
                blockChance = .3;
                warcry = " casts a fireball at ";
                special = new SpecialCureWounds(25, 50);
                break;
            case "Thief":
                hp = 75;
                attackSpeed = 6;
                hitChance = .8;
                minDamage = 20;
                maxDamage = 40;
                blockChance = .5;
                warcry = " throws a dagger at ";
                special = new SpecialBackstab();
                break;
            case "Warrior":
                hp = 125;
                attackSpeed = 4;
                hitChance = .8;
                minDamage = 35;
                maxDamage = 60;
                blockChance = .2;
                warcry = " swings a mighty sword at ";
                special = new SpecialCrushingBlow(.4);
                break;
            default:
                System.out.println("Unknown hero!");
                return null;
        }

        return new Hero(hp, attackSpeed,
                hitChance, minDamage, maxDamage,
                blockChance,
                warcry, special);
    }

    static Monster createMonster(String monster) {
        String name;
        int hp;
        int attackSpeed;
        double hitChance;
        double healChance;
        int minDamage;
        int maxDamage;
        int minHeal;
        int maxHeal;
        String warcry;

        switch (monster) {
            case "Gremlin":
                name = "Gnarltooth the Gremlin";
                hp = 70;
                attackSpeed = 5;
                hitChance = .7;
                healChance = .4;
                minDamage = 15;
                maxDamage = 30;
                minHeal = 20;
                maxHeal = 40;
                warcry = " jabs his kris at ";
                break;
            case "Ogre":
                name = "Oscar the Ogre";
                hp = 200;
                attackSpeed = 2;
                hitChance = .6;
                healChance = .1;
                minDamage = 30;
                maxDamage = 50;
                minHeal = 30;
                maxHeal = 50;
                warcry = " slowly swings a club toward ";
                break;
            case "Skeleton":
                name = "Sargath the Skeleton";
                hp = 100;
                attackSpeed = 3;
                hitChance = .8;
                healChance = .3;
                minDamage = 30;
                maxDamage = 50;
                minHeal = 30;
                maxHeal = 50;
                warcry = " slices his rusty blade at ";
                break;
            default:
                System.out.println("Unknown monster!");
                return null;
        }

        return new Monster(name, hp, attackSpeed,
                hitChance, healChance,
                minDamage, maxDamage,
                minHeal, maxHeal,
                warcry);
    }
}
