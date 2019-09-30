package com.example.ontap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button btnXem,btnLuu,btnMoi;
    EditText edtTieuDe,edtNoiDung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnXem = (Button) findViewById(R.id.btn_Xem);
        btnLuu = (Button) findViewById(R.id.btn_Luu);
        btnMoi = (Button) findViewById(R.id.btn_Moi);

        edtTieuDe = (EditText) findViewById(R.id.edt_Nhap);
        edtNoiDung = (EditText) findViewById(R.id.edt_NoiDung);


        btnXem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tieude = edtTieuDe.getText().toString();
                String noidung = edtNoiDung.getText().toString();

                if (tieude!= null && !tieude.isEmpty() && noidung!=null && !noidung.isEmpty()){
                    List<String> strings = new ArrayList<>();
                    strings.add(tieude);
                    FileInputStream fnamein = null;
                    try {
                        fnamein = openFileInput("filename");
                        BufferedReader br = new BufferedReader(new InputStreamReader(fnamein));

                        String hienthi = null;
                        while ((hienthi = br.readLine())!=null){
                            if (!hienthi.equalsIgnoreCase(tieude)){
                                strings.add(hienthi);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        String luufilename = "";
                        for (int i=0;i<strings.size();i++){
                            luufilename = luufilename+"\n"+strings.get(i);
                        }

                        FileOutputStream fname = openFileOutput("filename",Context.MODE_PRIVATE);
                        fname.write(luufilename.getBytes());
                        fname.close();
                        FileOutputStream fout = openFileOutput(tieude, Context.MODE_PRIVATE);
                        fout.write(noidung.getBytes());
                        xoaTrang();
                        Toast.makeText(MainActivity.this,"Da luu file vao bo nho!!!",Toast.LENGTH_LONG).show();
                        fout.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(MainActivity.this,"Ten file va noi dung khong duoc bo trong",Toast.LENGTH_LONG).show();
                }

            }
        });
        btnMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               xoaTrang();
            }
        });
    }
    private void xoaTrang() {
        edtTieuDe.setText("");
        edtNoiDung.setText("");
    }
}
