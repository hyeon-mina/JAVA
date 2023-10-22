package com.movie.reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieManager {
    private List<Movie> movies; // 영화정보를 저장하는 리스트
    private boolean[][] seatAvailability; // 영화별 좌석 예약 여부를 저장하는 배열
    private final int[] moviePrices = {10500, 13000, 12000, 11500, 12500, 11000, 10000}; // 영화별 가격(시간대별로 정의)
    private final String[][] movieTimes = { // 각 영화별 상영 시간표
            {"10:00 AM", "2:00 PM", "6:00 PM"},    // 천박사 퇴마 연구소-설경의 비밀
            {"11:00 AM", "3:00 PM", "7:00 PM"},    // 크리에이터
            {"12:00 PM", "4:00 PM", "8:00 PM"},    // 1947 보스톤
            {"1:00 PM", "5:00 PM", "9:00 PM"},     // 거미집
            {"2:00 PM", "6:00 PM", "10:00 PM"},    // 사나-저주의 아이
            {"3:00 PM", "7:00 PM", "11:00 PM"},    // 더넌 2
            {"4:00 PM", "8:00 PM", "12:00 AM"}     // 잠
    };

    private PaymentManager paymentManager; // 결제 관리자

    public MovieManager() {
        movies = new ArrayList<>(); // 영화 정보를 저장하는 리스트 초기화
        movies.add(new Movie("천박사 퇴마 연구소", "김성식", "강동원, 허준호, 이솜", 8.5, "드라마", 98));
        movies.add(new Movie("크리에이터", "가렛 에드워즈", "존 데이비드 워싱턴, 젬마 찬, 와타나베 켄", 4.7, "SF, 액션", 133));
        movies.add(new Movie("1947 보스톤", "강제규", "하정우, 임시완, 배성우", 4.5, "드라마", 108));
        movies.add(new Movie("사나-저주의 아이", "시미즈 다카시", "호시 토모코", 3.5, "호러", 102));
        movies.add(new Movie("거미집", "김지운", "송강호, 임수정, 오정세", 3.3, "코미디, 드라마", 132));
        movies.add(new Movie("더넌 2", "마이클 차베즈", "타이사 파미가, 보니 아론스, 조나스 블로켓", 1.5, "호러", 110));
        movies.add(new Movie("잠", "유재선", "정유미, 이선균", 0.5, "미스테리", 94));
        // 다른 영화 정보 

        // 각 영화별 40개의 좌석 초기화
        seatAvailability = new boolean[movies.size()][40];

        paymentManager = new PaymentManager(); // 결제 관리자 초기화
    }

    // 영화 랭킹을 출력하는 메소드
    public void movieRankings() {
        System.out.println("영화 랭킹:");
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            System.out.println((i + 1) + "위 - 제목: " + movie.getTitle() + ", 감독: " + movie.getDirector()
                    + ", 배우: " + movie.getActor() + ", 예매율: " + movie.getReservationRate() +
                    "%, 장르: " + movie.getGenre() + ", 상영시간: " + movie.getDuration() + "분");
        }
    }

    // 영화 예약을 처리하는 메소드
    public void makeReservation(Scanner scanner, Member member) {
        // 상영관 선택
        System.out.println("상영관을 선택하세요:");
        System.out.println("1. CGV강남");
        System.out.println("2. CGV건대입구");
        System.out.println("3. CGV동탄");
        int theaterChoice = Integer.parseInt(scanner.nextLine());
        String selectedTheater = getSelectedTheater(theaterChoice);

        if (selectedTheater.equals("잘못된 메뉴 선택입니다 다시 한번 입력해주세요.")) {
            System.out.println(selectedTheater);
            return;
        }
        System.out.println("극장: " + selectedTheater);

        // 영화 선택
        System.out.println("영화 목록:");
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            System.out.println((i + 1) + ". " + movie.getTitle());
        }
        System.out.print("영화를 선택하세요: ");
        int movieChoice = Integer.parseInt(scanner.nextLine()) - 1;
        if (movieChoice < 0 || movieChoice >= movies.size()) {
            System.out.println("잘못된 영화 선택입니다.");
            return;
        }
        Movie selectedMovie = movies.get(movieChoice);
        System.out.println("선택한 영화: " + selectedMovie.getTitle());

        // 영화 시간 선택
        System.out.println("영화 시간을 선택하세요:");
        for (int i = 0; i < movieTimes[movieChoice].length; i++) {
            System.out.println((i + 1) + ". " + movieTimes[movieChoice][i]);
        }
        System.out.print("시간을 선택하세요: ");
        int timeChoice = Integer.parseInt(scanner.nextLine()) - 1;
        if (timeChoice < 0 || timeChoice >= movieTimes[movieChoice].length) {
            System.out.println("잘못된 시간 선택입니다.");
            return;
        }
        String selectedTime = movieTimes[movieChoice][timeChoice];
        System.out.println("선택한 시간: " + selectedTime);

        // 인원수 입력
        System.out.print("인원수를 입력하세요: ");
        int numberOfPeople = Integer.parseInt(scanner.nextLine());
        if (numberOfPeople <= 0) {
            System.out.println("잘못된 인원수 입력입니다.");
            return;
        }
        System.out.println("인원수: " + numberOfPeople + "명");

        // 좌석 선택
        System.out.println("좌석을 선택하세요.");
        showSeatAvailability(seatAvailability[movieChoice]);
        List<Integer> selectedSeats = new ArrayList<>();
        for (int i = 0; i < numberOfPeople; i++) {
            System.out.print("좌석 번호 " + (i + 1) + ": ");
            int seatNumber = Integer.parseInt(scanner.nextLine()) - 1;

            if (seatNumber < 0 || seatNumber >= seatAvailability[movieChoice].length || seatAvailability[movieChoice][seatNumber]) {
                System.out.println("잘못된 좌석 번호이거나 이미 예약된 좌석입니다.");
                return;
            }
            selectedSeats.add(seatNumber);
        }
        System.out.println("선택한 좌석: " + selectedSeats.toString());

        // 결제 절차
        int totalAmount = numberOfPeople * moviePrices[movieChoice];
        System.out.println("총 결제 금액: " + totalAmount + "원");
        System.out.print("결제를 진행하시겠습니까? (y/n): ");
        String paymentChoice = scanner.nextLine();
        if (paymentChoice.equalsIgnoreCase("y")) {
            paymentManager.processPayment(scanner, totalAmount); // 결제 처리 메소드 호출

            // 예약 정보 업데이트
            for (int seat : selectedSeats) {
                seatAvailability[movieChoice][seat] = true;
            }

            // 여기에서 회원 정보에 예매 내역을 추가하는 등의 작업을 수행할 수 있습니다.
        } else {
            System.out.println("결제가 취소되었습니다.");
        }
    }

    private String getSelectedTheater(int theaterChoice) {
        switch (theaterChoice) {
            case 1:
                return "CGV강남";
            case 2:
                return "CGV건대입구";
            case 3:
                return "CGV동탄";
            default:
                return "잘못된 메뉴 선택입니다 다시 한번 입력해주세요.";
        }
    }

    private void showSeatAvailability(boolean[] seats) {
        System.out.println("좌석 현황:");
        for (int i = 0; i < seats.length; i++) {
            char status = seats[i] ? '▣' : '□';
            System.out.printf("%2d번 좌석: %c   ", i + 1, status);
            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }
}
