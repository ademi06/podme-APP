package uk.ac.tees.w9312536.bukolafatunde.ui.add;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uk.ac.tees.w9312536.bukolafatunde.R;
import uk.ac.tees.w9312536.bukolafatunde.model.Result;

//public class AddPodcastAdapter extends RecyclerView.Adapter<AddPodcastAdapter.AddPodcastViewHolder> {
//
//    /* Member variable for the list of {@link Result} */
//    private List<Result> mResults;
//
//    // An onClick handler that we have defined to make it easy for an Activity to interface with
//    // our RecyclerView
//    private final AddPodcastAdapterOnClickHandler mOnClickHander;
//
//    @NonNull
//    @Override
//    public AddPodcastAdapter.AddPodcastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        AddPodListItemBinding addPodListItemBinding = DataBindingUtil.inflate(inflater, R.layout.add_pod_list_item, parent, false);
//        //View view = inflater.inflate(R.layout.add_pod_list_item, parent, false);
//
//        return new AddPodcastViewHolder(addPodListItemBinding);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull AddPodcastAdapter.AddPodcastViewHolder holder, int position) {
//        Result result = mResults.get(position);
//        holder.bind(result);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        if (mResults == null) return 0;
//        return mResults.size();
//    }
//
//    public void addAll(List<Result> results){
//        mResults.clear();
//        mResults.addAll(results);
//        notifyDataSetChanged();
//    }
//
//    /* The interface that receives onClick messages */
//    public interface AddPodcastAdapterOnClickHandler {
//        void onItemClick(Result result, ImageView imageView);
//    }
//
//    public AddPodcastAdapter(List<Result> results, AddPodcastAdapterOnClickHandler onClickHandler){
//        mResults = results;
//        mOnClickHander = onClickHandler;
//    }
//
//
//    public class AddPodcastViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//
//        private AddPodListItemBinding mAddPodListItemBinding;
//
//        public AddPodcastViewHolder(AddPodListItemBinding addPodListItemBinding) {
//            super(addPodListItemBinding.getRoot());
//            mAddPodListItemBinding = addPodListItemBinding;
//
//                    itemView.setOnClickListener(this);
//        }
//
//
//        @Override
//        public void onClick(View v) {
//
//            int adapterPosition = getBindingAdapterPosition();
//            Result result = mResults.get(adapterPosition);
//            mOnClickHander.onItemClick(result, mAddPodListItemBinding);
//        }
//    }
//}
