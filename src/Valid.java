import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
                Double critRate = rs.getDouble(10);
                Double critDmg = rs.getDouble(11);
                int quality = rs.getInt(12);
                Double elementalDmgBonus = rs.getDouble(13);
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
    public static LocalDate validDate(){
        Scanner scanner = new Scanner(System.in);
        String dateDtring = scanner.next();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateDtring, dateFormat);
        return date;
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
            }   }
        return element;     }
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
                }   }
            else{
                break;  }
        } return region; }
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
                }   }else {
                break;
            }   }
        return gender;      }
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
                }   }else{
                break;
            }   }
        return age;     }
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
            }   }
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
            }}else break;   }
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
            }   }else{
                break;
            }   }
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
            }   }else break;    }
        return inputDouble;     }
}