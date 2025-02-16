package ru.stqa.addressbook.model;

public record ContactData(String id, String firstName, String lastName, String address, String homePhone,
                          String mobilePhone,
                          String workPhone, String email, String email2, String email3) {

    public ContactData() {
        this("", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(id, this.firstName, this.lastName, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.email, this.email2, this.email3);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(this.id, firstName, this.lastName, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.email, this.email2, this.email3);

    }

    public ContactData withLastName(String lastName) {
        return new ContactData(this.id, this.firstName, lastName, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.email, this.email2, this.email3);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.firstName, this.lastName, address, this.homePhone, this.mobilePhone, this.workPhone, this.email, this.email2, this.email3);
    }

    public ContactData withHomePhone(String homePhone) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, homePhone, this.mobilePhone, this.workPhone, this.email, this.email2, this.email3);
    }

    public ContactData withMobilePhone(String mobilePhone) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.homePhone, mobilePhone, this.workPhone, this.email, this.email2, this.email3);
    }

    public ContactData withWorkPhone(String workPhone) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.homePhone, this.mobilePhone, workPhone, this.email, this.email2, this.email3);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.homePhone, this.mobilePhone, this.workPhone, email, this.email2, this.email3);
    }

    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.email, email2, this.email3);
    }

    public ContactData withEmail3(String email3) {
        return new ContactData(this.id, this.firstName, this.lastName, this.address, this.homePhone, this.mobilePhone, this.workPhone, this.email, this.email2, email3);
    }
}
