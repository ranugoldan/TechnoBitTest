package id.goldan.technobittest.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import id.goldan.technobittest.Database.DBHandler;
import id.goldan.technobittest.MainActivity;
import id.goldan.technobittest.Model.Data;
import id.goldan.technobittest.Model.Response;
import id.goldan.technobittest.Model.RestClient.RestClient;
import id.goldan.technobittest.R;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FormFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FormFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    View rootView;
    Calendar myCalendar;
    EditText editNama, editAlamat, editTanggal;
    RadioGroup radioKelamin;
    RadioButton radioKelaminSelected, radioPria, radioWanita;
    Button buttonRefresh, buttonSave;
    DBHandler dbHandler;
    MainActivity activity;

    private OnFragmentInteractionListener mListener;

    public FormFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FormFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FormFragment newInstance(String param1, String param2) {
        FormFragment fragment = new FormFragment();
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
        dbHandler = new DBHandler(getContext());
        activity = (MainActivity)getActivity();
        RestClient.initialize();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_form, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //dbHandler.deleteAll();
        List<Data> datas =dbHandler.getAllData();
        editNama = (EditText) rootView.findViewById(R.id.editNama);
        editAlamat = (EditText) rootView.findViewById(R.id.editAlamat);
        editTanggal = (EditText) rootView.findViewById(R.id.editTanggal);
        radioKelamin = (RadioGroup) rootView.findViewById(R.id.radioKelamin);
        radioPria = (RadioButton) rootView.findViewById(R.id.radioPria);
        radioWanita = (RadioButton) rootView.findViewById(R.id.radioWanita);
        myCalendar = Calendar.getInstance();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        editTanggal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        editTanggal.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    new DatePickerDialog(getContext(), date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }

            }
        });

        buttonSave = (Button) rootView.findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedRadio = radioKelamin.getCheckedRadioButtonId();
                radioKelaminSelected = (RadioButton) rootView.findViewById(selectedRadio);
                Data data = new Data();
                data.setNama(editNama.getText().toString());
                data.setAlamat(editAlamat.getText().toString());
                data.setJenisKelamin(radioKelaminSelected.getText().toString());
                data.setTanggalLahir(editTanggal.getText().toString());
                dbHandler.addData(data);
                TabLayout.Tab tab = activity.tabLayout.getTabAt(1);
                tab.select();
            }
        });

        buttonRefresh = (Button) rootView.findViewById(R.id.buttonRefresh);
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RestClient.dataService.getData().enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        if(response.isSuccessful()){
                            Response responseData = response.body();
                            Data data = new Data();
                            data.setNama(responseData.getNama());
                            data.setAlamat(responseData.getAlamat());
                            data.setJenisKelamin(responseData.getJenisKelamin());
                            data.setTanggalLahir(responseData.getTanggalLahir());
                            editNama.setText(data.getNama());
                            editAlamat.setText(data.getAlamat());
                            editTanggal.setText(data.getTanggalLahir());
                            if (data.getJenisKelamin().equalsIgnoreCase("pria")){
                                radioPria.setChecked(true);
                            } else radioWanita.setChecked(true);
                            Log.d("hehe","wewe");

                        }
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Log.d("response", t.getMessage());
                    }
                });
            }
        });

        RestClient.dataService.getData().enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if(response.isSuccessful()){
                    Response responseData = response.body();
                    Data data = new Data();
                    data.setNama(responseData.getNama());
                    data.setAlamat(responseData.getAlamat());
                    data.setJenisKelamin(responseData.getJenisKelamin());
                    data.setTanggalLahir(responseData.getTanggalLahir());
                    editNama.setText(data.getNama());
                    editAlamat.setText(data.getAlamat());
                    editTanggal.setText(data.getTanggalLahir());
                    if (data.getJenisKelamin().equalsIgnoreCase("pria")){
                        radioPria.setChecked(true);
                    } else radioWanita.setChecked(true);

                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                //Log.d("response", t.getMessage());
            }
        });


    }

    private void updateLabel() {

        String myFormat = "yyyy/MM/dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editTanggal.setText(sdf.format(myCalendar.getTime()));
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


}
