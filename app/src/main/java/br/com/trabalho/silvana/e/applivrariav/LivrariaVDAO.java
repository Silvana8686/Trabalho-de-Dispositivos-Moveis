package br.com.trabalho.silvana.e.applivrariav;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.style.IconMarginSpan;

import java.util.ArrayList;
import java.util.List;

public class LivrariaVDAO {

    public static void inserir(LivrariaV livrariaV, Context context){

        ContentValues valores = new ContentValues();
        valores.put("nome", livrariaV.nome);
        valores.put("autor", livrariaV.autor);
        valores.put("ano", livrariaV.getAno());

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();

        db.insert("livrariaV", null, valores);


    }

    public static void editar(LivrariaV livrariaV, Context context){

        ContentValues valores = new ContentValues();
        valores.put("nome", livrariaV.nome);
        valores.put("autor", livrariaV.autor);
        valores.put("ano", livrariaV.getAno());

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.update("livrariaV", valores, "id = " + livrariaV.id, null );

    }

    public static void excluir(int id, Context context){

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("livrariaV", "id = " + id , null);

    }

    public static List<LivrariaV> getLivrariaV( Context context){

        List<LivrariaV> lista = new ArrayList<>();

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM livrariaV ORDER BY nome", null);

        if (cursor.getCount() > 0 ){
            cursor.moveToFirst();

            do{
                LivrariaV livrariaV = new LivrariaV();
                livrariaV.id = cursor.getInt(0);
                livrariaV.nome = cursor.getString(1);
                livrariaV.autor = cursor.getString(2);
                livrariaV.setAno(cursor.getInt(3));

                lista.add(livrariaV);

            }while(cursor.moveToNext());
        }


        return lista;
    }

    public static LivrariaV getLivrariaVBiId( Context context, int id){

        Banco banco = new Banco(context);
        SQLiteDatabase db = banco.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM livrariaV WHERE id = "+ id, null);

        if (cursor.getCount() > 0 ){
            cursor.moveToFirst();

            LivrariaV livrariaV = new LivrariaV();
            livrariaV.id = cursor.getInt(0);
            livrariaV.nome = cursor.getString(1);
            livrariaV.autor = cursor.getString(2);
            livrariaV.setAno(cursor.getInt(3));

            return livrariaV;
        }else return  null;

    }

}