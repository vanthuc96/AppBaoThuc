package com.example.admin.baothuc;

/**
 * Created by admin on 4/14/2018.
 */
import android.content.Intent;
import android.os.*;
import android.widget.*;
import java.util.*;
import android.support.v7.widget.*;
import android.view.*;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.support.annotation.Nullable;

public class AlarmFragment extends MyFragment {
    private RecyclerView mRecyclerView;
    private ArrayList<Alarm> alarms;
    private RecyclerViewAlarmAdapter alarmAdapter;

    private TextView txtCurrentTime;
    private TextView txtAdd;

    private String sm = " ";
    public AlarmFragment() {
        setName("Báo Thức");
        alarms = new ArrayList<>();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 500);
                Calendar calendar = Calendar.getInstance();
                int h = calendar.get(Calendar.HOUR_OF_DAY);
                int m = calendar.get(Calendar.MINUTE);
                int s = calendar.get(Calendar.SECOND);
                String hh = (h<10)?"0"+h:h+"";
                String mm = (m<10)?"0"+m:m+"";
                String ss = (s<10)?"0"+s:s+"";
                sm = (sm.equals(":"))?" ":":";

                txtCurrentTime.setText(hh + ":" + mm + sm + ss);

            }
        }, 500);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        alarmAdapter = new RecyclerViewAlarmAdapter(getContext(), alarms);
        mRecyclerView.setAdapter(alarmAdapter);

        txtCurrentTime = (TextView) view.findViewById(R.id.txtCurrentTime);

        txtAdd = (TextView) view.findViewById(R.id.txtAdd);

        txtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Khi click vào nút add thì start cái AddAlarmActivty ở đây
                Intent intent=new Intent();
                startActivity(intent);
                            }
        });
        initSwipe();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        alarms.clear();
        alarms.addAll(MyDatabase.getInstance(getContext()).getAll());
        alarmAdapter.notifyDataSetChanged();

    }
    private void initSwipe(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                alarmAdapter.removeItem(position);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

}