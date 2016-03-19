package com.example.roshk1n.test_hubert_dreyfus;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roshk1n.test_hubert_dreyfus.fragments.FragmentAdvancedBeginner;
import com.example.roshk1n.test_hubert_dreyfus.fragments.FragmentCompetent;
import com.example.roshk1n.test_hubert_dreyfus.fragments.FragmentExpert;
import com.example.roshk1n.test_hubert_dreyfus.fragments.FragmentNovice;
import com.example.roshk1n.test_hubert_dreyfus.fragments.FragmentProficient;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Dialog dialog;
    public DB db;
    TextView tvAbout;
    User user;
    String username,pas;
    MenuItem menuItem;
    ListView listView;
    SimpleCursorAdapter simpleCursorAdapter;
    Cursor cursor;
    RadioButton rbMax,rbMid,rbMin;
    Intent intentforresult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        username=getIntent().getStringExtra("name");
        pas = getIntent().getStringExtra("pas");
        user = new User(username,pas);
       // user=db.getUser(user);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(listView.getChildCount()==0) {
            tvAbout = (TextView) findViewById(R.id.tvAbout);
            tvAbout.setText(R.string.tvAbout);
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String res="";

                if(toolbar.getTitle()=="Novice") {
                    user.setValnovice(0);
                    for (int i = 0; i < listView.getChildCount(); i++) {
                        rbMax = (RadioButton) listView.getChildAt(i).findViewById(R.id.rbMax);
                        rbMid = (RadioButton) listView.getChildAt(i).findViewById(R.id.rbMid);
                        rbMin = (RadioButton) listView.getChildAt(i).findViewById(R.id.rbMin);
                        if (rbMax.isChecked()) {
                            user.setValnovice(user.getValnovice() + 5);
                        }
                        if (rbMid.isChecked()) {
                            user.setValnovice(user.getValnovice() + 3);
                        }
                        if (rbMin.isChecked()) {
                            user.setValnovice(user.getValnovice() + 2);
                        }
                    }
                    res = String.valueOf(user.getValnovice());
                }
                if(toolbar.getTitle()=="Advanced beginner")
                {
                    user.setValadvanced_beginer(0);
                    for (int i = 0; i < listView.getChildCount(); i++) {
                        rbMax = (RadioButton) listView.getChildAt(i).findViewById(R.id.rbMax);
                        rbMid = (RadioButton) listView.getChildAt(i).findViewById(R.id.rbMid);
                        rbMin = (RadioButton) listView.getChildAt(i).findViewById(R.id.rbMin);
                        if (rbMax.isChecked()) {
                            user.setValadvanced_beginer(user.getValadvanced_beginer() + 5);
                        }
                        if (rbMid.isChecked()) {
                            user.setValadvanced_beginer(user.getValadvanced_beginer() + 3);
                        }
                        if (rbMin.isChecked()) {
                            user.setValadvanced_beginer(user.getValadvanced_beginer()  + 2);
                        }
                    }
                    res = String.valueOf(user.getValadvanced_beginer());

                }
                if(toolbar.getTitle()=="Competent")
                {
                    user.setValcompetent(0);
                    for (int i = 0; i < listView.getChildCount(); i++) {
                        rbMax = (RadioButton) listView.getChildAt(i).findViewById(R.id.rbMax);
                        rbMid = (RadioButton) listView.getChildAt(i).findViewById(R.id.rbMid);
                        rbMin = (RadioButton) listView.getChildAt(i).findViewById(R.id.rbMin);
                        if (rbMax.isChecked()) {
                            user.setValcompetent(user.getValcompetent() + 5);
                        }
                        if (rbMid.isChecked()) {
                            user.setValcompetent(user.getValcompetent() + 3);
                        }
                        if (rbMin.isChecked()) {
                            user.setValcompetent(user.getValcompetent() + 2);
                        }
                    }
                    res = String.valueOf(user.getValcompetent());
                }
                if(toolbar.getTitle()=="Proficient")
                {
                    user.setValproficient(0);
                    for (int i = 0; i < listView.getChildCount(); i++) {
                        rbMax = (RadioButton) listView.getChildAt(i).findViewById(R.id.rbMax);
                        rbMid = (RadioButton) listView.getChildAt(i).findViewById(R.id.rbMid);
                        rbMin = (RadioButton) listView.getChildAt(i).findViewById(R.id.rbMin);
                        if (rbMax.isChecked()) {
                            user.setValproficient(user.getValproficient() + 5);
                        }
                        if (rbMid.isChecked()) {
                            user.setValproficient(user.getValproficient() + 3);
                        }
                        if (rbMin.isChecked()) {
                            user.setValproficient(user.getValproficient()  + 2);
                        }
                    }
                    res = String.valueOf(user.getValproficient());
                }
                if(toolbar.getTitle()=="Expert")
                {
                    user.setValexpert(0);
                    for (int i = 0; i < listView.getChildCount(); i++) {
                        rbMax = (RadioButton) listView.getChildAt(i).findViewById(R.id.rbMax);
                        rbMid = (RadioButton) listView.getChildAt(i).findViewById(R.id.rbMid);
                        rbMin = (RadioButton) listView.getChildAt(i).findViewById(R.id.rbMin);
                        if (rbMax.isChecked()) {
                            user.setValexpert(user.getValexpert() + 5);
                        }
                        if (rbMid.isChecked()) {
                            user.setValexpert(user.getValexpert() + 3);
                        }
                        if (rbMin.isChecked()) {
                            user.setValexpert(user.getValexpert() + 2);
                        }
                    }
                    res = String.valueOf(user.getValexpert());
                }
                Snackbar.make(view, "Test Completed. Score:"+res, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                intentforresult = new Intent(MainActivity.this,ActivityDraw.class);
                intentforresult.putExtra("ScoreNovice",user.getValnovice());
                intentforresult.putExtra("ScoreAdva_Beg",user.getValadvanced_beginer());
                intentforresult.putExtra("ScoreCompetent",user.getValcompetent());
                intentforresult.putExtra("ScoreProficient",user.getValproficient());
                intentforresult.putExtra("ScoreExpert",user.getValexpert());
                intentforresult.putExtra("Username", user.getUsername());
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    protected Dialog onCreateDialog(int d)
    {
        if(d==1)
        {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("About");
            adb.setMessage("Testing of Huber Dreyfus\nCreator Roshka Oleh PI-34."+"\n"+"Version:1.0 \n 2016");
            adb.setPositiveButton("OK", null);
            dialog = adb.create();
        }
        return dialog;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        menuItem = menu.findItem(R.id.action_username);
        menuItem.setTitle(username);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id== R.id.action_logout)
        {
            startActivity(new Intent(this,LoginActivity.class));
        }
        if(id==R.id.action_showResult)
        {
            startActivity(intentforresult);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        db = new DB(this);
        db.open();

        if (id == R.id.nav_expert) {
            cursor = db.getAllData("Expert");
            toolbar.setTitle("Expert");
        } else if (id == R.id.nav_novice) {
            toolbar.setTitle("Novice");
            cursor = db.getAllData("Novice");
        } else if (id == R.id.nav_advanced_beginner) {
            cursor = db.getAllData("Advanced beginner");
            toolbar.setTitle("Advanced beginner");
        } else if (id == R.id.nav_competent) {
            cursor = db.getAllData("Competent");
            toolbar.setTitle("Competent");
        } else if (id == R.id.nav_proficient) {
            cursor = db.getAllData("Proficient");
            toolbar.setTitle("Proficient");
        } else if(id==R.id.nav_about){
            showDialog(1);
        } else if(id==R.id.nav_result)
        {

        }
        startManagingCursor(cursor);
        String[] from = new String[]{DB.COLUMN_TEXT,DB.COLUMN_FIRST_ANSWER,DB.COLUMN_SECOND_ANSWER,DB.COLUMN_THIRD_ANSWER};
        int[] to ={R.id.tvQue,R.id.rbMax,R.id.rbMid,R.id.rbMin};

        simpleCursorAdapter = new SimpleCursorAdapter(this, R.layout.itemlist, cursor, from, to);


        listView.setAdapter(simpleCursorAdapter);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        db.close();
        return true;
    }
}


