package com.example.newtodoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private EditText itemEditText;
    private Button btn;
    private ListView itemsList;
    private Spinner listSpinner;
    private ArrayList<String> items;
    private ArrayAdapter<String> adapter;
    private ArrayAdapter<String> spinnerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemEditText = findViewById(R.id.item_edit_text);
        btn = findViewById(R.id.add_btn);
        itemsList = findViewById(R.id.items_list);
        items = FileHelper.readData(this);

        btn.setOnClickListener(this);
        itemsList.setOnItemClickListener(this);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        itemsList.setAdapter(adapter);
        listSpinner = findViewById(R.id.spinner1);
        spinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.listNames));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listSpinner.setAdapter(spinnerAdapter);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.add_btn:
                String itemEntered = itemEditText.getText().toString();
                adapter.add(itemEntered);
                itemEditText.setText("");


                FileHelper.writeData(items,this);
                Toast.makeText(this, "item added", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        items.remove(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
    }
}
