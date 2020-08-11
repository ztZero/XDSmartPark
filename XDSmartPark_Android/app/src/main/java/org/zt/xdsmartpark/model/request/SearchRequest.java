package org.zt.xdsmartpark.model.request;

import com.google.gson.annotations.SerializedName;

public class SearchRequest {

    @SerializedName("searchContent")
    private String searchContent;

    public SearchRequest(String searchContent) {
        this.searchContent = searchContent;
    }

    public SearchRequest() {
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }
}
