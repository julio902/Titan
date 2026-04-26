package models;

public abstract class Usuario {
    protected String user;
    protected String correo;
    protected String password;
    protected String rol;

    public Usuario(String user,String correo, String password, String rol) {
        this.user = user;
        this.correo = correo;
        this.password = password;
        this.rol = rol;
    }


        // getters
    public String getUser() { return user; }
    public String getCorreo() { return correo; }
    public String getPassword() { return password; }
    public String getRol() { return rol; }

    public abstract void mostrarMenu(); // metodo abstracto para mostrar el menú específico de cada rol

    @Override
    public String toString() {
        return String.format("[%s] %s (%s)", rol.toUpperCase(), user, correo);
    }
}
