package com.hungryduck.viewmodel;

/**
 * Created by Ckh on 2016-10-28.
 */
public class ReviewItemDTO {
    private String writer;
    private double score;
    private String date;
    private String content;

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ReviewItemDTO(String writer, double score, String date, String content) {
        this.writer = writer;
        this.score = score;
        this.date = date;
        this.content = content;
    }

    public ReviewItemDTO() {
    }

}
