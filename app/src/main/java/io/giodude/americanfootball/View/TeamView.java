package io.giodude.americanfootball.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import io.giodude.americanfootball.Adapter.TeamAdapter;
import io.giodude.americanfootball.Model.Team;
import io.giodude.americanfootball.R;
import io.giodude.americanfootball.ViewModel.AmericanViewModel;

@AndroidEntryPoint
public class TeamView extends Fragment {
    private AmericanViewModel viewModel;
    private TeamAdapter teamAdapter;
    private List<Team> teamList;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager rvLayout;
    private ProgressBar progressBar;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_team_view, container, false);
        Fresco.initialize(getActivity());
        progressBar = view.findViewById(R.id.progress);
        observeData();
        return view;
    }

    private void observeData() {
        viewModel = new ViewModelProvider(this).get(AmericanViewModel.class);
        viewModel.getTeams();
        viewModel.getTeamList().observe(getViewLifecycleOwner(), pokemons -> {
            if (pokemons.size() == 0) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.INVISIBLE);
                initRecyclerView(pokemons);
                teamAdapter.updateList(pokemons);
            }

        });
    }

    private void initRecyclerView(List<Team> team) {
        recyclerView = view.findViewById(R.id.teamRV);
        rvLayout = new LinearLayoutManager((getActivity()));
        recyclerView.setLayoutManager(rvLayout);
        teamAdapter = new TeamAdapter(getActivity(), team);
        recyclerView.setAdapter(teamAdapter);
    }
}