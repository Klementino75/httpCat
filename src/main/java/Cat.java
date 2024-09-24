import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record Cat(String id, String text, String type, String user, String upvotes) {
    public Cat(@JsonProperty("id") String id,
               @JsonProperty("text") String text,
               @JsonProperty("type") String type,
               @JsonProperty("user") String user,
               @JsonProperty("upvotes") String upvotes)
    {
        this.id = id;
        this.text = text;
        this.type = type;
        this.user = user;
        this.upvotes = upvotes;
    }

    @Override
    public String toString() {
        return "Cat - > {" +
                "id = " + id +
                ", text = '" + text + '\'' +
                ", type = '" + type + '\'' +
                ", user = '" + user + '\'' +
                ", upvotes = " + upvotes +
                '}';
    }
}