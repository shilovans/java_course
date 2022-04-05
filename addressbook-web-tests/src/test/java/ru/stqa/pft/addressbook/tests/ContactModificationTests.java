package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification () {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Test", "Testov", "Test Address", "+79201234567", "user@test.com"), 1);
    }
    List<ContactData> beforeContact = app.getContactHelper().getContactList();
    app.getContactHelper().editContact();
    ContactData contact = new ContactData(beforeContact.get(beforeContact.size() - 1).getId(),"Test", "Testov", "Test Address", "+79201234567", "user@test.com");
    app.getContactHelper().fillContactForm(contact, false, 1);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> afterContact = app.getContactHelper().getContactList();
    Assert.assertEquals(afterContact.size(), beforeContact.size());

    beforeContact.remove(beforeContact.size() - 1);
    beforeContact.add(contact);
    Assert.assertEquals(new HashSet<Object>(beforeContact), new HashSet<Object>(afterContact));
  }
}
