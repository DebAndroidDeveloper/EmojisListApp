package assessment.mycompany.com.emojis.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import assessment.mycompany.com.emojis.R;
import assessment.mycompany.com.emojis.adapters.EmojisListAdapter;
import assessment.mycompany.com.emojis.callbacks.ApiCallBack;
import assessment.mycompany.com.emojis.callbacks.EmojisAppReceiver;
import assessment.mycompany.com.emojis.models.EmojiData;
import assessment.mycompany.com.emojis.system.network.ApiIntentService;
import assessment.mycompany.com.emojis.utils.Constants;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmojisFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmojisFragment extends BaseFragment implements ApiCallBack, AdapterView.OnItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
    //private String mParam2;

   // private OnFragmentInteractionListener mListener;
    private List<EmojiData> emojiDataList;
    private EmojisListAdapter emojisListAdapter;
    private EmojisAppReceiver emojisAppReceiver;
    private IntentFilter mFilter;
    private ProgressDialog mProgressDialog;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param sectionNumber Parameter 1.
     *                      //@param param2 Parameter 2.
     * @return A new instance of fragment EmojisFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EmojisFragment newInstance(int sectionNumber) {
        EmojisFragment fragment = new EmojisFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, sectionNumber);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public EmojisFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
        setRetainInstance(true);
        this.emojiDataList = new ArrayList<EmojiData>();
        mProgressDialog = new ProgressDialog(getActivity());
        //mProgressDialog.setTitle("Loading");
        mProgressDialog.setMessage("One moment please...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();

        ApiIntentService.getEmojisData(getActivity());
        //populateList();
    }

    @Override
    public void onResume() {
        super.onResume();
        emojisAppReceiver = new EmojisAppReceiver(this);
        mFilter = new IntentFilter();
        mFilter.addAction(Constants.IntentActions.ACTION_ERROR);
        mFilter.addAction(Constants.IntentActions.ACTION_SUCCESS);
        getActivity().registerReceiver(emojisAppReceiver, mFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(emojisAppReceiver);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View trendingShowsView = inflater.inflate(R.layout.fragment_trending_shows, container, false);
        ListView trendingShowsList = (ListView) trendingShowsView.findViewById(R.id.trending_shows_listView);
        trendingShowsList.setOnItemClickListener(this);
        emojisListAdapter = new EmojisListAdapter(getActivity(), emojiDataList);
        trendingShowsList.setAdapter(emojisListAdapter);
        return trendingShowsView;
    }



    @Override
    public String getTagName() {
        return EmojisFragment.class.getCanonicalName();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
       /* try {
          //  mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
       // mListener = null;
    }

    @Override
    public void onHttpResponseError(Intent intent) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
        String message = intent.getStringExtra(Constants.IntentExtras.MESSAGE);
        Log.e(getTag(), message);
        showErrorDialog(message);
    }

    @Override
    public void onHttpRequestComplete(Intent intent) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }

        if (intent.getIntExtra(Constants.IntentExtras.REQUEST_ID, -1) == Constants.ApiRequestId.GET_EMOJIS_DATA) {

            try {
                String trendingShows = intent.getStringExtra(Constants.IntentExtras.MESSAGE);
                JsonReader jsonReader = new JsonReader( new StringReader(trendingShows));
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    String emojisName = jsonReader.nextName();
                    String emojisImageUrl = jsonReader.nextString();
                    Log.d(getTag(),emojisName+":"+emojisImageUrl);
                    EmojiData emojiData = new EmojiData();
                    emojiData.setEmojisName(emojisName);
                    emojiData.setEmojisImageUrl(emojisImageUrl);
                    emojiDataList.add(emojiData);
                }
                jsonReader.endObject();
                this.emojisListAdapter.notifyDataSetChanged();
            }  catch (IOException e) {
                e.printStackTrace();
                Log.e(getTag(),e.getMessage());
            }

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

}
