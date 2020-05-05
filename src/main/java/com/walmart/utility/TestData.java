package com.walmart.utility;

import java.util.ArrayList;
import java.util.List;

public class TestData {

    public int testid;
    public Credentials creds;
    public List<Address> addresses = new ArrayList<>();

    public static class Address {
        public String address1;
        public String city;
        public String state;
        public String postcode;
        public String country;
        public String phone;
        public String alias;
    }
    public static class Credentials {
        public String username;
        public String password;
    }
}
