package com.example.tmdb.model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LatestDBResponse implements Parcelable
{

    @SerializedName("results")
    @Expose
    private List<LatestMovie> LatestMovies = new ArrayList<>();
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalLatestMovies;
    @SerializedName("dates")
    @Expose
    private Dates dates;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    public final static Parcelable.Creator<LatestDBResponse> CREATOR = new Creator<LatestDBResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public LatestDBResponse createFromParcel(Parcel in) {
            return new LatestDBResponse(in);
        }

        public LatestDBResponse[] newArray(int size) {
            return (new LatestDBResponse[size]);
        }

    }
            ;

    protected LatestDBResponse(Parcel in) {
        in.readList(this.LatestMovies, (LatestMovie.class.getClassLoader()));
        this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalLatestMovies = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.dates = ((Dates) in.readValue((Dates.class.getClassLoader())));
        this.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public LatestDBResponse() {
    }

    public List<LatestMovie> getLatestMovies() {
        return LatestMovies;
    }

    public void setLatestMovies(List<LatestMovie> LatestMovies) {
        this.LatestMovies = LatestMovies;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalLatestMovies() {
        return totalLatestMovies;
    }

    public void setTotalLatestMovies(Integer totalLatestMovies) {
        this.totalLatestMovies = totalLatestMovies;
    }

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(LatestMovies);
        dest.writeValue(page);
        dest.writeValue(totalLatestMovies);
        dest.writeValue(dates);
        dest.writeValue(totalPages);
    }

    public int describeContents() {
        return 0;
    }

}
