
public class Doctor {
    private String doctorID;
    private String nombre;
    private String especialidad;
    private String contraseña;
    public Doctor(String n,String e, String c, String id){
        this.nombre=n;
        this.especialidad=e;
        this.contraseña=c;
        this.doctorID=id;
    }

    public String getdoctorID() {
        return this.doctorID;
    }

    public String getContraseña() {
        return contraseña;
    }
}
