package com.example.roshk1n.test_hubert_dreyfus.fragments;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.roshk1n.test_hubert_dreyfus.DB;
import com.example.roshk1n.test_hubert_dreyfus.MainActivity;
import com.example.roshk1n.test_hubert_dreyfus.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentNovice.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentNovice#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentNovice extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    public DB db;

    ListView listView;
    SimpleCursorAdapter simpleCursorAdapter;
    Cursor cursor;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentNovice() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentNovice.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentNovice newInstance(String param1, String param2) {
        FragmentNovice fragment = new FragmentNovice();
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

      /*  db = new DB();
        db.open();

        cursor= db.getAllData();

        startManagingCursor(cursor);

        String[] from = new String[]{DB.COLUMN_TXT,DB.COLUMN_IMG};
        int[] to ={R.id.tvText,R.id.ivImg};

        simpleCursorAdapter = new SimpleCursorAdapter(this,R.layout.itemadpter,cursor,from,to);


        lvSimple = (ListView) findViewById(R.id.listwithAdCur);
        lvSimple.setAdapter(simpleCursorAdapter);
        registerForContextMenu(lvSimple);*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_novice, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
