import exo.Partie1;
import exo.Partie2;
import exo.Partie3;
import exo.Partie4;
import factory.TripFactory;
import models.Trip;

import java.util.List;
import java.util.Map;
import java.util.Optional;

void main() {
    List<Trip> trips = TripFactory.generateTrips(10000000);

    // PARTIE 1
    System.out.println("=== PARTIE 1 — Filtrage ===");
    Partie1 p1 = new Partie1();
    System.out.println("Ex1 - Longs et chers : " + p1.longAndExpensiveTrips(trips).size() + " trajets");
    System.out.println("Ex2 - Mauvaise note  : " + p1.badTrips(trips).size() + " trajets");
    System.out.println("Ex3 - Récents        : " + p1.recentTrips(trips).size() + " trajets");

    // PARTIE 2
    System.out.println("\n=== PARTIE 2 — Analyse & statistiques ===");
    Partie2 p2 = new Partie2();
    System.out.println("Ex4 - Nb trajets par ville       : " + p2.countByCity(trips));
    System.out.println("Ex5 - Revenu par chauffeur       : " + p2.revenueByDriver(trips).entrySet().stream().limit(3).toList());
    System.out.println("Ex6 - Durée moyenne par ville        : " + p2.avgDurationByCity(trips));

    // PARTIE 3
    System.out.println("\n=== PARTIE 3 — Tri & recherche ===");
    Partie3 p3 = new Partie3();
    List<Trip> top10 = p3.top10ExpensiveTrips(trips);
    System.out.println("Ex7 - Top 10 des + chers :");
    for (int i = 0; i < top10.size(); i++) {
        Trip t = top10.get(i);
        System.out.println("  " + (i + 1) + ". id=" + t.id() + " | " + t.price() + "€");
    }
    Optional<Trip> best = p3.bestTrip(trips);
    System.out.println("Ex8 - Meilleur trajet            : rating=" + best.map(t -> t.rating()).orElse(0.0));

    // PARTIE 4
    System.out.println("\n=== PARTIE 4 — Traitement parallèle ===");
    Partie4 p4 = new Partie4();
    long t1 = System.currentTimeMillis();
    double revSeq = p4.totalRevenueSequential(trips);
    System.out.println("Ex10 - Revenu séquentiel : " + revSeq + "€ (" + (System.currentTimeMillis() - t1) + "ms)");
    long t2 = System.currentTimeMillis();
    double revPar = p4.totalRevenueParallel(trips);
    System.out.println("Ex11 - Revenu parallèle  : " + revPar + "€ (" + (System.currentTimeMillis() - t2) + "ms)");
    System.out.println("Ex12 - Nb par ville (parallel) : " + p4.countByCityParallel(trips));
    System.out.println("Ex13 - Trajets premium     : " + p4.premiumTripsParallel(trips).size() + " trajets");
}