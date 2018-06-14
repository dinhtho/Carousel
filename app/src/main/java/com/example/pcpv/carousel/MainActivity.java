package com.example.pcpv.carousel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.pcpv.carousel.library.CarouselLayoutManager;
import com.example.pcpv.carousel.library.CarouselZoomPostLayoutListener;
import com.example.pcpv.carousel.library.CenterScrollListener;
import com.example.pcpv.carousel.library.DefaultChildSelectionListener;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private RecyclerView listHorizontal;
    private RecyclerView listVertical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TestAdapter adapter = new TestAdapter();
        listHorizontal = findViewById(R.id.list_horizontal);
        listVertical = findViewById(R.id.list_vertical);

        // create layout manager with needed params: vertical, cycle
        initRecyclerViewHorizontal(listHorizontal, new CarouselLayoutManager(CarouselLayoutManager.HORIZONTAL, false), new TestAdapter());
        initRecyclerViewVertical(listVertical, new CarouselLayoutManager(CarouselLayoutManager.VERTICAL, true), new TextAdapter());

        // fab button will add element to the end of the list
        findViewById(R.id.fab_scroll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
/*
                final int itemToRemove = adapter.mItemsCount;
                if (10 != itemToRemove) {
                    adapter.mItemsCount++;
                    adapter.notifyItemInserted(itemToRemove);
                }
*/
                listHorizontal.smoothScrollToPosition(adapter.getItemCount() - 2);
                listVertical.smoothScrollToPosition(adapter.getItemCount() - 2);
            }
        });

        // fab button will remove element from the end of the list
        findViewById(R.id.fab_change_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
/*
                final int itemToRemove = adapter.mItemsCount - 1;
                if (0 <= itemToRemove) {
                    adapter.mItemsCount--;
                    adapter.notifyItemRemoved(itemToRemove);
                }
*/
                listHorizontal.smoothScrollToPosition(1);
                listVertical.smoothScrollToPosition(1);
            }
        });
    }

    private void initRecyclerViewHorizontal(final RecyclerView recyclerView, final CarouselLayoutManager layoutManager, final TestAdapter adapter) {
        // enable zoom effect. this line can be customized
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        layoutManager.setMaxVisibleItems(2);

        recyclerView.setLayoutManager(layoutManager);
        // we expect only fixed sized item for now
        recyclerView.setHasFixedSize(true);
        // sample adapter with random data
        recyclerView.setAdapter(adapter);
        // enable center post scrolling
        recyclerView.addOnScrollListener(new CenterScrollListener());
        // enable center post touching on item and item click listener
        DefaultChildSelectionListener.initCenterItemListener(new DefaultChildSelectionListener.OnCenterItemClickListener() {
            @Override
            public void onCenterItemClicked(@NonNull final RecyclerView recyclerView, @NonNull final CarouselLayoutManager carouselLayoutManager, @NonNull final View v) {
                final int position = recyclerView.getChildLayoutPosition(v);
                final String msg = String.format(Locale.US, "Item %1$d was clicked", position);
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        }, recyclerView, layoutManager);

        layoutManager.addOnItemSelectionListener(new CarouselLayoutManager.OnCenterItemSelectionListener() {

            @Override
            public void onCenterItemChanged(final int adapterPosition) {
                if (CarouselLayoutManager.INVALID_POSITION != adapterPosition) {
                    final int value = adapter.getmPosition()[adapterPosition];
/*
                    adapter.mPosition[adapterPosition] = (value % 10) + (value / 10 + 1) * 10;
                    adapter.notifyItemChanged(adapterPosition);
*/
                }
            }
        });
    }

    private void initRecyclerViewVertical(final RecyclerView recyclerView, final CarouselLayoutManager layoutManager, final TextAdapter adapter) {
        // enable zoom effect. this line can be customized
        layoutManager.setPostLayoutListener(new CarouselZoomPostLayoutListener());
        layoutManager.setMaxVisibleItems(2);

        recyclerView.setLayoutManager(layoutManager);
        // we expect only fixed sized item for now
        recyclerView.setHasFixedSize(true);
        // sample adapter with random data
        recyclerView.setAdapter(adapter);
        // enable center post scrolling
        recyclerView.addOnScrollListener(new CenterScrollListener());
        // enable center post touching on item and item click listener
        DefaultChildSelectionListener.initCenterItemListener(new DefaultChildSelectionListener.OnCenterItemClickListener() {
            @Override
            public void onCenterItemClicked(@NonNull final RecyclerView recyclerView, @NonNull final CarouselLayoutManager carouselLayoutManager, @NonNull final View v) {
                final int position = recyclerView.getChildLayoutPosition(v);
                final String msg = String.format(Locale.US, "Item %1$d was clicked", position);
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        }, recyclerView, layoutManager);

        layoutManager.addOnItemSelectionListener(new CarouselLayoutManager.OnCenterItemSelectionListener() {

            @Override
            public void onCenterItemChanged(final int adapterPosition) {
                if (CarouselLayoutManager.INVALID_POSITION != adapterPosition) {
                    final int value = adapter.getmPosition()[adapterPosition];
/*
                    adapter.mPosition[adapterPosition] = (value % 10) + (value / 10 + 1) * 10;
                    adapter.notifyItemChanged(adapterPosition);
*/
                }
            }
        });
    }


}
