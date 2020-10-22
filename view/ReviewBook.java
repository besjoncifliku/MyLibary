/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Book;

/**
 *
 * @author besjon
 */
public class ReviewBook {
    private Book book;
    public ReviewBook(Book book){
        this.book=book;
    }
    
    public void showReview(){
     
        Stage stage = new Stage();

        BorderPane mainPane = new BorderPane();
        Label title =new Label(book.getTitle()+" by "+book.getAuthor());
        title.setTextFill(Color.BLACK);
        
        mainPane.setTop(title);
        
        //mainPane.setCenter(title);
        BorderPane.setAlignment(title, Pos.CENTER);

        TextArea area = new TextArea();
        area.setPrefSize(150,85);
        area.setPrefHeight(50);
        area.setPrefWidth(100);
        
        BorderPane pane = new BorderPane();
        pane.setCenter(new HBox(new Label("\t"),new VBox(new Label("\t"),area,new Label("\t"))));
        pane.setPrefSize(200, 150);
        Text tx = new Text();
        tx.setText(area.getText());
        //pane.resize(150,85);
        if(!book.getReview().isEmpty())
        area.setText(book.getReview());
        else area.setText("Empty Review!");
        area.setEditable(false);
        
        Alert al =new  Alert(AlertType.INFORMATION,title.getText()+"\n"+area.getText());
        al.show();
        
        mainPane.setStyle("-fx-background-color: white");
        
        Button close = new Button("Close");
        close.setOnAction(e->{
            stage.close();
        });
        BorderPane.setAlignment(close, Pos.CENTER);
        mainPane.setBottom(close);
       // mainPane.setCenter(close);
        Scene sc = new Scene(mainPane,200,150);
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setScene(sc);
        stage.setTitle("Review");
        //stage.show();
        
        }
}
