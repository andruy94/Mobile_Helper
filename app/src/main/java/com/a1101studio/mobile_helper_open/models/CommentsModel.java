package com.a1101studio.mobile_helper_open.models;

/**
 * Created by andruy94 on 2/8/2017.
 */

public class CommentsModel {
    private String commentTitle;
    private String comment;

    public CommentsModel(String commentTitle, String comment) {
        this.commentTitle = commentTitle;
        this.comment = comment;
    }

    public String getCommentTitle() {
        return commentTitle;
    }

    public void setCommentTitle(String commentTitle) {
        this.commentTitle = commentTitle;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
