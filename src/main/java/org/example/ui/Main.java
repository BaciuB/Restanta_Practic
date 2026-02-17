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

        //6.)
        System.out.println("\nTop 5 Astronauts Ranking:");
        Map<Integer, Integer> totalScores = new HashMap<>();
        for (Astronaut a : astronauts) {

            int total = 0;

            // adunăm computedPoints din events
            for (MissionEvent e : events) {
                if (e.getAstronautId() == a.getId()) {
                    total += service.computePoints(e);
                }
            }

            // adunam value din supplies
            for (Supply s : supplies) {
                if (s.getAstronautId() == a.getId()) {
                    total += s.getValue();
                }
            }

            totalScores.put(a.getId(), total);
        }

        // sortăm
        List<Astronaut> ranking = astronauts.stream()
                .sorted(
                        Comparator
                                .comparingInt((Astronaut a) -> totalScores.get(a.getId()))
                                .reversed()
                                .thenComparing(Astronaut::getName)
                )
                .toList();

        // afișăm top 5 si echipa castigatorului
        for (int i = 0; i < 5 && i < ranking.size(); i++) {
            Astronaut a = ranking.get(i);
            int score = totalScores.get(a.getId());

            System.out.println((i + 1) + ". " + a.getName() + "(" + a.getSpacecraft() + ")" + " -> " + score);
        }
        System.out.println("\nWinning team: " + ranking.getFirst().getSpacecraft());


        //7.)
        System.out.println("\nCreating mission_report.txt...");
        Map<MissionEventType, Integer> eventCount = new HashMap<>();

        // inițializăm count-urile cu 0
        for (MissionEventType type : MissionEventType.values()) {
            eventCount.put(type, 0);
        }

        // numărăm evenimentele
        for (MissionEvent e : events) {
            MissionEventType type = e.getType();
            eventCount.put(type, eventCount.get(type) + 1);
        }

        List<Map.Entry<MissionEventType, Integer>> sortedEventCount = eventCount.entrySet().stream()
                .sorted(
                        Comparator
                                .comparingInt((Map.Entry<MissionEventType, Integer> entry) -> entry.getValue())
                                .reversed()
                                .thenComparing(entry -> entry.getKey().name())
                )
                .toList();

        PrintWriter reportWriter = new PrintWriter("mission_report.txt");

        for (MissionEventType type : MissionEventType.values()) {
            reportWriter.println(type + " -> " + sortedEventCount.get(type.ordinal()).getValue());
        }

        reportWriter.close();

        System.out.println("mission_report.txt created successfully.");

    }
}

