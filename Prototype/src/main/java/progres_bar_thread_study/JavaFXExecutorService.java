package progres_bar_thread_study;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @web http://java-buddy.blogspot.com/
 */
public class JavaFXExecutorService extends Application {

    @Override
    public void start(Stage primaryStage) {
        ProgressBar progressBar1 = new ProgressBar();
        ProgressBar progressBar2 = new ProgressBar();

        progressBar1.progressProperty().bind(MainClass.getCountThread1().processProperty);
        progressBar2.progressProperty().bind(MainClass.getCountThread2().processProperty);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(progressBar1, progressBar2);

        StackPane root = new StackPane();
        root.getChildren().add(vBox);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("java-buddy.blogspot.com");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
