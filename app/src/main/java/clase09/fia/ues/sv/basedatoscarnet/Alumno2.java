package clase09.fia.ues.sv.basedatoscarnet;

/**
 * Created by winuser on 22/04/2015.
 */
public class Alumno2 {

    private String carnet;
    private String nombre;
    private String apellido;
    private String sexo;
    private int matganadas;
    private double total1;
    private double total2;
    private double total3;
    public Alumno2(){
    }

    public Alumno2(String carnet, String nombre, String apellido, String sexo) {
        this.carnet = carnet;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getMatganadas() {
        return matganadas;
    }

    public void setMatganadas(int matganadas) {
        this.matganadas = matganadas;

    }
    public double getTotal1() {
        return total1;
    }
    public void setTotal1(double total1) {
        this.total1 = total1;

    }

    public double getTotal2() {
        return total2;
    }
    public void setTotal2(double total2) {
        this.total2 = total2;

    }
    public double getTotal3() {
        return total3;
    }
    public void setTotal3(double total3) {
        this.total3 = total3;

    }
}

