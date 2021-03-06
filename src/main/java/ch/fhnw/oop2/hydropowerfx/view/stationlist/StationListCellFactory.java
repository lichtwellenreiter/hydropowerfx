package ch.fhnw.oop2.hydropowerfx.view.stationlist;

import ch.fhnw.oop2.hydropowerfx.presentationmodel.PowerStation;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StationListCellFactory extends ListCell<PowerStation> {

    private String canton;
    private Image cantonImage;

    public StationListCellFactory(){

        getStyleClass().add("stationlist-cell");
    }

    @Override
    protected void updateItem(PowerStation field, boolean empty) {
        super.updateItem(field, empty);
        if (empty) {
            setGraphic(null);
        } else {
            if (field != null) {

                Label stationName = new Label(field.getName());
                stationName.getStyleClass().add("station-cell-name");

                Label maxPower = new Label(field.getMaxPower() + " MW");
                maxPower.getStyleClass().add("station-cell-maxpower");

                VBox vBox = new VBox(stationName, maxPower);

                // ToDo Very Bad Code..... Must be changed
                try {
                    canton = String.format("/ch/fhnw/oop2/hydropowerfx/view/assets/cantons/%s.png", field.getCanton());
                    cantonImage = new Image(getClass().getResource(canton).toExternalForm());
                } catch (Exception e) {
                    canton = "../assets/cantons/ZZ.png";
                    cantonImage = new Image(getClass().getResourceAsStream(canton));
                }
                ImageView cantonView = new ImageView(cantonImage);
                cantonView.setFitWidth(30);
                cantonView.setFitHeight(30);
                HBox hBox = new HBox(cantonView, vBox);
                hBox.setSpacing(10);
                setGraphic(hBox);
            }
        }
    }
}
