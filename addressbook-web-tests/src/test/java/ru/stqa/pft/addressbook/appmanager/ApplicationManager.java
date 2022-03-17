package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private WebDriver wb;
  private JavascriptExecutor js;

  public void init() {
    System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
    wb = new ChromeDriver();
    wb.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    js = (JavascriptExecutor) wb;
    wb.get("http://localhost/addressbook/group.php");
    login("admin", "secret");
  }

  private void login(String username, String password) {
    wb.findElement(By.name("user")).click();
    wb.findElement(By.name("user")).clear();
    wb.findElement(By.name("user")).sendKeys(username);
    wb.findElement(By.name("pass")).click();
    wb.findElement(By.name("pass")).clear();
    wb.findElement(By.name("pass")).sendKeys(password);
    wb.findElement(By.xpath("//input[@value='Login']")).click();
  }

  public void returnToGroupPage() {
    wb.findElement(By.linkText("group page")).click();
  }

  public  void returnToHomePage() {
    wb.findElement(By.linkText("home page")).click();
    wb.get("http://localhost/addressbook/index.php");
  }

  public void submitGroupCreation() {
    wb.findElement(By.name("submit")).click();
  }

  public  void submitContactCreation() {
    wb.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  public void fillGroupForm(GroupData groupData) {
    wb.findElement(By.name("group_name")).click();
    wb.findElement(By.name("group_name")).clear();
    wb.findElement(By.name("group_name")).sendKeys(groupData.getName());
    wb.findElement(By.name("group_header")).click();
    wb.findElement(By.name("group_header")).clear();
    wb.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
    wb.findElement(By.name("group_footer")).click();
    wb.findElement(By.name("group_footer")).clear();
    wb.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
  }

  public void fillContactForm(ContactData contactData) {
    wb.findElement(By.name("firstname")).click();
    wb.findElement(By.name("firstname")).clear();
    wb.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
    wb.findElement(By.name("lastname")).click();
    wb.findElement(By.name("lastname")).clear();
    wb.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
    wb.findElement(By.name("address")).click();
    wb.findElement(By.name("address")).clear();
    wb.findElement(By.name("address")).sendKeys(contactData.getAddress());
    wb.findElement(By.name("home")).click();
    wb.findElement(By.name("home")).clear();
    wb.findElement(By.name("home")).sendKeys(contactData.getHomePhone());
    wb.findElement(By.name("email")).click();
    wb.findElement(By.name("email")).clear();
    wb.findElement(By.name("email")).sendKeys(contactData.getEmail());
  }

  public void initGroupCreation() {
    wb.findElement(By.name("new")).click();
  }

  public void gotoGroupPage() {
    wb.findElement(By.linkText("groups")).click();
  }

  public void gotoAddContactPage() {
    wb.findElement(By.linkText("add new")).click();
    wb.get("http://localhost/addressbook/edit.php");
  }

  public void stop() {
    wb.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      wb.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wb.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  public void deleteSelectedGroups() {
    wb.findElement(By.name("delete")).click();
  }

  public void selectGroup() {
    wb.findElement(By.name("selected[]")).click();
  }
}
