package disuqi.model;

public enum SHIP {
    BEIGE("assets/ships/shipBeige_manned.png", "assets/ships/shipBeige.png"),
    BLUE("assets/ships/shipBlue_manned.png", "assets/ships/shipBlue.png"),
    PINK("assets/ships/shipPink_manned.png", "assets/ships/shipPink.png"),
    YELLOW("assets/ships/shipYellow_manned.png", "assets/ships/shipYellow.png");

    private String bodyUrl;
    private String shipUrl;
    
    private SHIP(String bodyUrl, String shipUrl){
        this.bodyUrl = bodyUrl;
        this.shipUrl = shipUrl;
    }

    public String getUrl(){
        return bodyUrl;
    }

    public String getShipUrl(){
        return shipUrl;
    }
}
