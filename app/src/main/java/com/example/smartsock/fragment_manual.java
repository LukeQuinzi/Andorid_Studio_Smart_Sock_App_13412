package com.example.smartsock;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.smartsock.ui.login.LoginActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_manual#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_manual extends Fragment implements AdapterView.OnItemClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_manual() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_manual.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_manual newInstance(String param1, String param2) {
        fragment_manual fragment = new fragment_manual();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    ListView list_items;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_manual, container, false);

        list_items = view.findViewById(R.id.list_item_1);
        String[] manuals = {"APP User Manual", "Smart Sock Device User Manual"};

        list_items.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, manuals));

        list_items.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        if (item == "APP User Manual"){
            Intent intent = new Intent();
            intent.setClass(getActivity(), SettingsActivity.class);
            getActivity().startActivity(intent);
            Toast.makeText(getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
        }
        else if (item == "Smart Sock Device User Manual"){
            Intent intent = new Intent();
            intent.setClass(getActivity(), MainActivity.class);
            getActivity().startActivity(intent);
            Toast.makeText(getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

        }
        else {
            Toast.makeText(getContext(), "Please Select a manual", Toast.LENGTH_LONG).show();
        }

    }
}