package com.example.bustamante.unifitv2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bustamante.unifitv2.Models.Alimento;
import com.hadiidbouk.charts.BarData;
import com.hadiidbouk.charts.ChartProgressBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    String mParam1;
    String mParam2;
    TextView desayuno;
    private Alimento alimento;
    ImageView dropdown_routine;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    HashMap<String, List<String>> comidas;
    List<String> comidasPrincipales;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                alimento = (Alimento) data.getSerializableExtra("result");
                System.out.println(alimento.getNombre());
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//onActivityResult

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
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        ArrayList<BarData> dataList = new ArrayList<>();

        BarData data = new BarData("Total", 2f, "2k");
        dataList.add(data);

        data = new BarData("Carb", 1.8f, "1.8k");
        dataList.add(data);

        data = new BarData("Prot", 1.3f, "1.3k");
        dataList.add(data);

        data = new BarData("Grasas", 2.2f, "2.2k");
        dataList.add(data);

        ChartProgressBar mChart = (ChartProgressBar) v.findViewById(R.id.ChartProgressBar);

        expListView = (ExpandableListView)v.findViewById(R.id.comidas_principales);

        setListView(expListView);

        mChart.setDataList(dataList);
        mChart.build();
        //desayuno = (TextView) v.findViewById(R.id.desayuno);
        /*desayuno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddAlimentActivity.class);
                intent.putExtra("myString", "Desayuno");
                startActivityForResult(intent, 1);
                //startActivity(intent);
            }
        });*/

        return v;
    }

    public boolean setListView(ExpandableListView expandablelist){

        comidasPrincipales = new ArrayList<String>();
        comidas = new HashMap<String, List<String>>();
        Resources res = getResources();
        comidasPrincipales.add(res.getString(R.string.desayuno));
        comidasPrincipales.add(res.getString(R.string.almuerzo));
        comidasPrincipales.add(res.getString(R.string.cena));
        comidasPrincipales.add(res.getString(R.string.otros));

        List<String> comida1 = new ArrayList<String>();

        List<String> comida2 = new ArrayList<String>();

        List<String> comida3 = new ArrayList<String>();

        List<String> comida4 = new ArrayList<String>();



        comidas.put(comidasPrincipales.get(0),comida1);
        comidas.put(comidasPrincipales.get(1),comida2);
        comidas.put(comidasPrincipales.get(2),comida3);
        comidas.put(comidasPrincipales.get(3),comida4);

        listAdapter = new ExpandableListAdapter(getActivity(),comidasPrincipales,comidas);
        expandablelist.setAdapter(listAdapter);

        return true;
    }

    class ExpandableListAdapter extends BaseExpandableListAdapter {

        private Context _context;
        private List<String> _listDataHeader; // header titles
        // child data in format of header title, child title
        private HashMap<String, List<String>> _listDataChild;

        public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                     HashMap<String, List<String>> listChildData) {
            this._context = context;
            this._listDataHeader = listDataHeader;
            this._listDataChild = listChildData;
        }

        @Override
        public Object getChild(int groupPosition, int childPosititon) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .get(childPosititon);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {

            final String childText = (String) getChild(groupPosition, childPosition);

            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.food_list_item, null);
            }

            TextView txtListChild = (TextView) convertView
                    .findViewById(R.id.food);

            txtListChild.setText(childText);
            return convertView;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                    .size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return this._listDataHeader.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return this._listDataHeader.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(final int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            final String headerTitle = (String) getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater infalInflater = (LayoutInflater) this._context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = infalInflater.inflate(R.layout.food_list_header, null);
            }

            TextView lblListHeader = (TextView) convertView
                    .findViewById(R.id.myRoutine);
            lblListHeader.setTypeface(null, Typeface.BOLD);
            lblListHeader.setText(headerTitle);

            dropdown_routine = (ImageView)convertView.findViewById(R.id.editRoutine);
            dropdown_routine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch(groupPosition){
                        case 0:
                            /*intent = new Intent(getActivity(),MainActivity.class);
                            intent.putExtra("rutina",headerTitle);
                            startActivity(intent);*/
                    }
                }
            });

            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
