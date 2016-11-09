package com.mainli.suspension;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView titleView = (TextView) findViewById(R.id.suspension);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        final LinearLayoutManager layout = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layout);
        final List<User> list = new ArrayList<>(100);
        for (int i = 0; i < 500; i++) {
            list.add(new User("名字-" + i, "手机号-" + i));
        }
        final List<String> titles = new ArrayList<>(10);
        for (int i = 0; i < 50; i++) {
            titles.add("分组" + i);
        }
        titleView.setText(titles.get(0) + "0");
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = null;
                if (viewType == 0) {
                    view = getLayoutInflater().inflate(R.layout.item_header, parent, false);
                } else {
                    view = getLayoutInflater().inflate(R.layout.item, parent, false);
                }
                return new RecyclerView.ViewHolder(view) {
                };
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                TextView itemView = (TextView) holder.itemView;
                if (holder.getItemViewType() == 0) {
                    itemView.setText(titles.get(position / 11) + "0");
                } else {
                    position -= (position / 11 + 1);
                    itemView.setText(list.get(position).toString());
                }
            }

            @Override
            public int getItemCount() {
                return list.size() + titles.size();
            }

            @Override
            public int getItemViewType(int position) {
                return position % 11 == 0 && position / 11 < titles.size() ? 0 : 1;
            }
        });
        recyclerView.addOnScrollListener(new RVHeadSuspensionListener<TextView>(titleView, new RVHeadSuspensionListener.ResetViewDataListener<TextView>() {
            @Override
            public void onResetView(RecyclerView recyclerView, TextView textView, int itemPosition) {
                textView.setText(titles.get(itemPosition / 11) + "0");
            }
        }));
    }
}
