import java.util.*;
import java.util.List;
public class Main {
    public static void main(String[] args){
        System.out.println("---------- GENSHIN DATABASE PROJECT ----------");
        System.out.println("1. Import data from CSV file");
        System.out.println("2. Import data from database");
        DataBase database;
        while(true){
            System.out.print("\nplease put your input: ");
            String choice = inputString();
            switch (choice) {
                case "1" -> {
                    List<Banner> bannersCSV = Valid.bannerImportCSV();
                    List<Character> charactersCSV = Valid.characterImportCSV();
//                    database = new DataBase(charactersCSV, bannersCSV);
//                    displayMenu(database);
                }
                case "2" -> {
                    List<Banner> banners = Valid.bannerImportDatabase();
                    List<Character> characters = Valid.characterImportDatabase();
                    List<CharacterId> charId = Valid.charactersId();
                    database = new DataBase(characters, banners,charId);
                    System.out.println("Successfully loaded data from database");
                    displayMenu(database);
                }
                default -> System.out.println("Wrong option - try again!");
            }
        }
    }
    public static String inputString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    public static void exitToMenu(DataBase database){
        System.out.print("\nclick any button to go back to menu:");
        inputString();
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
        System.out.println("7. Search banners by criteria");
        System.out.println("8. Add new banner");
        System.out.println("9. Export characters to csv file");
        System.out.println("10. Export banners to csv file");
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
                    database.ExportCharacter();
                    exitToMenu(database);
                    break;
                case "10":
                    database.ExportBanner();
                    exitToMenu(database);
                    break;
                case "11":
                    System.out.println("Exit program...");
                    System.exit(0); //wymuszenie zamkniecia konsoli
                default:
                    System.out.println("Wrong option - try again");
                    displayMenu(database);
            }
        }
    }
}