/*
 * Copyright (c) 2015. All rights reserved for autor and owner of email domain JLyc.Development@gmial.com.
 */

package URLStorage;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;

import java.util.*;

/**
 * Created 29. 6. 2015.
 *
 * @author JLyc
 */
public class UIDisplay extends Application{
    Map<String, Tab> paneTabs = new HashMap<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }

    private void init(Stage primaryStage) {
        Group panel = new Group();

        TabPane tabPane = DynamicTabPane();
        tabPane.setTabMaxWidth(250);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
        panel.getChildren().add(tabPane);
        Scene scene = new Scene(panel, 250, 400);
        primaryStage.setScene(scene);
    }

    private TabPane DynamicTabPane() {
        TabPane tabPane = new TabPane();

        paneTabs.put("Settings",new Tab("Settings"));

        CheckBox box = new CheckBox("Closeable tabs");
        box.setOnAction(event -> {
            if(box.isSelected()){
                tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.SELECTED_TAB);
            }else {
                tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
            }
        });

        paneTabs.get("Settings").setClosable(false);
        paneTabs.get("Settings").setContent(box);
        paneTabs.put("BookMark",new Tab("BookMark"));

        for(Map.Entry tab: paneTabs.entrySet()){
            tabPane.getTabs().add((Tab) tab.getValue());
        }
        return tabPane;
    }


    public TabPane constructTabPane(){
        ScrollPane scrollPane = new ScrollPane();

    }

    public static void loadVisualUI(){
        UIDisplay.launch();
    }

    public static void main(String[] args) {
        loadVisualUI();
    }

}
