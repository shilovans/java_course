package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> beforeContact = app.getContactHelper().getContactList();
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    ContactData contact = new ContactData("Test", "Testov", "Test Address", "+79201234567", "user@test.com");
    app.getContactHelper().createContact(contact, 1);
    app.wait(2);
    List<ContactData> afterContact = app.getContactHelper().getContactList();
    Assert.assertEquals(afterContact.size(), beforeContact.size() + 1);

    int max = 0;
    for (ContactData c : afterContact) {
      if (c.getId() > max) {
        max = c.getId();
      }
    }
    contact.setId(max);
    beforeContact.add(contact);
    Assert.assertEquals(new HashSet<Object>(beforeContact), new HashSet<Object>(afterContact));
  }
}
