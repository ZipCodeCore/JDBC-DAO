package models;

public class Movie {
    Integer id;
    String title;
    Integer runtime;
    Integer imdbscore;
    Integer releaseyear;

    public Movie(String title, Integer runtime, Integer imdbscore, Integer releaseyear){
        this.title = title;
        this.runtime = runtime;
        this.imdbscore = imdbscore;
        this.releaseyear = releaseyear;
    }

    public Movie(Integer id, String title, Integer runtime, Integer imdbscore, Integer releaseyear){
        this.id = id;
        this.title = title;
        this.runtime = runtime;
        this.imdbscore = imdbscore;
        this.releaseyear = releaseyear;
    }

    public Movie(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public Integer getImdbscore() {
        return imdbscore;
    }

    public void setImdbscore(Integer imdbscore) {
        this.imdbscore = imdbscore;
    }

    public Integer getReleaseyear() {
        return releaseyear;
    }

    public void setReleaseyear(Integer releaseyear) {
        this.releaseyear = releaseyear;
    }

    @Override
    public String toString(){
        return String.format("%s | %s | %s | %s",
                this.getTitle(), this.getRuntime(), this.getImdbscore(), this.getReleaseyear());
    }
}
