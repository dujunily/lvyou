package com.bishe.me;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.bishe.me.bean.Raider;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NoteAddActivity extends BaseActivity {

    private EditText etTitle, etContent;
    private Button bt;
    private TextView tvTime;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add);
        etContent = this.findViewById(R.id.et_content);
        etTitle = this.findViewById(R.id.et_title);
        tvTime = this.findViewById(R.id.tv_time);
       bt= this.findViewById(R.id.tv_save);
        String date = simpleDateFormat.format(new Date());
        tvTime.setText(date);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Raider notepad=new Raider();
                notepad.setContent(etContent.getText().toString());
                notepad.setName(etTitle.getText().toString());
                notepad.setTime(tvTime.getText().toString());

                if(notepad.save()){
                    finish();
                    Toast.makeText(NoteAddActivity.this,"保存成功",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(NoteAddActivity.this,"保存失败",Toast.LENGTH_LONG).show();
                }

            }
        });



    }

}
