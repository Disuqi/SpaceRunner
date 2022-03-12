package disuqi.view;

import java.util.ArrayList;
import java.util.List;

import disuqi.model.*;


import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class View {
    
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    //Buttons
    List<MyButton> menuButtons;

    List<ShipPicker> shipPickers;

    private SHIP choosenShip;

    private final static int MENU_BUTTON_START_X= 100;
    private final static int MENU_BUTTON_START_Y = 150;

    private MySubScene playSS;
    private MySubScene scoreSS;
    private MySubScene helpSS;

    private MySubScene currentSS;

    public View(){
        menuButtons = new ArrayList<MyButton>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createButtons();
        createBackground();
        createSubScenes();
    }

    public Stage getMainStage(){
        return mainStage;
    }

    private void createSubScenes(){
        createPlaySS();

        scoreSS = new MySubScene();
        mainPane.getChildren().add(scoreSS);

        helpSS = new MySubScene();
        mainPane.getChildren().add(helpSS);

        currentSS = null;
    }

    private void createPlaySS(){
        playSS = new MySubScene();
        mainPane.getChildren().add(playSS);

        InfoLabel chooseShipLabel = new InfoLabel("CHOOSE YOUR SHIP");
        chooseShipLabel.setLayoutX(0);
        chooseShipLabel.setLayoutY(0);

        playSS.getPane().getChildren().add(chooseShipLabel);

        playSS.getPane().getChildren().add(createShipsToChoose());

        MyButton start = new MyButton("START");
        start.setLayoutX(190);
        start.setLayoutY(330);

        start.setOnAction((event) ->{
            if(choosenShip != null){
                GameView gameView = new GameView();
                gameView.createNewGame(mainStage, choosenShip);
            }
        });

        playSS.getPane().getChildren().add(start);

    }

    private HBox createShipsToChoose(){
        HBox box = new HBox();
        box.setSpacing(10);

        shipPickers = new ArrayList<>();
        for(SHIP ship : SHIP.values()){
            ShipPicker shipToPick = new ShipPicker(ship);
            shipToPick.setOnMouseClicked((event) ->{
                for(ShipPicker shipPicker : shipPickers){
                    shipPicker.setIsChoosen(false);
                }
                shipToPick.setIsChoosen(true);
                choosenShip = shipToPick.getShip();
            });
            shipPickers.add(shipToPick);
            box.getChildren().add(shipToPick);
        }
        box.setLayoutX(10);
        box.setLayoutY(100);
        return box;
    }

    private void moveSS(MySubScene scene){
        if(currentSS == scene){
            scene.moveSubScene();
            currentSS = null;
        }else{
            if(currentSS != null){
                currentSS.moveSubScene();
            }
            scene.moveSubScene();
            currentSS = scene;
        }
    }

    private void createButtons(){
        MyButton playBtn  = createButton("PLAY");
        playBtn.setOnAction((event) -> {
            moveSS(playSS);
        });

        MyButton scoreBtn = createButton("SCORES");
        scoreBtn.setOnAction((event) -> {
            moveSS(scoreSS);
        });
        
        MyButton helpBtn  = createButton("HELP");
        helpBtn.setOnAction((event) -> {
            moveSS(helpSS);
        });

        MyButton exitBtn  = createButton("EXIT");
        exitBtn.setOnAction((event) -> {
            mainStage.close();
        });
        
    }

    private void createMenuButton(MyButton button){
        button.setLayoutX(MENU_BUTTON_START_X);
        button.setLayoutY(MENU_BUTTON_START_Y + menuButtons.size() * 100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    private MyButton createButton(String name){
        MyButton button = new MyButton(name);
        createMenuButton(button);
        return button;
    }

    private void createBackground(){
        mainPane.setStyle("-fx-background-color: lightblue;");
    }
}
