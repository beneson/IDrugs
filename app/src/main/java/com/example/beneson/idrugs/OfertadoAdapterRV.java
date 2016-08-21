package com.example.beneson.idrugs;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Beneson on 06/06/2016.
 */
public class OfertadoAdapterRV extends RecyclerView.Adapter<OfertadoAdapterRV.MyViewHolder> {
    private List<Ofertado> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;

    public OfertadoAdapterRV(Context c, List<Ofertado> l){
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Log.i("logx", "onCreateViewHolder()");
        View v = mLayoutInflater.inflate(R.layout.cell_product, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        Log.i("logx", "onBindViewHolder()");
        //myViewHolder.ivOfertado.setImageResource( mList.get(position).getPhoto() );
        myViewHolder.nomeComercialCelula.setText(mList.get(position).getProduto().getNomeComercial());
        myViewHolder.laboratorioCelula.setText(mList.get(position).getProduto().getLaboratorio());
        myViewHolder.nomeQuimicoCelula.setText(mList.get(position).getProduto().getNomeQuimico());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setmRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r){
        mRecyclerViewOnClickListenerHack = r;
    }

    public void addListItem(Ofertado ofertado, int position){
        mList.add(ofertado);
        notifyItemInserted(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView ivOfertado;
        public TextView nomeComercialCelula;
        public TextView laboratorioCelula;
        public TextView nomeQuimicoCelula;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivOfertado = (ImageView) itemView.findViewById(R.id.ivOfertado);
            nomeComercialCelula = (TextView) itemView.findViewById(R.id.nome_comercial);
            laboratorioCelula = (TextView) itemView.findViewById(R.id.laboratorio);
            nomeQuimicoCelula = (TextView) itemView.findViewById(R.id.nome_quimico);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(mRecyclerViewOnClickListenerHack != null){
                mRecyclerViewOnClickListenerHack.onClickListener(v, getPosition());
            }
        }
    }
}
