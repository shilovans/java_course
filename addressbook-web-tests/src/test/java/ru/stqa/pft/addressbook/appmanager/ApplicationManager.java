package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  WebDriver wd;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private ContactHelper contactHelper;
  private GroupHelper groupHelper;
  private JavascriptExecutor js;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }
  public void init() {

    if (browser == BrowserType.CHROME) {
      System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
      wd = new ChromeDriver();
    } else if (browser == BrowserType.FIREFOX) {
      System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
      wd = new FirefoxDriver();
    } else if (browser == BrowserType.OPERA) {
      System.setProperty("webdriver.opera.driver", "/usr/local/bin/operadriver");
      wd = new OperaDriver();
    }


    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    js = (JavascriptExecutor) wd;
    wd.get("http://localhost/addressbook/group.php");
    groupHelper = new GroupHelper(wd);
    contactHelper = new ContactHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
  }

  public void stop() {
    wd.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
}
