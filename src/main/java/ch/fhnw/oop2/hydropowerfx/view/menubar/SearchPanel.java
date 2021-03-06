package ch.fhnw.oop2.hydropowerfx.view.menubar;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import ch.fhnw.oop2.hydropowerfx.view.RootPanel;
import ch.fhnw.oop2.hydropowerfx.view.ViewMixin;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;

import java.util.Date;

public class SearchPanel extends StackPane implements ViewMixin {

    private final int SPHEIGHT = 50;
    private final int SPWIDTH = 250;
    private RootPanel rootPanel;
    private RootPM rootPM;
    private Menubar menubar;
    private SearchPanel sp;
    private TextField searchInput;
    private Boolean shown = false;

    public SearchPanel(RootPanel rootPanel, RootPM rootPM, Menubar menubar) {
        this.rootPanel = rootPanel;
        this.rootPM = rootPM;
        this.menubar = menubar;
        this.sp = this;
        this.inits();
        this.init();
    }


    private void inits() {
        this.getStyleClass().add("search-panel");
        this.setManaged(false);
        this.setWidth(SPWIDTH);
        this.setHeight(SPHEIGHT);
    }

    @Override
    public void initializeControls() {
        searchInput = new TextField();
    }

    @Override
    public void layoutControls() {
        this.getChildren().add(searchInput);
    }

    @Override
    public void setupEventHandlers() {

        // close search when escape is pressed
        this.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE || event.getCode() == KeyCode.ENTER) {
                hide();
            }
        });

        // close search input when lost focus
        searchInput.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                hide();
            }
        });
    }

    @Override
    public void setupValueChangedListeners() {
        /*
        searchInput.textProperty().addListener((observable, oldValue, newValue) -> {
            rootPM.searchPowerStations(searchInput.getText());
        });
        */
    }

    @Override
    public void setupBindings() {
        searchInput.textProperty().bindBidirectional(rootPM.searchTextProperty());
    }

    public void showhide() {
        if (this.rootPM.isSearchpanelShown()) {
            hide();
        } else {
            show();
        }
    }


    //get that search visible
    public void show() {
        if ((rootPM.getSearchLastVisible() + 500) <= new Date().getTime()) {
            this.setLayoutY(this.menubar.getSearch().getLayoutY());
            this.setLayoutX(50);

            Platform.runLater(() -> {
                searchInput.selectAll();
                searchInput.requestFocus();
                searchInput.isFocused();
            });

            rootPanel.getChildren().add(this);
            rootPM.setSearchpanelShown(true);
        }
    }

    // empty that search and hide it
    public void hide() {
        this.setLayoutY(this.menubar.getSearch().getLayoutY());
        this.setLayoutX(-(this.SPWIDTH));
        rootPM.setSearchpanelShown(false);
        rootPanel.getChildren().remove(this);
        rootPM.setSearchLastVisible(new Date().getTime());
    }
}
