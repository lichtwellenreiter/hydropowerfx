package ch.fhnw.oop2.hydropowerfx.database.neo4j;

import ch.fhnw.oop2.hydropowerfx.database.Database;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.Canton;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.PowerStation;
import ch.fhnw.oop2.hydropowerfx.presentationmodel.RootPM;
import javafx.collections.ObservableList;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Neo4j extends Database {

    private SessionFactory sessionFactory;

    public Neo4j(RootPM rootPM, ObservableList<Canton> cantons, ObservableList<PowerStation> stations) {
        super(rootPM, cantons, stations);

        Configuration configuration = new Configuration.Builder()
                .uri(rootPM.getFilePathURI().toString() + "neo4j.db")
                .build();

        sessionFactory = new SessionFactory(configuration, "ch.fhnw.oop2.hydropowerfx");

        init();
    }

    @Override
    public void close() {
        sessionFactory.close();
    }

    @Override
    protected void addAllCantonsAndStations() {
        Session session = sessionFactory.openSession();

        for (Canton canton : cantonList) {

            CantonNode cn = new CantonNode(canton);

            for (PowerStation station : canton.getCantonStationsList()) {
                StationNode sn = new StationNode(station);

                sn.setCantonNode(cn);
            }

            session.save(cn);
        }

        List<PowerStation> psList = stationList.stream().filter(station -> station.getCanton().equals("")).collect(Collectors.toList());

        for (PowerStation station : psList) {
            StationNode sn = new StationNode(station);

            session.save(sn);
        }
    }

    @Override
    protected Collection<Canton> getAllCantons() {
        Session session = sessionFactory.openSession();

        Set<Canton> cantons = new HashSet<>();

        session.loadAll(CantonNode.class).stream().forEach(cantonNode -> cantons.add(cantonNode.asCanton(stationList)));

        return cantons;
    }

    @Override
    protected void addCanton(Canton canton) {
        Session session = sessionFactory.openSession();

        CantonNode cn = new CantonNode(canton);
        session.save(cn);
    }

    @Override
    protected void updateCanton(Canton canton) {
        Session session = sessionFactory.openSession();

        CantonNode cn = new CantonNode(canton);
        session.save(cn);
    }

    @Override
    protected void deleteCanton(Canton canton) {
        Session session = sessionFactory.openSession();

        CantonNode cn = new CantonNode(canton);
        session.delete(cn);
    }

    @Override
    protected Collection<PowerStation> getAllStations() {
        Session session = sessionFactory.openSession();

        Set<PowerStation> stations = new HashSet<>();

        session.loadAll(StationNode.class).stream().forEach(stationNode -> stations.add(stationNode.asPowerStation()));

        return stations;
    }

    @Override
    protected void addStation(PowerStation station) {
        Session session = sessionFactory.openSession();

        StationNode sn = new StationNode(station);

        if (!station.getCanton().equals("")) {
            CantonNode canton = session.load(CantonNode.class, station.getCanton());

            sn.setCantonNode(canton);
        }

        session.save(sn);
    }

    @Override
    protected void updateStation(PowerStation station) {
        Session session = sessionFactory.openSession();

        StationNode sn = session.load(StationNode.class, Long.valueOf(station.getEntitiyID()));

        if (sn == null) {
            sn = new StationNode(station);
        }

        if (!station.getCanton().equals(sn.getCanton())) {
            CantonNode cn = session.load(CantonNode.class, station.getCanton());
            sn.setCantonNode(cn);
            sn.setCanton(station.getCanton());
        }

        sn.setName(station.getName());
        sn.setType(station.getType());
        sn.setSite(station.getSite());
        sn.setMaxWater(station.getMaxWater());
        sn.setMaxPower(station.getMaxPower());
        sn.setStartOperation(station.getStartOperation());
        sn.setLastOperation(station.getLastOperation());
        sn.setLatitude(station.getLatitude());
        sn.setLongitude(station.getLongitude());
        sn.setStatus(station.getStatus());
        sn.setWaterbodies(station.getWaterbodies());
        sn.setImgUrl(station.getImgUrl());

        session.save(sn);
    }

    @Override
    protected void deleteStation(PowerStation station) {
        Session session = sessionFactory.openSession();

        StationNode sn = session.load(StationNode.class, station.getEntitiyID());

        if (sn != null) {
            session.delete(sn.getEntityID());
        }
    }
}
