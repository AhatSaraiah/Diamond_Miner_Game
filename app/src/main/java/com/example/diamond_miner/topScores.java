//package com.example.diamond_miner;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import java.util.ArrayList;
//
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//public class topScores extends Fragment {
//
//    private GameOver GameOver;
//    private MainActivity mActivity;
//    private Context context;
//    private View view;
//    private RecyclerView list_LST_notes;
//    private adapter myAdapter;
// //   ArrayList<player> players;
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Log.d("pttt", "onCreateView");
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        Log.d("pttt", "onCreateView");
//
//       if (view == null) {
//           view = inflater.inflate(R.layout.top_scores, container, false);
//        }
//   //     players=new ArrayList<>();
//        list_LST_notes = view.findViewById(R.id.my_recycler_view);
//        initList();
//
//
//        return view;
//
//    }
//
//
//    public void setActivity(GameOver gameOver) {
//        this.GameOver = gameOver;
//    }
//
//    private void initList() {
//        //players = getPlayers();
//        ArrayList<player> players = setPlayers();
//
//       // setPlayers(players);
//        myAdapter = new adapter(context, players);
//        list_LST_notes.setHasFixedSize(true);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
//        list_LST_notes.setLayoutManager(layoutManager);
//        list_LST_notes.setAdapter(myAdapter);
//
//        myAdapter.SetOnItemClickListener(new adapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position, player p) {
//                openPlayer(p);
//            }
//        });
//    }
//
//    private void openPlayer(player p) {
//        Toast.makeText(context,p.getName(), Toast.LENGTH_SHORT).show();
//    }
//
//    private ArrayList<player> setPlayers() {
//        ArrayList<player> players = new ArrayList<>();
//        player p =  new player();
//        p=mActivity.setPlayer();
//        players.add(p);
//        return players;
//    }
//
//}
//
//
