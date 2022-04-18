package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
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
  public void testContactDeletion () {

    List<ContactData> beforeContact = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(beforeContact.size() - 1);
    app.getContactHelper().deleteContact();
    app.getContactHelper().confirmDeletion();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> afterContact = app.getContactHelper().getContactList();
    Assert.assertEquals(afterContact.size(), beforeContact.size() - 1);
    beforeContact.remove(beforeContact.size() - 1);
    Assert.assertEquals(beforeContact, afterContact);

  }


}
