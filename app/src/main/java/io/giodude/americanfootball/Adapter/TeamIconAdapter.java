package io.giodude.americanfootball.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.List;

import io.giodude.americanfootball.Model.Team;
import io.giodude.americanfootball.R;

public class TeamIconAdapter extends RecyclerView.Adapter<TeamIconAdapter.ViewHolder> {
    Context context;
    private List<Team> data;

    public TeamIconAdapter(Context context, List<Team> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View item = layoutInflater.inflate(R.layout.teamiconitem,parent,false);
        ViewHolder viewHolder = new ViewHolder(item);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Uri imageRequest = Uri.parse(data.get(position).getStrTeamBadge());
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(imageRequest)
                .setResizeOptions(new ResizeOptions(100, 100))
                .build();
        holder.img.setController(
                Fresco.newDraweeControllerBuilder()
                        .setOldController(holder.img.getController())
                        .setImageRequest(request)
                        .build());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView img;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.id);
            img = itemView.findViewById(R.id.teamicon);
        }
    }
    public void updateList(List<Team> updatedList) {
        data = updatedList;
        notifyDataSetChanged();
    }
}
