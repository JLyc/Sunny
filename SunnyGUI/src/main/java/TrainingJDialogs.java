import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * Created by JLyc on 23. 2. 2015.
 */
public class TrainingJDialogs extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        String mainText = JOptionPane.showInputDialog("get fucking input! ! !").toLowerCase();

        if (lookforpatter(mainText)) {

        }
    }

    private boolean lookforpatter(String mainText) {
        if (mainText.startsWith("sunny")) {

        }

        return false;
    }
}
