package com.savstanis.exhibitionservice.model.entity;

import lombok.Data;


@Data
public class User {
    private int id;
    private String name;
    private String email;
    private String role;
    private String password;

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private int id;
        private String name;
        private String email;
        private String role;
        private String password;

        public UserBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setRole(String role) {
            this.role = role;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public User build() {
            User user = new User();

            user.setId(id);
            user.setEmail(email);
            user.setName(name);
            user.setPassword(password);
            user.setRole(role);

            return user;
        }
    }
}
