package com.example.leandro.pi_4sem;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class logado extends AppCompatActivity {

    private static final String TAG = "Main";
    private SectionPageAdpter mSectionPageAdpter;
    private ViewPager mViewPager;
    private String nome;
    private int id;

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
        Tab1Fragment tab1 = new Tab1Fragment();
        Tab2Fragment tab2 = new Tab2Fragment();

        SectionPageAdpter adpter = new SectionPageAdpter(getSupportFragmentManager());
        adpter.addFragment(tab1, "Grafico Linha");
        adpter.addFragment(tab2, "Grafico Barra");
        viewPager.setAdapter(adpter);
        Bundle bundle = new Bundle();
        bundle.putString("nome", nome);
        bundle.putInt("id", id);

        tab1.getArguments(bundle);
        tab2.getArguments(bundle);

    }
    
}
