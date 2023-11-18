package br.ufrn.imd.mobile.imdmarket.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoProdutosManager extends SQLiteOpenHelper {

    public BancoProdutosManager(Context ctx, String nome, SQLiteDatabase.CursorFactory factory, int version) {
        super(ctx, nome, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase banco) {
        banco.execSQL(
                "CREATE TABLE IF NOT EXISTS produtos " +
                "(codigo INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, " +
                "descricao TEXT, " +
                "estoque INTEGER)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase banco, int i, int i1) {

    }
}
