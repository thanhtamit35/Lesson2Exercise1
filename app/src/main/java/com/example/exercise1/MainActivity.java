package com.example.exercise1;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvList;
    Button btnAdd, btnEdit;
    ArrayAdapter adapter;
    List<String> data;
    EditText edtName;
    int currentPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
            - Các bước làm ListView:
            1. chuẩn bị 1 màn hình chứa ListView, khai báo và ánh xạ ListView
            2. Trong file Java khai báo Adapter là ArrayAdapter
            3. Tạo List<String> dữ liệu
            4. Khởi tạo Adapter với 3 đối số:
                1. context
                2. dòng dữ liệu có sẵn( android.R.layout.simple_list_item)
                3. List<String>
            5. Gán adapter cho ListView qua hàm setAdapter của ListView
        */

        mapping();

        addActions();
    }

    private void addActions() {
        lvList.setOnItemClickListener((adapterView, view, i, l) -> {
            edtName.setText(data.get(i));
            currentPos = i;
        });

        lvList.setOnItemLongClickListener((adapterView, view, i, l) -> {
//           Toast.makeText(this, "onItemLongClick" + data.get(i), Toast.LENGTH_SHORT).show();
            acceptDel(i);

            return false;
        });

        btnAdd.setOnClickListener(view -> {
            String name = edtName.getText().toString();
            data.add(name);
            adapter.notifyDataSetChanged();
        });

        btnEdit.setOnClickListener(view -> {
            String name = edtName.getText().toString();

            data.set(currentPos, name);
            adapter.notifyDataSetChanged();
        });
    }

    private void acceptDel(int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận xoá");
//        builder.setIcon(R.drawable.ic_baseline_warning_24);
        builder.setMessage("Bạn có thật sự muốn xoá " + data.get(i) + " không?");
        builder.setPositiveButton("Có", (dialogInterface, i1) -> {
            data.remove(i);
            adapter.notifyDataSetChanged();
        });

        builder.setNegativeButton("Không", (dialogInterface, i1) -> {

        });

        builder.show();
    }

    private void mapping() {
        btnAdd = findViewById(R.id.btn_add);
        btnEdit = findViewById(R.id.btn_edit);
        edtName = findViewById(R.id.edt_name);
        lvList = findViewById(R.id.lv_List);

        data = new ArrayList<>();
        data.add("Android");
        data.add("PHP");
        data.add("Node JS");
        data.add("Java");
        data.add("JavaScript");
        data.add("C#");

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        lvList.setAdapter(adapter);
    }
}