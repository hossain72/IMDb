package com.example.tmdb.model;


import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopRatedDBResponse implements Parcelable
{

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalTopRatedMovies;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("results")
    @Expose
    private List<TopRatedMovie> TopRatedMovies = new ArrayList<>();
    public final static Parcelable.Creator<TopRatedDBResponse> CREATOR = new Creator<TopRatedDBResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TopRatedDBResponse createFromParcel(Parcel in) {
            return new TopRatedDBResponse(in);
        }

        public TopRatedDBResponse[] newArray(int size) {
            return (new TopRatedDBResponse[size]);
        }

    }
            ;

    protected TopRatedDBResponse(Parcel in) {
        this.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalTopRatedMovies = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.TopRatedMovies, (com.example.tmdb.model.TopRatedMovie.class.getClassLoader()));
    }

    public TopRatedDBResponse() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalTopRatedMovies() {
        return totalTopRatedMovies;
    }

    public void setTotalTopRatedMovies(Integer totalTopRatedMovies) {
        this.totalTopRatedMovies = totalTopRatedMovies;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<TopRatedMovie> getTopRatedMovies() {
        return TopRatedMovies;
    }

    public void setTopRatedMovies(List<TopRatedMovie> TopRatedMovies) {
        this.TopRatedMovies = TopRatedMovies;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeValue(totalTopRatedMovies);
        dest.writeValue(totalPages);
        dest.writeList(TopRatedMovies);
    }

    public int describeContents() {
        return 0;
    }

}