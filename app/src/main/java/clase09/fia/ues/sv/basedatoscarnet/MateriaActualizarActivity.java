package clase09.fia.ues.sv.basedatoscarnet;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MateriaActualizarActivity extends Activity {

    ControlBDCarnet helper;
    EditText editCodmateria;
    EditText editNommateria;
    EditText editUnidadesval;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_actualizar);
        helper = new ControlBDCarnet(this);
        editCodmateria = (EditText) findViewById(R.id.editCodmateria);
        editNommateria = (EditText) findViewById(R.id.editNommateria);
        editUnidadesval = (EditText) findViewById(R.id.editUnidadesval);
    }

    public void actualizarMateria(View v) {
        Materia materia = new Materia();
        materia.setCodmateria(editCodmateria.getText().toString());
        materia.setNommateria(editNommateria.getText().toString());
        materia.setUnidadesval(editUnidadesval.getText().toString());

        helper.abrir();
        String estado = helper.actualizar(materia);
        helper.cerrar();

        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();

    }

    public void limpiarTexto(View v) {

        editCodmateria.setText("");
        editNommateria.setText("");
        editUnidadesval.setText("");
    }
}
