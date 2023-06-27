public class CharacterId {
    private String name;
    private int quality;
    private int character_id;
    public CharacterId(String name,int quality, int character_id) {
        this.name = name;
        this.character_id = character_id;
        this.quality = quality;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCharacter_id() {
        return character_id;
    }
    public void setCharacter_id(int character_id) {
        this.character_id = character_id;
    }
    public int getQuality() {
        return quality;
    }
    public void setQuality(int quality) {
        this.quality = quality;
    }
}