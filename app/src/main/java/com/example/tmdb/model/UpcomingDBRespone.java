package com.example.tmdb.model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpcomingDBRespone implements Parcelable
{

    @SerializedName("results")
    @Expose
    private List<UpcomingMovie> UpcomingMovies = new ArrayList<>();
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalUpcomingMovies;
    @SerializedName("dates")
    @Expose
    private Dates dates;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    public final static Parcelable.Creator<UpcomingDBRespone> CREATOR = new Creator<UpcomingDBRespone>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UpcomingDBRespone createFromParcel(Parcel in) {
            return new UpcomingDBRespone(in);
        }

        public UpcomingDBRespone[] newArray(int size) {
            return (new UpcomingDBRespone[size]);
        }

    }
            ;

    protected UpcomingDBRespone(Parcel in) {
        in.readList(this.UpcomingMovies, (com.example.tmdb.model.UpcomingMovie.class.getClassLoader()));
        this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalUpcomingMovies = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.dates = ((Dates) in.readValue((Dates.class.getClassLoader())));
        this.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public UpcomingDBRespone() {
    }

    public List<UpcomingMovie> getUpcomingMovies() {
        return UpcomingMovies;
    }

    public void setUpcomingMovies(List<UpcomingMovie> UpcomingMovies) {
        this.UpcomingMovies = UpcomingMovies;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalUpcomingMovies() {
        return totalUpcomingMovies;
    }

    public void setTotalUpcomingMovies(Integer totalUpcomingMovies) {
        this.totalUpcomingMovies = totalUpcomingMovies;
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
        dest.writeList(UpcomingMovies);
        dest.writeValue(page);
        dest.writeValue(totalUpcomingMovies);
        dest.writeValue(dates);
        dest.writeValue(totalPages);
    }

    public int describeContents() {
        return 0;
    }

}
