import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class DataBase implements DataBase_operations {
    public List<Character> characters;
    public List<Banner> banners;
    public DataBase(List<Character> characters, List<Banner> banners) {
        this.characters = characters;
        this.banners = banners;
    }
    Scanner scanner = new Scanner(System.in);
    public static boolean goToMenu(String input){
        if(input.equals("-")) {
            return false;
        }else{
            return true;
        }   }
    public void displayCharacter(){
        System.out.println("Displaying all characters: ");
        for(Character search : characters){
            System.out.println(search.toString());
        }   }
    @Override
    public void displayCharacterStats() {
        boolean valid = false;
        while (!valid) {
            System.out.print("Give character name: ");
            String name = scanner.nextLine();
            if (goToMenu(name)) {
                for (Character search : characters) {
                    if (search.getName().equals(name)) {
                        System.out.println(search.toString());
                        valid = true;
                    }   }
                if (!valid) {
                    System.out.print("Character not found in database! try again\n");
                }
            }else{
                break;
            }   }   }
    @Override
    public void addCharacter() {
        System.out.println("Adding new character...");
        boolean valid = false;
        String name = "";
        while (!valid) {
            System.out.print("Name: ");
            name = scanner.nextLine();
            if(DataBase.goToMenu(name)){
            for (Character search : characters) {
                if (!search.getName().equals(name)) {
                    valid = true;
                } else {
                    System.out.print("Character already in database! try again!\n");
                    valid = false;
                    break;
                }   }   }else break;}
        String element = Valid.validElement();
        String region = Valid.validRegion();
        String gender = Valid.validGender();
        String age = Valid.validAge();
        String weapon = Valid.validWeapon();
        int health = Valid.validInt(1);
        int attack = Valid.validInt(2);
        int defense = Valid.validInt(3);
        double critRate = Valid.validDouble(1);
        double critDmg = Valid.validDouble(2);
        int quality = Valid.validQuality();
        double elDmgB = Valid.validDouble(3);
        Character newCharater = new Character(name, element, region, gender, age, weapon, health, attack, defense, critRate, critDmg, quality, elDmgB);
        characters.add(newCharater);
        //dodawanie postaci do bazy danych
        final String login = "gubbl";
        final String pswd = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/genshin_test", login, pswd);
            String query = "INSERT INTO `characters` (`name`, `element`, `region`, `gender`, `age`, `weapon`, `health`," +
                    "`attack`, `defense`, `critRate`, `critDamage`, `quality`, `elemenDmgBonus`, `id`) VALUES" +
                    "('" + name + "', '" + element + "', '" + region + "', '" + gender + "', '" + age + "', '" + weapon + "', '" + health + "', '" + attack +
                    "', '" + defense + "', '" + critRate + "', '" + critDmg + "', '" + quality + "', '" + elDmgB + "', NULL);";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Sucesfully added character to database!");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }   }
    @Override
    public void searchCharacters() {
        System.out.println("Search characters by:");
        System.out.println("1. Element");
        System.out.println("2. Region");
        System.out.println("3. Sex");
        System.out.println("4. Age");
        System.out.println("5. Weapon");
        System.out.print("\nplease put your input: ");
        String input = scanner.nextLine();
        if(goToMenu(input)){
            switch(input){
                case "1":
                    searchElement();
                    break;
                case "2":
                    searchRegion();
                    break;
                case "3":
                    searchSex();
                    break;
                case "4":
                    searchAge();
                    break;
                case "5":
                    searchWeapon();
                    break;
                default:
                    System.out.println("Wrong option - try again");
                    searchCharacters();
            }   }  }
    public void searchElement(){
        System.out.println("\nSearching by element...");
        String element = Valid.validElement();
        int count = 0;
        for(Character search : characters){
            if(element.equals(search.getElement())){
                System.out.println(search.toString());
                count++;
            }   }
        System.out.println("Printed " + count + " character/s");
    }
    public void searchRegion(){
        System.out.println("\nSearching by region...");
        String region = Valid.validRegion();
        int count = 0;
        for(Character search : characters){
            if(region.equals(search.getRegion())){
                System.out.println(search.toString());
                count++;
            }   }
        System.out.println("Printed " + count + " character/s");
    }
    public void searchSex(){
        System.out.println("\nSearching by gender...");
        String gender = Valid.validGender();
        int count = 0;
        for(Character search : characters){
            if(gender.equals(search.getGender())){
                System.out.println(search.toString());
                count++;
            }   }
        System.out.println("Printed " + count + " character/s");
    }
    public void searchAge(){
        System.out.println("\nSearching by age...");
        String age = Valid.validAge();
        int count = 0;
        for(Character search : characters){
            if(age.equals(search.getAge())){
                System.out.println(search.toString());
                count++;
            }   }
        System.out.println("Printed " + count + " character/s");
    }
    public void searchWeapon(){
        System.out.println("\nSearching by weapon...");
        String weapon = Valid.validWeapon();
        int count = 0;
        for(Character search : characters){
            if(weapon.equals(search.getWeapon())){
                System.out.println(search.toString());
                count++;
            }   }
        System.out.println("Printed " + count + " character/s");
    }
    @Override
    public void displayBanner() {
        System.out.println("Displaying all banners...");
        for(Banner search : banners){
            System.out.println(search.toString());
        }   }
    @Override
    public void currentBanner() {
        System.out.println("Current banners: ");
        Date currentDate = new Date();
        for(Banner search : banners){
            if(currentDate.after(search.getDateStart()) && currentDate.before(search.getDateEnd())){
                System.out.println(search.toString());
            }   }   }
    @Override
    public void searchBanners() {
        System.out.println("Searching banners by character...");
        System.out.print("Give characater name: ");
        String name = scanner.nextLine();
        for (Banner search : banners){
            if(search.getCharacter5().equals(name) || search.getCharacter4_1().equals(name) || search.getCharacter4_2().equals(name)
                    || search.getCharacter4_3().equals(name)){
                System.out.println(search.toString());
            }   }   }
    @Override
    public void addBanner() {

    }
    @Override
    public void importExportCharacter() {

    }
    @Override
    public void importExportBanner() {

    }
}