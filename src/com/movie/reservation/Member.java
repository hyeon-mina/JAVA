package com.movie.reservation;

public class Member {
    private String id; // 회원 ID
    private String password; // 회원 비밀번호
    private String name; // 회원 이름

    // 회원 정보를 초기화하는 생성자
    public Member(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }

    // 회원 ID를 반환하는 메소드
    public String getId() {
        return id;
    }

    // 회원 비밀번호를 반환하는 메소드
    public String getPassword() {
        return password;
    }

    // 회원 이름을 반환하는 메소드
    public String getName() {
        return name;
    }
}
