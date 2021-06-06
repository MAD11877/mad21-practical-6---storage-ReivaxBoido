package sg.edu.np.myapplication;

public class User {
    private String name;
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    private String description;
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    private boolean follow;
    public boolean isFollow() { return follow; }
    public void setFollow(boolean follow) { this.follow = follow; }
}
