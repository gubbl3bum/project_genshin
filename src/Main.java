import java.util.*;
public class Main {
    public static void main(String[] args){
        System.out.println("---------- GENSHIN DATABASE PROJECT ----------");
        System.out.println("1. Import data from CSV file");
        System.out.println("2. Import data from database");
        DataBase database = Valid.selectDatabaseInput();
        displayMenu(database);
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
        System.out.println("5. Delete character");
        System.out.println("6. Display all banners");
        System.out.println("7. Current banner");
        System.out.println("8. Search banners by criteria");
        System.out.println("9. Add new banner");
        System.out.println("10. Delete banner");
        System.out.println("10. Export characters to csv file");
        System.out.println("11. Export banners to csv file");
        System.out.println("12. Exit ");
        System.out.print("\nplease put your input: ");
        String option = inputString();
        System.out.println(" ");
        while(true){
            switch(option){
                case "1":
                    database.displayCharacter();
                    exitToMenu(database);
                case "2":
                    database.displayCharacterStats();
                    exitToMenu(database);
                case "3":
                    database.addCharacter();
                    exitToMenu(database);
                case "4":
                    database.searchCharacters();
                    exitToMenu(database);
                case "5":
                    database.deleteCharacter();
                    exitToMenu(database);
                case "6":
                    database.displayBanner();
                    exitToMenu(database);
                case "7":
                    database.currentBanner();
                    exitToMenu(database);
                case "8":
                    database.searchBanners();
                    exitToMenu(database);
                case "9":
                    database.addBanner();
                    exitToMenu(database);
                case "10":
                    database.deleteBanner();
                    exitToMenu(database);
                case "11":
                    database.ExportCharacter();
                    exitToMenu(database);
                case "12":
                    database.ExportBanner();
                    exitToMenu(database);
                case "13":
                    System.out.println("Exit program...");
                    System.exit(0); //wymuszenie zamkniecia konsoli
                default:
                    System.out.println("Wrong option - try again");
                    displayMenu(database);
            }
        }
    }
}