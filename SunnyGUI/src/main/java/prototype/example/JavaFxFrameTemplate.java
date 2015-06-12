/*
 * Copyright (c) 2015. All rights reserved for autor and owner of email domain JLyc.Development@gmial.com.
 */

package prototype.example;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.stage.Stage;

/**
 * Created 11. 6. 2015.
 *
 * @author JLyc
 */
public class JavaFxFrameTemplate extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        init(primaryStage); //
        primaryStage.show();
    }

    private void init(Stage primaryStage ) {
        Group root = new Group();


        root.getChildren().add(lave);
        Scene scene = new Scene(root, 90, 100);
        primaryStage.setScene(scene);
    }


    public static void startLaunch() {
        launch();
    }
}
