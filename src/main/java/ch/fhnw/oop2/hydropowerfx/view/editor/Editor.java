package ch.fhnw.oop2.hydropowerfx.view.editor;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import ch.fhnw.oop2.hydropowerfx.view.RootPanel;
import ch.fhnw.oop2.hydropowerfx.view.ViewMixin;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Editor extends HBox implements ViewMixin {

    private RootPM rootPM;
    private RootPanel rootPanel;
    private Label editorStationName;

    public Editor(RootPM rootPM, RootPanel rootPanel) {
        this.rootPM = rootPM;
        this.rootPanel = rootPanel;
        this.init();
    }


    @Override
    public void initializeSelf(){
        this.getStyleClass().add("editor");
    }

    @Override
    public void initializeControls() {
        editorStationName = new Label();
        editorStationName.getStyleClass().addAll("editor-stationname", "title");
    }

    @Override
    public void layoutControls() {
        getChildren().addAll(editorStationName);
    }

    @Override
    public void setupBindings() {
        editorStationName.textProperty().bind(rootPM.editorStationNameProperty());
    }

    @Override
    public void setupEventHandlers() {
    }
}