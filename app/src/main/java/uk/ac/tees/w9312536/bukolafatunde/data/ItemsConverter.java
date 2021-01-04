package uk.ac.tees.w9312536.bukolafatunde.data;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

import uk.ac.tees.w9312536.bukolafatunde.model.rss.Item;

/**
 * {@link TypeConverter} for string to the list of {@link Item}s
 * <p>
 * This stores the list of items as a string in the database, but returns it as a list of {@link Item}s
 *
 * References: @see "https://stackoverflow.com/questions/44580702/android-room-persistent-library
 * -how-to-insert-class-that-has-a-list-object-fie"
 * "https://medium.com/@toddcookevt/android-room-storing-lists-of-objects-766cca57e3f9"
 * "https://google.github.io/gson/apidocs/com/google/gson/Gson.html"
 */
public class ItemsConverter {

    @TypeConverter
    public static List<Item> toItemList(String itemString) {
        if (itemString == null) {
            return Collections.emptyList();
        }
        // Create a Gson instance
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Item>>() {}.getType();
        // Deserializes the specified Json into the list of items
        return gson.fromJson(itemString, listType);
    }

    @TypeConverter
    public static String toItemString(List<Item> itemList) {
        if (itemList == null) {
            return null;
        }
        // Create a Gson instance
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Item>>() {}.getType();
        // Serializes the list of items into its equivalent Json representation
        return gson.toJson(itemList, listType);
    }
}

