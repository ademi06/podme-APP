package uk.ac.tees.w9312536.PodMe.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import uk.ac.tees.w9312536.PodMe.model.SearchResult;
import uk.ac.tees.w9312536.bukolafatunde.R;
import uk.ac.tees.w9312536.bukolafatunde.databinding.SearchListItemBinding;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {

    private List<SearchResult> mSearchResults;

    private final SearchAdapterOnClickHandler mOnClickHandler;

    public interface SearchAdapterOnClickHandler {
        void onItemClick(SearchResult searchResult, ImageView imageView);
    }

    public SearchAdapter(List<SearchResult> searchResults, SearchAdapterOnClickHandler onClickHandler) {
        mSearchResults = searchResults;
        mOnClickHandler = onClickHandler;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        SearchListItemBinding searchListItemBinding = DataBindingUtil
                .inflate(layoutInflater, R.layout.search_list_item, viewGroup, false);
        return new SearchViewHolder(searchListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, int position) {
        SearchResult searchResult = mSearchResults.get(position);
        searchViewHolder.bind(searchResult);
    }

    @Override
    public int getItemCount() {
        if (mSearchResults == null) return 0;
        return mSearchResults.size();
    }

    /**
     * This method is to update a list of {@link SearchResult}s and notify the adapter of any changes.
     * @param searchResults The list of searchResults
     */
    public void addAll(List<SearchResult> searchResults) {
        mSearchResults.clear();
        mSearchResults.addAll(searchResults);
        notifyDataSetChanged();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private SearchListItemBinding mSearchListItemBinding;

        public SearchViewHolder(@NonNull SearchListItemBinding searchListItemBinding) {
            super(searchListItemBinding.getRoot());
            mSearchListItemBinding = searchListItemBinding;

            itemView.setOnClickListener(this);
        }

        void bind(SearchResult searchResult) {
            RequestOptions options = new RequestOptions()
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.ic_baseline_error);

            Glide.with(itemView.getContext())
                    .load(searchResult.getArtworkUrl600())
                    .apply(options)
                    .into(mSearchListItemBinding.ivArtwork);

            mSearchListItemBinding.tvName.setText(searchResult.getCollectionName());
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            SearchResult searchResult = mSearchResults.get(adapterPosition);
            mOnClickHandler.onItemClick(searchResult, mSearchListItemBinding.ivArtwork);
        }
    }
}

