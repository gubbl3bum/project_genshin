import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class Main {
    public static void main(String[] args){
        System.out.println("---------- GENSHIN PROJECT ----------");
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
        for(Banner banner : banners){
            System.out.print("name: " + banner.getName());
            System.out.print(", date start: " + banner.getDateStart());
            System.out.print(", date end: " + banner.getDateEnd());
            System.out.print("\n5* character: " + banner.getCharacter5());
            System.out.print("\n4* character: " + banner.getCharacter4_1());
            System.out.print(", 4* character: " + banner.getCharacter4_2());
            System.out.print(", 4* character: " + banner.getCharacter4_3());
            System.out.println("\n");
        }

        Iterator<Banner> it = banners.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
