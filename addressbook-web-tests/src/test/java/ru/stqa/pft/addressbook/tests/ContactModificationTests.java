package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void ensureContactPreconditions () {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoGroupPage();
      if (! app.getGroupHelper().isThereAGroup()) {
        app.getGroupHelper().createGroup(new GroupData("test1", null, null));
      }
      app.getNavigationHelper().gotoHomePage();
      app.getContactHelper().createContact(new ContactData("Test", "Testov", "Test Address", "+79201234567", "user@test.com"), 1);
    }
  }

  @Test
  public void testContactModification () {

    Comparator<? super ContactData> ById = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    List<ContactData> beforeContact = app.getContactHelper().getContactList();
    beforeContact.sort(ById);

    int contactIndex = beforeContact.size() - 1;
    int contactId = beforeContact.get(contactIndex).getId();
    ContactData contact = new ContactData(contactId,"Test", "Testov", "Test Address", "+79201234567", "user@test.com");

    app.getContactHelper().modifyContact(contactId, contact);
    List<ContactData> afterContact = app.getContactHelper().getContactList();
    afterContact.sort(ById);
    Assert.assertEquals(afterContact.size(), beforeContact.size());

    beforeContact.remove(contactIndex);
    beforeContact.add(contact);
    Assert.assertEquals(beforeContact, afterContact);
  }
}
