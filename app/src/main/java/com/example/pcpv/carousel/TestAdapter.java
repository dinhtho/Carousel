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

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestViewHolder> {

    @SuppressWarnings("UnsecureRandomNumberGeneration")
    private final Random mRandom = new Random();
    private final int[] mColors;
    private final int[] mPosition;
    private int mItemsCount = 100;

    public int[] getmPosition() {
        return mPosition;
    }

    TestAdapter() {
        mColors = new int[mItemsCount];
        mPosition = new int[mItemsCount];
        for (int i = 0; mItemsCount > i; ++i) {
            //noinspection MagicNumber
            mColors[i] = Color.argb(255, mRandom.nextInt(256), mRandom.nextInt(256), mRandom.nextInt(256));
            mPosition[i] = i;
        }
    }


    @Override
    public TestViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TestViewHolder holder, final int position) {
        Log.e("!!!!!!!!!", "onBindViewHolder: " + position);
        holder.tv1.setText(String.valueOf(mPosition[position]));
        holder.tv2.setText(String.valueOf(mPosition[position]));
        holder.itemView.setBackgroundColor(mColors[position]);
    }

    @Override
    public int getItemCount() {
        return mItemsCount;
    }

    @Override
    public long getItemId(final int position) {
        return position;
    }


    class TestViewHolder extends RecyclerView.ViewHolder {
        TextView tv1;
        TextView tv2;

        TestViewHolder(View view) {
            super(view);
            tv1 = view.findViewById(R.id.item_1);
            tv2 = view.findViewById(R.id.item_2);
        }
    }
}
