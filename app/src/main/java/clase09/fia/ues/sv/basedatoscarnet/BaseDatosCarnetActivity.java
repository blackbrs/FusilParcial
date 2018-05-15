package clase09.fia.ues.sv.basedatoscarnet;


import android.os.Bundle;

import android.app.ListActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
public class BaseDatosCarnetActivity extends ListActivity  {

    String[] menu={"Tabla Alumno","Tabla Nota","Tabla Materia","Consultas","LLenar Base de Datos"};
    String[] activities={"AlumnoMenuActivity","NotaMenuActivity","MateriaMenuActivity","ConsultasMenuActivity"};
    ControlBDCarnet BDhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, menu));
        BDhelper=new ControlBDCarnet(this);
    }

    @Override
    protected void onListItemClick(ListView l,View v,int position,long id){
        super.onListItemClick(l, v, position, id);
        if(position!=4){

            String nombreValue=activities[position];

            try{
                Class<?> clase=Class.forName("clase09.fia.ues.sv.basedatoscarnet."+nombreValue);

                Intent inte = new Intent(this,clase);
                this.startActivity(inte);
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }

        }else{

            BDhelper.abrir();
            String tost=BDhelper.llenarBDCarnet();
            BDhelper.cerrar();
            Toast.makeText(this, tost, Toast.LENGTH_SHORT).show();

        }
    }
}
