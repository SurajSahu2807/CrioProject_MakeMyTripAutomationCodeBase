package demo;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestCases {
    ChromeDriver driver;

    public TestCases() {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    public void endTest() {
        // System.out.println("End Test: TestCases");
        // JavascriptExecutor js = (JavascriptExecutor) driver;
        // js.executeScript("window.localStorage.clear();"); // Clear local storage
        // js.executeScript("window.sessionStorage.clear();"); // Clear session storage
        driver.close();
        driver.quit();

    }

    // Verify that the Make My Trip homepage URL contains "makemytrip."
    public void testCase01() {
        try {
            System.out.println("Start Test case: testCase01");
            driver.get("https://www.makemytrip.com/");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='commonModal__close']")));
            driver.findElement(By.className("commonModal__close")).click();
            String ExpectedUrl = "https://www.makemytrip.com/";
            if (driver.getCurrentUrl().equals(ExpectedUrl)) 
                System.out.println("end Test case: testCase01");
            // System.out.println("end Test case: testCase01");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Get Flight Details from Bangalore to New Delhi
    public void testCase02() {
        try {
            System.out.println("Start Test case: testCase02");
            driver.get("https://www.makemytrip.com/");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='commonModal__close']")));
            // driver.findElement(By.className("commonModal__close")).click();
            WebElement FromCity = driver
                    .findElement(By.xpath("//div[@class='flt_fsw_inputBox searchCity inactiveWidget ']//input"));
            FromCity.click();
            WebElement FromtextArea = driver.findElement(By.xpath("//input[@placeholder='From']"));
            FromtextArea.sendKeys("BLR");
            List<WebElement> FromCityNearByAirports = driver.findElements(By.xpath(
                    "//div[@class='autoSuggestPlugin hsw_autocomplePopup']//ul[@role='listbox']/li//p/span[@class='sr_iata font14 lightGreyText latoBold']"));
            for (WebElement eachIf : FromCityNearByAirports) {
                if (eachIf.getText().contains("BLR")) {
                    eachIf.click();
                    break;
                }
            }
            WebElement ToCity = driver
                    .findElement(By.xpath("//div[@class='flt_fsw_inputBox searchToCity inactiveWidget ']//input"));
            ToCity.click();
            WebElement TolistTextarea = driver.findElement(By.xpath("//input[@placeholder='To']"));
            TolistTextarea.sendKeys("DEL");
            List<WebElement> ToCityNearByAirports = driver.findElements(By.xpath(
                    "//div[@class='autoSuggestPlugin hsw_autocomplePopup makeFlex column spaceBetween']//ul[@role='listbox']/li//p/span[@class='sr_iata font14 lightGreyText latoBold']"));
            for (WebElement eachIf : ToCityNearByAirports) {
                if (eachIf.getText().contains("DEL")) {
                    eachIf.click();
                    break;
                }
            }

            String m1 = "June 2024";

            // System.out.println(driver.findElement(By.xpath("//div[@class='DayPicker-Month']//div[@class='DayPicker-Caption']//div")).getText());
            while (true) {
                String month = driver
                        .findElement(By.xpath("//div[@class='DayPicker-Month']//div[@class='DayPicker-Caption']//div"))
                        .getText();
                if (!month.equalsIgnoreCase("June 2024")) {
                    driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
                } else {
                    break;
                }

            }

            // //
            // driver.findElement(By.xpath("(//div[@class='DayPicker-Month'])[2]")).click();
            List<WebElement> AllDays = driver.findElements(By.xpath(
                    "//div[@class='DayPicker-Week']//div[@class='DayPicker-Day']//div[@class='dateInnerCell']/p[1]"));
            for (WebElement e : AllDays) {
                String Day = e.getText();
                if (Day.equals("29")) {
                    e.click();
                    break;
                }
            }
            WebElement SearchBtn = driver.findElement(By.xpath("//p[@data-cy='submit']//a"));
            SearchBtn.click();
            int i = 0;
            // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='commonOverlay ']")));
            // driver.findElement(By.xpath("//*[text()='OKAY, GOT IT!']")).click();
            Thread.sleep(5000);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            // Scroll down the page by 500 pixels
            js.executeScript("window.scrollBy(0,7000)");
            List<WebElement> AllFlightsName = driver.findElements(By.xpath(
                    "//div[@class='makeFlex spaceBetween']//div//div//p[1][@class='boldFont blackText airlineName']"));
            // System.out.println(AllFlightsName.size());
            // for(WebElement f : AllFlightsName){
            // System.out.println(f.getText());
            // }
            List<WebElement> AllPrizes = driver.findElements(
                    By.xpath("//div[contains(@class,'priceSection')]//div[contains(@class,'blackText')]"));
            // System.out.println(AllPrizes.size());
            // for(WebElement ff : AllPrizes){
            // System.out.println(ff.getText());
            // }
            // for (WebElement e : AllFlightsName) {
            //     System.out.print(e.getText() + " ---> ");
            //     for (int j = i; i <= AllPrizes.size();) {
            //         System.out.print("Rs ." + AllPrizes.get(j).getText());
            //         System.out.println();
            //         i++;
            //         break;
            //     }
            // }
            System.out.println("end Test case: testCase02");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   

    public void testCase03() throws InterruptedException {
        System.out.println("Start Test case: testCase03");
        driver.get("https://www.makemytrip.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='commonModal__close']")));
        // driver.findElement(By.className("commonModal__close")).click();
        Thread.sleep(5000);
        List<WebElement> AllOptions = driver.findElements(
                By.xpath("//ul[@class='makeFlex font12 headerIconsGap']/li//span[contains(@class,'darkGreyText')]"));
        for (WebElement e : AllOptions) {
            if (e.getText().equalsIgnoreCase("Trains"))
                e.click();
        }
        WebElement TrainFrom = driver.findElement(By.xpath("//div[@class='rsw_inputBox selectRailCity'][1]"));
        TrainFrom.click();
        WebElement FromtextArea = driver.findElement(By.xpath("//input[@placeholder='From']"));
        FromtextArea.sendKeys("YPR");
        Thread.sleep(5000);
        // System.out.println(driver.findElement(By.xpath(
        //         "//div[@class='autoSuggestPlugin hsw_autocomplePopup']//ul[@role='listbox']/li//span[contains(@class,'grayText')]"))
        //         .getText());
        List<WebElement> FromCityAllTrains = driver.findElements(By.xpath(
                "//div[@class='autoSuggestPlugin hsw_autocomplePopup']//ul[@role='listbox']/li//span[contains(@class,'grayText')]"));
        for (WebElement eachIf : FromCityAllTrains) {
            if (eachIf.getText().contains("YPR")) {
                Thread.sleep(5000);
                eachIf.click();
                break;
            }
        }
        WebElement TrainTo = driver.findElement(By.xpath("//input[@placeholder='To']"));
        TrainTo.click();
        WebElement TolistTextarea = driver.findElement(By.xpath("//input[@placeholder='To']"));
        TolistTextarea.sendKeys("NDLS");
        Thread.sleep(5000);
        List<WebElement> ToCityAllTrains = driver.findElements(By.xpath(
                "//div[@class='autoSuggestPlugin hsw_autocomplePopup']//ul[@role='listbox']/li//span[contains(@class,'grayText')]"));
        for (WebElement eachIf : ToCityAllTrains) {
            if (eachIf.getText().contains("NDLS")) {
                Thread.sleep(5000);
                eachIf.click();
                break;
            }
        }

        String m1 = "June 2024";

        // System.out.println(driver.findElement(By.xpath("//div[@class='DayPicker-Month']//div[@class='DayPicker-Caption']//div")).getText());
        while (true) {
            String month = driver
                    .findElement(By.xpath("//div[@class='DayPicker-Month']//div[@class='DayPicker-Caption']//div"))
                    .getText();
            if (!month.equalsIgnoreCase("June 2024")) {
                driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
            } else {
                break;
            }

        }

        List<WebElement> AllDays = driver.findElements(By.xpath(
                "//div[@class='DayPicker-Week']//div[@class='DayPicker-Day']"));
        for (WebElement e : AllDays) {
            String Day = e.getText();
            if (Day.equals("29")) {
                e.click();
                break;
            }
        }

        WebElement CoachClasses = driver.findElement(By.xpath("//*[@class='travelForPopup']//li[text()='Third AC']"));
        CoachClasses.click();

        WebElement SearchBtn = driver.findElement(By.xpath("//a[@data-cy='submit']"));
        SearchBtn.click();
        int i = 0;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        List<WebElement> AllTrainsName = driver.findElements(
                By.xpath("//div[@class='single-train-detail single-train-padding']//div[@class='train-name']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // Scroll down the page by 500 pixels
        js.executeScript("window.scrollBy(0,300)");
        driver.findElement(By.xpath("//div[@class='appendBottom30'][4]//li[3]//label[@for='journeyClassFilter-3A']"))
                .click();
        Thread.sleep(3000);
        List<WebElement> AllPrices = driver.findElements(By.xpath(
                "//div[@class='flex container justify-space-between']//div[@class='trainSubsChild']//div[1]//div[@class='ticket-price justify-flex-end']"));
        // for (WebElement e : AllTrainsName) {
        //     System.out.print(e.getText() + " ---> ");
        //     for (int j = i; i <= AllPrices.size();) {
        //         System.out.print("Rs ." + AllPrices.get(j).getText());
        //         System.out.println();
        //         i++;
        //         break;
        //     }
        // }
        System.out.println("end Test case: testCase03");
    }



    public void testCase04() throws InterruptedException {
        System.out.println("Start Test case: testCase04");
        driver.get("https://www.makemytrip.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='commonModal__close']")));
        // driver.findElement(By.className("commonModal__close")).click();
        Thread.sleep(5000);
        List<WebElement> AllOptions = driver.findElements(
                By.xpath("//ul[@class='makeFlex font12 headerIconsGap']/li//span[contains(@class,'darkGreyText')]"));
        for (WebElement e : AllOptions) {
            if (e.getText().equalsIgnoreCase("Buses"))
                e.click();
        }

        WebElement BusesFrom = driver.findElement(By.xpath("//div[@class='bussw_inputBox selectHtlCity'][1]"));
        BusesFrom.click();
        WebElement FromtextArea = driver.findElement(By.xpath("//input[@placeholder='From']"));
        FromtextArea.sendKeys("bangal");
        Thread.sleep(5000);
        // System.out.println(driver.findElement(By.xpath(
        // "//div[@class='autoSuggestPlugin
        // hsw_autocomplePopup']//ul[@role='listbox']/li//span[contains(@class,'grayText')]")).getText());
        List<WebElement> BusesFromNearby = driver.findElements(By.xpath(
                "//div[@class='autoSuggestPlugin hsw_autocomplePopup']//ul[@role='listbox']/li//span[@class='sr_city blackText']"));
        for (WebElement eachIf : BusesFromNearby) {
            if (eachIf.getText().contains("Bangalore")) {
                Thread.sleep(5000);
                eachIf.click();
                break;
            }
        }

        // WebElement BusesTo = driver
        //         .findElement(By.xpath("//*[@id='top-banner']/div[2]/div/div/div[2]/div/div[2]/label"));
        // BusesTo.click();
        WebElement TolistTextarea = driver.findElement(By.xpath("//input[@placeholder='To']"));
        TolistTextarea.sendKeys("kathma");
        Thread.sleep(5000); 
        List<WebElement> BusesToNearby = driver.findElements(By.xpath(
                "//div[@class='autoSuggestPlugin hsw_autocomplePopup']//ul[@role='listbox']/li//span[@class='sr_city blackText']"));
        for (WebElement eachIf : BusesToNearby) {
            if (eachIf.getText().contains("Kathmandu")) {
                Thread.sleep(10000);
                eachIf.click();
                break;
            }
        }

        // WebElement DatePickerEle = driver.findElement(By.xpath("//label[@for='travelDate']//p[1]"));
        // DatePickerEle.click();

        String m1 = "June 2024";

        // System.out.println(driver.findElement(By.xpath("//div[@class='DayPicker-Month']//div[@class='DayPicker-Caption']//div")).getText());
        while (true) {
            String month = driver
                    .findElement(By.xpath("//div[@class='DayPicker-Month']//div[@class='DayPicker-Caption']//div"))
                    .getText();
            if (!month.equalsIgnoreCase("June 2024")) {
                driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
            } else {
                break;
            }

        }

        List<WebElement> AllDays = driver.findElements(By.xpath(
                "//div[@class='DayPicker-Week']//div[@class='DayPicker-Day']"));
        for (WebElement e : AllDays) {
            String Day = e.getText();
            if (Day.equals("29")) {
                e.click();
                break;
            }
        }

        WebElement SearchBtn = driver.findElement(By.xpath("//button[@data-cy='submit']"));
        SearchBtn.click();
        
        WebElement NoBusesInfoEle =  driver.findElement(By.xpath("//*[@id='root']/div[1]/div[3]/div[1]/span[text()='No buses found for 29 Jun']"));
        if(NoBusesInfoEle.getText().equalsIgnoreCase("No buses found for 29 Jun")){
            System.out.println("end Test case: testCase04");
        }


    }



}
