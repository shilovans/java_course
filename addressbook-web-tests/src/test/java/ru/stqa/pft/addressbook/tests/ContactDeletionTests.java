package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion () {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Test", "Testov", "Test Address", "+79201234567", "user@test.com"), 1);
    }
    List<ContactData> beforeContact = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(beforeContact.size() - 1);
    app.getContactHelper().viewContactDetails();
    app.getContactHelper().modifyContact();
    app.getContactHelper().deleteContactEditCard();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> afterContact = app.getContactHelper().getContactList();
    Assert.assertEquals(afterContact.size(), beforeContact.size() - 1);
  }


}
