package org.zt.model.request;

public class SearchRequest {
	
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
