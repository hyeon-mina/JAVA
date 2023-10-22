package com.movie.reservation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MemberManager memberManager = new MemberManager(); // 회원 관리 객체 생성
        MovieManager movieManager = new MovieManager();    // 영화 관리 객체 생성

        boolean isRunning = true;    // 프로그램 실행 여부를 나타내는 변수
        Member loggedInMember = null; // 현재 로그인한 회원 정보를 저장할 변수

        while (isRunning) {
            // 메뉴를 출력
            System.out.println("---------------------");
            System.out.println("안녕하세요 CGV입니다.");
            System.out.println();
            System.out.println("1. 회원 가입");
            System.out.println("2. 로그인");
            System.out.println("3. 영화 랭킹");
            System.out.println("4. 영화 예매");
            System.out.println("5. 로그아웃");
            System.out.println("6. 종료");
            System.out.println("---------------------");
            System.out.print("메뉴 선택> ");
            int menu = Integer.parseInt(scanner.nextLine());

            switch (menu) { //switch 반복문을 사용해서 선택하기 편하게 만듬
                case 1:
                    memberManager.registerMember(scanner); // 회원 가입 메소드 호출
                    break;
                case 2:
                    loggedInMember = memberManager.loginMember(scanner); // 로그인 메소드 호출
                    if (loggedInMember != null) {
                        System.out.println(loggedInMember.getName() + "님, 환영합니다!"); // 로그인시 회원 성함과 함께 환영메시지 출력
                    } else {
                        System.out.println("로그인에 실패하였습니다."); // 로그인 실패시 출력될 메시지
                    }
                    break;
                case 3:
                    movieManager.movieRankings(); // 영화 랭킹 메소드 호출 : 로그인을 하지 않아도 랭킹은 확인 가능
                    break;
                case 4:
                    if (loggedInMember != null) {
                        movieManager.makeReservation(scanner, loggedInMember); // 영화 예매 메소드 호출
                    } else {
                        System.out.println("로그인 후에 이용해주세요."); //로그인을 하지 않을 경우 예매 불가능
                    }
                    break;
                case 5:
                    loggedInMember = null; // 로그아웃 시 현재 로그인한 회원 정보 초기화
                    System.out.println("로그아웃되었습니다.");
                    break;
                case 6:
                    isRunning = false; // 프로그램 종료
                    break;
                default:
                    System.out.println("잘못된 메뉴 선택입니다. 다시 선택해주세요.");
                    break;
            }
        }
    }
}
