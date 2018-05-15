package clase09.fia.ues.sv.basedatoscarnet;

/**
 * Created by ing. Cesar on 17/4/2016.
 */
public class Nota {

    private String carnet;
    private String codmateria;
    private String ciclo;
    private double notafinal;

    public Nota(){

    }

    public Nota(String carnet, String codmateria, String ciclo, float notafinal) {
        this.carnet = carnet;
        this.codmateria = codmateria;
        this.ciclo = ciclo;
        this.notafinal = notafinal;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getCodmateria() {
        return codmateria;
    }

    public void setCodmateria(String codmateria) {
        this.codmateria = codmateria;
    }

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public double getNotafinal() {
        return notafinal;
    }

    public void setNotafinal(double notafinal) {
        this.notafinal = notafinal;
    }

}
