package app.melhoroftheworld.agenda.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import app.melhoroftheworld.agenda.R;
import app.melhoroftheworld.agenda.nota.Nota;
import app.melhoroftheworld.agenda.evento.Evento;
import app.melhoroftheworld.agenda.tarefa.Tarefa;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "agenda";
    private static final String TABLE_NOTA = "nota";
    private static final String TABLE_EVENTO = "evento";
    private static final String TABLE_TAREFA = "tarefa";

    private static final String CREATE_TABLE_NOTA = "CREATE TABLE " + TABLE_NOTA + " ("+
        "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
        "nome VARCHAR(100), " +
        "conteudo VARCHAR(500));";
    private static final String CREATE_TABLE_EVENTO = "CREATE TABLE " + TABLE_EVENTO + " ("+
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(100), " +
            "descricao VARCHAR(100), " +
            "data_evento VARCHAR(100), " +
            "hora VARCHAR(500));";
    private static final String CREATE_TABLE_TAREFA = "CREATE TABLE " + TABLE_TAREFA + " ("+
            "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "nome VARCHAR(100), " +
            "descricao VARCHAR(100), " +
            "periodo VARCHAR(500));";
    private static final String DROP_TABLE_NOTA = "DROP TABLE IF EXISTS " + TABLE_NOTA;
    private static final String DROP_TABLE_EVENTO = "DROP TABLE IF EXISTS " + TABLE_EVENTO;
    private static final String DROP_TABLE_TAREFA = "DROP TABLE IF EXISTS " + TABLE_TAREFA;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_NOTA);
        db.execSQL(CREATE_TABLE_EVENTO);
        db.execSQL(CREATE_TABLE_TAREFA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_NOTA);
        db.execSQL(DROP_TABLE_EVENTO);
        db.execSQL(DROP_TABLE_TAREFA);
        onCreate(db);
    }

    // Nota
    public long createNota (Nota n) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", n.getNome());
        cv.put("conteudo", n.getConteudo());
        long id = db.insert(TABLE_NOTA, null, cv);
        db.close();
        return id;
    }
    public long updateNota (Nota n) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", n.getNome());
        cv.put("conteudo", n.getConteudo());
        long id = db.update(TABLE_NOTA, cv,
                "_id = ?", new String[]{String.valueOf(n.getId())});
        db.close();
        return id;
    }

    public long deleteNota (Nota n) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABLE_NOTA, "_id = ?",
                new String[]{String.valueOf(n.getId())});
        db.close();
        return id;
    }

    public void getAllNota (Context context, ListView lv){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "conteudo"};
        Cursor data = db.query(TABLE_NOTA, columns, null, null,
                null, null, "_id");
        int[] to = {R.id.textViewIdListarNota, R.id.textViewNomeListarNota,
                R.id.textViewConteudoListarNota};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.nota_item_list_view, data, columns, to,0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }

    public Nota getByIdNota (int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "conteudo"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_NOTA, columns, "_id = ?", args,
                null, null, null);
        data.moveToFirst();
        Nota n = new Nota();
        n.setId(data.getInt(0));
        n.setNome(data.getString(1));
        n.setConteudo(data.getString(2));
        data.close();
        db.close();
        return n;
    }
    // Nota

    // Evento
    public long createEvento (Evento e) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", e.getNome());
        cv.put("descricao", e.getDescricao());
        cv.put("data_evento", e.getData_evento());
        cv.put("hora", e.getHora());
        long id = db.insert(TABLE_EVENTO, null, cv);
        db.close();
        return id;
    }
    public long updateEvento (Evento e) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", e.getNome());
        cv.put("descricao", e.getDescricao());
        cv.put("data_evento", e.getData_evento());
        cv.put("hora", e.getHora());
        long id = db.update(TABLE_EVENTO, cv,
                "_id = ?", new String[]{String.valueOf(e.getId())});
        db.close();
        return id;
    }

    public long deleteEvento (Evento e) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABLE_EVENTO, "_id = ?",
                new String[]{String.valueOf(e.getId())});
        db.close();
        return id;
    }

    public void getAllEvento (Context context, ListView lv) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "descricao", "data_evento", "hora"};
        Cursor data = db.query(TABLE_EVENTO, columns, null, null,
                null, null, "nome");
        int[] to = {R.id.textViewIdListarEvento, R.id.textViewNomeListarEvento,
                R.id.textViewDescricaoListarEvento, R.id.textViewDataListarEvento, R.id.textViewHoraListarEvento};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.evento_item_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }

    public Evento getByIdEvento (int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "descricao", "data_evento", "hora"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_EVENTO, columns, "_id = ?", args,
                null, null, null);
        data.moveToFirst();
        Evento e = new Evento();
        e.setId(data.getInt(0));
        e.setNome(data.getString(1));
        e.setDescricao(data.getString(2));
        e.setData_evento(data.getString(3));
        e.setHora(data.getString(4));
        data.close();
        db.close();
        return e;
    }
    // Evento Fim

    // Tarefa Inicio
    public long createTarefa(Tarefa t) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", t.getNome());
        cv.put("descricao", t.getDescricao());
        cv.put("periodo", t.getPeriodo());
        long id = db.insert(TABLE_TAREFA, null, cv);
        db.close();
        return id;
    }
    public long updateTarefa (Tarefa t) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nome", t.getNome());
        cv.put("descricao", t.getDescricao());
        cv.put("periodo", t.getPeriodo());
        long id = db.update(TABLE_TAREFA, cv,
                "_id = ?", new String[]{String.valueOf(t.getId())});
        db.close();
        return id;
    }

    public long deleteTarefa (Tarefa t) {
        SQLiteDatabase db = this.getWritableDatabase();
        long id = db.delete(TABLE_TAREFA, "_id = ?",
                new String[]{String.valueOf(t.getId())});
        db.close();
        return id;
    }

    public void getAllTarefa (Context context, ListView lv) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "descricao", "periodo"};
        Cursor data = db.query(TABLE_TAREFA, columns, null, null,
                null, null, "nome");
        int[] to = {R.id.textViewIdListarTarefa, R.id.textViewNomeListarTarefa,
                R.id.textViewDescricaoListarTarefa, R.id.textViewPeriodoListarTarefa};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.tarefa_item_list_view, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }

    public Tarefa getByIdTarefa(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"_id", "nome", "descricao", "periodo"};
        String[] args = {String.valueOf(id)};
        Cursor data = db.query(TABLE_TAREFA, columns, "_id = ?", args,
                null, null, null);
        data.moveToFirst();
        Tarefa t = new Tarefa();
        t.setId(data.getInt(0));
        t.setNome(data.getString(1));
        t.setDescricao(data.getString(2));
        t.setPeriodo(data.getString(3));
        data.close();
        db.close();
        return t;
    }
    // tarefa fim

}
