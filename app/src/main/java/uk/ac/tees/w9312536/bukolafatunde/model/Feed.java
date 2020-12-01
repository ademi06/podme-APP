package uk.ac.tees.w9312536.bukolafatunde.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.xml.transform.Result;

public class Feed {
    @SerializedName("title")
    private String mTitle;

    @SerializedName("country")
    private String mCountry;

    @SerializedName("results")
    private List<Result> mResults;

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public List<Result> getResults() {
        return mResults;
    }

    public void setResults(List<Result> results) {
        mResults = results;
    }
}
