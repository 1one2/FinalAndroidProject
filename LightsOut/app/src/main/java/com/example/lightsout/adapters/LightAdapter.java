package com.example.lightsout.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lightsout.R;
import com.example.lightsout.interfaces.LightClickListener;
import com.example.lightsout.models.Board;
import com.example.lightsout.models.Light;

public class LightAdapter extends RecyclerView.Adapter<LightAdapter.LightViewHolder> {

    private Board board;
    private LightClickListener clickListener;

    public LightAdapter(Board pBoard,LightClickListener pClickListener)
    {
        this.board = pBoard;
        this.clickListener = pClickListener;
    }

    @NonNull
    @Override
    public LightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.light_cell,parent,false);
        LightViewHolder viewHolder = new LightViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LightViewHolder holder, int position) {

        Light light = board.getLights().get(position);
        holder.bind(light);
    }

    @Override
    public int getItemCount() {
        return board.getLights().size();
    }

    public class LightViewHolder extends RecyclerView.ViewHolder {

        private View view;
        private ImageView imageView;

        public LightViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            imageView = view.findViewById(R.id.cellLightHolder);
        }

        public void bind(Light light)
        {

            imageView.setImageResource(light.getImageSource());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onLightClicked(light);
                }
            });
        }
    }
}