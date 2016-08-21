package com.example.beneson.idrugs;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Beneson on 06/06/2016.
 */
public class ProdutoAdapter extends FirebaseRecyclerAdapter<ProdutoAdapter.ViewHolder, Produto> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ProgressBar spinner;
        TextView nomeComercial;
        TextView laboratorio;
        TextView nomeQuimico;
        TextView categoria;
        ImageView icFormaFarmaceutiva;
        ImageView imgProduto;


        public ViewHolder(View view) {

            // Map a Chat object to an entry in our listview

            super(view);
            //spinner = (ProgressBar) view.findViewById(R.id.progress_bar_cell_product);
            //spinner.setVisibility(ProgressBar.VISIBLE);

            nomeComercial = (TextView) view.findViewById(R.id.nome_comercial);
            laboratorio = (TextView) view.findViewById(R.id.laboratorio);
            nomeQuimico = (TextView) view.findViewById(R.id.nome_quimico);
            categoria = (TextView) view.findViewById(R.id.categoria);
            //icFormaFarmaceutiva = (ImageView) view.findViewById(R.id.ic_forma_farmaceutica);
            //imgProduto = (ImageView) view.findViewById(R.id.img_produto);
        }

    }

    public ProdutoAdapter(String whereClause, Class<Produto> itemClass, @Nullable ArrayList<Produto> items,
                          @Nullable ArrayList<String> keys) {
        super(whereClause, itemClass, items, keys);
    }

    @Override
    public ProdutoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("tagx", "onCreateViewHolder");
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProdutoAdapter.ViewHolder holder, int position) {
        Log.i("tagx", "onBindViewHolder");

        Produto item = getItem(position);
        holder.nomeComercial.setText(item.getNomeComercial());
        holder.laboratorio.setText(item.getLaboratorio());
        holder.nomeQuimico.setText(item.getNomeQuimico());
        holder.categoria.setText(item.getCategoria());
        //holder.icFormaFarmaceutiva.setImageResource(R.mipmap.ic_tablet_48dp);


        /*FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReferenceFromUrl("gs://project-1453308271285243406.appspot.com");
        StorageReference produtoRef = storageRef.child("produtos/" + item.getRegistroMS() + ".jpg");

        final long ONE_MEGABYTE = 270 * 187;
        produtoRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Image img = new Image(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                holder.imgProduto.setImageBitmap(img.bitmap);
                holder.spinner.setVisibility(ProgressBar.GONE);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                holder.imgProduto.setImageResource(R.drawable.medicamento);
                holder.spinner.setVisibility(ProgressBar.GONE);
            }
        });*/

    }

    @Override
    protected void itemAdded(Produto item, String key, int position) {
        Log.d("ProdutoAdapter", "Added a new item to the adapter.");
    }

    @Override
    protected void itemChanged(Produto oldItem, Produto newItem, String key, int position) {
        Log.d("ProdutoAdapter", "Changed an item.");
    }

    @Override
    protected void itemRemoved(Produto item, String key, int position) {
        Log.d("ProdutoAdapter", "Removed an item from the adapter.");
    }

    @Override
    protected void itemMoved(Produto item, String key, int oldPosition, int newPosition) {
        Log.d("ProdutoAdapter", "Moved an item.");
    }

}