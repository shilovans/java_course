package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().gotoAddContactPage();
    app.getContactHelper().fillContactForm(new ContactData("Test", "Testov", "Test Address", "+79201234567", "user@test.com"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();
  }
}
