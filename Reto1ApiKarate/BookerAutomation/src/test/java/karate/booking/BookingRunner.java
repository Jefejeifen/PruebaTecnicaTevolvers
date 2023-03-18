package karate.booking;

import com.intuit.karate.junit5.Karate;

public class BookingRunner {

    private static final String BOOKING = "booking";

    @Karate.Test
    Karate BookingRunner() {
        return Karate.run(BOOKING).relativeTo(getClass());
    }
}
