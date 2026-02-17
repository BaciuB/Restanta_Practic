package org.example.ui;

import org.example.model.*;
import org.example.repository.JsonDataRepository;
import org.example.service.MissionService;

import java.util.Comparator;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.Map;
import java.util.HashMap;


import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        //1.)
        JsonDataRepository repo = new JsonDataRepository();
        MissionService service = new MissionService();

        List<Astronaut> astronauts = repo.loadAstronauts();
        List<MissionEvent> events = repo.loadEvents();
        List<Supply> supplies = repo.loadSupplies();

        System.out.println("Astronauts loaded: " + astronauts.size());
        System.out.println("Events loaded: " + events.size());
        System.out.println("Supplies loaded: " + supplies.size());

        System.out.println("\nAstronauts:");
        astronauts.forEach(System.out::println);
        Scanner scanner = new Scanner(System.in);

        //2.)
        System.out.println("\nEnter spacecraft name:");
        String spacecraft = scanner.nextLine();
        System.out.println("Active astronauts in spacecraft " + spacecraft + ":");
        astronauts.stream().filter(d -> d.getSpacecraft().equalsIgnoreCase(spacecraft) && d.getStatus() == AstronautStatus.ACTIVE)
                .forEach(System.out::println);

        //3.)
        System.out.println("\nSorted astronauts (skill desc, name asc):");

        List<Astronaut> sortedAstronaut = astronauts.stream()
                .sorted(
                        Comparator.comparingInt(Astronaut::getExperienceLevel)
                                .reversed()
                                .thenComparing(Astronaut::getName)
                )
                .toList();

        sortedAstronaut.forEach(System.out::println);

        //4.)
        PrintWriter writer = new PrintWriter("astronauts_sorted.txt.");

        for (Astronaut a : sortedAstronaut) {
            writer.println(a);
        }

        writer.close();

        System.out.println("\nFile astronauts_sorted.txt. created.");

        //5.)
        System.out.println("\nFirst 5 Events with computedPoints:");
        events.stream().limit(5).forEach(e -> {
            int computed = service.computePoints(e);
            System.out.println("Event " + e.getId()
                    + " -> raw=" + e.getBasePoints()
                    + " -> computed=" + computed);
        });


    }
}

