package ui_fx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by sochaa on 5. 12. 2014.
 */
public class SunnyFace extends Application{
    public ProgressBar[] pb = {new ProgressBar(),new ProgressBar(),new ProgressBar()};

    @Override
    public void start(Stage stage) throws Exception {
        init(stage);
        StageStyle stageStyle = StageStyle.TRANSPARENT;
        stage.initStyle(stageStyle);

        stage.setX(Screen.getPrimary().getVisualBounds().getMaxX()-90);
        stage.setY(Screen.getPrimary().getVisualBounds().getMaxY() - 100);
        stage.getIcons().add(new Image(SunnyFace.class.getClassLoader().getResourceAsStream("sunny.png")));
        stage.show();
        stage.setAlwaysOnTop(true);
        System.out.println("Face \t\t\t\t load successful: "+ true);
        FaceThread.getInstance().returnInstance(this);
    }


    private void init(Stage mainFrame) {
        mainFrame.setResizable(false);
        mainFrame.setTitle("Sunny");

        pb[0].setRotate(270);
        pb[0].setPrefWidth(50);
        pb[0].setLayoutX(10);
        pb[0].setLayoutY(50);

        pb[1].setRotate(270);
        pb[1].setPrefWidth(70);
        pb[1].setLayoutX(15);
        pb[1].setLayoutY(50);

        pb[2].setRotate(270);
        pb[2].setPrefWidth(50);
        pb[2].setLayoutX(40);
        pb[2].setLayoutY(50);

        Group root = new Group();
        root.getChildren().addAll(pb);
        Scene scene = new Scene(root, 90, 100);
        scene.setFill(Color.rgb(0, 0, 0));
        mainFrame.setScene(scene);
    }



    static SunnyFace instance;

    public static void show()
    {
        SunnyFace.launch();
    }
}
