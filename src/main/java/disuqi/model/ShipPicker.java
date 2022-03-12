package disuqi.model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ShipPicker extends VBox{
    private ImageView circleImage;
    private ImageView shipImage;
    
    private String circleNotChoosen = "assets/ships/grey_circle.png";
    private String circleChoosen = "assets/ships/yellow_boxTick.png";

    private SHIP ship;

    private boolean isCircleChoosen;

    public ShipPicker(SHIP ship) {
        circleImage = new ImageView(ShipPicker.class.getResource(circleNotChoosen).toExternalForm());
        shipImage = new ImageView(ShipPicker.class.getResource(ship.getUrl()).toExternalForm());

        shipImage.setFitWidth(85);
        shipImage.setPreserveRatio(true);

        this.ship = ship;
        isCircleChoosen = false;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        this.getChildren().add(circleImage);
        this.getChildren().add(shipImage);
    }

    public SHIP getShip(){
        return ship;
    }

    public boolean getIsChoosen(){
        return isCircleChoosen;
    }

    public void setIsChoosen(boolean isChoosen){
        this.isCircleChoosen = isChoosen;
        String imageToSet = this.isCircleChoosen ? circleChoosen : circleNotChoosen;
        circleImage.setImage(new Image(ShipPicker.class.getResource(imageToSet).toExternalForm()));
    }
}
