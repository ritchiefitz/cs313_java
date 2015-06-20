/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloWorld;

/**
 *
 * @author ritchiefitzgerald
 */
public class Post {
    private String username;
    private String date;
    private String content;

    public Post(String username, String date, String content) {
        this.username = username;
        this.date = date;
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    
    public String[] toArray() {
        String[] post = {username, date, content};
        return post;
    }
}
