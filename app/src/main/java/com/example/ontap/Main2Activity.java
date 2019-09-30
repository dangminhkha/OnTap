package com.example.ontap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    Button btnXem;
    EditText edtHienThi;

    Spinner spnTieuDe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnXem = (Button)findViewById(R.id.btn_Mo);
        List<String> list = new ArrayList<>();
        FileInputStream fnamein = null;
        try {
            fnamein = openFileInput("filename");
            BufferedReader br = new BufferedReader(new InputStreamReader(fnamein));

            String hienthi = null;
            while ((hienthi = br.readLine())!=null){
                if (!hienthi.isEmpty()&& hienthi!=null){
                    list.add(hienthi);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        spnTieuDe = (Spinner) findViewById(R.id.spn_TD);
        edtHienThi = (EditText)findViewById(R.id.edt_HienThi);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnTieuDe.setAdapter(adapter);

        btnXem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileInputStream fin = openFileInput(spnTieuDe.getSelectedItem().toString());
                    BufferedReader br = new BufferedReader(new InputStreamReader(fin));
                    StringBuffer s = new StringBuffer();
                    String hienthi = null;
                    while ((hienthi = br.readLine())!=null){
                        s.append(hienthi).append("\n");
                    }
                    edtHienThi.setText(s.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
