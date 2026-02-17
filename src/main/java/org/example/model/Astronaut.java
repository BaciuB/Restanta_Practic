package org.example.model;

public class Astronaut {
    int id;
    String name;
    String spacecraft;
    AstronautStatus status;
    int experiencedLevel;

    public Astronaut(int id, String name, String spacecraft, AstronautStatus status, int experiencedLevel) {
        this.id = id;
        this.name = name;
        this.spacecraft = spacecraft;
        this.status = status;
        this.experiencedLevel = experiencedLevel;
    }

    public Astronaut() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpacecraft() {
        return spacecraft;
    }

    public void setSpacecraft(String spacecraft) {
        this.spacecraft = spacecraft;
    }

    public AstronautStatus getStatus() {
        return status;
    }

    public void setStatus(AstronautStatus status) {
        this.status = status;
    }

    public int getExperiencedLevel() {
        return experiencedLevel;
    }

    public void setExperiencedLevel(int experiencedLevel) {
        this.experiencedLevel = experiencedLevel;
    }

    @Override
    public String toString() {
        return "Astronaut{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", spacecraft='" + spacecraft + '\'' +
                ", status=" + status +
                ", experiencedLevel=" + experiencedLevel +
                '}';
    }
}
