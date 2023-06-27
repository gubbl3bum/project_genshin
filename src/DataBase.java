import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class DataBase implements DataBase_operations {
    public List<Character> characters;
    public List<Banner> banners;
    public List<CharacterId> charactersId;
    public DataBase(List<Character> characters, List<Banner> banners, List<CharacterId> charactersId) {
        this.characters = characters;
        this.banners = banners;
        this.charactersId = charactersId;
    }
    Scanner scanner = new Scanner(System.in);
    public static boolean goToMenu(String input){
        return !input.equals("-");
    }
    public void displayCharacter(){
        System.out.println("Displaying all characters: ");
        for(Character search : characters){
            System.out.println(search.toString());
        }
    }
    @Override
    public void displayCharacterStats() {
        boolean valid = false;
        while (!valid) {
            System.out.print("Give character name: ");
            String name = scanner.nextLine();
            if (goToMenu(name)) {
                for (Character search : characters) {
                    if (search.getName().equals(name)) {
                        System.out.println(search);
                        valid = true;
                    }
                }
                if (!valid) {
                    System.out.print("Character not found in database! try again\n");
                }
            }else{
                break;
            }
        }
    }
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
                }
            }
            }else break;
        }
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
        Character newCharacter = new Character(name, element, region, gender, age, weapon, health, attack, defense, critRate, critDmg, quality, elDmgB);
        characters.add(newCharacter);
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
            System.out.println("Successfully added character to database!");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
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
            switch (input) {
                case "1" -> searchElement();
                case "2" -> searchRegion();
                case "3" -> searchSex();
                case "4" -> searchAge();
                case "5" -> searchWeapon();
                default -> {
                    System.out.println("Wrong option - try again");
                    searchCharacters();
                }
            }
        }
    }
    public void searchElement(){
        System.out.println("\nSearching by element...");
        String element = Valid.validElement();
        int count = 0;
        if(goToMenu(element)){
            for(Character search : characters){
                if(element.equals(search.getElement())){
                    System.out.println(search);
                    count++;
                }
            }
            if(count>0){
                System.out.println("Printed " + count + " character/s");
            }
        }
  }
    public void searchRegion(){
        System.out.println("\nSearching by region...");
        String region = Valid.validRegion();
        int count = 0;
        if(goToMenu(region)){
            for(Character search : characters){
                if(region.equals(search.getRegion())){
                    System.out.println(search);
                    count++;
                }
            }
            if(count>0){
                System.out.println("Printed " + count + " character/s");
            }
        }
 }
    public void searchSex(){
        System.out.println("\nSearching by gender...");
        String gender = Valid.validGender();
        int count = 0;
        if(goToMenu(gender)){
            for(Character search : characters){
                if(gender.equals(search.getGender())){
                    System.out.println(search);
                    count++;
                }
            }
            if(count>0){
                System.out.println("Printed " + count + " character/s");
            }
        }
}
    public void searchAge(){
        System.out.println("\nSearching by age...");
        String age = Valid.validAge();
        int count = 0;
        if(goToMenu(age)){
            for(Character search : characters){
                if(age.equals(search.getAge())){
                    System.out.println(search);
                    count++;
                }
            }
            if(count>0){
                System.out.println("Printed " + count + " character/s");
            }
        }
    }
    public void searchWeapon(){
        System.out.println("\nSearching by weapon...");
        String weapon = Valid.validWeapon();
        int count = 0;
        if(goToMenu(weapon)){
            for(Character search : characters){
                if(weapon.equals(search.getWeapon())){
                    System.out.println(search);
                    count++;
                }
            }
            if(count>0){
                System.out.println("Printed " + count + " character/s");
            }
        }
    }
    @Override
    public void displayBanner() {
        System.out.println("Displaying all banners...");
        for(Banner search : banners){
            System.out.println(search.toString());
        }
    }
    @Override
    public void currentBanner() {
        System.out.println("Current banners: ");
        Date currentDate = new Date();
        for(Banner search : banners){
            if(currentDate.after(search.getDateStart()) && currentDate.before(search.getDateEnd())){
                System.out.println(search);
            }
        }
    }
    @Override
    public void searchBanners() {
        System.out.println("Searching banners by:");
        System.out.println("1. Characters");
        System.out.println("2. Date");
        System.out.print("\nplease put your input: ");
        String choice = scanner.nextLine();
        Date date;
        int prints = 0;
        boolean valid = false;
        switch (choice) {
            case "1" -> {
                while (!valid) {
                    System.out.print("Give character name: ");
                    String name = scanner.nextLine();
                    if (goToMenu(name)) {
                        for (Banner search : banners) {
                            if (search.getCharacter5().equals(name) || search.getCharacter4_1().equals(name) || search.getCharacter4_2().equals(name)
                                    || search.getCharacter4_3().equals(name)) {
                                System.out.println(search);
                                prints++;
                                valid = true;
                            }
                        }
                        if (prints <= 0) {
                            System.out.println("Character not found in database! please try again\n");
                            valid = false;
                        }
                    }
                }
            }
            case "2" -> {
                while (!valid) {
                    System.out.print("Give date: ");
                    try {
                        date = Valid.stringToDate();
                    } catch (ParseException e) {
                        System.out.println("Error while parsing date type data.\nExiting to menu...");
                        break;
                    }
                    for (Banner search : banners) {
                        if (date.after(search.getDateStart()) && date.before(search.getDateEnd())) {
                            System.out.println(search);
                            prints++;
                            valid = true;
                        }
                    }
                    if (prints <= 0) {
                        System.out.println("Banner not found in database! please try again\n");
                        valid = false;
                    }
                }
            }
        }
    }
    @Override
    public void addBanner() {
        System.out.println("Adding banner...");
        String version = "";
        boolean valid = false;
        while(!valid){
            System.out.print("Version: ");
            version = scanner.nextLine();
            if(goToMenu(version)){
                try{
                    Integer.parseInt(version); //sprawdzenie czy input jest liczba
                    valid = true;
                } catch (NumberFormatException e) {
                    System.out.println("Wrong input! try again\n(format like 1.1)");
                }
            }

        }
        int i = 0; // określa liczbe banerów na wersje, nie więcej niż 4!!
        for (Banner search : banners) {
            if (search.getVersion().equals(version)) i++;
        }
        if (i == 4) {
            System.out.println("Too many banners for that version! \nExiting to menu...");
        } else {
            System.out.print("Name: ");
            String name = scanner.nextLine();
            if(name.equals("-")){

            }
            System.out.print("Date start:");
            LocalDate dateStart = Valid.validDate();
            System.out.print("Date end: ");
            LocalDate dateEnd = Valid.validDate();
            boolean validChar = false;
            String character5;
            int char5 = 0;
            while(!validChar){
            System.out.print("5* character: ");
            character5 = scanner.nextLine();
            for (CharacterId search : charactersId) {
                if (search.getName().equals(character5) && search.getQuality() == 5) {
                    validChar = true;
                    char5 = search.getCharacter_id();
                    break;
                }
            }
            if(!validChar){
                System.out.println("Selected character is invalid, please try again");
            }
            }
            validChar = false;
            String character4_1 = "";
            int char4_1 = 0;
            while(!validChar){
                System.out.print("4* first character: ");
                character4_1 = scanner.nextLine();
                for (CharacterId search : charactersId) {
                    if(search.getName().equals(character4_1) && search.getQuality() == 4){
                        char4_1 = search.getCharacter_id();
                        validChar = true;
                        break;  }
                } if(!validChar){
                    System.out.println("Selected character is invalid , please try again");
                }
            }
            validChar = false;
            String character4_2 = "";
            int char4_2 = 0;
            while(!validChar){
                System.out.print("4* second character: ");
                character4_2 = scanner.nextLine();
                for (CharacterId search : charactersId) {
                    if(search.getName().equals(character4_2) && search.getQuality() == 4){
                        if(character4_2.equals(character4_1)){
                            System.out.println("Character already in banner, please try again");
                        }else{
                            char4_2 = search.getCharacter_id();
                            validChar = true;
                        }
                        break;
                    }
                } if(!validChar) {
                    System.out.println("Selected character is invalid, please try again");
                }   }
        validChar = false;
        String character4_3;
        int char4_3 = 0;
        while(!validChar){
            System.out.print("4* third character: ");
            character4_3 = scanner.nextLine();
            for (CharacterId search : charactersId) {
                if(search.getName().equals(character4_3) && search.getQuality() == 4){
                    if(character4_3.equals(character4_2) || character4_3.equals(character4_1)){
                        System.out.println("Character already in banner, please try again");
                    }else{
                        validChar = true;
                        char4_3 = search.getCharacter_id();
                    }
                    break;
                }
            } if(!validChar) {
                System.out.println("Selected character is invalid, please try again");
            }   }
        //dodanie baneru do bazy danych
        final String login = "gubbl";
        final String pswd = "";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/genshin_test",login,pswd);
            Statement stmt = con.createStatement();
            String query = "INSERT INTO `banner` (`idBanner`, `name`, `dateStart`, `dateEnd`, `character5`, `character4_1`, " +
                    "`character4_2`, `character4_3`, `version`) VALUES " +
                    "(NULL, '"+name+"', '"+dateStart+"', '"+dateEnd+"', '"+char5+"', '"+char4_1+"', '"+char4_2+"', " +
                    "'"+char4_3+"', '"+version+"')";
            stmt.executeUpdate(query);
            System.out.println("Successfully added banner to database!");
            con.close();
        } catch (Exception e){
            System.out.println(e);
        }   }   }
    @Override
    public void ExportCharacter(){
            System.out.println("Default path: \\projekt_genshin\\characters.csv");
            String charactersPath = "C:\\Users\\gubbl\\IdeaProjects\\genszin projekt PROBA NAPRAWY FAJNEJ\\characters.csv";
            System.out.print("Give your input: ");
            String input = scanner.nextLine();
            if(!input.equals("")){
                charactersPath = input;}
            try{
                if(goToMenu(charactersPath)){
                    CSVWriter characterWriter = new CSVWriter(new FileWriter(charactersPath));
                    List<String[]> characterData = new ArrayList<>();
                    for (Character search : characters){
                        for(CharacterId searchid : charactersId){
                            String[] row = {
                                    search.getName(),
                                    search.getElement(),
                                    search.getRegion(),
                                    search.getGender(),
                                    search.getAge(),
                                    search.getWeapon(),
                                    String.valueOf(search.getHealth()),
                                    String.valueOf(search.getAttack()),
                                    String.valueOf(search.getDefense()),
                                    String.valueOf(search.getCritRate()),
                                    String.valueOf(search.getCritDamage()),
                                    String.valueOf(search.getQuality()),
                                    String.valueOf(search.getElementalDamageBonus()),
                                    String.valueOf((searchid.getCharacter_id())),
                            };
                            characterData.add(row);
                        }
                    }
                    //zapis characterData do pliku
                    characterWriter.writeAll(characterData);
                    characterWriter.close();
                    System.out.println("Characters saved to CSV file successfully");
                }
            } catch (IOException e) {
                System.out.println("Error occurred while saving data to CSV file.");
            }
    }
    //dodanie jako maven dependencies opencsv wersja 5.7
    @Override
    public void ExportBanner() {
            System.out.println("Default path: \\projekt_genshin\\banners.csv");
            String bannersPath = "C:\\Users\\gubbl\\IdeaProjects\\genszin projekt PROBA NAPRAWY FAJNEJ\\banners.csv";
            try{
                CSVWriter bannerWriter = new CSVWriter(new FileWriter(bannersPath));
                List<String[]> bannersData = new ArrayList<>();
                for(Banner search : banners){
                    String[] row = {
                            search.getName(),
                            String.valueOf(search.getDateStart()),
                            String.valueOf(search.getDateEnd()),
                            search.getCharacter5(),
                            search.getCharacter4_1(),
                            search.getCharacter4_2(),
                            search.getCharacter4_3(),
                            search.getVersion() };
                    bannersData.add(row);
                }
                bannerWriter.writeAll(bannersData);
                bannerWriter.close();
                System.out.println("Banners saved to CSV file successfully");
            } catch (IOException e){
                System.out.println("Error occurred while saving data to CSV file.");
            }
        }
    }