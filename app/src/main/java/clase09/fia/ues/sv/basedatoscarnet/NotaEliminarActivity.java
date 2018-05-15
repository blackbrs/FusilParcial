package clase09.fia.ues.sv.basedatoscarnet;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NotaEliminarActivity extends Activity {


    EditText editCarnet,editCodmateria,editCiclo;
    ControlBDCarnet controlhelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nota_eliminar);
        controlhelper=new ControlBDCarnet(this);
        editCarnet=(EditText)findViewById(R.id.editCarnet);
        editCodmateria=(EditText)findViewById(R.id.editCodmateria);
        editCiclo=(EditText)findViewById(R.id.editCiclo);
    }

    public void eliminarNota(View v){
        String regEliminadas;
        Nota nota=new Nota();
        nota.setCarnet(editCarnet.getText().toString());
        nota.setCodmateria(editCodmateria.getText().toString());
        nota.setCiclo(editCiclo.getText().toString());
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(nota);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
    public void limpiar(View v){
        editCarnet.setText("");
        editCodmateria.setText("");
        editCiclo.setText("");
    }
}
