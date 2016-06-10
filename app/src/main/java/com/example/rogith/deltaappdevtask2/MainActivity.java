package com.example.rogith.deltaappdevtask2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView SpeechTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SpeechTextView = (TextView) findViewById(R.id.SpeechView);

    }
    public void onSpeechBtnClick(View v){

        if(v.getId() == R.id.SpeechBtn){
            promptSpeechInput();
        }
    }
    public void promptSpeechInput(){
        Intent newIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        newIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        newIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        newIntent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say Something");
        try {
            startActivityForResult(newIntent, 100);
        }
        catch(ActivityNotFoundException a)
        {
            Toast.makeText(MainActivity.this,"Sorry Your Device doesn't support speech Language",Toast.LENGTH_SHORT).show();
        }
    }

    public void onActivityResult(int request_code,int result_code,Intent newIntent)
    {
        super.onActivityResult(request_code,result_code,newIntent);
        switch(request_code)
        {
            case 100:   if(result_code==RESULT_OK&& newIntent!=null){
            ArrayList<String> result = newIntent.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                SpeechTextView.setText(result.get(0));
                Toast.makeText(MainActivity.this,"Finished!",Toast.LENGTH_SHORT).show();
            }
                break;
        }
    }

}