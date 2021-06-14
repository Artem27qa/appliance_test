package Ampliance.app;

public class ProfileData {
    private String name;
    private String company;
    private String location;
    private int repos;
    private int gists;
    private int followers;
    private int following;

    public ProfileData(String name, String company, String location, int repos, int gists, int followers, int following) {
        this.name = name;
        this.company = company;
        this.location = location;
        this.repos = repos;
        this.gists = gists;
        this.followers = followers;
        this.following = following;
    };

    public String getName () {
        return name;
    }

    public String getCompany () {
        return company;
    }

    public String getLocation () {
        return location;
    }

    public int  getRepos () {
        return repos;
    }

    public int getGists () {
        return gists;
    }

    public int getFollowers () {
        return followers;
    }

    public int getFollowing () {
        return following;
    }

    @Override
    public String toString (){
        return String.format(name + company + location + repos + gists + followers + following);
    }
}
