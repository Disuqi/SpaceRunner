package disuqi.view;

import java.util.Random;

import disuqi.model.SHIP;
import disuqi.model.Score;
import disuqi.model.ShipPicker;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class GameView {
    private AnchorPane pane;
    private Scene scene;
    private Stage stage;

    private static int WIDTH = 600;
    private static int HEIGHT = 800;

    private Stage menuStage;
    private ImageView ship;

    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private int angle;
    private AnimationTimer timer;

    private GridPane gridPane1, gridPane2;
    private final static String BACKGROUND = "backgroundSpace.png";

    private final static String METEOR_GREY = "meteorGrey_big3.png";
    private final static String METEOR_BROWN = "meteorBrown_big1.png";

    private ImageView[] brownMeteors;
    private ImageView[] greyMeteors;

    Random randomPos;

    private ImageView star;
    private Score pointsLabel;
    private ImageView[] playerLifes;
    private int playerLife;
    private int points;
    private final static String STAR = "star.png";
    
    private final static int METEOR_SPEED = 500;
    private final static int METEOR_ROT = 250;

    private final static int STAR_SPEED = 200;

    private final static int PLAYER_SPEED = 400;
    private final static int PLAYER_ROT = 300;

    private final static int BACKGROUND_SPEED = 100;

    public GameView(){
        initStage();
        initEventsListeners();
        randomPos = new Random();
    }

    public void createNewGame(Stage menuStage, SHIP choosenShip){
        this.menuStage = menuStage;
        this.menuStage.hide();
        createBackground();
        createShip(choosenShip);
        createGameElements(choosenShip);
        initAnimTimer();
        this.stage.show();
    }

    private void initStage(){
        pane = new AnchorPane();
        scene = new Scene(pane, WIDTH, HEIGHT);
        stage = new Stage();
        stage.setScene(scene);
    }

    private void initEventsListeners(){
        scene.setOnKeyPressed((event) ->{
            if(event.getCode() == KeyCode.LEFT){
                isLeftKeyPressed = true;
            }else if(event.getCode() == KeyCode.RIGHT){
                isRightKeyPressed = true;
            }
        });

        scene.setOnKeyReleased((event) ->{
            if(event.getCode() == KeyCode.LEFT){
                isLeftKeyPressed = false;
            }else if(event.getCode() == KeyCode.RIGHT){
                isRightKeyPressed = false;
            }
        });
    }

    private void createGameElements(SHIP ship){
        playerLife = 2;
        star = new ImageView(GameView.class.getResource(STAR).toString());
        star.setFitWidth(70);
        star.setFitHeight(70);
        star.setPreserveRatio(true);
        setRandPos(star);
        pane.getChildren().add(star);
        pointsLabel = new Score("Score: 00");
        pointsLabel.setLayoutX(460);
        pointsLabel.setLayoutY(20);
        pane.getChildren().add(pointsLabel);
        playerLifes = new ImageView[3];

        for(int i = 0; i < playerLifes.length; i++){
            playerLifes[i] = new ImageView(Score.class.getResource(ship.getShipUrl()).toString());
            
            playerLifes[i].setFitWidth(40);
            playerLifes[i].setPreserveRatio(true);

            playerLifes[i].setLayoutX(455 + (i * 50));
            playerLifes[i].setLayoutY(80);

            pane.getChildren().add(playerLifes[i]);
        }

        brownMeteors = new ImageView[3];
        for(int i = 0; i < brownMeteors.length; i++){
            brownMeteors[i] = new ImageView(GameView.class.getResource(METEOR_BROWN).toString());
            brownMeteors[i].setFitWidth(90);
            brownMeteors[i].setFitHeight(90);
            brownMeteors[i].setPreserveRatio(true);
            setRandPos(brownMeteors[i]);
            pane.getChildren().add(brownMeteors[i]);
        }
        greyMeteors = new ImageView[3];
        for(int i = 0; i < greyMeteors.length; i++){
            greyMeteors[i] = new ImageView(GameView.class.getResource(METEOR_GREY).toString());
            greyMeteors[i].setFitWidth(90);
            greyMeteors[i].setFitHeight(90);
            greyMeteors[i].setPreserveRatio(true);
            setRandPos(greyMeteors[i]);
            pane.getChildren().add(greyMeteors[i]);
        }
    }

    private void setRandPos(ImageView element){
        element.setLayoutX(randomPos.nextInt(370));
        element.setLayoutY(-(randomPos.nextInt(3200) + 600));
    }

    private void moveGameElements(){
        double dt  = FPS.getDeltaTime();
        star.setLayoutY(star.getLayoutY() + STAR_SPEED * dt);

        for(int i = 0; i < brownMeteors.length; i++){
            brownMeteors[i].setLayoutY(brownMeteors[i].getLayoutY() + METEOR_SPEED * dt);
            brownMeteors[i].setRotate(brownMeteors[i].getRotate() + METEOR_ROT * dt);
        }

        for(int i = 0; i < greyMeteors.length; i++){
            greyMeteors[i].setLayoutY(greyMeteors[i].getLayoutY() + METEOR_SPEED * dt);
            greyMeteors[i].setRotate(greyMeteors[i].getRotate() + METEOR_ROT * dt);
        }
    }

    private void checkOutOfBounds(){
        if(star.getLayoutY() >= 1024){
            setRandPos(star);
        }

        for(int i = 0; i < brownMeteors.length; i++){
            if(brownMeteors[i].getLayoutY() >= 1024){
                setRandPos(brownMeteors[i]);
            }
        }

        for(int i = 0; i < greyMeteors.length; i++){
            if(greyMeteors[i].getLayoutY() >= 1024){
                setRandPos(greyMeteors[i]);
            }
        }
    }

    private void createShip(SHIP choosenShip){
        try{

            ship = new ImageView(SHIP.class.getResource(choosenShip.getUrl()).toExternalForm());
            ship.setFitWidth(100);
            ship.setFitHeight(150);
            ship.setPreserveRatio(true);
            ship.setLayoutX((WIDTH/2) - (85/2));
            ship.setLayoutY(HEIGHT - 120);
            pane.getChildren().add(ship);
        }catch(NullPointerException e){
            System.out.println("Failed");
        }
    }

    private void initAnimTimer(){
        timer = new AnimationTimer(){
            @Override
            public void handle(long now){
                FPS.calcDeltaTime();
                moveBackground();
                moveGameElements();
                checkOutOfBounds();
                moveShip();
                checkShipCollision();
            }
        };
        FPS.calcBeginTime();
        timer.start();
    }

    private void moveShip(){
        double dt = FPS.getDeltaTime();
        if(isLeftKeyPressed && !isRightKeyPressed){
            if(angle > -30){
                angle -= PLAYER_ROT * dt;
            }
            ship.setRotate(angle);
            if(ship.getLayoutX() > -20){
                ship.setLayoutX(ship.getLayoutX() - PLAYER_SPEED * dt);
            }
        }

        if(isRightKeyPressed && !isLeftKeyPressed){
            if(angle < 30){
                angle += PLAYER_ROT * dt;
            }
            ship.setRotate(angle);
            if(ship.getLayoutX() < 522){
                ship.setLayoutX(ship.getLayoutX() + PLAYER_SPEED * dt);
            }
        }

        if((!isLeftKeyPressed && !isRightKeyPressed)||(isLeftKeyPressed && isRightKeyPressed)){
            if(angle < 0){
                angle += PLAYER_ROT * dt;
            } else if(angle > 0){
                angle -= PLAYER_ROT * dt;
            }
            ship.setRotate(angle);
        }   
    }   

    private void createBackground(){
        gridPane1 = new GridPane();
        gridPane2 = new GridPane();

        for(int i = 0; i < 12; i++){
            ImageView backgroundImage1 = new ImageView(GameView.class.getResource(BACKGROUND).toString());
            ImageView backgroundImage2 = new ImageView(GameView.class.getResource(BACKGROUND).toString());
            GridPane.setConstraints(backgroundImage1, i% 3, i/3);
            GridPane.setConstraints(backgroundImage2, i% 3, i/3);
            gridPane1.getChildren().add(backgroundImage1);
            gridPane2.getChildren().add(backgroundImage2);
        }

        gridPane1.setLayoutY(0);
        gridPane2.setLayoutY(-1024);

        pane.getChildren().addAll(gridPane1, gridPane2);
    }

    private void moveBackground(){
        double dt = FPS.getDeltaTime();
        gridPane1.setLayoutY(gridPane1.getLayoutY() + BACKGROUND_SPEED * dt);
        gridPane2.setLayoutY(gridPane2.getLayoutY() + BACKGROUND_SPEED * dt);

        if(gridPane1.getLayoutY() >= 1024){
            gridPane1.setLayoutY(-1024);
        }
        else if(gridPane2.getLayoutY() >= 1024){
            gridPane2.setLayoutY(-1024);
        }
    }

    private void checkShipCollision(){
        if(ship.getFitWidth()/2 + star.getFitWidth()/2 > calculateDistance(ship.getLayoutX() + ship.getFitWidth()/2, star.getLayoutX() + star.getFitWidth()/2, ship.getLayoutY() + ship.getFitHeight()/2, star.getLayoutY() + star.getFitHeight()/2)){
            setRandPos(star);
            points++;
            String text = "POINTS: ";
            if(points < 10){
                text += "0";
            }
            text += points;
            pointsLabel.setText(text);
        }

        for(int i = 0; i < greyMeteors.length; i++){
            ImageView mt = greyMeteors[i];
            if(ship.getFitWidth()/2 + mt.getFitWidth()/2 > calculateDistance(ship.getLayoutX() +  ship.getFitWidth()/2, mt.getLayoutX() + mt.getFitWidth()/2, ship.getLayoutY() +ship.getFitHeight()/2, mt.getLayoutY() + mt.getFitHeight()/2)){
                removeLife();
                setRandPos(mt);
            }
        }

        
        for(int i = 0; i < brownMeteors.length; i++){
            ImageView mt = brownMeteors[i];
            if(ship.getFitWidth()/2 + mt.getFitWidth()/2 > calculateDistance(ship.getLayoutX() +  ship.getFitWidth()/2, mt.getLayoutX() + mt.getFitWidth()/2, ship.getLayoutY() +ship.getFitHeight()/2, mt.getLayoutY() + mt.getFitHeight()/2)){
                removeLife();
                setRandPos(mt);
            }
        }
    }

    private void removeLife(){
        pane.getChildren().remove(playerLifes[playerLife]);
        playerLife--;
        if(playerLife < 0){
            stage.close();
            timer.stop();
            menuStage.show();
        }
    }

    private double calculateDistance(double x1, double x2, double y1, double y2){
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }   

}
