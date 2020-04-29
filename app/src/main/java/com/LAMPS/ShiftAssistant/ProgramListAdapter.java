package com.LAMPS.ShiftAssistant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ProgramListAdapter extends ArrayAdapter<ProgramGetterSetter> {

    private static final String TAG = "ProgramListAdapter";

    private Context mContext;
    private int mResource;
    private int LastPosition = -1;

    static class ViewHolder{
        TextView Date , Shift, Worker , Team , TilVAC;
    }

    public ProgramListAdapter(Context context , int resource , ArrayList<ProgramGetterSetter> object){
        super(context,resource,object);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position , View convertView , ViewGroup parent) {

        String Date = getItem(position).getDate();
        String Shift = getItem(position).getShiftID();

        int Worker = getItem(position).getWorkerID();
        int Team = getItem(position).getTeamID();
        int TilVAC = getItem(position).getTilVAC();

        final View Result;

        ViewHolder Holder;

        ProgramGetterSetter GetterSetter = new ProgramGetterSetter(Date,Shift,Worker,Team,TilVAC);

        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource,parent,false);
            Holder = new ViewHolder();
            Holder.Date = convertView.findViewById(R.id.WorkerAdapterDate);
            Holder.Shift = convertView.findViewById(R.id.WorkerAdapterShiftID);
            Holder.Worker = convertView.findViewById(R.id.WorkerAdapterWorkerID);
            Holder.Team = convertView.findViewById(R.id.WorkerAdapterTeamID);
            Holder.TilVAC = convertView.findViewById(R.id.WorkerAdapterTilVacation);

            Result = convertView;
            convertView.setTag(Holder);
        }else{
            Holder = (ViewHolder) convertView.getTag();
            Result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext,
                (position > LastPosition) ? R.anim.loading_down_anim : R.anim.loading_up_anim);
        Result.startAnimation(animation);
        LastPosition = position;

        Holder.Date.setText(GetterSetter.getDate());
        Holder.Shift.setText(GetterSetter.getShiftID());
        Holder.Worker.setText(String.valueOf(GetterSetter.getWorkerID()));
        Holder.Team.setText(String.valueOf(GetterSetter.getTeamID()));
        Holder.TilVAC.setText(String.valueOf(GetterSetter.getTilVAC()));

        return convertView;
    }
}
