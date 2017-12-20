package com.kaellah.data.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Chekashov R.(email:roman_woland@mail.ru)
 * @since 17.03.17
 */

public class MoviesListResponse {

    @SerializedName("page")
    private Integer page;
    @SerializedName("results")
    private List<MovieResponse> results = null;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<MovieResponse> getResults() {
        return results;
    }

    public void setResults(List<MovieResponse> results) {
        this.results = results;
    }
}
