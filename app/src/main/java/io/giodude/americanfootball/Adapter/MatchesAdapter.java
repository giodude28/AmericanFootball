package io.giodude.americanfootball.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.giodude.americanfootball.Model.Event;
import io.giodude.americanfootball.Model.Team;
import io.giodude.americanfootball.R;
import io.giodude.americanfootball.View.MatchesView;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder> {
    Context context;
    private List<Event> data;
    TextView title, yt;

    public MatchesAdapter(Context context, List<Event> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.matchesitem, parent, false);
        MatchesAdapter.ViewHolder viewHolder = new MatchesAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.home.setText(data.get(position).getStrHomeTeam());
        holder.away.setText(data.get(position).getStrAwayTeam());
        holder.homescore.setText(data.get(position).getIntHomeScore().toString());
        holder.awayscore.setText(data.get(position).getIntAwayScore().toString());
        Uri imageRequest = Uri.parse(data.get(position).getStrThumb());
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(imageRequest)
                .setResizeOptions(new ResizeOptions(200, 200))
                .build();
        holder.eimg.setController(
                Fresco.newDraweeControllerBuilder()
                        .setOldController(holder.eimg.getController())
                        .setImageRequest(request)
                        .build());

        final Dialog myDialog;
        myDialog = new Dialog(context);
        myDialog.setContentView(R.layout.matchesdetails);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        title = myDialog.findViewById(R.id.title);
        yt = myDialog.findViewById(R.id.youtube);
        for (int i = 0; i < data.size(); i++) {
            if (holder.home.getText() == data.get(position).getStrHomeTeam()) {
                title.setText(data.get(position).getStrEvent());
                yt.setText(data.get(position).getStrVideo());
            }
        }

   holder.itemView.setOnClickListener(v -> myDialog.show());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView home;
        public TextView away;
        public TextView homescore;
        public TextView awayscore;
        public SimpleDraweeView eimg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            home = (TextView) itemView.findViewById(R.id.home);
            awayscore = (TextView) itemView.findViewById(R.id.awayscore);
            homescore = (TextView) itemView.findViewById(R.id.homescore);
            away = (TextView) itemView.findViewById(R.id.away);
            eimg = itemView.findViewById(R.id.eventimg);
        }
    }

    public void updateList(List<Event> updatedList) {
        data = updatedList;
        notifyDataSetChanged();
    }
}
