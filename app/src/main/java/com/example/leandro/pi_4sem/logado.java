package com.example.leandro.pi_4sem;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class logado extends AppCompatActivity {

    private static final String TAG = "Main";
    private SectionPageAdpter mSectionPageAdpter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logado);

        mSectionPageAdpter = new SectionPageAdpter(getSupportFragmentManager());
        ViewPager mViewPager = (ViewPager) findViewById(R.id.container);
        SetupViewPager(mViewPager);


        TabLayout tableLayout = (TabLayout) findViewById(R.id.tabs);
        tableLayout.setupWithViewPager(mViewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id ==  R.id.sairMenu) {
            finish();
            startActivity(new Intent(getApplicationContext(), login.class));
        }

        if (id == R.id.sobreMenu) {
            onPause();
            startActivity(new Intent(getApplicationContext(), SobreActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    private void SetupViewPager (ViewPager viewPager) {
        SectionPageAdpter adpter = new SectionPageAdpter(getSupportFragmentManager());
        adpter.addFragment(new Tab1Fragment(), "Grafico Linha");
        adpter.addFragment(new Tab2Fragment(), "Grafico Barra");
        viewPager.setAdapter(adpter);
    }
    
}
