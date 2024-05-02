package com.ar.enbaldeapp.models;

import java.util.Date;

public class User {
    private final long id;
    private final String lastName;
    private final String firstName;
    private final String email;
    private final String address;
    private final String phone;
    private final String observations;

    public User(long id, String lastName, String firstName, String email, String address, String phone, String observations) {
        if (id <= 0) throw new RuntimeException("El id es inválido");
        if (lastName == null || lastName.trim().isEmpty()) throw new RuntimeException("El apellido es inválido");
        if (firstName == null || firstName.trim().isEmpty()) throw new RuntimeException("El nombre es inválido");
        if (email == null || email.trim().isEmpty()) throw new RuntimeException("El e-mail es inválido"); // TODO: Agregar mejor chequeo de email
        if (address == null || address.trim().isEmpty()) throw new RuntimeException("La dirección es inválida");
        if (phone == null) throw new RuntimeException("El teléfono es inválido");
        if (observations == null) throw new RuntimeException("Las observaciones son inválidas");

        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.observations = observations;
    }

    public long getId() { return id; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getObservations() { return observations; }
}

