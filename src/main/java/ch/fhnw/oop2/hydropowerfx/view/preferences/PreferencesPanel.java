package ch.fhnw.oop2.hydropowerfx.view.preferences;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import ch.fhnw.oop2.hydropowerfx.view.ViewMixin;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.apache.commons.io.IOUtils;

import java.io.IOException;

public class PreferencesPanel extends VBox implements ViewMixin {

    private RootPM rootPM;

    private TabPane tabPane;
    private Tab generalTab;
    private Tab dbTab;
    private Tab aboutTab;

    private Button cancel;
    private Button save;
    private Button close;

    private Label dbLabel;
    private ToggleGroup dbGroup;
    private ToggleButton csvButton;
    private ToggleButton sqliteButton;
    private ToggleButton neo4jButton;

    private CheckBox showIntro;

    private HBox buttonBox;

    public PreferencesPanel(RootPM rootPM) {
        this.rootPM = rootPM;
        init();
    }

    @Override
    public void initializeSelf() {
        addStylesheetFiles("../assets/style.css");
        this.getStyleClass().add("preferences-panel");
    }

    @Override
    public void initializeControls() {
        this.getStyleClass().add("preferences-panel");
        tabPane = new TabPane();
        tabPane.setPrefSize(400, 260);
        tabPane.setMinSize(TabPane.USE_PREF_SIZE, TabPane.USE_PREF_SIZE);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        /*********************************************** Tab 1 - Allgemein *********************************************/
        generalTab = new Tab();

        VBox generalBox = new VBox();
        generalBox.setPadding(new Insets(10));

        showIntro = new CheckBox("Intro beim nächsten Start anzeigen");
        showIntro.setSelected(rootPM.getShowIntro());
        showIntro.getStyleClass().add("preference-showintro");
        generalBox.getChildren().add(showIntro);


        generalTab.setContent(generalBox);
        /*********************************************** Tab 2 - Database *********************************************/
        dbTab = new Tab();
        final VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setTranslateX(10);
        vbox.setTranslateY(10);

        dbTab.setContent(vbox);

        dbLabel = new Label();
        dbLabel.getStyleClass().add("preference-title");

        dbGroup = new ToggleGroup();
        csvButton = new RadioButton();
        csvButton.setToggleGroup(dbGroup);
        csvButton.getStyleClass().add("preference-radio");
        sqliteButton = new RadioButton();
        sqliteButton.setToggleGroup(dbGroup);
        sqliteButton.getStyleClass().add("preference-radio");
        neo4jButton = new RadioButton();
        neo4jButton.setToggleGroup(dbGroup);

        RootPM.DATABASES dbType = rootPM.getDatabaseType();

        if (dbType == RootPM.DATABASES.SQLITE) {
            sqliteButton.setSelected(true);
        } else if (dbType == RootPM.DATABASES.NEO4J) {
            neo4jButton.setSelected(true);
        } else {
            csvButton.setSelected(true);
        }

        neo4jButton.getStyleClass().add("preference-radio");
        vbox.getChildren().addAll(dbLabel, csvButton, sqliteButton, neo4jButton);

        /*********************************************** Tab 3 - About ************************************************/
        aboutTab = new Tab();

        WebView webview = new WebView();
        WebEngine engine = webview.getEngine();
        webview.setContextMenuEnabled(false);

        try {
            String content = IOUtils.toString(this.getClass().getResourceAsStream("/ch/fhnw/oop2/hydropowerfx/view/assets/html/about.html"));
            engine.loadContent(content);
        } catch (IOException e) {
            // TODO Exception handling
        }


        aboutTab.setContent(webview);

        /*********************************************** Set Window up* ***********************************************/
        tabPane.getTabs().addAll(generalTab, dbTab, aboutTab);

        buttonBox = new HBox();
        buttonBox.getStyleClass().add("preferences-buttonbox");
        cancel = new Button("Abbrechen");
        cancel.getStyleClass().add("preferences-cancel");
        save = new Button("Speichern");
        save.getStyleClass().add("preferences-save");

        this.setVgrow(buttonBox, Priority.ALWAYS);

    }

    @Override
    public void layoutControls() {
        buttonBox.getChildren().addAll(cancel, save);
        getChildren().addAll(tabPane, buttonBox);
    }

    @Override
    public void setupEventHandlers() {
        cancel.setOnAction(event -> cancel.getScene().getWindow().hide());

        save.setOnAction(event -> {
            if (sqliteButton.isSelected()) {
                rootPM.updateDatabaseType(RootPM.DATABASES.SQLITE);
            } else if (neo4jButton.isSelected()) {
                rootPM.updateDatabaseType(RootPM.DATABASES.NEO4J);
            } else {
                rootPM.updateDatabaseType(RootPM.DATABASES.CSV);
            }

            rootPM.setShowIntro(showIntro.isSelected());

            cancel.getScene().getWindow().hide();
        });

    }

    @Override
    public void setupValueChangedListeners() {
    }

    @Override
    public void setupBindings() {

        generalTab.textProperty().bind(rootPM.generalTitleProperty());

        dbTab.textProperty().bind(rootPM.dbTitleProperty());
        aboutTab.textProperty().bind(rootPM.aboutTitleProperty());

        dbLabel.textProperty().bind(rootPM.dbTextProperty());
        csvButton.textProperty().bind(rootPM.dbCsvTextProperty());
        sqliteButton.textProperty().bind(rootPM.dbSqliteTextProperty());
        neo4jButton.textProperty().bind(rootPM.dbNeo4jTextProperty());

        cancel.setOnAction(event -> {
            cancel.getScene().getWindow().hide();
        });
    }

}
