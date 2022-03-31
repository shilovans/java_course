package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public  void returnToHomePage() {
    click(By.linkText("home page"));
    wd.get("http://localhost/addressbook/index.php");
  }

  public void gotoAddContactPage() {
    click(By.linkText("add new"));
    wd.get("http://localhost/addressbook/edit.php");
  }

  public void selectContact() {
    click(By.name("selected[]"));
  }

  public  void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("email"), contactData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByIndex(contactData.getGroupIndex());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void viewContactDetails() {
    //переход в карточку контакта по нажатию на иконку [Details] в таблице контактов
    click(By.xpath("//img[@alt='Details']"));
  }

  public void editContact() {
    //переход в карточку редактирования контакта по нажатию на иконку [Edit] в таблице контактов
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void deleteContactEditCard() {
    click(By.xpath("//div[@id='content']/form[2]/input[2]"));
  }

  public void confirmDeletion() {
    wd.switchTo().alert().accept();
  }

  public void modifyContact() {
    click((By.name("modifiy")));
  }

  public void createContact(ContactData contact, boolean creation) {
    gotoAddContactPage();

    fillContactForm(contact, creation);
    submitContactCreation();
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }
}
