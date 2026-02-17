package org.example.ui;

import org.example.model.*;
import org.example.repository.JsonDataRepository;
//import org.example.service.ArenaService;

import java.util.Comparator;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.Map;
import java.util.HashMap;


import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        //1.)Load data from JSON files
        JsonDataRepository repo = new JsonDataRepository();
        //ArenaService service = new ArenaService();

        //Load and display all astronauts, events, and supplies
        List<Astronaut> astronauts = repo.loadAstronauts();
        List<MissionEvent> events = repo.loadEvents();
        List<Supply> supplies = repo.loadSupplies();

        //Display counts and details of loaded data
        System.out.println("Astronauts loaded: " + astronauts.size());
        System.out.println("Events loaded: " + events.size());
        System.out.println("Supplies loaded: " + supplies.size());

        //Display all astronauts
        System.out.println("\nAstronauts:");
        astronauts.forEach(System.out::println);
        Scanner scanner = new Scanner(System.in);

        //2.)Enter a spacecraft name and display drivers from that team that have status "active"
        System.out.println("\nEnter spacecraft name:");
        String spacecraft = scanner.nextLine();
        System.out.println("Active astronauts in spacecraft " + spacecraft + ":");
        astronauts.stream().filter(d -> d.getSpacecraft().equalsIgnoreCase(spacecraft) && d.getStatus() == AstronautStatus.ACTIVE)
                .forEach(System.out::println);

        //3.)Sort astronauts by experience level (absteigend) und name (steigend)
        System.out.println("\nSorted astronauts (skill desc, name asc):");

        List<Astronaut> sortedAstronaut = astronauts.stream()
                .sorted(
                        Comparator.comparingInt(Astronaut::getExperienceLevel)
                                .reversed()
                                .thenComparing(Astronaut::getName)
                )
                .toList();

        sortedAstronaut.forEach(System.out::println);



    }
}

