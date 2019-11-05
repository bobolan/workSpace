package com.example.roomwordsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewWordActivity extends AppCompatActivity {
    private static String TAG = "NewWordActivity";
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    private EditText mEditWordView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        mEditWordView = findViewById(R.id.edit_word);
        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(mEditWordView.getText())) {
                    Log.d(TAG, "输入的文本为空！");
                    setResult(RESULT_CANCELED, replyIntent);
                } else{
                    String word = mEditWordView.getText().toString();
                    Log.d(TAG, "输入的文本为：" + word);
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
