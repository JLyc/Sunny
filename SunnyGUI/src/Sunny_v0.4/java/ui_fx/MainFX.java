package ui_fx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

/**
 * Created by sochaa on 2. 12. 2014.
 */
public class MainFX extends Application
{

    public static ProgressBar p1 = new ProgressBar();
    public static ProgressBar p2 = new ProgressBar();
    public static ProgressBar p3 = new ProgressBar();

    @Override
    public void start(Stage stage) throws Exception {
        init(stage);
        stage.show();
    }

    private void init(Stage mainFrame) {
        mainFrame.setResizable(false);
        mainFrame.setTitle("Sunny");

        Group root = new Group();

        p1.setRotate(90);
        p1.setPrefWidth(50);
        p1.setLayoutX(20);
        p1.setLayoutY(50);

        p2.setRotate(90);
        p2.setPrefWidth(70);
        p2.setLayoutX(25);
        p2.setLayoutY(50);

        p3.setRotate(90);
        p3.setPrefWidth(50);
        p3.setLayoutX(50);
        p3.setLayoutY(50);


        root.getChildren().addAll(p1, p3, p2);
        mainFrame.setScene(new Scene(root, 100, 150));
        p1.setProgress(1.0);
        p2.setProgress(-1.0);
        p3.setProgress(1.0);
    }

    public static void makeItWork()
    {
        MainFX.launch();
    }
}
