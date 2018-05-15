package clase09.fia.ues.sv.basedatoscarnet;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ConsultasMenuActivity extends ListActivity {

    String[] menu={"Consulta1","Consulta 2","Consulta 3", "Consulta 4*"};
    String[] activities={"Consulta1Activity","Consulta2Activity","Consulta3Activity","Consulta4Activity"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, menu));

        ListView listView = getListView();
        listView.setBackgroundColor(Color.rgb(64, 0, 128));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l,View v,int position,long id){
        super.onListItemClick(l, v, position, id);

        String nombreValue=activities[position];

        l.getChildAt(position).setBackgroundColor(Color.rgb(230, 128, 0));

        try{
            Class<?> clase=Class.forName("clase09.fia.ues.sv.basedatoscarnet."+nombreValue);
            Intent inte = new Intent(this,clase);
            this.startActivity(inte);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}

