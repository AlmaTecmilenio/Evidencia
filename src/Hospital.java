import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Hospital {
    public static HashMap<String, Doctor> doctores = new HashMap<String, Doctor>();
    public static HashMap<String, Paciente> pacientes = new HashMap<String, Paciente>();
    public static HashMap<String, Cita> citas = new HashMap<String, Cita>();
    public static int doctorId=1;
    public static int pacienteID=1;
    public static int citaID=1;

    public static void agregarDoctor(){
        Scanner sc = new Scanner(System.in);
        String nombre, especialidad, contrasena;
        System.out.println("Cual es el nombre completo de el o la doctor/a");
        nombre = sc.nextLine();
        System.out.println("Cual es la especialidad de el o la doctor/a");
        especialidad = sc.nextLine();
        System.out.println("Cual es la contraseña de el o la doctor/a");
        contrasena = sc.nextLine();
        doctores.put("doc"+doctorId,new Doctor(nombre,especialidad,contrasena,"doc"+doctorId));
        System.out.println("Doctor agregado con ID: doc"+doctorId);
        doctorId++;
    }

    public static void agregarPaciente(){
        Scanner sc = new Scanner(System.in);
        String nombre;
        System.out.println("Cual es el nombre completo de el o la paciente");
        nombre = sc.nextLine();
        pacientes.put("pac"+pacienteID,new Paciente(nombre,"pac"+pacienteID));
        System.out.println("Paciente agregado con ID: pac"+pacienteID);
        pacienteID++;
    }

    public static void agregarCita(){
        Scanner sc = new Scanner(System.in);
        String fecha, hora, motivo,dID,pID;
        boolean agendando = true;
        while (agendando) {
            System.out.println("Cual es el ID del doctor con el que desea hacer la cita");
            dID = sc.nextLine();
            if (doctores.containsKey(dID)) {
                System.out.println("Cual es el ID del paciente para el que desea hacer la cita");
                pID = sc.nextLine();
                if (pacientes.containsKey(pID)) {
                    System.out.println("Cual es la fecha de la cita. El formato de la fecha debe ser DD/MM/AAAA");
                    fecha = sc.nextLine();
                    System.out.println("Cual es el hora de la cita. El formato de la hora debe ser hh:mm");
                    hora = sc.nextLine();
                    System.out.println("Cual es el motivo de la cita");
                    motivo = sc.nextLine();
                    citas.put("cita" + citaID, new Cita(fecha, hora, motivo, "cita" + citaID, dID, pID));
                    System.out.println("Cita agregagada con ID: cita" + citaID);
                    citaID++;
                    agendando=false;
                }else{
                    System.out.println("El Id del paciente no existe, intentelo nuevamente");
                }
            } else {
                System.out.println("El Id del doctor no existe, intentelo nuevamente");
            }
        }
    }

    public static void desplegarCitas(){
        for(Map.Entry<String,Cita> item: citas.entrySet()){
            System.out.println("ID de la cita:" + item.getKey());
            System.out.println("ID del doctor:" + item.getValue().getDoctorID());
            System.out.println("ID del paciente:" + item.getValue().getPacienteID());
            System.out.println("Fecha: " + item.getValue().getFecha() + " Hora: " + item.getValue().getHora());
            System.out.println("Motivo: " + item.getValue().getMotivo() + "\n");
        }
    }

    public static void main(String [] args) {
        boolean contrasenaInvalida = true;
        HashMap<String, String> admins = new HashMap<String, String>();

        Scanner sc = new Scanner(System.in);
        String usuario;
        String password;
        String opcion;
        boolean admin_user=false;
        boolean sesion_activa=true;
        boolean salir=false;


        //cargar admin user
        admins.put("admin1","superadmin1!");


        while(!salir) {
            System.out.println("Bienvenido al sistema de citas");
            while (contrasenaInvalida) {
                System.out.println("Por favor ingrese su ID de usuario:");
                usuario = sc.nextLine();
                //checar si usuario es admin
                if (admins.containsKey(usuario)) {
                    //checar password
                    System.out.println("Por favor ingrese su contraseña");
                    password = sc.nextLine();
                    if (admins.get(usuario).equals(password)) {
                        admin_user = true;
                        contrasenaInvalida = false;
                    } else {
                        System.out.println("Contraseña incorrecta");
                    }
                } else if (doctores.containsKey(usuario)) {
                    //checar password
                    System.out.println("Por favor ingrese su contraseña");
                    password = sc.nextLine();
                    if (doctores.get(usuario).getContraseña().equals(password)) {
                        contrasenaInvalida = false;
                    } else {
                        System.out.println("Contraseña incorrecta");
                    }
                } else {
                    System.out.println("Usuario incorrecto");
                }
            }

            if (admin_user) {
                System.out.println("Usuario identificado con derechos de administrador");
                while (sesion_activa) {
                    System.out.println("Escriba el numero de acuerdo al menu");
                    System.out.println("1   Agregar doctores");
                    System.out.println("2   Agregar pacientes");
                    System.out.println("3   Desplegar citas");
                    System.out.println("4   Cerrar sesion");
                    System.out.println("5   Salir del programa");
                    opcion = sc.nextLine();
                    if (opcion.equals("1")) {
                        //agrega doctor
                        System.out.println("1   Agregar doctores");
                        agregarDoctor();
                    } else if (opcion.equals("2")) {
                        // agrega paciente
                        System.out.println("2   Agregar pacientes");
                        agregarPaciente();
                    } else if (opcion.equals("3")) {
                        // despliega citas
                        System.out.println("3   Desplegar citas");
                        desplegarCitas();
                    } else if (opcion.equals("4")) {
                        // Cerrar sesion
                        System.out.println("4   Cerrar sesion");
                        sesion_activa = false;
                    } else if (opcion.equals("5")) {
                        System.out.println("5   Salir del programa");
                        sesion_activa = false;
                        salir=true;
                    } else {
                            // opcion invalida
                            System.out.println("La opcion seleccionada no existe");
                    }
                }
            } else {
                System.out.println("Usuario identificado con derechos de doctor");
                while (sesion_activa) {
                    System.out.println("Escriba el numero de acuerdo al menu");
                    System.out.println("1   Agregar pacientes");
                    System.out.println("2   Crear una cita");
                    System.out.println("3   Desplegar citas");
                    System.out.println("4   Cerrar sesion");
                    System.out.println("5   Salir del programa");
                    opcion = sc.nextLine();
                    if (opcion.equals("1")) {
                        //agrega pacientes
                        System.out.println("1   Agregar pacientes");
                        agregarPaciente();
                    } else if (opcion.equals("2")) {
                        // Crear una cita
                        System.out.println("2   Crear una cita");
                        agregarCita();
                    } else if (opcion.equals("3")) {
                        // despliega citas
                        System.out.println("3   Desplegar citas");
                        desplegarCitas();
                    } else if (opcion.equals("4")) {
                        // Cerrar sesion
                        System.out.println("4   Cerrar sesion");
                        sesion_activa = false;
                    } else if (opcion.equals("5")) {
                        System.out.println("5   Salir del programa");
                        sesion_activa = false;
                        salir=true;
                    } else {
                        // opcion invalida
                        System.out.println("La opcion seleccionada no existe");
                    }
                }
            }
            contrasenaInvalida = true;
            admin_user=false;
            sesion_activa=true;
        }
    }
}
