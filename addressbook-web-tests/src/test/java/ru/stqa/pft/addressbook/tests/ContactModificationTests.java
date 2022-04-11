package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification () {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoGroupPage();
      if (! app.getGroupHelper().isThereAGroup()) {
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
      }
      app.getNavigationHelper().gotoHomePage();
      app.getContactHelper().createContact(new ContactData("Test", "Testov", "Test Address", "+79201234567", "user@test.com"), 1);
    }
    List<ContactData> beforeContact = app.getContactHelper().getContactList();
    int contactIndex = beforeContact.size() - 1;
    app.getContactHelper().editContact(contactIndex);
    ContactData contact = new ContactData(beforeContact.get(contactIndex).getId(),"Test", "Testov", "Test Address", "+79201234567", "user@test.com");
    app.getContactHelper().fillContactForm(contact, false, 1);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();
    List<ContactData> afterContact = app.getContactHelper().getContactList();
    Assert.assertEquals(afterContact.size(), beforeContact.size());

    beforeContact.remove(beforeContact.size() - 1);
    beforeContact.add(contact);
    Comparator<? super ContactData> ById = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());;
    beforeContact.sort(ById);
    afterContact.sort(ById);
    Assert.assertEquals(beforeContact, afterContact);
  }
}
