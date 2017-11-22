package com.example.bustamante.unifitv2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyRoutinesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyRoutinesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyRoutinesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    List<String> rutinas;
    ListView lista_mis_rutinas;
    Intent intent;
    String nombre_rutina;
    MenuItem item;
    ImageView dropdown_routine;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    HashMap<String, List<String>> ejercicios;
    private static final int MENU_ITEM_ITEM1 = 0;

    public MyRoutinesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyRoutinesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyRoutinesFragment newInstance(String param1, String param2) {
        MyRoutinesFragment fragment = new MyRoutinesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View rootView = inflater.inflate(R.layout.fragment_my_routines, container, false);

        expListView = (ExpandableListView)rootView.findViewById(R.id.listViewRoutines);

        setListView(expListView);

        getActivity().setTitle("Mis rutinas");

        setHasOptionsMenu(true);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //inflater.inflate(R.menu.main,menu);
        super.onCreateOptionsMenu(menu,inflater);
        item = menu.add(Menu.NONE, MENU_ITEM_ITEM1, Menu.NONE, "Agregar");
        item.setIcon(android.R.drawable.ic_input_add);
        item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_IF_ROOM);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_ITEM_ITEM1:
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("Agregar nueva rutina");
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;

            default:
                return false;
        }
    }

    public boolean setListView(ExpandableListView expandablelist){

        rutinas = new ArrayList<String>();
        ejercicios = new HashMap<String, List<String>>();

        rutinas.add("Espalda y hombros");
        rutinas.add("Rutina martes");
        rutinas.add("Rutina miércoles");
        rutinas.add("Rutina jueves");
        rutinas.add("Rutina piernas y glúteos");

        List<String> espalda_hombros = new ArrayList<String>();
        espalda_hombros.add("Pull-ups");
        espalda_hombros.add("Chin-ups");
        espalda_hombros.add("Remo");
        espalda_hombros.add("Flexiones de brazo");

        List<String> rutina_martes = new ArrayList<String>();
        rutina_martes.add("Remo");

        List<String> rutina_miercoles = new ArrayList<String>();
        rutina_miercoles.add("Pull-ups");

        List<String> rutina_jueves = new ArrayList<String>();
        rutina_jueves.add("Chin-ups");

        List<String> piernas_gluteos = new ArrayList<String>();
        piernas_gluteos.add("Sentadillas");

        ejercicios.put(rutinas.get(0),espalda_hombros);
        ejercicios.put(rutinas.get(1),rutina_martes);
        ejercicios.put(rutinas.get(2),rutina_miercoles);
        ejercicios.put(rutinas.get(3),rutina_jueves);
        ejercicios.put(rutinas.get(4),piernas_gluteos);

        listAdapter = new ExpandableListAdapter(getActivity(),rutinas,ejercicios);
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
                convertView = infalInflater.inflate(R.layout.list_item, null);
            }

            TextView txtListChild = (TextView) convertView
                    .findViewById(R.id.exercise);

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
                convertView = infalInflater.inflate(R.layout.list_header, null);
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
                            intent = new Intent(getActivity(),EditRoutineActivity.class);
                            intent.putExtra("rutina",headerTitle);
                            startActivity(intent);
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
