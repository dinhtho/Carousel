package com.example.pcpv.carousel;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

public class TextAdapter extends RecyclerView.Adapter<TextHolder> {
    @SuppressWarnings("UnsecureRandomNumberGeneration")
    private final Random mRandom = new Random();
    private final int[] mColors;
    private final int[] mPosition;
    private int mItemsCount = 100;

    TextAdapter() {
        mColors = new int[mItemsCount];
        mPosition = new int[mItemsCount];
        for (int i = 0; mItemsCount > i; ++i) {
            //noinspection MagicNumber
            mColors[i] = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
            mPosition[i] = i;
        }
    }

    public int[] getmPosition() {
        return mPosition;
    }

    @Override
    public TextHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.text_simple_view, parent, false);
        return new TextHolder(view);
    }

    @Override
    public void onBindViewHolder(TextHolder holder, final int position) {
        Log.e("!!!!!!!!!", "onBindViewHolder: " + position);
        holder.tvNumber.setText(String.valueOf(mPosition[position]));
        holder.tvNumber.setTextColor(mColors[position]);
    }

    @Override
    public int getItemCount() {
        return mItemsCount;
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }
}

class TextHolder extends RecyclerView.ViewHolder {
    TextView tvNumber;

    TextHolder(View view) {
        super(view);
        tvNumber = view.findViewById(R.id.tv_number);
    }

}
