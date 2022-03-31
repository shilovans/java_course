package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String address;
  private final String homePhone;
  private final String email;
  private final Integer groupIndex;

  public ContactData(String firstname, String lastname, String address, String homePhone, String email, Integer groupIndex) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.homePhone = homePhone;
    this.email = email;
    this.groupIndex = groupIndex;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getEmail() {
    return email;
  }

  public Integer getGroupIndex() {
    return groupIndex;
  }
}
