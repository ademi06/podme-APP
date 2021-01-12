package uk.ac.tees.w9312536.PodMe.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LookupResponse {

    @SerializedName("results")
    private List<LookupResult> mLookupResults;

    public List<LookupResult> getLookupResults() {
        return mLookupResults;
    }

    public void setLookupResults(List<LookupResult> lookupResults) {
        mLookupResults = lookupResults;
    }
}
