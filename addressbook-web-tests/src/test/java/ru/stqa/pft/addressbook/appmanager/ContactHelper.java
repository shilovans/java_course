package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

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

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
    //click(By.name("selected[]"));
  }

  public  void submitContactCreation() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation, Integer groupIndex) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("email"), contactData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByIndex(groupIndex);
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void viewContactDetails() {
    //переход в карточку контакта по нажатию на иконку [Details] в таблице контактов
    click(By.xpath("//img[@alt='Details']"));
  }

  public void editContact(int contactId) {
    //переход в карточку редактирования контакта по нажатию на иконку [Edit] в таблице контактов
    click(By.xpath("(//input[@id='" + contactId + "']/../..//img[@alt='Edit'])"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void deleteContactEditCard() {
    click(By.xpath("//div[@id='content']/form[2]/input[2]"));
  }

  public void deleteContact() {
    click(By.cssSelector("input[value=Delete]"));
  }

  public void confirmDeletion() {
    wd.switchTo().alert().accept();
  }

  public void modifyContact() {
    click((By.name("modifiy")));
  }

  public void createContact(ContactData contact, Integer groupIndex) {
    gotoAddContactPage();
    fillContactForm(contact, true, groupIndex);
    submitContactCreation();
    returnToHomePage();
  }

  public void modifyContact(int contactId, ContactData contact) {
    editContact(contactId);
    fillContactForm(contact, false, 1);
    submitContactModification();
    returnToHomePage();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
    for (WebElement element: elements) {
      String lastName = element.findElement(By.xpath(".//td[2]")).getText();
      String firstName = element.findElement(By.xpath(".//td[3]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("Value"));
      ContactData contact = new ContactData(id, firstName, lastName, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
