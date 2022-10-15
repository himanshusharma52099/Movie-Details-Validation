package test.java;

import main.java.PageEvents.MainEvents;
import org.testng.annotations.Test;

public class MovieDetailsValidationTest extends BaseTest {

    @Test
    public void compareMovieDetails() throws InterruptedException {
        MainEvents mainEvents =new MainEvents();
        //Url for Imdb Site
        driver.navigate().to("https://www.imdb.com/");
        logger.info("Successfully navigated to Imdb Homepage");
        //Method to capture the details from Imdb Site
        mainEvents.searchTheMovieNameImdb();
        //Url for Wikipedia Site
        driver.navigate().to("https://en.wikipedia.org/");
        logger.info("Successfully navigated to Wikipedia Homepage");
        //Method to capture the details from Wikipedia Site
        mainEvents.searchTheMovieNameWiki();
        //Method to Validate the movie details
        mainEvents.movieDetailsValidation();
    }
}
