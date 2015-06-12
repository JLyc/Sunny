package com.development.jlyc;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created 22. 4. 2015.
 * @author JLyc
 */
public class JLycDialog extends Application{

    private static String response;
    private int wight = 150;
    private int height = 100;

    private void init(Stage mainFrame) {
        mainFrame.setTitle("Sunny");

        TextField text = new TextField("Text");
//        text.setMaxSize(140, 20);
        Button button = new Button("OK");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                response = text.getText();
                this.notifyAll();
            }
        });

        VBox vBoxPane = new VBox();
        vBoxPane.setAlignment(Pos.CENTER);


        Group root = new Group();
        vBoxPane.getChildren().add(text);
        vBoxPane.getChildren().add(button);
        root.getChildren().add(vBoxPane);
        Scene scene = new Scene(root, wight, height);
        mainFrame.setScene(scene);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }

    public void showDialog(){
        JLycDialog.launch();
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(JLycDialog.getResponse());
    }

    public static String getResponse() {
        return response;
    }
}
