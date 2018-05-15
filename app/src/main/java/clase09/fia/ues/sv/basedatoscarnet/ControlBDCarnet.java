package clase09.fia.ues.sv.basedatoscarnet;

/**
 * Created by ing. Cesar on 17/4/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ControlBDCarnet{

    private static final String[]camposAlumno = new String [] {"carnet","nombre","apellido","sexo", "matganadas"};
    private static final String[]camposNota = new String [] {"carnet","codmateria","ciclo","notafinal"};
    private static final String[]camposMateria = new String [] {"codmateria","nommateria","unidadesval"};
    private static final String[]campos_cons1 = new String [] {"count (carnet) "};
    private static final String[]campos_cons2 = new String [] {"alumno.carnet","nombre","apellido","sexo", "matganadas","sum(notafinal) total1","max(notafinal) total2","min(notafinal) total3 "};
    private static final String[]campos_cons3 = new String [] {"materia.codmateria","materia.nommateria","count(notafinal) total "};
    private static final String[]campos_cons4 = new String [] {"materia.codmateria "};

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;


    public ControlBDCarnet(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);

    }


    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String BASE_DATOS = "alumno1.s3db";
        private static final int VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL("CREATE TABLE alumno(carnet VARCHAR(7) NOT NULL PRIMARY KEY,nombre VARCHAR(30),apellido VARCHAR(30),sexo VARCHAR(1),matganadas INTEGER);");
                db.execSQL("CREATE TABLE materia(codmateria VARCHAR(6) NOT NULL PRIMARY KEY,nommateria VARCHAR(30),unidadesval VARCHAR(1));");
                db.execSQL("CREATE TABLE nota(carnet VARCHAR(7) NOT NULL ,codmateria VARCHAR(6) NOT NULL ,ciclo VARCHAR(5) ,notafinal REAL ,PRIMARY KEY(carnet,codmateria,ciclo));");
                db.execSQL("CREATE TRIGGER Fk1 " +
                        "BEFORE INSERT ON nota FOR EACH ROW " +
                        "BEGIN " +
                        "SELECT CASE WHEN ((SELECT carnet FROM alumno WHERE carnet = NEW.carnet) IS NULL) THEN RAISE(ABORT, 'No existe alumno') " +
                        "END; "+
                        "END;");

                db.execSQL("CREATE TRIGGER Fk2 " +
                        "BEFORE INSERT ON nota FOR EACH ROW " +
                        "BEGIN " +
                        "SELECT CASE WHEN ((SELECT codmateria FROM materia WHERE codmateria = NEW.codmateria) IS NULL) THEN RAISE(ABORT, 'No existe materia') " +
                        "END; "+
                        "END;");

                db.execSQL("CREATE TRIGGER nota1 " +
                        "AFTER UPDATE OF notafinal ON nota " +
                        "FOR EACH ROW WHEN new.notafinal>=6 AND old.notafinal<6 " +
                        "BEGIN " +
                        "UPDATE alumno SET matganadas=matganadas+1 " +
                        "WHERE alumno.carnet=new.carnet ; " +
                        "END; ");
                db.execSQL("CREATE TRIGGER nota2 " +
                        "AFTER UPDATE OF notafinal ON nota " +
                        "FOR EACH ROW WHEN new.notafinal<6 AND old.notafinal>=6 " +
                        "BEGIN " +
                        "UPDATE alumno SET matganadas=matganadas-1 WHERE alumno.carnet=new.carnet; " +
                        "END; ");






            }catch(SQLException e){

                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub

        }


    }

    public void abrir() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return;
    }

    public void cerrar(){
        DBHelper.close();
    }



    public String insertar(Alumno alumno){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;

        if (verificarIntegridad(alumno,5)) {
            regInsertados= "Error al Insertar el registro, Registro Duplicado(PK). Verificar inserción";
        }
        else
        {
            ContentValues alum = new ContentValues();
            alum.put("carnet", alumno.getCarnet());
            alum.put("nombre", alumno.getNombre());
            alum.put("apellido", alumno.getApellido());
            alum.put("sexo", alumno.getSexo());
            alum.put("matganadas", alumno.getMatganadas());
            contador=db.insert("alumno", null, alum);
            regInsertados=regInsertados+contador;

        }




        return regInsertados;
    }

    public String insertar(Nota nota){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;
        if(verificarIntegridad(nota,1))
        {
            ContentValues notas = new ContentValues();
            notas.put("carnet", nota.getCarnet());
            notas.put("codmateria", nota.getCodmateria());
            notas.put("ciclo", nota.getCiclo());
            notas.put("notafinal", nota.getNotafinal());
            contador=db.insert("nota", null, notas);
            if(contador==-1 || contador==0)
            {
                regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
            }
            else {
                regInsertados=regInsertados+contador;
            }
       }
        else
        {
            regInsertados= "Error al Insertar el registro, No Existe Alumno o Materia. Verificar ";
        }


        return regInsertados;

    }



    public String insertar(Materia materia){

        String regInsertados="Registro Insertado Nº= ";
        long contador=0;


        ContentValues mate = new ContentValues();
        mate.put("codmateria", materia.getCodmateria());
        mate.put("nommateria", materia.getNommateria());
        mate.put("unidadesval", materia.getUnidadesval());
        contador=db.insert("materia", null, mate);

        if(contador==-1 || contador==0)
        {
            regInsertados= "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            regInsertados=regInsertados+contador;
        }
        return regInsertados;
    }
    public String actualizar(Alumno alumno){

        if(verificarIntegridad(alumno, 5)){
            String[] id = {alumno.getCarnet()};
            ContentValues cv = new ContentValues();
            cv.put("nombre", alumno.getNombre());
            cv.put("apellido", alumno.getApellido());
            cv.put("sexo", alumno.getSexo());
            db.update("alumno", cv, "carnet = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con carnet " + alumno.getCarnet() + " no existe";
        }
    }



    public String actualizar(Materia materia){

        if(verificarIntegridad(materia, 6)){
            String[] id = {materia.getCodmateria()};
            ContentValues cv = new ContentValues();
            cv.put("nommateria", materia.getNommateria());
            cv.put("unidadesval", materia.getUnidadesval());
            db.update("materia", cv, "codmateria = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro con carnet " + materia.getCodmateria() + " no existe";
        }
    }

    public String actualizar(Nota nota){

        if(verificarIntegridad(nota, 2)){
            String[] id = {nota.getCodmateria(), nota.getCarnet(), nota.getCiclo()};
            ContentValues cv = new ContentValues();
            cv.put("notafinal", nota.getNotafinal());
            db.update("nota", cv, "codmateria = ? AND carnet = ? AND ciclo = ?", id);
            return "Registro Actualizado Correctamente";
        }else{
            return "Registro no Existe";
        }


    }


    public String eliminar(Alumno alumno){
        //Eliminacion en cascada
        String regAfectados="filas afectadas= ";
        int contador=0;
        if (verificarIntegridad(alumno,3)) {
            contador+=db.delete("nota", "carnet='"+alumno.getCarnet()+"'", null);
        }
        contador+=db.delete("alumno", "carnet='"+alumno.getCarnet()+"'", null);
        regAfectados+=contador;
        return regAfectados;
    }
    public String eliminar2(Alumno alumno){
        //Eliminacion Restringida
        String regAfectados="filas afectadas= ";
        int contador=0;
        if (verificarIntegridad(alumno,3)) {
            regAfectados="Error, existen registros asociados";
        }
        else
        {
            contador+=db.delete("alumno", "carnet='"+alumno.getCarnet()+"'", null);
            regAfectados+=contador;
        }


        return regAfectados;
    }


    public String eliminar(Nota nota){
        String regAfectados="filas afectadas= ";
        int contador=0;
        String where="carnet='"+nota.getCarnet()+"'";
        where=where+" AND codmateria='"+nota.getCodmateria()+"'";
        where=where+" AND ciclo="+nota.getCiclo();
        contador+=db.delete("nota", where, null);
        regAfectados+=contador;
        return regAfectados;
    }

    public String eliminar(Materia materia){
        String regAfectados="filas afectadas= ";
        int contador=0;
        if (verificarIntegridad(materia,4)) {
            contador+=db.delete("nota", "codmateria='"+materia.getCodmateria()+"'", null);
        }
        contador+=db.delete("alumno", "codmateria='"+materia.getCodmateria() + "'", null);
        regAfectados += contador;
        return regAfectados;
    }
    public Alumno consultarAlumno(String carnet){

        String[] id = {carnet};

        Cursor cursor = db.query("alumno", camposAlumno, "carnet = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Alumno alumno = new Alumno();
            alumno.setCarnet(cursor.getString(0));
            alumno.setNombre(cursor.getString(1));
            alumno.setApellido(cursor.getString(2));
            alumno.setSexo(cursor.getString(3));
            alumno.setMatganadas(cursor.getInt(4));
            return alumno;
        }else{
            return null;
        }
    }

    public Materia consultarMateria(String codmateria){


        String[] id = {codmateria};
        Cursor cursor = db.query("materia", camposMateria, "codmateria = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Materia materia = new Materia();
            materia.setCodmateria(cursor.getString(0));
            materia.setNommateria(cursor.getString(1));
            materia.setUnidadesval(cursor.getString(2));
            return materia;
        }else{
            return null;
        }
    }
    //Otras consultas
    //Consulta 1 :Asignaturas inscritas de un carnet digitado en una caja de texto
    public String consulta_1(String carnet){

        String[] id = {carnet};
        String tempo;
        Cursor cursor = db.query("nota", campos_cons1, "carnet = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            tempo=cursor.getString(0);
            return tempo;
        }else{
            return null;
        }
    }
    //Consulta 2 :Nota mayor, menor y cantidad de asignaturas de un alumno del cual se digitara su carnet
    public Alumno2 consulta_2(String carnet){

        String[] id = {carnet};
        Cursor cursor = db.query("alumno,nota", campos_cons2, "alumno.carnet=nota.carnet and nota.carnet = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Alumno2 alumno2 = new Alumno2();
            alumno2.setCarnet(cursor.getString(0));
            alumno2.setNombre(cursor.getString(1));
            alumno2.setApellido(cursor.getString(2));
            alumno2.setSexo(cursor.getString(3));
            alumno2.setMatganadas(cursor.getInt(4));
            alumno2.setTotal1(cursor.getInt(5));
            alumno2.setTotal2(cursor.getDouble(6));
            alumno2.setTotal3(cursor.getDouble(7));
            return alumno2;
        }else{
            return null;
        }
    }
    //Consulta 3 :Cantidad de alumnos que han cursado una asignatura leida desde una lista desplegable
    public Materia2 consulta_3(String codmateria){

        String[] id = {codmateria};
        Cursor cursor = db.query("nota,materia", campos_cons3, "materia.codmateria=nota.codmateria and nota.codmateria = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Materia2 materia2 = new Materia2();
            materia2.setCodmateria(cursor.getString(0));
            materia2.setNommateria(cursor.getString(1));
            materia2.setCantidad_materias(cursor.getDouble(2));


            return materia2;
        }else{
            return null;
        }
    }
    public Cursor consulta_4(){


        // cursor = this.db.query("materia", campos_cons4, null, null, null, null, null);
        Cursor    cursor1 = db.rawQuery("SELECT  * FROM materia ", null);


        return cursor1;

    }



    public Nota consultarNota(String carnet, String codmateria, String ciclo){

        String[] id = {carnet, codmateria, ciclo};
        Cursor cursor = db.query("nota", camposNota, "carnet = ? AND codmateria = ? AND ciclo = ?", id, null, null, null);
        if(cursor.moveToFirst()){
            Nota nota = new Nota();
            nota.setCarnet(cursor.getString(0));
            nota.setCodmateria(cursor.getString(1));
            nota.setCiclo(cursor.getString(2));
            nota.setNotafinal(cursor.getFloat(3));
            return nota;
        }else{
            return null;
        }
    }



    private boolean verificarIntegridad(Object dato, int relacion) throws SQLException{

        switch(relacion){

            case 1:
            {
                //verificar que al insertar nota exista carnet del alumno y el codigo de materia
                Nota nota = (Nota)dato;
                String[] id1 = {nota.getCarnet()};
                String[] id2 = {nota.getCodmateria()};
                //abrir();
                Cursor cursor1 = db.query("alumno", null, "carnet = ?", id1, null, null, null);
                Cursor cursor2 = db.query("materia", null, "codmateria = ?", id2, null, null, null);
                if(cursor1.moveToFirst() && cursor2.moveToFirst()){
                    //Se encontraron datos
                    return true;
                }
                return false;
            }

            case 2:
            {
                //verificar que al modificar nota exista carnet del alumno, el codigo de materia y el ciclo
                Nota nota1 = (Nota)dato;
                String[] ids = {nota1.getCarnet(), nota1.getCodmateria(), nota1.getCiclo()};
                abrir();
                Cursor c = db.query("nota", null, "carnet = ? AND codmateria = ? AND ciclo = ?", ids, null, null, null);
                if(c.moveToFirst()){
                    //Se encontraron datos
                    return true;
                }
                return false;
            }

            case 3:
            {
                Alumno alumno = (Alumno)dato;
                Cursor c=db.query(true, "nota", new String[] {
                        "carnet" }, "carnet='"+alumno.getCarnet()+"'",null, null, null, null, null);
                if(c.moveToFirst())
                    return true;
                else
                    return false;
            }

            case 4:
            {
                Materia materia = (Materia)dato;
                Cursor cmat=db.query(true, "nota", new String[] {
                        "codmateria" }, "codmateria='"+materia.getCodmateria()+"'",null, null, null, null, null);
                if(cmat.moveToFirst())
                    return true;
                else
                    return false;
            }

            case 5:
            {
                //verificar que exista alumno
                Alumno alumno2 = (Alumno)dato;
                String[] id = {alumno2.getCarnet()};
                abrir();
                Cursor c2 = db.query("alumno", null, "carnet = ?", id, null, null, null);
                if(c2.moveToFirst()){
                    //Se encontro Alumno
                    return true;
                }
                return false;
            }

            case 6:
            {
                //verificar que exista Materia
                Materia materia2 = (Materia)dato;
                String[] idm = {materia2.getCodmateria()};
                abrir();
                Cursor cm = db.query("materia", null, "codmateria = ?", idm, null, null, null);
                if(cm.moveToFirst()){
                    //Se encontro Materia
                    return true;
                }
                return false;
            }

            default:
                return false;

        }


    }

    public String llenarBDCarnet(){

        final String[] VAcarnet = {"OO12035","OF12044","GG11098","CC12021"};
        final String[] VAnombre = {"Carlos","Pedro","Sara","Gabriela"};
        final String[] VAapellido = {"Orantes","Ortiz","Gonzales","Coto"};
        final String[] VAsexo = {"M","M","F","F"};

        final String[] VMcodmateria = {"MAT115","PRN115","IEC115","TSI115"};
        final String[] VMnommateria = {"Matematica I","Programacion I","Ingenieria Economica","Teoria de Sistemas"};
        final String[] VMunidadesval = {"4","4","4","4"};

        final String[] VNcarnet = {"OO12035","OF12044","GG11098","CC12021","OO12035","GG11098","OF12044"};
        final String[] VNcodmateria = {"MAT115","PRN115","IEC115","TSI115","IEC115","MAT115","PRN115"};
        final String[] VNciclo = {"12015","12015","12015","12016","12016","12016","12016"};
        final double[] VNnotafinal = {7.1,5.3,8.5,7.1,6.9,10,7.3};
        abrir();
        db.execSQL("DELETE FROM alumno");
        db.execSQL("DELETE FROM materia");
        db.execSQL("DELETE FROM nota");

        Alumno alumno = new Alumno();
        for(int i=0;i<4;i++){
            alumno.setCarnet(VAcarnet[i]);
            alumno.setNombre(VAnombre[i]);
            alumno.setApellido(VAapellido[i]);
            alumno.setSexo(VAsexo[i]);
            alumno.setMatganadas(0);
            insertar(alumno);
        }

        Materia materia = new Materia();
        for(int i=0;i<4;i++){
            materia.setCodmateria(VMcodmateria[i]);
            materia.setNommateria(VMnommateria[i]);
            materia.setUnidadesval(VMunidadesval[i]);
            insertar(materia);
        }

        Nota nota = new Nota();
        for(int i=0;i<7;i++){
            nota.setCarnet(VNcarnet[i]);
            nota.setCodmateria(VNcodmateria[i]);
            nota.setCiclo(VNciclo[i]);
            nota.setNotafinal(VNnotafinal[i]);
            insertar(nota);
        }

        cerrar();
        return "Guardo Correctamente";
    }
}
