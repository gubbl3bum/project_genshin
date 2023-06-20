import java.util.Date;
public class Banner {
    private String name;
    private Date dateStart;
    private Date dateEnd;
    private String character5;
    private String character4_1;
    private String character4_2;
    private String character4_3;
    private String version;
//konstruktor
    public Banner(String name, Date dateStart, Date dateEnd, String character5, String character4_1, String character4_2, String character4_3, String version) {
        this.name = name;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.character5 = character5;
        this.character4_1 = character4_1;
        this.character4_2 = character4_2;
        this.character4_3 = character4_3;
        this.version = version;
    }
    //gettery i setteru
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Date getDateStart() {
        return dateStart;
    }
    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }
    public Date getDateEnd() {
        return dateEnd;
    }
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
    public String getCharacter5() {
        return character5;
    }
    public void setCharacter5(String character5) {
        this.character5 = character5;
    }
    public String getCharacter4_1() {
        return character4_1;
    }
    public void setCharacter4_1(String character4_1) {
        this.character4_1 = character4_1;
    }
    public String getCharacter4_2() {
        return character4_2;
    }
    public void setCharacter4_2(String character4_2) {
        this.character4_2 = character4_2;
    }
    public String getCharacter4_3() {
        return character4_3;
    }
    public void setCharacter4_3(String character4_3) {
        this.character4_3 = character4_3;
    }
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    //wygenerowany toString
    @Override
    public String toString() {
        return "Banner{" +
                "name='" + name + '\'' +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", character5='" + character5 + '\'' +
                ", character4_1='" + character4_1 + '\'' +
                ", character4_2='" + character4_2 + '\'' +
                ", character4_3='" + character4_3 + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}