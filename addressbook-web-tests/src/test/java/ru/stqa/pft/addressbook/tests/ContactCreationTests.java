package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> beforeContact = app.getContactHelper().getContactList();
    //int beforeContact = app.getContactHelper().getContactCount();
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", null, null));
    }
    app.getContactHelper().createContact(new ContactData("Test", "Testov", "Test Address", "+79201234567", "user@test.com"), 1);
   List<ContactData> afterContact = app.getContactHelper().getContactList();
    //int afterContact = app.getContactHelper().getContactCount();
    Assert.assertEquals(afterContact.size(), beforeContact.size() + 1);
    //Assert.assertEquals(afterContact, beforeContact + 1);

  }
}
