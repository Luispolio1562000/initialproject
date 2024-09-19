package com.qrsof.initialproject.request;

import java.io.Serial;
import java.io.Serializable;

public class AuthRequest implements Serializable {


    @Serial
    private static final long serialVersionUID = -3646302296993033473L;


    private String usuario;
    private String password;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
