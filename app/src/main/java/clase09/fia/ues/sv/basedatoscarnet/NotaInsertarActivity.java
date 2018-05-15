package clase09.fia.ues.sv.basedatoscarnet;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NotaInsertarActivity extends Activity {

    ControlBDCarnet helper;
    EditText editCarnet;
    EditText editCodmateria;
    EditText editCiclo;
    EditText editNotafinal;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota_insertar);

        helper = new ControlBDCarnet(this);
        editCarnet = (EditText) findViewById(R.id.editCarnet);
        editCodmateria = (EditText) findViewById(R.id.editCodmateria);
        editCiclo = (EditText) findViewById(R.id.editCiclo);
        editNotafinal = (EditText) findViewById(R.id.editNotafinal);
    }
    public void insertarNota(View v) {

        String regInsertados;
        String carnet=editCarnet.getText().toString();
        String codmateria=editCodmateria.getText().toString();
        String ciclo=editCiclo.getText().toString();
        Double notafinal=Double.valueOf(editNotafinal.getText().toString());
        Nota nota= new Nota();
        nota.setCarnet(carnet);
        nota.setCodmateria(codmateria);
        nota.setCiclo(ciclo);
        nota.setNotafinal(notafinal);
        helper.abrir();
        regInsertados=helper.insertar(nota);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v) {

        editCarnet.setText("");
        editCodmateria.setText("");
        editCiclo.setText("");
        editNotafinal.setText("");
    }
}
