package pl.edu.icm.meteo.android.meteo.app.location;

import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {
    @Test
    public void shouldCompareLocationsWithPolishLetters() {
        //when
        Location location1 = new Location(1,"Łodź");
        Location location2 = new Location(2, "Warszawa");

        //then
        assertTrue(location1.compareTo(location2) > 0);
    }

}
