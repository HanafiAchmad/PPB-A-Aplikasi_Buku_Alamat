package com.tugasppb.bukualamat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



/**
 * Created by Herdi_WORK on 08.08.17.
 */

public class TambahAlamat extends AppCompatActivity {

    // variable yang merefers ke Firebase Realtime Database
    private DatabaseReference database;

    // variable fields EditText dan Button
    private Button btnAdd;
    private EditText etNama;
    private EditText etAlamat;
    private EditText etNo_hp;
    private EditText etEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_alamat);

        Button btn1=(Button)findViewById(R.id.button8);

        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent inte = new Intent(TambahAlamat.this,MainActivity.class);
                startActivity(inte);
            }
        });
        // inisialisasi fields EditText dan Button
        etNama = (EditText) findViewById(R.id.editText1);
        etAlamat = (EditText) findViewById(R.id.editText2);
        etNo_hp = (EditText) findViewById(R.id.editText3);
        etEmail = (EditText) findViewById(R.id.editText4);
        btnAdd = (Button) findViewById(R.id.button1);

        // mengambil referensi ke Firebase Database
        database = FirebaseDatabase.getInstance().getReference();

        // kode yang dipanggil ketika tombol Submit diklik
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isEmpty(etNama.getText().toString()) && !isEmpty(etAlamat.getText().toString()) && !isEmpty(etNo_hp.getText().toString()) && !isEmpty(etEmail.getText().toString()))
                    submitAlamat(new Alamat(etNama.getText().toString(), etAlamat.getText().toString(), etNo_hp.getText().toString(), etEmail.getText().toString()));
                else
                    Snackbar.make(findViewById(R.id.button1), "Data barang tidak boleh kosong", Snackbar.LENGTH_LONG).show();

                InputMethodManager imm = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(
                        etNama.getWindowToken(), 0);
            }
        });

    }

    private boolean isEmpty(String s) {
        // Cek apakah ada fields yang kosong, sebelum disubmit
        return TextUtils.isEmpty(s);
    }


    private void submitAlamat(Alamat alamat) {
        /**
         * Ini adalah kode yang digunakan untuk mengirimkan data ke Firebase Realtime Database
         * dan juga kita set onSuccessListener yang berisi kode yang akan dijalankan
         * ketika data berhasil ditambahkan
         */
        database.child("alamat").push().setValue(alamat).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                etNama.setText("");
                etAlamat.setText("");
                etNo_hp.setText("");
                etEmail.setText("");
                Snackbar.make(findViewById(R.id.button1), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public static Intent getActIntent(Activity activity) {
        // kode untuk pengambilan Intent
        return new Intent(activity, TambahAlamat.class);
    }
}