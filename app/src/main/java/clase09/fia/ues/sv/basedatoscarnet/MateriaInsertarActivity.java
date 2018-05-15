package clase09.fia.ues.sv.basedatoscarnet;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MateriaInsertarActivity extends Activity {

    ControlBDCarnet helper;
    EditText editCodmateria;
    EditText editNommateria;
    EditText editUnidadesval;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_insertar);
        helper = new ControlBDCarnet(this);
        editCodmateria = (EditText) findViewById(R.id.editCodmateria);
        editNommateria = (EditText) findViewById(R.id.editNommateria);
        editUnidadesval = (EditText) findViewById(R.id.editUnidadesval);

    }
    public void insertarMateria(View v) {
        String codmateria=editCodmateria.getText().toString();
        String nommateria=editNommateria.getText().toString();
        String unidadesval=editUnidadesval.getText().toString();
        String regInsertados;
        Materia materia=new Materia();
        materia.setCodmateria(codmateria);
        materia.setNommateria(nommateria);
        materia.setUnidadesval(unidadesval);
        helper.abrir();
        regInsertados=helper.insertar(materia);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {

        editCodmateria.setText("");
        editNommateria.setText("");
        editUnidadesval.setText("");

    }
}
