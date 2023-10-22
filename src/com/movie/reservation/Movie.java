package com.movie.reservation;

public class Movie {
    private String title; // 영화 제목
    private String director; // 감독
    private String actor; // 주연 배우
    private double reservationRate; // 예매율
    private String genre; // 장르
    private int duration; // 상영시간 (분)

    // 영화 정보를 초기화하는 생성자
    public Movie(String title, String director, String actor, double reservationRate, String genre, int duration) {
        this.title = title;
        this.director = director;
        this.actor = actor;
        this.reservationRate = reservationRate;
        this.genre = genre;
        this.duration = duration;
    }

    // 영화 제목을 반환하는 메소드
    public String getTitle() {
        return title;
    }

    // 감독 이름을 반환하는 메소드
    public String getDirector() {
        return director;
    }
    
    // 주연 배우 이름을 반환하는 메소드
    public String getActor() {
        return actor;
    }

    // 예매율을 반환하는 메소드
    public double getReservationRate() {
        return reservationRate;
    }

    // 영화 장르를 반환하는 메소드
    public String getGenre() {
        return genre;
    }

    // 상영시간을 반환하는 메소드
    public int getDuration() {
        return duration;
    }
}
