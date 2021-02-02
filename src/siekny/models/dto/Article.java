package siekny.models.dto;

import java.time.LocalDate;

public class Article {
    private int id;
    private String title;
    private String description;
    private LocalDate createdDate;
    private Author author;
    private Category category;

    public Article() {
    }

    public Article(int id, String title, String description, LocalDate createdDate, Author author, Category category) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;
        this.author = author;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdDate=" + createdDate +
                ", author=" + author +
                ", category=" + category +
                '}';
    }
}
