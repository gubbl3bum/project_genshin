import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class Valid {
    static Scanner scanner = new Scanner(System.in);
    //importowanie tabeli characters z bazy danych
    public static List<Character> characterImportDatabase(){
        final String login = "gubbl";
        final String pswd = "";
        List<Character> character = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/genshin_test",login,pswd);
            String query = "SELECT name, element, region, gender, age, weapon, health, attack, defense, critRate, critDamage, quality, elemenDmgBonus\n" +
                    "FROM characters;";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                String name = rs.getString(1);
                String element = rs.getString(2);
                String region = rs.getString(3);
                String gender = rs.getString(4);
                String age = rs.getString(5);
                String weapon = rs.getString(6);
                int health = rs.getInt(7);
                int attack = rs.getInt(8);
                int defense = rs.getInt(9);
                double critRate = rs.getDouble(10);
                double critDmg = rs.getDouble(11);
                int quality = rs.getInt(12);
                double elementalDmgBonus = rs.getDouble(13);
                Character characters = new Character(name,element,region,gender,age,weapon,health,attack,defense,critRate,critDmg,quality,elementalDmgBonus);
                character.add(characters);
            }
            con.close();
        } catch (Exception e){
            System.out.println(e);
        }
        return character;
    }
    //importowanie tabeli banners z bazy danych
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
    //importowanie tabeli characters z pliku CSV
    public static List<Character> characterImportCSV(){
        System.out.println("Default path: \\projekt_genshin\\characters.csv");
        String charactersPath = "C:\\Users\\gubbl\\IdeaProjects\\genszin projekt PROBA NAPRAWY FAJNEJ\\characters.csv";
        System.out.print("Give your input: ");
        String input = scanner.nextLine();
        if(!input.equals("")){
            charactersPath = input;}
        List<Character> characters = new ArrayList<>();
        try{
            CSVReader characterReader = new CSVReader(new FileReader(charactersPath));
            List<String[]> characterData = characterReader.readAll();
            for(String[] row : characterData){
                String name = row[0];
                String element = row[1];
                String region = row[2];
                String gender = row[3];
                String age = row[4];
                String weapon = row[5];
                int health = Integer.parseInt(row[6]);
                int attack = Integer.parseInt(row[7]);
                int defense = Integer.parseInt(row[8]);
               double critRate = Double.parseDouble(row[9]);
                double critDmg = Double.parseDouble(row[10]);
                int quality = Integer.parseInt(row[11]);
                double elementalDmg = Double.parseDouble(row[12]);
                Character character = new Character(name,element,region,gender,age,weapon,
                    health,attack,defense,critRate,critDmg,quality,elementalDmg);
                characters.add(character);
        }
            characterReader.close();
            System.out.println("Characters loaded from CSV files successfully");
        } catch (IOException | CsvException e){
            System.out.println("An error occured while loading data from CSV file.");
        }
            return characters;
    }
    //importowanie tabeli banners z pliku csv
    public static List<Banner> bannerImportCSV(){
        List<Banner> banners = new ArrayList<>();
        System.out.println("Default path: \\projekt_genshin\\banners.csv");
        String bannersPath = "C:\\Users\\gubbl\\IdeaProjects\\genszin projekt PROBA NAPRAWY FAJNEJ\\banners.csv";
        System.out.print("Give file path: ");
        String input = scanner.nextLine();
        if(!input.equals("")){
            bannersPath = input;
        }
        try {
            CSVReader bannerReader = new CSVReader(new FileReader(bannersPath));
            List<String[]> bannerData = new ArrayList<>();
            for (String[] row : bannerData) {
                String name = row[0];
                Date dateStart = stringToDate(row[1]);
                Date dateEnd = stringToDate(row[2]);
                String character5 = row[3];
                String character4_1 = row[4];
                String character4_2 = row[5];
                String character4_3 = row[6];
                String version = row[7];
                Banner banner = new Banner(name, dateStart, dateEnd, character5, character4_1, character4_2, character4_3, version);
                banners.add(banner);
            }
            bannerReader.close();
            System.out.println("Banners loaded from CSV file successfully");
        }catch (IOException e){
            System.out.println("Error occurred while loading data from CSV file");
        }
            return banners;
    }
    public static LocalDate validDate(){
        Scanner scanner = new Scanner(System.in);
        String dateDtring = scanner.next();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateDtring, dateFormat);
        return date;
    }
    public static Date stringToDate(String date){
        Date result = null;
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            result = dateFormat.parse(date);
        } catch(ParseException e){
            e.printStackTrace();
        }
        return result;
    }
    public static Date stringToDate(){
        Scanner scanner = new Scanner(System.in);
        String dateS = scanner.nextLine();
        Date result = stringToDate(dateS);
        return result ;
    }
    public static String validElement(){
        String element = "";
        boolean valid = false;
        while (!valid) {
            System.out.print("Element: ");
            element = scanner.nextLine();
            if(DataBase.goToMenu(element)){
                if (element.equals("Anemo") || element.equals("Geo") || element.equals("Electro")
                        || element.equals("Dendro") || element.equals("Hydro") || element.equals("Pyro")
                        || element.equals("Cryo")) {
                    valid = true;
                } else {
                    System.out.println("Wrong element! please try again\n(Anemo, Geo, Electro, Dendro, Hydro, Pyro, Cryo)");
                }
            }else{
                break;
            }
        }
        return element;
    }
    public static String validRegion(){
        boolean valid = false;
        String region = "";
        while (!valid) {
            System.out.print("Region: ");
            region = scanner.nextLine();
            if(DataBase.goToMenu(region)){
                if (region.equals("Mondstadt") || region.equals("Liyue") || region.equals("Inazuma") || region.equals("Sumeru")
                        || region.equals("Fontaine") || region.equals("Natlan") || region.equals("Snezhnaya")) {
                    valid = true;
                } else {
                    System.out.println("Wrong region! please try again\n(Mondstadt, Liyue, Inazuma, Sumeru, Fontaine, Natlan, Snezhnaya)");
                }
            }
            else{
                break;
            }
        } return region;
    }
    public static String validGender(){
        String gender = "";
        boolean valid = false;
        while (!valid) {
            System.out.print("Sex: ");
            gender = scanner.nextLine();
            if(DataBase.goToMenu(gender)){
                if (gender.equals("Male") || gender.equals("Female")) {
                    valid = true;
                } else {
                    System.out.println("Wrong sex! please try again\n(Male, Female)");
                }
            }else {
                break;
            }
        }
        return gender;
    }
    public static String validAge(){
        boolean valid = false;
        String age = "";
        while (!valid) {
            System.out.print("Age: ");
            age = scanner.nextLine();
            if(DataBase.goToMenu(age)){
                if (age.equals("Child") || age.equals("Teenager") || age.equals("Adult")) {
                    valid = true;
                } else {
                    System.out.println("Wrong age! please try again\n(Child, Teenager, Adult)");
                }
            }else{
                break;
            }
        }
        return age;
    }
    public static String validWeapon() {
        boolean valid = false;
        String weapon = "";
        while (!valid) {
            System.out.print("Weapon: ");
            weapon = scanner.nextLine();
            if (DataBase.goToMenu(weapon)) {
                if (weapon.equals("Sword") || weapon.equals("Claymore") || weapon.equals("Polearm") || weapon.equals("Catalyst") || weapon.equals("Bow")) {
                    valid = true;
                } else {
                    System.out.println("Wrong weapon! please try again\n(Sword, Claymore, Polearm, Catalyst, Bow)");
                }
            } else {
                break;
            }
        }
        return weapon;      }
    public static int validQuality(){
        String qualityS = "";
        int quality = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print("Quality(4 or5): ");
            qualityS = scanner.nextLine();
            if(DataBase.goToMenu(qualityS)){
            try {
                quality = Integer.parseInt(qualityS);
                if (quality == 4 || quality == 5) {
                    valid = true;
                } else {
                    System.out.println("Wrong quality! please try again\n(4 or 5)");
                    valid = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Wrong quality! please try again\n(4 or 5)");
                valid = false;
            }
            }else break;
        }
        return  quality;}
    public static int validInt(int type){
        String inputS= "";
        int inputInt = 0;
        boolean valid = false;
        while (!valid) {
            switch(type){
                case 1:
                    System.out.print("Health: ");
                    break;
                case 2:
                    System.out.print("Attack: ");
                    break;
                case 3:
                    System.out.print("Defense: ");
                    break;}
            inputS = scanner.nextLine();
            if(DataBase.goToMenu(inputS)){
            try {
                inputInt = Integer.parseInt(inputS);
                if (inputInt < 0) {
                    System.out.print("Wrong input! please try again\n(positive number)\n");
                    valid = false;
                } else valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Wrong input! please try again\n(integer)");
                valid = false;
            }
            }else{
                break;
            }
        }
        return inputInt;       }
    public static double validDouble(int type){
        String inputS= "";
        double inputDouble = 0;
        boolean valid = false;
        while (!valid) {
            switch(type){
                case 1:
                    System.out.print("Crit rate: ");
                    break;
                case 2:
                    System.out.print("Crit damage: ");
                    break;
                case 3:
                    System.out.print("Elemental damage bonus: ");
                    break;}
            inputS = scanner.nextLine();
            if(DataBase.goToMenu(inputS)){
            try {
                inputDouble = Double.parseDouble(inputS);
                if (inputDouble < 0) {
                    System.out.print("Wrong input! please try again\n(positive number)\n");
                    valid = false;
                } else valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Wrong input! please try again\n(double)");
                valid = false;
            }
            }else break;
        }
        return inputDouble;     }
}