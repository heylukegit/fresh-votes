package com.freshvotes.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "comment")
public class Comment {

    private Long id;
    private String text;
    private User user;
    private Feature feature;

    // the parent comment, this attribute is nullable
    private Comment comment;

    // the children comments
    private List<Comment> comments = new ArrayList<>();

    private Date createdDate;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(length = 5000)  // set the max length of the comment
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @ManyToOne
    @JsonIgnore  // ignore this attribute, when converting into json file
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JsonIgnore  // ignore this attribute, when converting into json file
    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    @ManyToOne
    // explanation about JoinColumn: https://www.baeldung.com/jpa-join-column
    // This create a column called comment_id, which points to the id of parent comment
    @JoinColumn(name = "comment_id", nullable = true)
    @JsonIgnore
    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    @OneToMany(mappedBy = "comment")
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
