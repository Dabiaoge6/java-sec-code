package org.vulhunter.common.sqlinjection;

import javax.persistence.*;

@SqlResultSetMapping
(
        name = "UserResults",
        entities = {
            @EntityResult(
                entityClass = App1User.class, //就是当前这个类的名字
                fields = {
                    @FieldResult(name = "id", column = "id"),
                    @FieldResult(name = "username", column = "username"),
                    @FieldResult(name = "pwd", column = "pwd"),
                }
            )
        }
)

@Table
@Entity(name = "app1_user")
public class App1User {

    @Id
    private int id;

    private String username;

    private String pwd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
