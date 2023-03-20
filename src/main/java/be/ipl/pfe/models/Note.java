package be.ipl.pfe.models;

import lombok.Data;

@Data
public class Note {
    private String subject;
    private String content;
    private String image;

    public Note(String subject, String content) {
        this.subject = subject;
        this.content = content;
        this.image = "https://www.flaticon.com/svg/static/icons/svg/3461/3461581.svg";
    }
}