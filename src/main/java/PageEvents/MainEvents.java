package main.java.PageEvents;

import main.java.Utils.CommonFunctions;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import test.java.BaseTest;

public class MainEvents extends BaseTest {
    private String movieName="Pushpa: The Rise";
    private final String searchImdb="//input[@placeholder='Search IMDb']";
    private final String clickMovieNameImdb="//div[contains(text(),'"+movieName+"')]";
    private  String releaseDateImdb="//a[text()='Release date']/following::a[1]";
    private final String countryImdb="//span[text()='Country of origin']/following::a[1]";
    private final String searchWiki="//input[@placeholder='Search Wikipedia']";
    private final String clickMovieNameWiki="//span[contains(text(),'"+movieName+"')]";
    private final String releaseDateWiki="//div[text()='Release date']/following::li[1]";
    private final String countryWiki="//th[text()='Country']/following::td[1]";
    private String countryImdbTxt;
    private String countryWikiTxt;
    CommonFunctions commonFunctions =new CommonFunctions();
    public void searchTheMovieNameImdb()  {
        //Searching for the movie
        commonFunctions.getWebElement("XPATH",searchImdb).sendKeys(movieName);
        logger.pass("Entered the movie name in search field as: "+movieName);
        //Clicking the movie from suggestion's
        commonFunctions.getWebElement("XPATH",clickMovieNameImdb).click();
        logger.info("Clicked the movie name");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", commonFunctions.getWebElement("XPATH",releaseDateImdb));
        //Captures the Release Date value
        String releaseDateTxt= commonFunctions.getWebElement("XPATH",releaseDateImdb).getText().substring(0, 17);;
        logger.info("Successfully navigated to Release date and Country details");
        logger.pass("Release Date at Imdb site: "+ releaseDateTxt);
        //This method change the current format of Date
        commonFunctions.convertDateFormat(releaseDateTxt,"Imdb");
        //Captures the Country value
        countryImdbTxt= commonFunctions.getWebElement("XPATH",countryImdb).getText();
        logger.pass("Country at Imdb site: "+ countryImdbTxt);
    }

    public void searchTheMovieNameWiki()  {
        //Searching for the movie
        commonFunctions.getWebElement("XPATH", searchWiki).sendKeys(movieName);
        logger.pass("Entered the movie name in search field as: "+movieName);
        //Clicking the movie from suggestion's
        commonFunctions.getWebElement("XPATH",clickMovieNameWiki).click();
        logger.info("Clicked the movie name");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", commonFunctions.getWebElement("XPATH",releaseDateWiki));
        logger.info("Successfully navigated to Release date and Country details");
        //Captures the Release Date value
        String releaseDateWikiTxt= commonFunctions.getWebElement("XPATH",releaseDateWiki).getText();
        logger.pass("Release Date: "+ releaseDateWikiTxt);
        //This method change the current format of Date
        commonFunctions.convertDateFormat(releaseDateWikiTxt,"Wiki");
        //Captures the Country value
        countryWikiTxt= commonFunctions.getWebElement("XPATH",countryWiki).getText();
        logger.pass("Country: "+ countryWikiTxt);
    }

    public void movieDetailsValidation(){

        //Validation for checking the Release dates from both sites are matching or not
        try{
            Assert.assertEquals(commonFunctions.capturedDateImdb, commonFunctions.capturedDateWiki);
        }catch(AssertionError e){
            logger.fail("Date's are not matching");
            throw e;
        }
        logger.pass("Date of Release on Imdb: "+commonFunctions.capturedDateImdb+" and Date of Release on Wikipedia: "+commonFunctions.capturedDateWiki+" is matching");

        //Validation for checking the Country from both sites are matching or not
        try{
            Assert.assertEquals(countryImdbTxt, countryWikiTxt);
        }catch(AssertionError e){
            logger.fail("Country is not matching");
        }
        logger.pass("Country on Imdb: "+countryImdbTxt+" and Country on Wikipedia: "+countryWikiTxt+" is matching");

    }
}


