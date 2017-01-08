package id.goldan.technobittest.Fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.goldan.technobittest.Database.DBHandler;
import id.goldan.technobittest.Model.Data;
import id.goldan.technobittest.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DataFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class DataFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    View rootView;
    ListView dataListView;
    List<Data> dataList = new ArrayList<>();
    DBHandler dbHandler;
    SwipeRefreshLayout swipeRefreshLayout;

    public DataFragment() {
        dbHandler = new DBHandler(getContext());
        Log.d("ononassa","w");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHandler = new DBHandler(getContext());
        dataList = dbHandler.getAllData();
        Log.d("ononcreate","w");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_data, container, false);
        Log.d("ononcreateview","w");
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("ononstart","w");
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
        Log.d("ononresume","w");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataListView = (ListView) rootView.findViewById(R.id.dataListView);
        View emptyView = rootView.findViewById(R.id.emptyView);
        dataListView.setEmptyView(emptyView);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        loadData();
    }

    public void loadData(){
        Log.d("ononload","w");
        dataList = dbHandler.getAllData();
        MyDataAdapter myDataAdapter = new MyDataAdapter(getContext(), dataList);
        dataListView.setAdapter(myDataAdapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

        Log.d("ononattach","w");

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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    class MyDataAdapter extends ArrayAdapter<Data>{

        List<Data> dataList;
        Context context;


        public MyDataAdapter(Context context, List<Data> objects) {
            super(context, 0, objects);
            dataList = objects;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Data data = dataList.get(position);
            if (convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_data, parent, false);
            }
            TextView textNama = (TextView) convertView.findViewById(R.id.textNama);
            TextView textAlamat = (TextView) convertView.findViewById(R.id.textAlamat);
            TextView textTanggalLahir = (TextView) convertView.findViewById(R.id.textTanggalLahir);
            ImageView imageKelamin = (ImageView) convertView.findViewById(R.id.imageKelamin);

            textNama.setText(data.getNama());
            textAlamat.setText(data.getAlamat());
            textTanggalLahir.setText(data.getTanggalLahir());
            if(data.getJenisKelamin().equalsIgnoreCase("wanita")){
                imageKelamin.setImageResource(R.drawable.female);
            }

            return convertView;
        }

        @Nullable
        @Override
        public Data getItem(int position) {
            return super.getItem(position);
        }
    }
}
