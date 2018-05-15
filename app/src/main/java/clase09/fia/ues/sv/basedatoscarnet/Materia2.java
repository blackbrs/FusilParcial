package clase09.fia.ues.sv.basedatoscarnet;

/**
 * Created by winuser on 24/04/2015.
 */
public class Materia2 {

    private String codmateria;
    private String nommateria;
    private String unidadesval;
    private double cantidad_materias;

    public Materia2(){

    }

    public Materia2(String codmateria, String nommateria, String unidadesval,double cantidad_materias) {
        this.codmateria = codmateria;
        this.nommateria = nommateria;
        this.unidadesval = unidadesval;
        this.cantidad_materias = cantidad_materias;
    }

    public String getCodmateria() {
        return codmateria;
    }

    public void setCodmateria(String codmateria) {
        this.codmateria = codmateria;
    }

    public String getNommateria() {
        return nommateria;
    }

    public void setNommateria(String nommateria) {
        this.nommateria = nommateria;
    }

    public String getUnidadesval() {
        return unidadesval;
    }

    public void setUnidadesval(String unidadesval) {
        this.unidadesval = unidadesval;
    }

    public void setCantidad_materias(double  cantidad_materias) {
        this.cantidad_materias = cantidad_materias;
    }
    public double getCantidad_materias() {
        return cantidad_materias;
    }
}




