package models;

public class Usuario {
    private String user;
    private String password;
    private String rol;

    public Usuario(String user, String password, String rol) {
        this.user = user;
        this.password = password;
        this.rol = rol;
    }

    public String getUser() { return user; }
    public String getPassword() { return password; }
    public String getRol() { return rol; }

    @Override
    public String toString() {
        return user + " - Rol: " + rol;
    }
}
