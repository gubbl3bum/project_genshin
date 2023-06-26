public class Character {
    private String name;
    private String element;
    private String region;
    private String gender;
    private String age;
    private String weapon;
    private int health;
    private int attack;
    private int defense;
    private double critRate;
    private double critDamage;
    private int quality;
    private double elementalDamageBonus;
    private int id;
    //konstruktor
    public Character(String name, String element, String region, String gender, String age, String weapon, int health, int attack, int defense, double critRate, double critDamage, int quality, double elementalDamageBonus) {
            this.name = name;
            this.element = element;
            this.region = region;
            this.gender = gender;
            this.age = age;
            this.weapon = weapon;
            this.health = health;
            this.attack = attack;
            this.defense = defense;
            this.critRate = critRate;
            this.critDamage = critDamage;
            this.quality = quality;
            this.elementalDamageBonus = elementalDamageBonus;
        }
    //gettery i settery
    public String getName() {
        return name;    }
    public void setName(String name) {
        this.name = name;    }
    public String getElement() {
        return element;    }
    public void setElement(String element) {
        this.element = element;    }
    public String getRegion() {
        return region;    }
    public void setRegion(String region) {
        this.region = region;    }
    public String getGender() {
        return gender;    }
    public void setGender(String gender) {
        this.gender = gender;    }
    public String getAge() {
        return age;    }
    public void setAge(String age) {
        this.age = age;    }
    public String getWeapon() {
        return weapon;    }
    public void setWeapon(String weapon) {
        this.weapon = weapon;    }
    public int getHealth() {
        return health;    }
    public void setHealth(int health) {
        this.health = health;    }
    public int getAttack() {
        return attack;    }
    public void setAttack(int attack) {
        this.attack = attack;    }
    public int getDefense() {
        return defense;    }
    public void setDefense(int defense) {
        this.defense = defense;    }
    public double getCritRate() {
        return critRate;    }
    public void setCritRate(double critRate) {
        this.critRate = critRate;   }
    public double getCritDamage() {
        return critDamage;    }
    public void setCritDamage(double critDamage) {
        this.critDamage = critDamage;    }
    public int getQuality() {
        return quality;    }
    public void setQuality(int quality) {
        this.quality = quality; }
    public double getElementalDamageBonus() {
        return elementalDamageBonus;    }
    public void setElementalDamageBonus(double elementalDamageBonus) {
        this.elementalDamageBonus = elementalDamageBonus;   }
    public int getId(){
        return id;  }
//wygenerowany toString()
    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", element='" + element + '\'' +
                ", region='" + region + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", weapon='" + weapon + '\'' +
                ", health=" + health +
                ", attack=" + attack +
                ", defense=" + defense +
                ", critRate=" + critRate +
                "%, critDamage=" + critDamage +
                "%, quality=" + quality +
                ", elementalDamageBonus=" + elementalDamageBonus +
                "%}";
    }
  }