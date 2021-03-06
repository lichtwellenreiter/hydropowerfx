package ch.fhnw.oop2.hydropowerfx.view.menubar;

import ch.fhnw.oop2.hydropowerfx.export.PDFExport;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import ch.fhnw.oop2.hydropowerfx.view.RootPanel;
import ch.fhnw.oop2.hydropowerfx.view.ViewMixin;
import ch.fhnw.oop2.hydropowerfx.view.intro.IntroItem;
import ch.fhnw.oop2.hydropowerfx.view.notification.NotificationPanel;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class Menubar extends VBox implements ViewMixin {

    private RootPM rootPM;
    private RootPanel rootPanel;
    private Menubar menubar;
    private VBox buttonCol;
    private VBox footerCol;


    private Button undo;
    private Button redo;
    private Button newstation;
    private Button deletestation;
    private Button savestation;
    private Button search;
    private Button clearFilter;
    private Button topdf;
    private Button settings;
    private Label version;

    private ImageView hpfxlogo;
    private ImageView undoImage;
    private ImageView redoImage;
    private ImageView newstationImage;
    private ImageView deletestationImage;
    private ImageView savestationImage;
    private ImageView searchImage;
    private ImageView clearFilterImage;
    private ImageView topdfImage;
    private ImageView settingsImage;
    private SearchPanel searchpanel;

    public Menubar(RootPM rootPM, RootPanel rootPanel) {
        this.rootPM = rootPM;
        this.rootPanel = rootPanel;
        this.menubar = this;
        init();
    }

    @Override
    public void initializeSelf() {
        this.getStyleClass().add("menubar");
    }

    @Override
    public void initializeControls() {
        // undo Button
        undoImage = new ImageView(new Image(getClass().getResourceAsStream("/ch/fhnw/oop2/hydropowerfx/view/assets/images/undo.png")));
        undo = new Button();
        undo.getStyleClass().addAll("menubar-item", "menubar-button", "undo");
        undo.setGraphic(undoImage);
        undo.setTooltip(new Tooltip("Widerrufen"));
        rootPM.getIntroItems().add(new IntroItem(undo, IntroItem.ARROW.UPLEFT, "Mache ungewollte Änderungen rückgängig"));

        // redo Button
        redoImage = new ImageView(new Image(getClass().getResourceAsStream("/ch/fhnw/oop2/hydropowerfx/view/assets/images/redo.png")));
        redo = new Button();
        redo.getStyleClass().addAll("menubar-item", "menubar-button", "redo");
        redo.setGraphic(redoImage);
        redo.setTooltip(new Tooltip("Wiederholen"));
        rootPM.getIntroItems().add(new IntroItem(redo, IntroItem.ARROW.UPLEFT, "Stelle rückgängig gemacht Änderungen wieder her"));

        // new station
        newstationImage = new ImageView(new Image(getClass().getResourceAsStream("/ch/fhnw/oop2/hydropowerfx/view/assets/images/new.png")));
        newstation = new Button();
        newstation.getStyleClass().addAll("menubar-item", "menubar-button", "newstation");
        newstation.setGraphic(newstationImage);
        newstation.setTooltip(new Tooltip("Neue Station einfügen"));
        rootPM.getIntroItems().add(new IntroItem(newstation, IntroItem.ARROW.UPLEFT, "Erstelle eine neue Station"));

        // savestation
        savestationImage = new ImageView(new Image(getClass().getResourceAsStream("/ch/fhnw/oop2/hydropowerfx/view/assets/images/save.png")));
        savestation = new Button();
        savestation.getStyleClass().addAll("menubar-item", "menubar-button", "savestation");
        savestation.setGraphic(savestationImage);
        savestation.setTooltip(new Tooltip("Station speichern"));
        rootPM.getIntroItems().add(new IntroItem(savestation, IntroItem.ARROW.UPLEFT, "Speichere deine Änderungen"));

        // delete Station
        deletestationImage = new ImageView(new Image(getClass().getResourceAsStream("/ch/fhnw/oop2/hydropowerfx/view/assets/images/delete.png")));
        deletestation = new Button();
        deletestation.getStyleClass().addAll("menubar-item", "menubar-button", "deletestation");
        deletestation.setGraphic(deletestationImage);
        deletestation.setTooltip(new Tooltip("Station löschen"));
        rootPM.getIntroItems().add(new IntroItem(deletestation, IntroItem.ARROW.UPLEFT, "Lösche eine Station"));

        // search Button
        searchImage = new ImageView(new Image(getClass().getResourceAsStream("/ch/fhnw/oop2/hydropowerfx/view/assets/images/search.png")));
        search = new Button();
        search.getStyleClass().addAll("menubar-item", "menubar-button", "search");
        search.setGraphic(searchImage);
        searchpanel = new SearchPanel(rootPanel, rootPM, menubar);
        search.setTooltip(new Tooltip("Station suchen"));
        rootPM.getIntroItems().add(new IntroItem(search, IntroItem.ARROW.UPLEFT, "Suche nach Stationen"));

        // Delete Filter
        clearFilterImage = new ImageView(new Image(getClass().getResourceAsStream("/ch/fhnw/oop2/hydropowerfx/view/assets/images/clearfilter.png")));
        clearFilter = new Button();
        clearFilter.getStyleClass().addAll("menubar-item", "menubar-button", "clearfilter");
        clearFilter.setGraphic(clearFilterImage);
        clearFilter.setTooltip(new Tooltip("Filter löschen"));
        rootPM.getIntroItems().add(new IntroItem(clearFilter, IntroItem.ARROW.UPLEFT, "Lösche aktive Filter"));

        // PDF Exporter
        topdfImage = new ImageView(new Image(getClass().getResourceAsStream("/ch/fhnw/oop2/hydropowerfx/view/assets/images/topdf.png")));
        topdf = new Button();
        topdf.getStyleClass().addAll("menubar-item", "menubar-button", "topdf");
        topdf.setGraphic(topdfImage);
        topdf.setTooltip(new Tooltip("Station als PDF speichern"));
        rootPM.getIntroItems().add(new IntroItem(topdf, IntroItem.ARROW.UPLEFT, "Exportiere eine Station als PDF"));

        // settings Button
        settingsImage = new ImageView(new Image(getClass().getResourceAsStream("/ch/fhnw/oop2/hydropowerfx/view/assets/images/settings.png")));
        settings = new Button();
        settings.getStyleClass().addAll("menubar-item", "menubar-button", "settings");
        settings.setGraphic(settingsImage);
        settings.setTooltip(new Tooltip("Einstellungen öffnen"));
        rootPM.getIntroItems().add(new IntroItem(settings, IntroItem.ARROW.DOWMLEFT, "Nimm Einstellungen vor"));

        // version Label
        version = new Label();
        version.getStyleClass().addAll("menubar-item", "version");
        version.setAlignment(Pos.CENTER);
        rootPM.getIntroItems().add(new IntroItem(version, IntroItem.ARROW.DOWMLEFT, "WOW, sogar mit Versionsnummer"));

        buttonCol = new VBox();
        footerCol = new VBox();

    }

    @Override
    public void layoutControls() {
        buttonCol.getChildren().addAll(undo, redo, newstation, savestation, deletestation, search, clearFilter, topdf);
        footerCol.getChildren().addAll(settings, version);
        this.getChildren().addAll(buttonCol, footerCol);
        this.setVgrow(buttonCol, Priority.ALWAYS);
    }

    @Override
    public void setupEventHandlers() {
        undo.setOnAction(event -> rootPM.undo());
        redo.setOnAction(event -> rootPM.redo());

        settings.setOnAction(event -> rootPM.openPreferences());
        newstation.setOnAction(event -> rootPM.addPowerStation());


        deletestation.setOnAction(ae -> {

            String name = rootPM.getActualPowerStation().getName();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

            DialogPane dialogPane = alert.getDialogPane();
            String stylesheet = getClass().getResource("../assets/style.css").toExternalForm();
            dialogPane.getStylesheets().add(stylesheet);
            dialogPane.getStyleClass().add("alert-dialog");

            alert.setTitle("Löschen bestätigen");
            alert.setHeaderText("Soll die Station " + name + " gelöscht werden?");
            alert.showAndWait().ifPresent(result -> {
                if (result.getButtonData() == ButtonBar.ButtonData.OK_DONE) {
                    rootPM.deletePowerStation();
                    new NotificationPanel(rootPanel, name + " gelöscht", NotificationPanel.Type.SUCCESS).show();
                }
            });

        });


        savestation.setOnAction(event -> {
            rootPM.save();
        });

        search.setOnAction(event -> searchpanel.showhide());
        topdf.setOnAction(event -> new PDFExport(rootPM.getActualPowerStation(), rootPM, rootPanel));

        clearFilter.setOnAction(event -> {
            rootPM.setCantonFilter("");
            rootPM.setSearchText("");
            new NotificationPanel(rootPanel, "Filter gelöscht", NotificationPanel.Type.SUCCESS).show();
        });

        version.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getClickCount() == 4) {
                    rootPM.openEgg();
                }
            }
        });

    }


    @Override
    public void setupBindings() {
        undo.disableProperty().bind(rootPM.undoDisabledProperty());
        redo.disableProperty().bind(rootPM.redoDisabledProperty());
        clearFilter.disableProperty().bind(rootPM.disabledClearFilterProperty());
        savestation.disableProperty().bind(rootPM.disableSaveProperty());
        version.textProperty().bind(rootPM.versionInformationProperty());
    }

    //getter for searchbutton

    public Button getSearch() {
        return search;
    }
}
