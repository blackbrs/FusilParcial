package clase09.fia.ues.sv.basedatoscarnet;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NotaActualizarActivity extends Activity {

    ControlBDCarnet helper;
    EditText editCarnet;
    EditText editCodigo;
    EditText editCiclo;
    EditText editNota;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota_actualizar);
        helper = new ControlBDCarnet(this);
        editCarnet = (EditText) findViewById(R.id.editCarnet);
        editCodigo = (EditText) findViewById(R.id.editCodigo);
        editCiclo = (EditText) findViewById(R.id.editCiclo);
        editNota = (EditText) findViewById(R.id.editNota);
    }

    public void actualizarNota(View v) {
        Nota nota = new Nota();
        nota.setCarnet(editCarnet.getText().toString());
        nota.setCodmateria(editCodigo.getText().toString());
        nota.setCiclo(editCiclo.getText().toString());
        nota.setNotafinal(Double.valueOf(editNota.getText().toString()));

        helper.abrir();
        String estado = helper.actualizar(nota);
        helper.cerrar();

        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();

    }

    public void limpiarTexto(View v) {
        editCarnet.setText("");
        editCodigo.setText("");
        editCiclo.setText("");
        editNota.setText("");
    }
}
