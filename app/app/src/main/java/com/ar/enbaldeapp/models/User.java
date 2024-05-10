package com.ar.enbaldeapp.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    private final long id;
    @SerializedName("apellido")
    private final String lastName;
    @SerializedName("nombre")
    private final String firstName;
    private final String email;
    @SerializedName("direccion")
    private final String address;
    @SerializedName("telefono")
    private final String phone;
    @SerializedName("observaciones")
    private final String observations;
    @SerializedName("usuario")
    private final String username;
    @SerializedName("clave")
    private final String password;

    public User(long id, String lastName, String firstName, String email, String address, String phone, String observations, String username, String password) {
        if (id <= 0) throw new RuntimeException("El id es inválido");
        if (lastName == null || lastName.trim().isEmpty()) throw new RuntimeException("El apellido es inválido");
        if (firstName == null || firstName.trim().isEmpty()) throw new RuntimeException("El nombre es inválido");
        if (email == null || email.trim().isEmpty()) throw new RuntimeException("El e-mail es inválido"); // TODO: Agregar mejor chequeo de email
        if (address == null || address.trim().isEmpty()) throw new RuntimeException("La dirección es inválida");
        if (phone == null) throw new RuntimeException("El teléfono es inválido");
        if (observations == null) throw new RuntimeException("Las observaciones son inválidas");
        if (username == null || username.trim().isEmpty()) throw new RuntimeException("El nombre de usuario es inválido");
        if (password == null || password.trim().isEmpty()) throw new RuntimeException("La clave es inválida");

        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.observations = observations;
        this.username = username;
        this.password = password;
    }

    public long getId() { return id; }
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getPhone() { return phone; }
    public String getObservations() { return observations; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}