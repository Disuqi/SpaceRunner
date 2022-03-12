package disuqi.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

public class MyButton extends Button {
    private final String FONT_PATH = "C:/Users/Disuqi/Desktop/Uni/Year2/Design Patterns/Assignment/assignment/src/main/java/disuqi/model/assets/buttons/kenvector.ttf";

    public MyButton(String text){
        setText(text);
        setButtonFont();
        setPrefWidth(190);
        setButtonReleasedStyle();
        constructEventHandlers();
    }

    private void setButtonFont(){
        try{
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
        }catch(FileNotFoundException e){
            setFont(Font.font("Verdana", 23));
        }
    }

    private void setButtonPressedStyle(){
        Image backgroundImage = new Image(MyButton.class.getResource("assets/buttons/yellow_button_pressed.png").toExternalForm());
        BackgroundImage background  = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        setBackground(new Background(background));
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4);
    }

    private void setButtonReleasedStyle(){
        Image backgroundImage = new Image(MyButton.class.getResource("assets/buttons/yellow_button.png").toExternalForm());
        BackgroundImage background  = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        setBackground(new Background(background));
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 4);
    }

    private void constructEventHandlers(){
        setOnMousePressed(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    setButtonPressedStyle();
                }
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    setButtonReleasedStyle();
                }
            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                setEffect(new DropShadow());
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                setEffect(null);
            }
        });
    }
}
