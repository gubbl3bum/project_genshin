import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class DataBase implements DataBase_operations {
    private static List<Character> characters;
    private static List<Banner> banners;
    Scanner scanner = new Scanner(System.in);
    public DataBase(){
        characters = new ArrayList<>();
        banners = new ArrayList<>();
    }
    @Override
    public void addCharacter() {
        System.out.println("Adding new character: ");
        System.out.print("name: ");
        String name = scanner.nextLine();
        System.out.print("element: ");
        String element = scanner.nextLine();
        System.out.print("region: ");
        String region = scanner.nextLine();
        System.out.print("sex: ");
        String sex = scanner.nextLine();
        System.out.print("age: ");
        String age = scanner.nextLine();
        System.out.print("weapon: ");
        String weapon = scanner.nextLine();
        System.out.print("health: ");
        int health = scanner.nextInt();
        System.out.print("attack: ");
        int attack = scanner.nextInt();
        System.out.print("defense: ");
        int defense = scanner.nextInt();
        System.out.print("crit rate: ");
        double critRate = scanner.nextDouble();
        System.out.print("crit damage: ");
        double critDmg = scanner.nextDouble();
        System.out.print("quality(4 or5): ");
        int quality = scanner.nextInt();
        System.out.print("elemental damage bonus: ");
        double elDmgB = scanner.nextDouble();
        Character newCharater = new Character(name,element,region,sex,age, weapon,health,attack,defense,critRate,critDmg,quality,elDmgB);
    }
    public Character getCharacter(String name) {
        for (Character character : characters) {
            if (character.getName().equals(name)) {
                return character;
            }
        }
        return null;
    }
    public void addCharacter(Character character) {
        if (!characters.contains(character)) {
            characters.add(character);
            System.out.println("Character added successfully!");
        } else {
            System.out.println("Character already exists in the database.");
        }
    }
    @Override
    public void addBanner() {

    }
    @Override
    public void updateCharacter() {

    }
    @Override
    public void updateBanner() {

    }
    @Override
    public void display() {

    }
    @Override
    public void delete() {

    }
}