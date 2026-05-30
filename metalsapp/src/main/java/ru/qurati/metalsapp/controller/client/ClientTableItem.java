package ru.qurati.metalsapp.controller.client;

import javafx.beans.property.SimpleStringProperty;
import ru.qurati.metalsapp.model.Client;

public class ClientTableItem {
    private SimpleStringProperty fullName;
    private SimpleStringProperty passport;
    private SimpleStringProperty phone;
    private SimpleStringProperty address;
    private Client client;

    public ClientTableItem(Client client) {
        this.fullName = new SimpleStringProperty(client.getFullName());
        this.passport = new SimpleStringProperty(client.getPassport());
        this.phone = new SimpleStringProperty(client.getPhone());
        this.address = new SimpleStringProperty(client.getAddress());
        this.client = client;
    }

    public String getFullName() { return fullName.get(); }
    public SimpleStringProperty fullNameProperty() { return fullName; }
    public void setFullName(String fullName) { this.fullName.set(fullName); }

    public String getPassport() { return passport.get(); }
    public SimpleStringProperty passportProperty() { return passport; }
    public void setPassport(String passport) { this.passport.set(passport); }

    public String getPhone() { return phone.get(); }
    public SimpleStringProperty phoneProperty() { return phone; }
    public void setPhone(String phone) { this.phone.set(phone); }

    public String getAddress() { return address.get(); }
    public SimpleStringProperty addressProperty() { return address; }
    public void setAddress(String address) { this.address.set(address); }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }
}