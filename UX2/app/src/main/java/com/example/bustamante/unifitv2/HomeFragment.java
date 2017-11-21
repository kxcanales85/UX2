package com.example.bustamante.unifitv2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.LineChartView;
import lecho.lib.hellocharts.view.PieChartView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView mTextMessage;
    private PieChartView pieChart;
    private PieChartData data;
    private boolean hasLabels = false;
    private boolean hasLabelsOutside = false;
    private boolean hasLabelForSelected = false;
    private double carbo = 25.9, proteinas = 20.1, grasas = 30.8, total = 0.0;


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private class ValueTouchListener implements PieChartOnValueSelectListener {

        @Override
        public void onValueSelected(int arcIndex, SliceValue value) {
            Toast.makeText(getActivity(), value.getValue() + " %", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onValueDeselected() {
        }
    }

    private void toggleLabels() {
        hasLabels = !hasLabels;
        if (hasLabels) {
            hasLabelForSelected = false;
            pieChart.setValueSelectionEnabled(hasLabelForSelected);
            if (hasLabelsOutside) {
                pieChart.setCircleFillRatio(0.7f);
            } else {
                pieChart.setCircleFillRatio(1.0f);
            }
        }
        generateData();
    }

    /*Reinicia los valores de las variables*/
    public void resetCounts() {
        carbo = 0;
        proteinas = 0;
        grasas = 0;
    }

    private void generateData() {
        int numValues = 3;	// Numero de particiones y/o variables
        List<SliceValue> values = new ArrayList<SliceValue>();
	    /*Definimos el tamano (mediante un valor porcentual referente a cierta variable) y el color que tendra*/
        if (carbo > 0) {
            SliceValue sliceValueBuenas = new SliceValue((float) ((float) carbo * 100 / total), getResources().getColor(R.color.colorCarbo));
            values.add(sliceValueBuenas);
        }
        if (proteinas > 0) {
            SliceValue sliceValueMalas = new SliceValue((float) ((float) proteinas * 100 / total), getResources().getColor(R.color.colorProteina));
            values.add(sliceValueMalas);
        }
        if (grasas > 0) {
            SliceValue sliceValueNulas = new SliceValue((float) ((float) grasas * 100 / total), getResources().getColor(R.color.colorGrasa));
            values.add(sliceValueNulas);
        }
        data = new PieChartData(values);
        data.setHasLabels(hasLabels); // Muesta el valor de la particion
        pieChart.setPieChartData(data);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Home");

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        pieChart = (PieChartView)rootView.findViewById(R.id.chart);
        carbo = 25.9;
        proteinas = 20.1;
        grasas = 30.8;
        total=carbo+proteinas+grasas;
        pieChart.setOnValueTouchListener(new ValueTouchListener());
        toggleLabels();
        generateData();

        return rootView;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
