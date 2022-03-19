package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public  void returnToHomePage() {
    click(By.linkText("home page"));
    wd.get("http://localhost/addressbook/index.php");
  }

  public  void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("email"), contactData.getEmail());
  }

  public void selectContact() {
      click(By.id("1"));
  }

  public void editSelectedContact() {
    click(By.xpath("//tr[4]/td[8]/a/img"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }
}
