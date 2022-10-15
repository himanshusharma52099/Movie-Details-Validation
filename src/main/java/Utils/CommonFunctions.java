package main.java.Utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.java.BaseTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CommonFunctions extends BaseTest{
    public static LocalDate capturedDateImdb;
    public static LocalDate capturedDateWiki;

    public WebElement getWebElement(String identifierType, String identifierValue) {
        if (identifierType == "XPATH") {
            return BaseTest.driver.findElement(By.xpath(identifierValue));
        } else {
            return null;
        }
    }
    public static LocalDate getDateFromString(String releaseDate, DateTimeFormatter format)
    {
        // Converting the string to date in the specified format
        LocalDate date = LocalDate.parse(releaseDate, format);
        // Returning the converted date
        return date;
    }
    public static void convertDateFormat(String releaseDate, String movieSite) {
        // Getting the format by creating an object of DateTImeFormatter class
        DateTimeFormatter formatImdb = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        DateTimeFormatter formatWiki = DateTimeFormatter.ofPattern("d MMMM yyyy");
        if (movieSite.equals("Imdb")) {
            // Getting the Date from String
                capturedDateImdb = getDateFromString(releaseDate, formatImdb);
                // Printing the converted date
                logger.info("Imdb converted Date: " + capturedDateImdb);
        } else {
                // Getting the Date from String
                capturedDateWiki = getDateFromString(releaseDate, formatWiki);
                // Printing the converted date
                logger.info("Wiki converted Date: " + capturedDateWiki);
        }
    }
}