package by.bntu.fitr.poisit.lytkina.technosila.beans;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private Double price;


    private String phone;

    @Enumerated(EnumType.STRING)
    private Payment payment;
    @Enumerated(EnumType.STRING)

    private Delivery delivery;
    private String street;
    private String building;
    private String corps;
    private String apartment;
    private String intercom;

    public Order() {
    }

    public Order(Long id, String username, Double price, @NotBlank String phone, Payment payment, Delivery delivery, String street, String building, String corps, String apartment, String intercom) {
        this.id = id;
        this.username = username;
        this.price = price;
        this.phone = phone;
        this.payment = payment;
        this.delivery = delivery;
        this.street = street;
        this.building = building;
        this.corps = corps;
        this.apartment = apartment;
        this.intercom = intercom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String builder) {
        this.building = builder;
    }

    public String getCorps() {
        return corps;
    }

    public void setCorps(String corps) {
        this.corps = corps;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public String getIntercom() {
        return intercom;
    }

    public void setIntercom(String intercom) {
        this.intercom = intercom;
    }
}

