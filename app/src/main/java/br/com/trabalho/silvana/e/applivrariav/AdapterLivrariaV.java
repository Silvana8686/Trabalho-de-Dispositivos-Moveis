package br.com.trabalho.silvana.e.applivrariav;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterLivrariaV extends BaseAdapter {
    private List<LivrariaV> livroList;
    private Context context;
    private LayoutInflater inflater;

    public AdapterLivrariaV(Context context, List<LivrariaV> listalivro){
        this.livroList = listalivro;
        this.context = context;
        this.inflater = LayoutInflater.from( context );
    }

    @Override
    public int getCount() {
        return livroList.size();
    }

    @Override
    public Object getItem(int i) {
        return livroList.get( i );
    }

    @Override
    public long getItemId(int i) {
        return livroList.get(i).id;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ItemSuporte item;

        if( convertView == null){
            convertView = inflater.inflate(R.layout.layout_lista, null);

            item = new ItemSuporte();
            item.tvNome = convertView.findViewById(R.id.tvListaNome);
            item.tvAutor = convertView.findViewById(R.id.tvListaAutor);
            item.tvAno = convertView.findViewById(R.id.tvListaAno);
            item.layout = convertView.findViewById(R.id.llFundoLista);
            convertView.setTag( item );
        }else {
            item = (ItemSuporte) convertView.getTag();
        }

        LivrariaV livro = livroList.get(i);
        item.tvNome.setText(  livro.nome );
        item.tvAutor.setText(  livro.autor );
        item.tvAno.setText(  String.valueOf( livro.getAno() ) );

        if( i % 2 == 0 ){
            item.layout.setBackgroundColor(Color.rgb(230, 230, 230));
        }else {
            item.layout.setBackgroundColor( Color.WHITE );
        }
        return convertView;
    }

    private class ItemSuporte{
        TextView tvNome,tvAutor, tvAno;
        LinearLayout layout;
    }
}