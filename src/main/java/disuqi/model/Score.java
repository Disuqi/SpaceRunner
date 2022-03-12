package disuqi.model;

import java.io.FileInputStream;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Score extends Label {
    private final static String FONT ="C:/Users/Disuqi/Desktop/Uni/Year2/Design Patterns/Assignment/assignment/src/main/java/disuqi/model/assets/buttons/kenvector.ttf";

    public Score(String text){
        setPrefWidth(150);
        setPrefHeight(50);
        setScoreFont();
        setAlignment(Pos.CENTER_LEFT);
        setPadding(new Insets(10, 10, 10, 10));
        setText(text);
    }

    public void setScoreFont(){
        try{
            setFont(Font.loadFont(new FileInputStream(FONT), 20));
        }
        catch(Exception e){
            System.out.println("Font not found");
        }
    }
}
