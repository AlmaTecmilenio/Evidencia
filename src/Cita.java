public class Cita {
    private String citaID;
    private String doctorID;
    private String pacienteID;
    private String fecha;
    private String hora;
    private String motivo;
    public Cita(String f, String h, String mo, String id, String dID, String pID){
        this.fecha=f;
        this.hora=h;
        this.motivo=mo;
        this.citaID=id;
        this.doctorID=dID;
        this.pacienteID=pID;
    }

    public String getCitaID() {
        return this.citaID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public String getPacienteID() {
        return pacienteID;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public String getMotivo() {
        return motivo;
    }
}
