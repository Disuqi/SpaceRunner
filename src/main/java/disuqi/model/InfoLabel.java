package disuqi.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class InfoLabel extends Label{
    final static String FONT_URL = "C:/Users/Disuqi/Desktop/Uni/Year2/Design Patterns/Assignment/assignment/src/main/java/disuqi/model/assets/buttons/kenvector.ttf";

    public InfoLabel(String text){
        setPrefWidth(380);
        setPrefHeight(20);
        setPadding(new Insets(40, 40, 40, 40));
        setText(text);
        setWrapText(true);
        setLabelFont();
        setAlignment(Pos.CENTER);
    }

    private void setLabelFont(){
        try{
            setFont(Font.loadFont(new FileInputStream(FONT_URL), 23));
        }catch(FileNotFoundException e){
            setFont(Font.font("Verdana", 23));
        }
    }

}
