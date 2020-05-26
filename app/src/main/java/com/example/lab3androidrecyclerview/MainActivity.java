package com.example.lab3androidrecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    List<Spend> spends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //добавление данных в список
        spends = new ArrayList<>();
        spends.add(new Spend("-200 UAH", "Products", "Bread, butter, sausages"));
        spends.add(new Spend("-100 UAH", "Internet/Phone", "Vodafone mobile phone"));
        spends.add(new Spend("-180 UAH", "Internet/Phone", "Kyivstar Home Internet"));
        spends.add(new Spend("-50 UAH", "Transport", "Taxi"));
        spends.add(new Spend("-400 UAH", "Products", "Milk, cucumbers, salt fish"));

        RecyclerView rview = findViewById(R.id.recyclerview);
        LinearLayoutManager mng = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rview.setLayoutManager(mng);
        rview.setAdapter(new RecyclerAdapter(spends));
    }

    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.SpendsViewHolder> {

        private List<Spend> spends;
        private int pos;

        //создание холдера
        @NonNull
        @Override
        public SpendsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
            SpendsViewHolder holder = new SpendsViewHolder(v);
            return holder;
        }

        public RecyclerAdapter(List<Spend> spends) {
            this.spends = spends;
        }

        //связывание данных и представления
        @Override
        public void onBindViewHolder(@NonNull SpendsViewHolder holder, final int position) {
            holder.spent.setText(spends.get(position).getSpent());
            holder.category.setText(spends.get(position).getCategory());


            //листенер на картинку
            holder.img.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View v) {
                                                  Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                                                  intent.putExtra("spent", spends.get(position).getSpent());
                                                  intent.putExtra("category", spends.get(position).getCategory());
                                                  intent.putExtra("comment", spends.get(position).getComment());
                                                  startActivity(intent);
                                              }
                                          }
            );
        }

        @Override
        public int getItemCount() {
            return spends.size();
        }

        //класс холдера
        public class SpendsViewHolder extends RecyclerView.ViewHolder {

            public ImageView img; //иконка
            public TextView spent; //расход
            public TextView category; //категория

            public SpendsViewHolder(@NonNull View itemView) {
                super(itemView);
                img = itemView.findViewById(R.id.imageView4);
                spent = itemView.findViewById(R.id.number);
                category = itemView.findViewById(R.id.text);
            }

        }
    }
}
