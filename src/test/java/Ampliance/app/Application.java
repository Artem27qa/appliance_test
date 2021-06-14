package Ampliance.app;

import okhttp3.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class Application {
    private ProfilePage profilePage;
    protected WebDriver wd;


    public Application () {
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
    }


    public ProfilePage profilePage() {
        if (profilePage == null) {
            profilePage = new ProfilePage(this);
        }
        return profilePage;
    }

    public void navigateTo (String link) {
        wd.navigate().to(link);
    }

    public void start() {
        if (wd == null){
            wd = new ChromeDriver();
        }
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    public void stop() {
        wd.quit();
    }

    public String get (String url){
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            return response.body().string();
        }
        catch (IOException e) {
            Assert.fail();
            return null;
        }
    }
}
