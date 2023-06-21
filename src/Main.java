import java.sql.Connection;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args){
        System.out.println("---------- GENSHIN DATABASE PROJECT ----------");
        List<Banner> banners = bannerImportDatabase();
        List<Character> characters = characterImportDatabase();
        displayMenu(banners,characters);
    }
    public static int inputInt(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
    public static String inputString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public static Double inputDouble(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }
    public static LocalDate inputDate(){
        Scanner scanner = new Scanner(System.in);
        String dateDtring = scanner.next();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateDtring, dateFormat);
        return date;
    }
    public static List<Banner> bannerImportDatabase(){
        final String login = "gubbl";
        final String pswd = "";
        List<Banner> banners = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/genshin_test",login,pswd);
            String query = "SELECT banner.name, dateStart, dateEnd, characters.name, characters1.name AS character4_1, characters2.name AS character4_2, characters3.name AS character4_3, version\n" +
                    "FROM banner\n" +
                    "JOIN characters ON banner.character5 = characters.id\n" +
                    "JOIN characters AS characters1 ON banner.character4_1 = characters1.id\n" +
                    "JOIN characters AS characters2 ON banner.character4_2 = characters2.id\n" +
                    "JOIN characters AS characters3 ON banner.character4_3 = characters3.id;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                String name = rs.getString(1);
                Date dateStart = rs.getDate(2);
                Date dateEnd = rs.getDate(3);
                String character5 = rs.getString(4);
                String character4_1 = rs.getString(5);
                String character4_2 = rs.getString(6);
                String character4_3 = rs.getString(7);
                String version = rs.getString(8);
                Banner banner = new Banner(name, dateStart, dateEnd, character5, character4_1, character4_2, character4_3, version);
                banners.add(banner);
            }
            con.close();
        } catch (Exception e){
            System.out.println(e);
        }
        return banners;
    }
    public static List<Character> characterImportDatabase(){
        final String login = "gubbl";
        final String pswd = "";
        List<Character> character = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/genshin_test",login,pswd);
            String query = "SELECT name, element, region, sex, age, weapon, health, attack, defense, critRate, critDamage, quality, elemenDmgBonus\n" +
                    "FROM characters;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                String name = rs.getString(1);
                String element = rs.getString(2);
                String region = rs.getString(3);
                String sex = rs.getString(4);
                String age = rs.getString(5);
                String weapon = rs.getString(6);
                int health = rs.getInt(7);
                int attack = rs.getInt(8);
                int defense = rs.getInt(9);
                Double critRate = rs.getDouble(10);
                Double critDmg = rs.getDouble(11);
                int quality = rs.getInt(12);
                Double elementalDmgBonus = rs.getDouble(13);
                Character characters = new Character(name,element,region,sex,age,weapon,health,attack,defense,critRate,critDmg,quality,elementalDmgBonus);
                character.add(characters);
            }
            con.close();
        } catch (Exception e){
            System.out.println(e);
        }
        return character;
    }
    public static void displayMenu(List<Banner> banners, List<Character> characters){
        System.out.println("---------- MENU ----------");
        System.out.println("1. Display all characters");
        System.out.println("2. Display character stats");
        System.out.println("3. Add new character");
        System.out.println("4. Display all banners");
        System.out.println("5. Current banner");
        System.out.println("6. Search banners by character");
        System.out.println("7. Add new banner");
        System.out.println("8. Import/export characters from/to csv file");
        System.out.println("9. Import/export banners from/to csv file");
        System.out.println("10. Exit ");
        System.out.print("\nplease put your input: ");
        String option = inputString();
        System.out.println(" ");
        while(true){
            switch(option){
                case "1":
                    displayCharacter(banners,characters);
                    break;
                case "2":
                    displayCharcterStats(banners,characters);
                    break;
                case "3":
                    addCharacter(banners,characters);
                    break;
                case "4":
                    displayBanner(banners,characters);
                    break;
                case "5":
                    currentBanner(banners,characters);
                    break;
                case "6":
                    searchBanners(banners,characters);
                case "7":
                    addBanner(banners,characters);
                    break;
                case "8":
                    importExportCharacter(banners,characters);
                    break;
                case "9":
                    importExportBanner(banners,characters);
                    break;
                case "10":
                    System.out.println("Exit the program");
                    System.exit(0); //wymuszenie zamkniecia konsoli
                default:
                    System.out.println("Wrong option - try again");
                    displayMenu(banners, characters);
            }
        }
    }
    public static void displayCharacter(List<Banner> banners, List<Character> characters){
        System.out.println("Displaying all characters: ");
        for(Character search : characters){
            System.out.println(search.toString());
        }
        System.out.println("\nclick any button to go back to menu:");
        String exit = inputString();
        displayMenu(banners,characters);
    }
    public static void displayCharcterStats(List<Banner> banners, List<Character> characters){
        System.out.print("Give character name: ");
        String name = inputString();
        for (Character search : characters){
            if(search.getName().equals(name)){
                System.out.println(search.toString());
                break;
            }
        }
        System.out.println("\nclick any button to go back to menu:");
        String exit = inputString();
        displayMenu(banners,characters);
    }
    public static void addCharacter(List<Banner> banners, List<Character> characters){
        System.out.println("Adding new character...");
        System.out.print("Name: ");
        String name = inputString();
        for (Character search : characters){
            if(search.getName().equals(name)){
                System.out.println("Character already in database! \nExiting to menu...");
                String exit = inputString();
                displayMenu(banners,characters);
            }
        }
        System.out.print("Element: ");
        String element = inputString();
        System.out.print("Region: ");
        String region = inputString();
        System.out.print("Sex: ");
        String sex = inputString();
        System.out.print("Age: ");
        String age = inputString();
        System.out.print("Weapon: ");
        String weapon = inputString();
        System.out.print("Health: ");
        int health = inputInt();
        System.out.print("Attack: ");
        int attack = inputInt();
        System.out.print("Defense: ");
        int defense = inputInt();
        System.out.print("Crit rate: ");
        double critRate = inputDouble();
        System.out.print("Crit damage: ");
        double critDmg = inputDouble();
        System.out.print("Quality(4 or5): ");
        int quality = inputInt();
        System.out.print("Elemental damage bonus: ");
        double elDmgB = inputDouble();
        Character newCharater = new Character(name,element,region,sex,age, weapon,health,attack,defense,critRate,critDmg,quality,elDmgB);
        characters.add(newCharater);

        final String login = "gubbl";
        final String pswd = "";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/genshin_test",login,pswd);
            String query = "INSERT INTO `characters` (`name`, `element`, `region`, `sex`, `age`, `weapon`, `health`," +
            "`attack`, `defense`, `critRate`, `critDamage`, `quality`, `elemenDmgBonus`, `id`) VALUES" +
                    "('"+name+"', '"+ element+"', '"+region+"', '"+sex+"', '"+age+"', '"+weapon+"', '"+health+"', '"+attack+
                    "', '"+defense+"', '"+critRate+"', '"+critDmg+"', '"+quality+"', '"+elDmgB+"', NULL);";

            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Sucesfully added character to database!");
            con.close();
        } catch (Exception e){
            System.out.println(e);
        }
        System.out.println("\nclick any button to go back to menu:");
        String exit = inputString();
        displayMenu(banners,characters);
    }
    public static void displayBanner(List<Banner> banners, List<Character> characters){

        for(Banner search : banners){
            System.out.println(search.toString());
        }
        System.out.println("\nclick any button to go back to menu:");
        String exit = inputString();
        displayMenu(banners,characters);
    }
    public static void currentBanner(List<Banner> banners, List<Character> characters){
        System.out.println("Current banner: ");
        Date currentDate = new Date();
        for(Banner search : banners){
            if(currentDate.after(search.getDateStart()) && currentDate.before(search.getDateEnd())){
                System.out.println(search.toString());
            }
        }
        System.out.println("\nclick any button to go back to menu:");
        String exit = inputString();
        displayMenu(banners,characters);
    }
    public static void searchBanners(List<Banner> banners, List<Character> characters){
        System.out.println("Searching banners by character...");
        System.out.print("Give characater name: ");
        String name = inputString();
        for (Banner search : banners){
            if(search.getCharacter5().equals(name) || search.getCharacter4_1().equals(name) || search.getCharacter4_2().equals(name)
                    || search.getCharacter4_3().equals(name)){
                System.out.println(search.toString());
            }
        }
        System.out.println("\nclick any button to go back to menu:");
        String exit = inputString();
        displayMenu(banners,characters);
    }
    public static void addBanner(List<Banner> banners, List<Character> characters){
        System.out.println("Adding banner...");
        System.out.print("Version: ");
        String version = inputString();
        int i = 0; // określa liczbe banerów na wersje, nie więcej niż 4!!
        for (Banner search : banners){
            if(search.getVersion().equals(version)) i++;
        }
        if(i == 4){
            System.out.println("Too many banners for that version! \nExiting to menu...");
            String exit = inputString();
            displayMenu(banners,characters);
        }else{
            System.out.print("Name: ");
            String name = inputString();
            System.out.print("Date start:");
            LocalDate dateStart = inputDate();
            System.out.print("Date end: ");
            LocalDate dateEnd = inputDate();
            System.out.print("Character 5*: ");
            String character5 = inputString();
            for (Character search : characters){
                if(search.getName().equals(character5) && search.getQuality() != 5){
                    System.out.print("Selected character is invalid (wrong quality), please try again");
                    break;
                }
            }

        }


    }
    public static void importExportCharacter(List<Banner> banners, List<Character> characters){

    }
    public static void importExportBanner(List<Banner> banners, List<Character> characters){

    }
}