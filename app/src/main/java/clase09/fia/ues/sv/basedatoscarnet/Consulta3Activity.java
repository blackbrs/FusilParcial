package clase09.fia.ues.sv.basedatoscarnet;;


import android.app.Activity;
import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;

import android.widget.Toast;


public class Consulta3Activity extends Activity {

    ControlBDCarnet helper;
    EditText editCodmateria;
    EditText editNomMateria;
    EditText editTotal4;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta3);
        helper = new ControlBDCarnet(this);
        editCodmateria = (EditText) findViewById(R.id.editCodmateria);
        editNomMateria = (EditText) findViewById(R.id.editNommateria);
        editTotal4 = (EditText) findViewById(R.id.editTotal4);
    }



    public void consulta3(View v) {

        helper.abrir();
        Materia2 materia2 = helper.consulta_3(editCodmateria.getText().toString());

        helper.cerrar();
        if(materia2 == null)
            Toast.makeText(this, "Materia con codigo " + editCodmateria.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editNomMateria.setText(materia2.getNommateria());
            editTotal4.setText(String.valueOf(materia2.getCantidad_materias()));



        }
    }

    public void limpiarTextoConsulta3(View v){
        editCodmateria.setText("");
        editNomMateria.setText("");
        editTotal4.setText("");

    }


}
