package exo;

import models.Trip;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

public class Partie1 {

    Predicate<Trip> isLongTrip = trip -> trip.distanceKm() > 10;
    Predicate<Trip> isExpensive = trip -> trip.price() > 20;
    Predicate<Trip> isBadRating = trip -> trip.rating() < 3;
    Predicate<Trip> isToday = trip -> trip.startTime().toLocalDate().equals(LocalDate.now());
    Predicate<Trip> isYesterday = trip -> trip.startTime().toLocalDate().equals(LocalDate.now().minusDays(1));

    public List<Trip> longAndExpensiveTrips(List<Trip> trips) {
        return trips.stream()
                .filter(isLongTrip.and(isExpensive))
                .toList();
    }

    public List<Trip> badTrips(List<Trip> trips) {
        return trips.stream()
                .filter(isBadRating)
                .toList();
    }

    public List<Trip> recentTrips(List<Trip> trips) {
        return trips.stream()
                .filter(isToday.or(isYesterday))
                .toList();
    }
}