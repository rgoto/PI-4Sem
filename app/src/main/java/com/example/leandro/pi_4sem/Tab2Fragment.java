package com.example.leandro.pi_4sem;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

/**
 * Created by Leandro on 21/10/2017.
 */

public class Tab2Fragment extends Fragment {
    private final Handler mHandler = new Handler();
    private Runnable mTimer2;
    private BarGraphSeries<DataPoint> mSeries2;
    private double graph2LastXValue = 5d;
    private TextView maior;
    private TextView menor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_grafico1, container, false);
        menor = rootView.findViewById(R.id.textMenor);
        maior = rootView.findViewById(R.id.textMaior);

        GraphView graph2 = rootView.findViewById(R.id.graph);
        mSeries2 = new BarGraphSeries<>();
        graph2.addSeries(mSeries2);
        graph2.getViewport().setXAxisBoundsManual(true);
        graph2.getViewport().setMinX(0);
        graph2.getViewport().setMaxX(40);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        mTimer2 = new Runnable() {
            @Override
            public void run() {
                graph2LastXValue += 1d;
                mSeries2.appendData(new DataPoint(graph2LastXValue, getRandom()), true, 40);
                maior.setText("R$ " + mSeries2.getHighestValueY());
                menor.setText("R$ " + mSeries2.getLowestValueY());
                mHandler.postDelayed(this, 1000);
            }
        };
        mHandler.postDelayed(mTimer2, 1500);
    }

    @Override
    public void onPause() {
        mHandler.removeCallbacks(mTimer2);
        super.onPause();
    }

    double mLastRandom = 2;
    Random mRand = new Random();
    private double getRandom() {
        return mLastRandom += mRand.nextDouble()*0.5 - 0.25;
    }
}
