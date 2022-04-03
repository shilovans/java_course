package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {
  @Test
  public void testContactDeletion () {
    app.getNavigationHelper().gotoHomePage();
    int beforeContact = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Test", "Testov", "Test Address", "+79201234567", "user@test.com"), 1);
    }
    app.getContactHelper().selectContact(beforeContact - 1);
    app.getContactHelper().viewContactDetails();
    app.getContactHelper().modifyContact();
    app.getContactHelper().deleteContactEditCard();
    app.getNavigationHelper().gotoHomePage();
    int afterContact = app.getContactHelper().getContactCount();
    Assert.assertEquals(afterContact, beforeContact - 1);
  }


}
