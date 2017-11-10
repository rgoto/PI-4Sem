package com.example.leandro.pi_4sem;


import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by Leandro on 21/10/2017.
 */

public class Tab1Fragment extends Fragment {
    private final Handler mHandler = new Handler();
    private Runnable mTimer2;
    private LineGraphSeries<DataPoint> mSeries2;
    private double graph2LastXValue = 5d;
    private TextView maior;
    private TextView menor;
    private GraphView graph2;
    private Switch sw;
    private TextView ligado;
    private View rootView;
    private int success;
    private String nome;
    private int id;
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_grafico1, container, false);
        menor = rootView.findViewById(R.id.textMenor);
        maior = rootView.findViewById(R.id.textMaior);
        button = rootView.findViewById(R.id.button2);

        graph2 = rootView.findViewById(R.id.graph);
        mSeries2 = new LineGraphSeries<>();
        graph2.addSeries(mSeries2);
        graph2.getViewport().setXAxisBoundsManual(true);
        graph2.getViewport().setMinX(0);
        graph2.getViewport().setMaxX(40);

        graph2.getViewport().setYAxisBoundsManual(true);
        graph2.getViewport().setMinY(0);
        graph2.getViewport().setMaxY(mSeries2.getHighestValueY()+1);

        NumberFormat nf = NumberFormat.getInstance();
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        nf.setMinimumIntegerDigits(1);
        nf.setMaximumIntegerDigits(2);

        graph2.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter(nf, nf));
        graph2.getGridLabelRenderer().setNumVerticalLabels(5);

        graph2.getGridLabelRenderer().setHumanRounding(false);

        graph2.getViewport().setScrollableY(true);

        graph2.getGridLabelRenderer().setGridColor(Color.BLACK);
        graph2.getGridLabelRenderer().setHorizontalLabelsColor(Color.BLACK);
        graph2.getGridLabelRenderer().setVerticalLabelsColor(Color.BLACK);

        graph2.getViewport().setBackgroundColor(Color.argb(255, 222, 222, 222));
        graph2.getViewport().setDrawBorder(true);
        graph2.getViewport().setBorderColor(Color.BLUE);

        mSeries2.setColor(Color.RED);

        sw = rootView.findViewById(R.id.switch5);
        ligado = rootView.findViewById(R.id.textLigado);

        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ligado.getText().equals("ligada")) {
                    sw.setChecked(false);
                    ligado.setText("desligada");
                } else {
                    sw.setChecked(true);
                    ligado.setText("ligada");
                }
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDadosID(id);

//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable() {
//
//                    @Override
//                    public void run() {
//
//                    }
//                },500);

            }
        });

        return rootView;
    }


    public void getArguments(Bundle bundle) {
        nome = bundle.getString("nome");
        id = bundle.getInt("id");
    }

    public void getDadosID(int id) {
        RequestQueue queue = Volley.newRequestQueue(rootView.getContext());
        String url = "http://pi4sem.rbbr.com.br/teste/getDadosUSER.php?id=1";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getInt("success") == 1) {
                                success = 1;
                                JSONArray object = response.getJSONArray("user");

                                for (int i = 0; i < object.length(); i++) {
                                    JSONObject c = object.getJSONObject(i);

                                    Double gasto = c.getDouble("gasto");

                                    mSeries2.appendData(new DataPoint(i, gasto), true, 40);
                                }

                            } else {
                                Toast.makeText(rootView.getContext(),
                                        response.getString("message").trim(),Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                    }
                });

        // Access the RequestQueue through your singleton class.
        queue.add(jsObjRequest);

    }
}
