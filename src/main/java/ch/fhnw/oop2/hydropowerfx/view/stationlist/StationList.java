package ch.fhnw.oop2.hydropowerfx.view.stationlist;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.CSVFields;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import ch.fhnw.oop2.hydropowerfx.view.ViewMixin;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;


public class StationList extends VBox implements ViewMixin {

    private RootPM rootPM;
    private Label stationListTitle;
    private ListView<CSVFields> stationList;
    private Label currentMaxItems;

    public StationList(RootPM rootPM) {
        this.rootPM = rootPM;
        init();
    }


    @Override
    public void initializeSelf() {
        this.getStyleClass().add("stationlist");

    }

    @Override
    public void initializeControls() {
        stationListTitle = new Label();
        stationListTitle.getStyleClass().addAll("stationlist-title", "title");

        stationList = new ListView<>();
        stationList.getStyleClass().add("stationlist-listview");
        stationList.setItems(rootPM.getDataList());
        stationList.setCellFactory(new Callback<ListView<CSVFields>, ListCell<CSVFields>>() {
            @Override
            public ListCell<CSVFields> call(ListView<CSVFields> param) {
                return new StationListCell();
            }
        });
        stationList.getSelectionModel().select(0);
        stationList.getFocusModel().focus(0);
        stationList.scrollTo(0);
        setVgrow(stationList, Priority.ALWAYS);

        currentMaxItems = new Label();
        currentMaxItems.getStyleClass().add("label-sm");
        currentMaxItems.setTextAlignment(TextAlignment.RIGHT);
    }

    @Override
    public void layoutControls() {
        this.getChildren().addAll(stationListTitle, stationList, currentMaxItems);
    }

    @Override
    public void setupEventHandlers() {
        stationList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            rootPM.setEditorStationName(newValue.getName());
        });
    }

    @Override
    public void setupValueChangedListeners() {

    }

    @Override
    public void setupBindings() {
        stationListTitle.textProperty().bind(rootPM.stationListTitleTextProperty());
        currentMaxItems.textProperty().bind(rootPM.currentMaxItemsTextProperty());
    }
}