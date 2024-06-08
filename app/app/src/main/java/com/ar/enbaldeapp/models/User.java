package com.ar.enbaldeapp.models;

import com.ar.enbaldeapp.ui.Utilities;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    public static final int Admin = 1;
    public static final int Client = 2;

    public static final String INVALID_PASSWORD = "La clave es inválida";
    public static final String INVALID_PASSWORD_LENGTH = "La longitud de la clave debe ser mayor a 6";
    public static final String INVALID_USERNAME = "El nombre de usuario es inválido";
    public static final String INVALID_OBSERVATIONS = "Las observaciones son inválidas";
    public static final String INVALID_PHONE = "El teléfono es inválido";
    public static final String INVALID_ADDRESS = "La dirección es inválida";
    public static final String INVALID_EMAIL = "El e-mail es inválido";
    public static final String INVALID_FIRST_NAME = "El nombre es inválido";
    public static final String INVALID_LAST_NAME = "El apellido es inválido";
    public static final String INVALID_ID = "El id es inválido";
    public static final String INVALID_TYPE = "El tipo es inválido";

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
    private final String password;  // TODO: el password no se envia desde el servidor, quitarlo
    @SerializedName("tipo")
    private final int type;

    public User(long id, String lastName, String firstName, String email, String address, String phone, String observations, String username, String password, int type) {
        if (id <= 0) throw new RuntimeException(INVALID_ID);
        if (lastName == null || lastName.trim().isEmpty()) throw new RuntimeException(INVALID_LAST_NAME);
        if (firstName == null || firstName.trim().isEmpty()) throw new RuntimeException(INVALID_FIRST_NAME);
        if (! isValidEmail(email)) throw new RuntimeException(INVALID_EMAIL);
        if (address == null || address.trim().isEmpty()) throw new RuntimeException(INVALID_ADDRESS);
        if (phone == null) throw new RuntimeException(INVALID_PHONE);
        if (observations == null) throw new RuntimeException(INVALID_OBSERVATIONS);
        if (username == null || username.trim().isEmpty()) throw new RuntimeException(INVALID_USERNAME);
        if (password == null || password.trim().isEmpty()) throw new RuntimeException(INVALID_PASSWORD);
        if (password.length() < 6) throw new RuntimeException(INVALID_PASSWORD_LENGTH);
        if (type != Admin && type != Client) throw new RuntimeException(INVALID_TYPE);

        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.observations = observations;
        this.username = username;
        this.password = password;
        this.type = type;
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
    public int getType() { return type; }

    private boolean isValidEmail(String email) {
        String validChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890.+_-";

        if (email == null) return false;
        if (email.trim().isEmpty()) return false;

        String[] sections = email.split("@");
        if (sections.length != 2) return false;

        String[] states = sections[1].split("\\.");
        if (states.length < 1) return false;

        for (int index = 0; index < sections[0].length(); index++) {
            if (validChars.indexOf(sections[0].charAt(index)) == -1) {
                return false;
            }
        }

        for (String state : states) {
            for (int index = 0; index < state.length(); index++) {
                if (validChars.indexOf(state.charAt(index)) == -1) {
                    return false;
                }
            }
        }

        return true;
    }
}