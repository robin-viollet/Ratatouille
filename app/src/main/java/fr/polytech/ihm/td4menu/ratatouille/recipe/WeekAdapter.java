package fr.polytech.ihm.td4menu.ratatouille.recipe;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import fr.polytech.ihm.td4menu.ratatouille.MVC.Model_Ratatouille;
import fr.polytech.ihm.td4menu.ratatouille.MVC.View_Ratatouille;
import fr.polytech.ihm.td4menu.ratatouille.R;
import fr.polytech.ihm.td4menu.ratatouille.datas.Day;

public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.ViewHolder> {

    private List<Day> dayList;
    private Model_Ratatouille model_ratatouille;
    private OnButtonClickedListener callBackActivity;

    public WeekAdapter(Context context, View_Ratatouille view_ratatouille, Model_Ratatouille model_ratatouille, OnButtonClickedListener callBackActivity) {
        this.model_ratatouille = model_ratatouille;
        this.dayList = new ArrayList<>();
        List<Day> list = model_ratatouille.getWeek(model_ratatouille.getWeekNumber()).getDays();
        if (list != null) this.dayList.addAll(list);
        this.callBackActivity = callBackActivity;
    }

    public void updateModel(Model_Ratatouille model) {
        this.model_ratatouille = model;
    }

    public void refresh(Model_Ratatouille model) {
        this.model_ratatouille = model;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView dayName;
        RecyclerView recyclerView;

        ViewHolder(View v){
            super(v);
            dayName = v.findViewById(R.id.week_name_txt);

            DayAdapter dayAdapter = new DayAdapter(v.getContext(), model_ratatouille, 0, callBackActivity);

            this.recyclerView = v.findViewById(R.id.dayRecipeListFragment);
            this.recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
            this.recyclerView.setAdapter(dayAdapter);

            v.setOnClickListener(clic ->{
                callBackActivity.onButtonClicked2(model_ratatouille, getAbsoluteAdapterPosition());
            });
        }

        public void display(Day day){
            if(day != null)
                dayName.setText(day.getDayString());
        }

        @Override
        public void onClick(View view){
            int position = getLayoutPosition(); //gets item position

            Log.d("info", position + "");
            //Toast.makeText(context,"Vous voulez voir la Recette : " + recipeList.get(position).getName() , Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onLongClick(View v){
            return true;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.day_view, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekAdapter.ViewHolder viewHolder, int position){
        viewHolder.display(dayList.get(position));
    }

    @Override
    public int getItemCount(){
        return dayList.size();
    }
}