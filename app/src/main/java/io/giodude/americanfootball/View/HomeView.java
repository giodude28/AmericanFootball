package io.giodude.americanfootball.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import io.giodude.americanfootball.Adapter.TeamIconAdapter;
import io.giodude.americanfootball.Model.Team;
import io.giodude.americanfootball.R;
import io.giodude.americanfootball.ViewModel.AmericanViewModel;

@AndroidEntryPoint
public class HomeView extends Fragment {
    private static RecyclerView recyclerView;
    private RecyclerView.LayoutManager rvLayout;
    private AmericanViewModel viewModel;
    private TeamIconAdapter teamIconAdapter;
    TextView see1,see2,name,desc;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_home_view, container, false);
        Fresco.initialize(getActivity());
        see1 = view.findViewById(R.id.seeamerican);
        see2 = view.findViewById(R.id.see);
        observeData1();
        see1.setOnClickListener(v -> {
            final Dialog myDialog;
            myDialog = new Dialog(getActivity());
            myDialog.setContentView(R.layout.homeclick);
            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            name = myDialog.findViewById(R.id.title);
            desc = myDialog.findViewById(R.id.desc);
            name.setText("American Football");
            desc.setText("  American football is a tough, physical game requiring a wide range of attributes including explosive power, strength, agility, speed, and physical and mental toughness. The huge collisions and intricate plays involving the 22 players on the field are what make the sport the spectacle that it is.\n" +
                    "\n" +
                    "   Games can take up to 3 hours to complete and can look quite complex, largely because each play is often highly tactical. In reality football is actually a relatively easy sport to follow and enjoy. It is the complexities of the sport, gained from years of avid watching, which make the game so interesting to watch and make it more than a test of brute strength and muscle. Also referred to as American football, as distinct from football (or soccer as it is also known.) In some countries, American football is often referred to as gridiron or gridiron football to differentiate it from other football games.");
            myDialog.show();
        });

        see2.setOnClickListener(v -> {
            final Dialog myDialog;
            myDialog = new Dialog(getActivity());
            myDialog.setContentView(R.layout.homeclick);
            myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            name = myDialog.findViewById(R.id.title);
            desc = myDialog.findViewById(R.id.desc);
            name.setText("NFL");
            desc.setText("  The National Football League (NFL) is the pinnacle of the sport in the US and the world. It consists of 32 teams split into two conferences – the American Football Conference (AFC) and the National Football Conference (NFC). Within each conference there are four divisions (North, South, East and West) with four teams in each.\n" +
                    "\n\n" +
                    "   The pinnacle of the season is the final championship game, the Super Bowl, played by each conference champion; a game that is watched by an estimated half of all US households and which is broadcasted in around 150 nations worldwide.\n" +
                    "\n\n" +
                    "   The NFL operated a now defunct developmental league, NFL Europa, with teams in five German cities and one in the Netherlands. The professional Canadian Football League plays under slightly different Canadian rules.");
            myDialog.show();
        });
        return view;
    }

    private void observeData1() {
        viewModel = new ViewModelProvider(this).get(AmericanViewModel.class);
        viewModel.getTeams();
        viewModel.getTeamList().observe(getViewLifecycleOwner(), teams -> {
            System.out.println("onchanged: " + teams.size());
            initRecyclerViewstaduim(teams);
            teamIconAdapter.updateList(teams);
        });
    }
    private void initRecyclerViewstaduim(List<Team> events) {
        recyclerView = view.findViewById(R.id.recyclerview);
        rvLayout = new GridLayoutManager(getActivity(), 1, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(rvLayout);
        teamIconAdapter = new TeamIconAdapter(getActivity(), events);
        recyclerView.setAdapter(teamIconAdapter);
    }
}