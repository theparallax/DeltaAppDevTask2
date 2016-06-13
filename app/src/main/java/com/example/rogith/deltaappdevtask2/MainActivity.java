package com.example.rogith.deltaappdevtask2;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.Shape;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView SpeechTextView;

    private int selectedShape = myViewComponent.Square;
    Context mContext;
    myViewComponent viewComponent;

    int X =0;
    int Y=0;
    int height =100;
    int width =100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SpeechTextView = (TextView) findViewById(R.id.SpeechView);
//        ShapeDrawable rectangle= new ShapeDrawable();
//        rectangle.setShape(new RectShape());
//        rectangle.setIntrinsicHeight(100);
//        	rectangle.setIntrinsicWidth(200);
//        	rectangle.getPaint().setColor(Color.GREEN);

        mContext =this;

        viewComponent=(myViewComponent) findViewById(R.id.CanvasView);
        viewComponent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                X =viewComponent.getWidth()/2;
                Y = viewComponent.getHeight()/2;
                viewComponent.DrawShape(mContext,selectedShape,X,Y,height,width,true);
            }
        });
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

//    public void OnUpBtnClick(View v){
//        Y-=10;
//        Boundary();
//        viewComponent.DrawShape(mContext,selectedShape,X,Y,height,width,true);
//
//    }
//    public void OnDownBtnClick(View v){
//        Y+=10;
//        Boundary();
//        viewComponent.DrawShape(mContext,selectedShape,X,Y,height,width,true);
//
//    }
//    public void OnLeftBtnClick(View v){
//        X-=10;
//        Boundary();
//        viewComponent.DrawShape(mContext,selectedShape,X,Y,height,width,true);
//
//    }
//    public void OnRightBtnClick(View v){
//        Y-=10;
//        Boundary();
//        viewComponent.DrawShape(mContext,selectedShape,X,Y,height,width,true);
//
//    }

}