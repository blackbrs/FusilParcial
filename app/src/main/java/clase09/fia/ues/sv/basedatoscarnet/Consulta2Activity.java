package clase09.fia.ues.sv.basedatoscarnet;;

import android.app.Activity;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class Consulta2Activity extends Activity {
    ControlBDCarnet helper;
    EditText editCarnet;
    EditText editNombre;
    EditText editApellido;
    EditText editSexo;
    EditText editMatganadas;
    EditText editTotal1;
    EditText editTotal2;
    EditText editTotal3;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta2);
        helper = new ControlBDCarnet(this);

        editCarnet = (EditText) findViewById(R.id.editCarnet);
        editNombre = (EditText) findViewById(R.id.editNombre);
        editApellido = (EditText) findViewById(R.id.editApellido);
        editSexo = (EditText) findViewById(R.id.editSexo);
        editMatganadas = (EditText) findViewById(R.id.editMatganadas);
        editTotal1 = (EditText) findViewById(R.id.editTotal1);
        editTotal2 = (EditText) findViewById(R.id.editTotal2);
        editTotal3 = (EditText) findViewById(R.id.editTotal3);
    }

    public void consulta2(View v) {

        helper.abrir();
        Alumno2 alumno2 = helper.consulta_2(editCarnet.getText().toString());

        helper.cerrar();
        if(alumno2 == null)
            Toast.makeText(this, "Alumno con carnet " + editCarnet.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editNombre.setText(alumno2.getNombre());
            editApellido.setText(alumno2.getApellido());
            editSexo.setText(alumno2.getSexo());
            editMatganadas.setText(String.valueOf(alumno2.getMatganadas()));
            editTotal1.setText(String.valueOf(alumno2.getTotal1()));
            editTotal2.setText(String.valueOf(alumno2.getTotal2()));
            editTotal3.setText(String.valueOf(alumno2.getTotal3()));


        }
    }

    public void limpiarTextoConsulta2(View v){
        editCarnet.setText("");
        editNombre.setText("");
        editApellido.setText("");
        editSexo.setText("");
        editMatganadas.setText("");
        editTotal1.setText("");
        editTotal2.setText("");
        editTotal3.setText("");
    }
}
