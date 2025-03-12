package ru.stqa.addressbook.manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


    @Entity
    @Table(name = "addressbook")
    public class ContactRecord {

        @Id
        @Column(name = "id")
        public int id;
        @Column(name = "firstname")
        public String firstName;
        @Column(name = "lastname")
        public String middleName;
        @Column(name = "middlename")
        public String lastName;
        @Column(name = "address")
        public String address;
        @Column(name = "home")
        public String homePhone;
        @Column(name = "mobile")
        public String mobilePhone;
        @Column(name = "work")
        public String workPhone;
        @Column(name = "email")
        public String email;
        @Column(name = "email2")
        public String email2;
        @Column(name = "email3")
        public String email3;

        public ContactRecord() {
        }

        public ContactRecord(int id, String firstName, String middleName, String lastName, String address, String homePhone, String mobilePhone, String workPhone, String email, String email2, String email3) {
            this.id = id;
            this.firstName = firstName;
            this.middleName = middleName;
            this.lastName = lastName;
            this.address = address;
            this.homePhone = homePhone;
            this.mobilePhone = mobilePhone;
            this.workPhone = workPhone;
            this.email = email;
            this.email2 = email2;
            this.email3 = email3;
        }
    }

