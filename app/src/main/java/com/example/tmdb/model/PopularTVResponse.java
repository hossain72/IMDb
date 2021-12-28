package com.example.tmdb.model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.example.tmdb.model.PopularTV;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PopularTVResponse implements Parcelable
{

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalPopularTVs;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("results")
    @Expose
    private List<PopularTV> PopularTVs = new ArrayList<>();
    public final static Parcelable.Creator<PopularTVResponse> CREATOR = new Creator<PopularTVResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PopularTVResponse createFromParcel(Parcel in) {
            return new PopularTVResponse(in);
        }

        public PopularTVResponse[] newArray(int size) {
            return (new PopularTVResponse[size]);
        }

    }
            ;

    protected PopularTVResponse(Parcel in) {
        this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalPopularTVs = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.PopularTVs, (PopularTV.class.getClassLoader()));
    }

    public PopularTVResponse() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPopularTVs() {
        return totalPopularTVs;
    }

    public void setTotalPopularTVs(Integer totalPopularTVs) {
        this.totalPopularTVs = totalPopularTVs;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<PopularTV> getPopularTVs() {
        return PopularTVs;
    }

    public void setPopularTVs(List<PopularTV> PopularTVs) {
        this.PopularTVs = PopularTVs;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeValue(totalPopularTVs);
        dest.writeValue(totalPages);
        dest.writeList(PopularTVs);
    }

    public int describeContents() {
        return 0;
    }

}
