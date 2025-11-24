package models;

public class Usuario {
    private String user;
    private String correo;
    private String password;
    private String rol;

    public Usuario(String user,String correo, String password, String rol) {
        this.user = user;
        this.correo = correo;
        this.password = password;
        this.rol = rol;
    }

    public String getUser() { return user; }
    public String getCorreo() { return correo; }
    public String getPassword() { return password; }
    public String getRol() { return rol; }

    @Override
    public String toString() {
        return user + " (" + correo + ") - Rol: " + rol;
    }
}
