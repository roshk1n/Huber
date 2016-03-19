package com.example.roshk1n.test_hubert_dreyfus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityDraw extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawView(this));
    }

    class DrawView extends View {
        Paint pRed;
        Paint pBlack;
        Paint pCoast;
        Rect rect;
        float heigth,width;
        Intent intent;
        int scoreNovice,scoreAdva_Beg,scoreCompetent,scoreProficient,scoreExpert;
        String username;
        public DrawView(Context contex) {
            super(contex);
            pRed = new Paint();
            pBlack = new Paint();
            pCoast = new Paint();
            rect = new Rect();
           intent = getIntent();
            scoreNovice = intent.getIntExtra("ScoreNovice", 0);
            scoreAdva_Beg = intent.getIntExtra("ScoreAdva_Beg",0);
            scoreCompetent = intent.getIntExtra("ScoreCompetent",0);
            scoreProficient = intent.getIntExtra("ScoreProficient",0);
            scoreExpert = intent.getIntExtra("ScoreExpert", 0);
            username = intent.getStringExtra("Username");

        }
        @Override
        protected void onDraw(Canvas canvas)
        {
            heigth = canvas.getHeight();
            width = canvas.getWidth();
            canvas.drawColor(Color.WHITE);

            pRed.setColor(Color.BLUE);
            pRed.setTextSize(100);
            pBlack.setColor(Color.BLACK);
            pBlack.setStrokeWidth(15);
            pCoast.setColor(Color.BLACK);
            pCoast.setStrokeWidth(5);
            pCoast.setTextSize(60);
            canvas.drawText("I`ts your result, " + username, width / 2 - 500, 150, pRed);//Ім’я користувача

            canvas.drawLine(120, 300, 120, heigth - 500, pBlack);
            canvas.drawLine(120, heigth-500,width-120,heigth-500, pBlack);
            float valuecoast = (heigth-500-300)/21;
            int val = 20;
            int count=6;
            for(float i=300+valuecoast;i<heigth-500;i+=valuecoast)//будуємо вісь Y + підписи
            {
                if(count>4)
                {
                    pCoast.setStrokeWidth(10);
                    canvas.drawLine(90, i, 150, i, pCoast);
                    canvas.drawText(String.valueOf(val), 40, i-10, pCoast);
                    count=0;
                    val= val-5;
                }
                count++;
                pCoast.setStrokeWidth(5);

                canvas.drawLine(100, i, 140, i, pCoast);
            }
            float valcoastX = (width-240)/6;
            for(float i=120+valcoastX;i<width-120;i+=valcoastX)
            {
                canvas.drawLine(i, heigth-480, i, heigth-520, pCoast);
            }

            canvas.drawText("N", 100+valcoastX, heigth - 430, pCoast);
            canvas.drawText("A_B", 80 + 2 * valcoastX, heigth - 430, pCoast);
            canvas.drawText("C", 100 + 3 * valcoastX, heigth - 430, pCoast);
            canvas.drawText("P", 100 + 4 * valcoastX, heigth - 430, pCoast);
            canvas.drawText("E", 100 + 5 * valcoastX, heigth - 430, pCoast);


            pCoast.setTextSize(50);
            canvas.drawText("N - Novice", 280, heigth - 300, pCoast);
            canvas.drawText("A_B - Advanced Beginner", 280, heigth - 230, pCoast);
            canvas.drawText("C - Competent", 280, heigth - 160, pCoast);
            canvas.drawText("P - Proficient", 900, heigth - 300, pCoast);
            canvas.drawText("E - Expert", 900, heigth - 230, pCoast);
            pCoast.setColor(Color.GREEN);
            pCoast.setStrokeWidth(10);
            canvas.drawLine(120, heigth - 500, 120 + valcoastX, heigth - 500 - scoreNovice * valuecoast,pCoast);
            canvas.drawLine(120 + valcoastX, heigth - 500 - scoreNovice * valuecoast, 120 + 2*valcoastX, heigth - 500 - scoreAdva_Beg * valuecoast,pCoast);
            canvas.drawLine(120 + 2*valcoastX, heigth - 500 - scoreAdva_Beg * valuecoast, 120 + 3*valcoastX, heigth - 500 - scoreCompetent * valuecoast,pCoast);
            canvas.drawLine(120 + 3*valcoastX, heigth - 500 - scoreCompetent * valuecoast, 120 + 4*valcoastX, heigth - 500 - scoreProficient * valuecoast,pCoast);
            canvas.drawLine(120 + 4*valcoastX, heigth - 500 - scoreProficient * valuecoast, 120 + 5*valcoastX, heigth - 500 - scoreExpert * valuecoast,pCoast);
            rect.set(200, 250, 350, 500);
        }

    }
}
