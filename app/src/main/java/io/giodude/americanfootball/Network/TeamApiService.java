package io.giodude.americanfootball.Network;

import io.giodude.americanfootball.Model.TeamModel;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TeamApiService {

    @GET("search_all_teams.php?")
    Observable<TeamModel> getTeam(@Query("l")String team);
}
