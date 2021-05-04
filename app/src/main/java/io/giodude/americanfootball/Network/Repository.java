package io.giodude.americanfootball.Network;

import java.security.Key;

import javax.inject.Inject;

import io.giodude.americanfootball.Model.MatchesModel;
import io.giodude.americanfootball.Model.TeamModel;
import io.reactivex.rxjava3.core.Observable;

public class Repository {

    private TeamApiService apiService;
    private MatchesApiService matchesApiService;
//    private TableApiService tableApiService;
//    private LiveApiService liveApiService;

    @Inject
    public Repository(TeamApiService apiService,MatchesApiService matchesApiService){
        this.matchesApiService = matchesApiService;
        this.apiService = apiService;
//        this.tableApiService = tableApiService;
//        this.liveApiService = liveApiService;
    }

    public Observable<TeamModel> getTeam(){
        return apiService.getTeam(Keys.Team);
    }

    public Observable<MatchesModel> getPast(){
        return matchesApiService.getPast(Keys.League);
    }
}
