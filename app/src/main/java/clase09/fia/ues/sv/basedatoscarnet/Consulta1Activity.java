package clase09.fia.ues.sv.basedatoscarnet;

//import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Consulta1Activity extends Activity {
    ControlBDCarnet helper;
    EditText editCarnet;
    EditText editMatinscritas;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta1);
        helper = new ControlBDCarnet(this);
        editCarnet = (EditText) findViewById(R.id.editCarnet);
        editMatinscritas = (EditText) findViewById(R.id.editMatinscritas);
    }

    public void consulta1(View v) {

        helper.abrir();
        String mat_ins = helper.consulta_1(editCarnet.getText().toString());

        helper.cerrar();
        if(mat_ins == null)
            Toast.makeText(this, "Alumno con carnet " + editCarnet.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{

            editMatinscritas.setText(String.valueOf(mat_ins));
        }
    }

    public void limpiarTextoConsulta1(View v){
        editCarnet.setText("");
        editMatinscritas.setText("");
    }
}
