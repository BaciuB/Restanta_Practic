package org.example.service;
import org.example.model.MissionEvent;
import org.example.model.MissionEventType;
public class MissionService {

    public int computePoints(MissionEvent e) {

        MissionEventType t = e.getType();

        switch (t) {
                case EVA:
                    return e.getBasePoints() + 2 * e.getDay();
                case SYSTEM_FAILURE:
                    return e.getBasePoints() - 3 - e.getDay();

                case SCIENCE:
                    return e.getBasePoints() + (e.getDay() % 4);

                case MEDICAL:
                    return e.getBasePoints() -2 * (e.getDay() % 3);

                case COMMUNICATION:
                    return e.getBasePoints() + 5;

                default:
                    return e.getBasePoints();
        }
    }
}

