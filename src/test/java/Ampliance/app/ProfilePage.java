package Ampliance.app;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class ProfilePage extends Application {
    protected Application app;
    @FindBy (css = "span.p-name")
    private WebElement fullname;
    @FindBy (css = "span.p-label")
    private WebElement location;
    @FindBy (css = "span>div>a[data-hovercard-type=\"organization\"]")
    private WebElement companyName;
    @FindBy (css = ".UnderlineNav-body .Counter")
    private WebElement publicRepos;
    @FindBy (css = "div.pagehead [data-selected-links*=\"all\"] span")
    private WebElement publicGists; //can be found on another page
    @FindBy (css = "[href*=\"followers\"] span")
    private WebElement followers;
    @FindBy (css = "[href*=\"following\"] span")
    private WebElement following;


    public ProfilePage (Application app) {
        super();
        if (wd == null) {
            wd = new ChromeDriver();
            wd.get("https://github.com/");
            PageFactory.initElements(wd, this);
        }
    }

    public String getInfo (String userID) throws IOException {
        String url = "https://api.github.com/users/" + userID;
        return url;
    }

    public String checkName() {
        String displayedName = fullname.getAttribute("innerText");
            return ("\""+displayedName+"\"");
    }

    public String checkCompany () {
        String displayedCompany = companyName.getAttribute("innerText");
        return ("\""+displayedCompany+"\"");
    }

    public String checkLocation () {
        String displayedLocation = location.getAttribute("innerText");
        return ("\""+displayedLocation+"\"");
    }

    public String checkRepos () {
        String displayedRepos = publicRepos.getAttribute("innerText");
        return ("\"" + displayedRepos + "\"");
    }

    public String checkGists (String userID) {
        wd.navigate().to("https://gist.github.com/"+userID);
        String displayedGists = publicGists.getAttribute("innerText");
        wd.navigate().to("https://github.com/"+userID);
        return ("\""+displayedGists+"\"");
    }

    public String checkFollowers () {
        String displayedFollowers = followers.getAttribute("innerText");
        return ("\""+displayedFollowers+"\"");
    }

    public String checkFollowing () {
        String displayedFollowing = following.getAttribute("innerText");
        return ("\""+displayedFollowing+"\"");
    }
}
