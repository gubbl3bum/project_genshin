import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
public class Main {
    public static void main(String[] args){
        System.out.println("---------- GENSHIN DATABASE PROJECT ----------");
        List<Banner> banners = Valid.bannerImportDatabase();
        List<Character> characters = Valid.characterImportDatabase();
        DataBase database = new DataBase(characters, banners);
        displayMenu(database);
    }
    public static String inputString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void goToMenu(String input,DataBase database){
        if(input.equals("-")){
            System.out.print("Exiting to menu...\nclick any button...");
            inputString();
            displayMenu(database);
        }   }
    public static void exitToMenu(DataBase database){
        System.out.print("\nclick any button to go back to menu:");
        String exit = inputString();
        displayMenu(database);
    }
    public static void displayMenu(DataBase database){
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
                    database.displayCharacter();
                    exitToMenu(database);
                    break;
                case "2":
                    database.displayCharacterStats();
                    exitToMenu(database);
                    break;
                case "3":
                    database.addCharacter();
                    exitToMenu(database);
                    break;
                case "4":
                    database.searchCharacters();
                    exitToMenu(database);
                    break;
                case "5":
                    database.displayBanner();
                    exitToMenu(database);
                    break;
                case "6":
                    database.currentBanner();
                    exitToMenu(database);
                    break;
                case "7":
                    database.searchBanners();
                    exitToMenu(database);
                case "8":
                    database.addBanner();
                    exitToMenu(database);
                    break;
                case "9":
                    database.importExportCharacter();
                    exitToMenu(database);
                    break;
                case "10":
                    database.importExportBanner();
                    exitToMenu(database);
                    break;
                case "11":
                    System.out.println("Exit the program");
                    System.exit(0); //wymuszenie zamkniecia konsoli
                default:
                    System.out.println("Wrong option - try again");
                    displayMenu(database);
            }   }   }
//    public static void addBanner(List<Banner> banners, List<Character> characters) {
//        System.out.println("Adding banner...");
//        System.out.print("Version: ");
//        String version = inputString();
//        int i = 0; // określa liczbe banerów na wersje, nie więcej niż 4!!
//        for (Banner search : banners) {
//            if (search.getVersion().equals(version)) i++;
//        }
//        if (i == 4) {
//            System.out.println("Too many banners for that version! \nExiting to menu...");
//            String exit = inputString();
//            displayMenu(banners, characters);
//        } else {
//            System.out.print("Name: ");
//            String name = inputString();
//            System.out.print("Date start:");
//            LocalDate dateStart = inputDate();
//            System.out.print("Date end: ");
//            LocalDate dateEnd = inputDate();
//            boolean validChar = false;
//            String character5 = "";
//            while(!validChar){
//            System.out.print("5* character: ");
//            character5 = inputString();
//            for (Character search : characters) {
//                if (search.getName().equals(character5) && search.getQuality() == 5) {
//                    validChar = true;
//                    break;
//                }   }
//            if(!validChar){
//                System.out.println("Selected character is invalid, please try again");
//            }   }
//            validChar = false;
//            String character4_1 = "";
//            while(!validChar){
//                System.out.print("4* first character: ");
//                character4_1 = inputString();
//                for(Character search : characters){
//                    if(search.getName().equals(character4_1) && search.getQuality() == 4){
//                        validChar = true;
//                        break;  }
//                } if(!validChar){
//                    System.out.println("Selected character is invalid , please try again");
//                }   }
//            validChar = false;
//            String character4_2 = "";
//            while(!validChar){
//                System.out.print("4* second character: ");
//                character4_2 = inputString();
//                for(Character search : characters){
//                    if(search.getName().equals(character4_2) && search.getQuality() == 4){
//                        if(character4_2.equals(character4_1)){
//                            System.out.println("Character already in banner, please try again");
//                            break;
//                        }else{
//                            validChar = true;
//                            break;} }
//                } if(!validChar) {
//                    System.out.println("Selected character is invalid, please try again");
//                }   }
//        validChar = false;
//        String character4_3 = "";
//        while(!validChar){
//            System.out.print("4* third character: ");
//            character4_3 = inputString();
//            for(Character search : characters){
//                if(search.getName().equals(character4_3) && search.getQuality() == 4){
//                    if(character4_3.equals(character4_2) || character4_3.equals(character4_1)){
//                        System.out.println("Character already in banner, please try again");
//                        break;
//                    }else{
//                        validChar = true;
//                        break;} }
//            } if(!validChar) {
//                System.out.println("Selected character is invalid, please try again");
//            }   }
//        //dodanie baneru do bazy danych
//        final String login = "gubbl";
//        final String pswd = "";
//        List<CharacterId> charactersIds = new ArrayList<>();
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/genshin_test",login,pswd);
//            String query = "SELECT name, id FROM characters";
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(query);
//            while(rs.next()){
//                String charName = rs.getString(1);
//                int id_char = rs.getInt(2);
//                CharacterId ch = new CharacterId(charName,id_char);
//                charactersIds.add(ch);
//            }
//            int char5=0, char4_1=0, char4_2=0, char4_3=0;
//            for(CharacterId search : charactersIds){
//                if(character5.equals(search.getName())){
//                    char5 = search.getCharacter_id();
//                }
//                if(character4_1.equals(search.getName())){
//                    char4_1 = search.getCharacter_id();
//                }
//                if(character4_2.equals(search.getName())){
//                    char4_2 = search.getCharacter_id();
//                }
//                if(character4_3.equals(search.getName())){
//                    char4_3 = search.getCharacter_id();
//                }   }
//            query = "INSERT INTO `banner` (`idBanner`, `name`, `dateStart`, `dateEnd`, `character5`, `character4_1`, " +
//                    "`character4_2`, `character4_3`, `version`) VALUES " +
//                    "(NULL, '"+name+"', '"+dateStart+"', '"+dateEnd+"', '"+char5+"', '"+char4_1+"', '"+char4_2+"', " +
//                    "'"+char4_3+"', '"+version+"')";
//            stmt.executeUpdate(query);
//            System.out.println("Sucesfully added banner to database!");
//            con.close();
//        } catch (Exception e){
//            System.out.println(e);
//        }   }
//        System.out.print("\nclick any button to go back to menu:");
//        String exit = inputString();
//        displayMenu(banners,characters);}
    public static void importExportCharacter(List<Banner> banners, List<Character> characters){

    }
    public static void importExportBanner(List<Banner> banners, List<Character> characters){

    }
}