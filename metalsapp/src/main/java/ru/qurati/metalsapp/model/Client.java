package ru.qurati.metalsapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clientId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "passport")
    private String passport;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        if (fullName != null && !fullName.isEmpty()) this.fullName = fullName;
        else throw new IllegalArgumentException("ФИО не должно быть пустым!");
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        if (passport != null && !passport.isEmpty()) this.passport = passport;
        else throw new IllegalArgumentException("Паспорт не должен быть пустым!");
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone != null && !phone.isEmpty()) this.phone = phone;
        else throw new IllegalArgumentException("Телефон не должен быть пустым!");
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address != null && !address.isEmpty()) this.address = address;
        else throw new IllegalArgumentException("Адрес не должен быть пустым!");
    }

    @Override
    public String toString() {
        return fullName;
    }
}