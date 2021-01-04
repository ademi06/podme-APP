package uk.ac.tees.w9312536.bukolafatunde.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import java.util.Date;
import java.util.List;

import uk.ac.tees.w9312536.bukolafatunde.model.rss.Item;

@Entity(tableName = "podcast")
public class PodcastEntry {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "podcast_id")
    private String podcastId;

    private String title;

    private String description;

    private String author;

    @ColumnInfo(name = "artwork_image_url")
    private String artworkImageUrl;

    @TypeConverters(ItemsConverter.class)
    private List<Item> items;

    private Date date;

    /**
     * Constructor
     *
     * @param podcastId
     * @param title
     * @param description
     * @param author
     * @param artworkImageUrl
     * @param items
     * @param date
     */
    @Ignore
    public PodcastEntry(String podcastId, String title, String description, String author,
                        String artworkImageUrl, List<Item> items, Date date) {
        this.podcastId = podcastId;
        this.title = title;
        this.description = description;
        this.author = author;
        this.artworkImageUrl = artworkImageUrl;
        this.items = items;
        this.date = date;
    }

    public PodcastEntry(int id, String podcastId, String title, String description, String author,
                        String artworkImageUrl, List<Item> items, Date date) {
        this.id = id;
        this.podcastId = podcastId;
        this.title = title;
        this.description = description;
        this.author = author;
        this.artworkImageUrl = artworkImageUrl;
        this.items = items;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getPodcastId() {
        return podcastId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthor() {
        return author;
    }

    public String getArtworkImageUrl() {
        return artworkImageUrl;
    }

    @TypeConverters(ItemsConverter.class)
    public List<Item> getItems() {
        return items;
    }

    public Date getDate() {
        return date;
    }
}

