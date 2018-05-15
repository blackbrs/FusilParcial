package clase09.fia.ues.sv.basedatoscarnet;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;


public class Consulta4Activity extends Activity {

    ControlBDCarnet helper;
    EditText editNomMateria;
    EditText editTotal4;
    Spinner spinner1;
    public Context mContext;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta4);
        helper = new ControlBDCarnet(this);

        editNomMateria = (EditText) findViewById(R.id.editNommateria);
        editTotal4 = (EditText) findViewById(R.id.editTotal4);

        ArrayList<String> my_array = new   ArrayList<String>();
        my_array = getMateriaValues();
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter my_Adapter = new ArrayAdapter(this, R.layout.spinnerrow, my_array);
        spinner1.setAdapter(my_Adapter);

    }


    public ArrayList<String> getMateriaValues() {

        ArrayList<String> my_array = new ArrayList<String>();
        try {
            helper.abrir();
            Cursor allrows = helper.consulta_4();
            if (allrows.moveToFirst()) {
                do {

                  my_array.add(allrows.getString(0));

                } while (allrows.moveToNext());
            }
            allrows.close();
            helper.cerrar();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error encountered.",
                    Toast.LENGTH_LONG);
        }
        return my_array;
    }

    public void consulta4(View v) {

        helper.abrir();
        Materia2 materia2 = helper.consulta_3(spinner1.getSelectedItem().toString());
        helper.cerrar();
        if(materia2 == null)
            Toast.makeText(this, "Materia con codigo " + spinner1.getSelectedItem().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editNomMateria.setText(materia2.getNommateria());
            editTotal4.setText(String.valueOf(materia2.getCantidad_materias()));



        }
    }
    public void limpiarTexto(View v){

        editNomMateria.setText("");
        editTotal4.setText("");

    }


}
