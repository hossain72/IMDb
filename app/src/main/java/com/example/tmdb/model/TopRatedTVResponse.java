package com.example.tmdb.model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopRatedTVResponse implements Parcelable
{

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalTopRatedTVs;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("results")
    @Expose
    private List<TopRatedTV> TopRatedTVs = new ArrayList<>();
    public final static Parcelable.Creator<TopRatedTVResponse> CREATOR = new Creator<TopRatedTVResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TopRatedTVResponse createFromParcel(Parcel in) {
            return new TopRatedTVResponse(in);
        }

        public TopRatedTVResponse[] newArray(int size) {
            return (new TopRatedTVResponse[size]);
        }

    }
            ;

    protected TopRatedTVResponse(Parcel in) {
        this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalTopRatedTVs = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.TopRatedTVs, (TopRatedTV.class.getClassLoader()));
    }

    public TopRatedTVResponse() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalTopRatedTVs() {
        return totalTopRatedTVs;
    }

    public void setTotalTopRatedTVs(Integer totalTopRatedTVs) {
        this.totalTopRatedTVs = totalTopRatedTVs;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<TopRatedTV> getTopRatedTVs() {
        return TopRatedTVs;
    }

    public void setTopRatedTVs(List<TopRatedTV> TopRatedTVs) {
        this.TopRatedTVs = TopRatedTVs;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeValue(totalTopRatedTVs);
        dest.writeValue(totalPages);
        dest.writeList(TopRatedTVs);
    }

    public int describeContents() {
        return 0;
    }

}