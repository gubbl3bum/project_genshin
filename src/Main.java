import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;
import java.util.List;
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
    public static void goToMenu(String input,List<Banner> banners, List<Character> characters){
        if(input.equals("-")){
            System.out.print("Exiting to menu...\nclick any button...");
            inputString();
            displayMenu(banners, characters);
        }   }
    public static void displayMenu(List<Banner> banners, List<Character> characters){
        System.out.println("\n-------------------- MENU --------------------");
        System.out.println("!To return to menu while in input mode, please input '-'!");
        System.out.println("1. Display all characters");
        System.out.println("2. Display character stats");
        System.out.println("3. Add new character");
        System.out.println("4. Search character by criteria");
        System.out.println("5. Display all banners");
        System.out.println("6. Current banner");
        System.out.println("7. Search banners by character");
        System.out.println("8. Add new banner");
        System.out.println("9. Import/export characters from/to csv file");
        System.out.println("10. Import/export banners from/to csv file");
        System.out.println("11. Exit ");
        System.out.print("\nplease put your input: ");
        String option = inputString();
        System.out.println(" ");
        while(true){
            switch(option){
                case "1":
                    displayCharacter(banners,characters);
                    break;
                case "2":
                    displayCharacterStats(banners,characters);
                    break;
                case "3":
                    addCharacter(banners,characters);
                    break;
                case "4":
                    searchCharacters(banners,characters);
                    break;
                case "5":
                    displayBanner(banners,characters);
                    break;
                case "6":
                    currentBanner(banners,characters);
                    break;
                case "7":
                    searchBanners(banners,characters);
                case "8":
                    addBanner(banners,characters);
                    break;
                case "9":
                    importExportCharacter(banners,characters);
                    break;
                case "10":
                    importExportBanner(banners,characters);
                    break;
                case "11":
                    System.out.println("Exit the program");
                    System.exit(0); //wymuszenie zamkniecia konsoli
                default:
                    System.out.println("Wrong option - try again");
                    displayMenu(banners, characters);
            }   }   }
    public static void displayCharacter(List<Banner> banners, List<Character> characters){
        System.out.println("Displaying all characters: ");
        for(Character search : characters){
            System.out.println(search.toString());
        }
        System.out.print("\nclick any button to go back to menu:");
        String exit = inputString();
        displayMenu(banners,characters);
    }
    public static void displayCharacterStats(List<Banner> banners, List<Character> characters){
        boolean valid = false;
        while (!valid) {
            System.out.print("Give character name: ");
            String name = inputString();
            goToMenu(name,banners,characters);
            for (Character search : characters) {
                if (search.getName().equals(name)) {
                    System.out.println(search.toString());
                    valid = true;}
            }
            if(!valid){
                System.out.print("Character not found in database! try again\n");
            }   }
        System.out.print("\nclick any button to go back to menu:");
        String exit = inputString();
        displayMenu(banners,characters);
    }
    public static void addCharacter(List<Banner> banners, List<Character> characters) {
        System.out.println("Adding new character...");
        boolean valid = false;
        String name = "";
        while (!valid) {
            System.out.print("Name: ");
            name = inputString();
            goToMenu(name,banners,characters);
            for (Character search : characters) {
                if (!search.getName().equals(name)) {
                    valid = true;
                } else {
                    System.out.print("Character already in database! try again!\n");
                    valid = false;
                    break;
                }   }   }
        String element = validElement(banners,characters);
        String region = validRegion(banners,characters);
        String sex = validSex(banners,characters);
        String age = validAge(banners, characters);
        String weapon = validWeapon(banners,characters);
        int health = validInt(banners, characters, 1);
        int attack = validInt(banners,characters,2);
        int defense = validInt(banners,characters,3);
        double critRate = validDouble(banners,characters,1);
        double critDmg = validDouble(banners,characters,2);
        String qualityS = "";
        int quality = 0;
        valid = false;
        while (!valid) {
            System.out.print("Quality(4 or5): ");
            qualityS = inputString();
            goToMenu(qualityS,banners,characters);
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
            }   }
        double elDmgB = validDouble(banners,characters,3);
        Character newCharater = new Character(name, element, region, sex, age, weapon, health, attack, defense, critRate, critDmg, quality, elDmgB);
        characters.add(newCharater);
        //dodawanie postaci do bazy danych
        final String login = "gubbl";
        final String pswd = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/genshin_test", login, pswd);
            String query = "INSERT INTO `characters` (`name`, `element`, `region`, `sex`, `age`, `weapon`, `health`," +
                    "`attack`, `defense`, `critRate`, `critDamage`, `quality`, `elemenDmgBonus`, `id`) VALUES" +
                    "('" + name + "', '" + element + "', '" + region + "', '" + sex + "', '" + age + "', '" + weapon + "', '" + health + "', '" + attack +
                    "', '" + defense + "', '" + critRate + "', '" + critDmg + "', '" + quality + "', '" + elDmgB + "', NULL);";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("Sucesfully added character to database!");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.print("\nclick any button to go back to menu:");
        String exit = inputString();
        displayMenu(banners, characters);
    }
    public static String validElement(List<Banner> banners,List<Character> characters){
        String element = "";
        boolean valid = false;
        while (!valid) {
            System.out.print("Element: ");
            element = inputString();
            goToMenu(element,banners,characters);
            if (element.equals("Anemo") || element.equals("Geo") || element.equals("Electro")
                    || element.equals("Dendro") || element.equals("Hydro") || element.equals("Pyro")
                    || element.equals("Cryo")) {
                valid = true;
            } else {
                System.out.println("Wrong element! please try again\n(Anemo, Geo, Electro, Dendro, Hydro, Pyro, Cryo)");
            }   }
        return element;
    }
    public static String validRegion(List<Banner> banners, List<Character> characters){
        boolean valid = false;
        String region = "";
        while (!valid) {
            System.out.print("Region: ");
            region = inputString();
            goToMenu(region,banners,characters);
            if (region.equals("Mondstadt") || region.equals("Liyue") || region.equals("Inazuma") || region.equals("Sumeru")
                    || region.equals("Fontaine") || region.equals("Natlan") || region.equals("Snezhnaya")) {
                valid = true;
            } else {
                System.out.println("Wrong region! please try again\n(Mondstadt, Liyue, Inazuma, Sumeru, Fontaine, Natlan, Snezhnaya)");
            }   }
        return region;
    }
    public static String validSex(List<Banner> banners, List<Character> characters){
        String sex = "";
        boolean valid = false;
        while (!valid) {
            System.out.print("Sex: ");
            sex = inputString();
            goToMenu(sex,banners,characters);
            if (sex.equals("Male") || sex.equals("Female")) {
                valid = true;
            } else {
                System.out.println("Wrong sex! please try again\n(Male, Female)");
            }   }
    return sex;}
    public static String validAge(List<Banner> banners, List<Character> characters){
        boolean valid = false;
        String age = "";
        while (!valid) {
            System.out.print("Age: ");
            age = inputString();
            goToMenu(age,banners,characters);
            if (age.equals("Child") || age.equals("Teenager") || age.equals("Adult")) {
                valid = true;
            } else {
                System.out.println("Wrong age! please try again\n(Child, Teenager, Adult)");
            }   }
    return age;}
    public static String validWeapon(List<Banner> banners, List<Character> characters){
        boolean valid = false;
        String weapon = "";
        while (!valid) {
            System.out.print("Weapon: ");
            weapon = inputString();
            goToMenu(weapon,banners,characters);
            if (weapon.equals("Sword") || weapon.equals("Claymore") || weapon.equals("Polearm") || weapon.equals("Catalyst") || weapon.equals("Bow")) {
                valid = true;
            } else {
                System.out.println("Wrong weapon! please try again\n(Sword, Claymore, Polearm, Catalyst, Bow)");
            }   }
    return weapon;}
    public static int validInt(List<Banner> banners, List<Character> characters, int type){
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
            inputS = inputString();
            goToMenu(inputS,banners,characters);
            try {
                inputInt = Integer.parseInt(inputS);
                if (inputInt < 0) {
                    System.out.print("Wrong input! please try again\n(positive number)\n");
                    valid = false;
                } else valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Wrong input! please try again\n(integer)");
                valid = false;
            }   }
        return inputInt;
    }
    public static double validDouble(List<Banner> banners, List<Character> characters, int type){
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
                inputS = inputString();
                goToMenu(inputS,banners,characters);
                try {
                    inputDouble = Double.parseDouble(inputS);
                    if (inputDouble < 0) {
                        System.out.print("Wrong input! please try again\n(positive number)\n");
                        valid = false;
                    } else valid = true;
                } catch (NumberFormatException e) {
                    System.out.println("Wrong input! please try again\n(double)");
                    valid = false;
                }   }
            return inputDouble;
    }
    public static void searchCharacters(List<Banner> banners, List<Character> characters){
        System.out.println("Search characters by:");
        System.out.println("1. Element");
        System.out.println("2. Region");
        System.out.println("3. Sex");
        System.out.println("4. Age");
        System.out.println("5. Weapon");
        System.out.print("\nplease put your input: ");
        String input = inputString();
        goToMenu(input, banners, characters);
        switch(input){
            case "1":
                searchElement(banners,characters);
                break;
            case "2":
                searchRegion(banners,characters);
                break;
            case "3":
                searchSex(banners, characters);
                break;
            case "4":
                searchAge(banners,characters);
                break;
            case "5":
                searchWeapon(banners,characters);
                break;
            default:
                System.out.println("Wrong option - try again");
                searchCharacters(banners,characters);
        }   }
    public static void searchElement(List<Banner> banners, List<Character> characters){
        System.out.println("\nSearching by element...");
        String element = validElement(banners,characters);
        int count = 0;
        for(Character search : characters){
            if(element.equals(search.getElement())){
                System.out.println(search.toString());
                count++;
            }   }
        System.out.println("Printed " + count + " characters");
        System.out.println("\nClick any button to go to menu...");
        inputString();
        displayMenu(banners,characters);
    }
    public static void searchRegion(List<Banner> banners, List<Character> characters){
        System.out.println("\nSearching by region...");
        String region = validRegion(banners,characters);
        int count = 0;
        for(Character search : characters){
            if(region.equals(search.getRegion())){
                System.out.println(search.toString());
                count++;
            }   }
        System.out.println("Printed " + count + " characters");
        System.out.println("\nClick any button to go to menu...");
        inputString();
        displayMenu(banners,characters);
    }
    public static void searchSex(List<Banner> banners, List<Character> characters){
        System.out.println("\nSearching by gender...");
        String gender = validSex(banners,characters);
        int count = 0;
        for(Character search : characters){
            if(gender.equals(search.getSex())){
                System.out.println(search.toString());
                count++;
            }   }
        System.out.println("Printed " + count + " characters");
        System.out.println("\nClick any button to go to menu...");
        inputString();
        displayMenu(banners,characters);
    }
    public static void searchAge(List<Banner> banners, List<Character> characters){
        System.out.println("\nSearching by age...");
        String age = validAge(banners,characters);
        int count = 0;
        for(Character search : characters){
            if(age.equals(search.getAge())){
                System.out.println(search.toString());
                count++;
            }   }
        System.out.println("Printed " + count + " characters");
        System.out.println("\nClick any button to go to menu...");
        inputString();
        displayMenu(banners,characters);
    }
    public static void searchWeapon(List<Banner> banners, List<Character> characters){
        System.out.println("\nSearching by weapon...");
        String gender = validWeapon(banners,characters);
        int count = 0;
        for(Character search : characters){
            if(gender.equals(search.getSex())){
                System.out.println(search.toString());
                count++;
            }   }
        System.out.println("Printed " + count + " characters");
        System.out.println("\nClick any button to go to menu...");
        inputString();
        displayMenu(banners,characters);
    }
    public static void displayBanner(List<Banner> banners, List<Character> characters){
        System.out.println("Displaying all banners...");
        for(Banner search : banners){
            System.out.println(search.toString());
        }
        System.out.print("\nclick any button to go back to menu:");
        String exit = inputString();
        displayMenu(banners,characters);
    }
    public static void currentBanner(List<Banner> banners, List<Character> characters){
        System.out.println("Current banner: ");
        Date currentDate = new Date();
        for(Banner search : banners){
            if(currentDate.after(search.getDateStart()) && currentDate.before(search.getDateEnd())){
                System.out.println(search.toString());
            }   }
        System.out.print("\nclick any button to go back to menu:");
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
            }   }
        System.out.print("\nclick any button to go back to menu:");
        String exit = inputString();
        displayMenu(banners,characters);
    }
    public static void addBanner(List<Banner> banners, List<Character> characters) {
        System.out.println("Adding banner...");
        System.out.print("Version: ");
        String version = inputString();
        int i = 0; // określa liczbe banerów na wersje, nie więcej niż 4!!
        for (Banner search : banners) {
            if (search.getVersion().equals(version)) i++;
        }
        if (i == 4) {
            System.out.println("Too many banners for that version! \nExiting to menu...");
            String exit = inputString();
            displayMenu(banners, characters);
        } else {
            System.out.print("Name: ");
            String name = inputString();
            System.out.print("Date start:");
            LocalDate dateStart = inputDate();
            System.out.print("Date end: ");
            LocalDate dateEnd = inputDate();
            boolean validChar = false;
            String character5 = "";
            while(!validChar){
            System.out.print("5* character: ");
            character5 = inputString();
            for (Character search : characters) {
                if (search.getName().equals(character5) && search.getQuality() == 5) {
                    validChar = true;
                    break;
                }   }
            if(!validChar){
                System.out.println("Selected character is invalid, please try again");
            }   }
            validChar = false;
            String character4_1 = "";
            while(!validChar){
                System.out.print("4* first character: ");
                character4_1 = inputString();
                for(Character search : characters){
                    if(search.getName().equals(character4_1) && search.getQuality() == 4){
                        validChar = true;
                        break;  }
                } if(!validChar){
                    System.out.println("Selected character is invalid , please try again");
                }   }
            validChar = false;
            String character4_2 = "";
            while(!validChar){
                System.out.print("4* second character: ");
                character4_2 = inputString();
                for(Character search : characters){
                    if(search.getName().equals(character4_2) && search.getQuality() == 4){
                        if(character4_2.equals(character4_1)){
                            System.out.println("Character already in banner, please try again");
                            break;
                        }else{
                            validChar = true;
                            break;} }
                } if(!validChar) {
                    System.out.println("Selected character is invalid, please try again");
                }   }
        validChar = false;
        String character4_3 = "";
        while(!validChar){
            System.out.print("4* third character: ");
            character4_3 = inputString();
            for(Character search : characters){
                if(search.getName().equals(character4_3) && search.getQuality() == 4){
                    if(character4_3.equals(character4_2) || character4_3.equals(character4_1)){
                        System.out.println("Character already in banner, please try again");
                        break;
                    }else{
                        validChar = true;
                        break;} }
            } if(!validChar) {
                System.out.println("Selected character is invalid, please try again");
            }   }
        //dodanie baneru do bazy danych
        final String login = "gubbl";
        final String pswd = "";
        List<CharacterId> charactersIds = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/genshin_test",login,pswd);
            String query = "SELECT name, id FROM characters";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                String charName = rs.getString(1);
                int id_char = rs.getInt(2);
                CharacterId ch = new CharacterId(charName,id_char);
                charactersIds.add(ch);
            }
            int char5=0, char4_1=0, char4_2=0, char4_3=0;
            for(CharacterId search : charactersIds){
                if(character5.equals(search.getName())){
                    char5 = search.getCharacter_id();
                }
                if(character4_1.equals(search.getName())){
                    char4_1 = search.getCharacter_id();
                }
                if(character4_2.equals(search.getName())){
                    char4_2 = search.getCharacter_id();
                }
                if(character4_3.equals(search.getName())){
                    char4_3 = search.getCharacter_id();
                }   }
            query = "INSERT INTO `banner` (`idBanner`, `name`, `dateStart`, `dateEnd`, `character5`, `character4_1`, " +
                    "`character4_2`, `character4_3`, `version`) VALUES " +
                    "(NULL, '"+name+"', '"+dateStart+"', '"+dateEnd+"', '"+char5+"', '"+char4_1+"', '"+char4_2+"', " +
                    "'"+char4_3+"', '"+version+"')";
            stmt.executeUpdate(query);
            System.out.println("Sucesfully added banner to database!");
            con.close();
        } catch (Exception e){
            System.out.println(e);
        }   }
        System.out.print("\nclick any button to go back to menu:");
        String exit = inputString();
        displayMenu(banners,characters);}
    public static void importExportCharacter(List<Banner> banners, List<Character> characters){

    }
    public static void importExportBanner(List<Banner> banners, List<Character> characters){

    }
}