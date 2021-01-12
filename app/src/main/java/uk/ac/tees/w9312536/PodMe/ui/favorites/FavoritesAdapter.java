package uk.ac.tees.w9312536.PodMe.ui.favorites;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import uk.ac.tees.w9312536.PodMe.data.FavoriteEntry;
import uk.ac.tees.w9312536.PodMe.utilities.Constants;
import uk.ac.tees.w9312536.PodMe.utilities.PodCastUtils;
import uk.ac.tees.w9312536.bukolafatunde.R;
import uk.ac.tees.w9312536.bukolafatunde.databinding.FavoritesListItemBinding;

/**
 * Exposes a list of favorite episodes from a list of {@link FavoriteEntry} to a {@link RecyclerView}
 */
public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder> {

    /** Member variable for the list of FavoriteEntries that holds favorite episode data */
    private List<FavoriteEntry> mFavoriteEntries;

    /** Context we use to utility methods, app resources and layout inflaters */
    private Context mContext;

    /**
     * An on-click handler that we've defined to make it easy for a Fragment to interface with
     * our RecyclerView
     */
    private final FavoritesAdapterOnClickHandler mOnClickHandler;

    /**
     * The interface that receives onClick messages.
     */
    public interface FavoritesAdapterOnClickHandler {
        void onFavoriteClick(FavoriteEntry favoriteEntry, ImageView imageView);
    }

    /**
     * Creates a FavoritesAdapter.
     */
    public FavoritesAdapter(Context context, FavoritesAdapterOnClickHandler onClickHandler) {
        mContext = context;
        mOnClickHandler = onClickHandler;
    }

    /**
     * Called when ViewHolders are created to fill a RecyclerView.
     *
     * @return A new FavoritesViewHolder that holds the FavoritesListItemBinding
     */
    @NonNull
    @Override
    public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        FavoritesListItemBinding favoritesListItemBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.favorites_list_item, viewGroup, false);
        return new FavoritesViewHolder(favoritesListItemBinding);
    }

    /**
     * Called by the RecyclerView to display the data at the specified position.
     */
    @Override
    public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {
        FavoriteEntry favoriteEntry = mFavoriteEntries.get(position);
        holder.bind(favoriteEntry);
    }

    /**
     * This method simply return the number of items to display. It is used behind the scenes
     * to help layout our Views and for animations.
     *
     * @return The number of items available in the favorites
     */
    @Override
    public int getItemCount() {
        if (mFavoriteEntries == null) return 0;
        return mFavoriteEntries.size();
    }

    /**
     * When data changes, updates the list of favoriteEntries and notifies the adapter to use
     * the new values on it.
     */
    public void setFavoriteEntries(List<FavoriteEntry> favoriteEntries) {
        mFavoriteEntries = favoriteEntries;
        notifyDataSetChanged();
    }

    /**
     * Cache of the children views for a list item.
     */
    public class FavoritesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /** This field is used for data binding */
        private FavoritesListItemBinding mFavListItemBinding;

        /**
         * Constructor for FavoritesViewHolder.
         *
         * @param favoritesListItemBinding Used to access the layout's variables and views
         */
        public FavoritesViewHolder(@NonNull FavoritesListItemBinding favoritesListItemBinding) {
            super(favoritesListItemBinding.getRoot());
            mFavListItemBinding = favoritesListItemBinding;

            // Set OnClickListener
            itemView.setOnClickListener(this);
        }

        /**
         * This method will take a FavoriteEntry object as input and use it to display
         * the appropriate text within a list item.
         */
        void bind(FavoriteEntry favoriteEntry) {
            // Get the podcast title and set the text
            String podcastTitle = favoriteEntry.getTitle();
            mFavListItemBinding.tvPodcastTitle.setText(podcastTitle);

            // Get image URL, and load the image using Glide
            String podcastImage = favoriteEntry.getArtworkImageUrl();
            String itemImageUrl = favoriteEntry.getItemImageUrl();
            if (TextUtils.isEmpty(itemImageUrl)) {
                itemImageUrl = podcastImage;
            }
            Glide.with(mContext)
                    .load(itemImageUrl)
                    .into(mFavListItemBinding.ivEpisode);

            // Get an episode title and set the text
            String itemTitle = favoriteEntry.getItemTitle();
            mFavListItemBinding.tvEpisodeTitle.setText(itemTitle);

            // Get the description of an episode and set the text
            String description = favoriteEntry.getItemDescription();
            // If the description contains the img tag, remove it, then convert HTML to plain text.
            // Reference: @see "https://stackoverflow.com/questions/11178533/how-to-skip-image-tag-in-html-data-in-android"
            // @see "https://stackoverflow.com/questions/22573319/how-to-convert-html-text-to-plain-text-in-android"
            if (description != null) {
                String descriptionWithoutImageTag = description.replaceAll(Constants.IMG_HTML_TAG, Constants.REPLACEMENT_EMPTY);
                mFavListItemBinding.tvDescription.setText(
                        Html.fromHtml(Html.fromHtml(descriptionWithoutImageTag).toString()));
            }

            // Get the pub date of an episode and set the text
            String pubDate = favoriteEntry.getItemPubDate();
            // Convert the pub date into something to display to users
            String formattedPubDate = PodCastUtils.getFormattedDateString(pubDate);
            mFavListItemBinding.tvPubDate.setText(formattedPubDate);

            // Get the duration of an episode and set the text
            String duration = favoriteEntry.getItemDuration();
            if (TextUtils.isEmpty(duration)) {
                // Hide the duration TextView when the duration is empty
                mFavListItemBinding.tvDuration.setVisibility(View.GONE);
            } else {
                mFavListItemBinding.tvDuration.setText(duration);
            }
        }

        /**
         * Called whenever a user clicks on an episode in the list
         * @param v The View that was clicked
         */
        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            FavoriteEntry favoriteEntry = mFavoriteEntries.get(adapterPosition);
            // Trigger the callback onFavoriteClick
            mOnClickHandler.onFavoriteClick(favoriteEntry, mFavListItemBinding.ivEpisode);
        }
    }
}

