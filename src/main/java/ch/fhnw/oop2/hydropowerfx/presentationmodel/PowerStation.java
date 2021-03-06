package ch.fhnw.oop2.hydropowerfx.presentationmodel;

import javafx.beans.property.*;

public class PowerStation {
    private IntegerProperty entitiyID = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty("New PowerStation");
    private StringProperty type = new SimpleStringProperty("");
    private StringProperty site = new SimpleStringProperty("");
    private StringProperty canton = new SimpleStringProperty("");
    private DoubleProperty maxWater = new SimpleDoubleProperty(0.0);
    private DoubleProperty maxPower = new SimpleDoubleProperty(0.0);
    private IntegerProperty startOperation = new SimpleIntegerProperty(0);
    private IntegerProperty lastOperation = new SimpleIntegerProperty(0);
    private DoubleProperty latitude = new SimpleDoubleProperty(0.0);
    private DoubleProperty longitude = new SimpleDoubleProperty(0.0);
    private StringProperty status = new SimpleStringProperty("");
    private StringProperty waterbodies = new SimpleStringProperty("");
    private StringProperty imgUrl = new SimpleStringProperty("");

    public PowerStation() {

    }

    public PowerStation(String[] fields) {
        setEntitiyID(Integer.valueOf(fields[0].replaceAll("'", "")));
        setName(fields[1]);
        setType(fields[2]);
        setSite(fields[3]);
        setCanton(fields[4]);
        setMaxWater(Double.parseDouble(fields[5].replaceAll("'", "")));
        setMaxPower(Double.parseDouble(fields[6].replaceAll("'", "")));
        setStartOperation(Integer.parseInt(fields[7].replaceAll("'", "")));
        setLastOperation(Integer.parseInt(fields[8].replaceAll("'", "")));
        setLatitude(Double.parseDouble(fields[9].replaceAll("'", "")));
        setLongitude(Double.parseDouble(fields[10].replaceAll("'", "")));
        setStatus(fields[11]);
        setWaterbodies(fields[12]);
        setImgUrl(fields[13]);
    }

    public String infoAsLine(String delimiter) {
        return String.join(delimiter,
                Integer.toString(getEntitiyID()),
                getName(),
                getType(),
                getSite(),
                getCanton(),
                Double.toString(getMaxWater()),
                Double.toString(getMaxPower()),
                Integer.toString(getStartOperation()),
                Integer.toString(getLastOperation()),
                Double.toString(getLatitude()),
                Double.toString(getLongitude()),
                getStatus(),
                getWaterbodies(),
                getImgUrl()
        );
    }

    public int getEntitiyID() {
        return entitiyID.get();
    }

    public IntegerProperty entitiyIDProperty() {
        return entitiyID;
    }

    public void setEntitiyID(int entitiyID) {
        this.entitiyID.set(entitiyID);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getSite() {
        return site.get();
    }

    public StringProperty siteProperty() {
        return site;
    }

    public void setSite(String site) {
        this.site.set(site);
    }

    public String getCanton() {
        return canton.get();
    }

    public StringProperty cantonProperty() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton.set(canton);
    }

    public double getMaxWater() {
        return maxWater.get();
    }

    public DoubleProperty maxWaterProperty() {
        return maxWater;
    }

    public void setMaxWater(double maxWater) {
        this.maxWater.set(maxWater);
    }

    public double getMaxPower() {
        return maxPower.get();
    }

    public DoubleProperty maxPowerProperty() {
        return maxPower;
    }

    public void setMaxPower(double maxPower) {
        this.maxPower.set(maxPower);
    }

    public int getStartOperation() {
        return startOperation.get();
    }

    public IntegerProperty startOperationProperty() {
        return startOperation;
    }

    public void setStartOperation(int startOperation) {
        this.startOperation.set(startOperation);
    }

    public int getLastOperation() {
        return lastOperation.get();
    }

    public IntegerProperty lastOperationProperty() {
        return lastOperation;
    }

    public void setLastOperation(int lastOperation) {
        this.lastOperation.set(lastOperation);
    }

    public double getLatitude() {
        return latitude.get();
    }

    public DoubleProperty latitudeProperty() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude.set(latitude);
    }

    public double getLongitude() {
        return longitude.get();
    }

    public DoubleProperty longitudeProperty() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude.set(longitude);
    }

    public String getStatus() {
        return status.get();
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public String getWaterbodies() {
        return waterbodies.get();
    }

    public StringProperty waterbodiesProperty() {
        return waterbodies;
    }

    public void setWaterbodies(String waterbodies) {
        this.waterbodies.set(waterbodies);
    }

    public String getImgUrl() {
        return imgUrl.get();
    }

    public StringProperty imgUrlProperty() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl.set(imgUrl);
    }
}
