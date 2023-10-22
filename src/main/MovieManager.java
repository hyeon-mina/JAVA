import java.util.Scanner;

public class MovieManager {


    private final String[] movieTitles = {
            "오펜하이머", "콘크리트 유토피아", "밀수", "엘리멘탈", "더 문", "비공식작전", "보호자"
    };

   public static final String[][] movieTimes = {
            {"10:00 AM", "2:00 PM", "6:00 PM"},    // 오펜하이머
            {"11:00 AM", "3:00 PM", "7:00 PM"},    // 콘크리트 유토피아
            {"12:00 PM", "4:00 PM", "8:00 PM"},    // 밀수
            {"1:00 PM", "5:00 PM", "9:00 PM"},     // 엘리멘탈
            {"2:00 PM", "6:00 PM", "10:00 PM"},    // 더 문
            {"3:00 PM", "7:00 PM", "11:00 PM"},    // 비공식작전
            {"4:00 PM", "8:00 PM", "12:00 AM"}     // 보호자
    };

    private boolean[][] seatAvailability;
    private final int[] moviePrices = {10000, 12000, 11000, 13000, 9000, 9500, 10500};

    //좌석 값을 초기화 영화 타이틀의 길이별 40 seats
    public MovieManager() {
        seatAvailability = new boolean[movieTitles.length][40];
    }

    public void MovieRankings() {


        System.out.println("1위 제목: 오펜하이머 감독: 크리스토퍼 놀란 예매율: 39.4% 장르: 스릴러,드라마 상영시간: 180분");
        System.out.println("2위 제목: 콘크리트 유토피아 감독: 엄태화 예매율: 18.8% 장르: 드라마 상영시간: 130분");
        System.out.println("3위 제목: 밀수 감독: 류승완 예매율: 8.1% 장르: 범죄 상영시간: 129분");
        System.out.println("4위 제목: 더 문 감독: 김용화 예매율: 4.8% 장르: 상영시간: 128분");
        System.out.println("5위 제목: 비공식작전 감독: 김성훈 예매율: 4.5% 장르: 상영시간: 132분");
        System.out.println("6위 제목: 보호자 감독: 정우성 예매율: 4.4% 장르: 상영시간: 98분");
        System.out.println("7위 제목: 엘리멘탈 감독: 피터 손 예매율: 2.8% 장르: 상영시간: 109분");

    }


    public void makeReservation(Scanner scanner) {
        System.out.println();
        System.out.println("영화관");
        System.out.println();
        System.out.println("1. CGV강남");
        System.out.println("2. CGV건대입구");
        System.out.println("3. CGV동탄");
        System.out.println();
        System.out.print("영화관을 선택하세요>");

        int regionChoice = Integer.parseInt(scanner.nextLine());
        String selectedRegion = getSelectedRegion(regionChoice);

        if (selectedRegion.equals("잘못된 메뉴 선택입니다 다시 한번 입력해주세요.")) {
            System.out.println(selectedRegion);
            makeReservation(scanner);
            return;
        }
        System.out.println("극장: " + selectedRegion);
        System.out.println();
        System.out.println("영화 목록");
        System.out.println();
        System.out.println("1. 영화: 오펜하이머, 감독: 크리스토퍼 놀란");
        System.out.println("2. 영화: 콘크리트 유토피아, 감독: 엄태화");
        System.out.println("3. 영화: 밀수, 감독: 류승완");
        System.out.println("4. 영화: 엘리멘탈, 감독: 피터 손");
        System.out.println("5. 영화: 더 문, 감독: 김용화");
        System.out.println("6. 영화: 비공식작전, 감독: 김성훈");
        System.out.println("7. 영화: 보호자, 감독: 정우성");
        System.out.println();
        System.out.print("영화를 선택하세요:");

        int movieChoice = Integer.parseInt(scanner.nextLine());
        switch (movieChoice) {
            case 1:
                System.out.println();
                System.out.println("제목: 오펜하이머, 감독: 크리스토퍼 놀란");
                showTimeOptions("오펜하이머");
                break;
            case 2:
                System.out.println();
                System.out.println("제목: 콘크리트 유토피아, 감독: 엄태화");
                showTimeOptions("콘크리트 유토피아");
                break;
            case 3:
                System.out.println();
                System.out.println("제목: 밀수, 감독: 류승완");
                showTimeOptions("밀수");
                break;
            case 4:
                System.out.println();
                System.out.println("제목: 엘리멘탈, 감독: 피터 손");
                showTimeOptions("엘리멘탈");
                break;
            case 5:
                System.out.println();
                System.out.println("제목: 더 문, 감독: 김용화");
                showTimeOptions("더 문");
                break;
            case 6:
                System.out.println();
                System.out.println("제목: 비공식작전, 감독: 김성훈");
                showTimeOptions("비공식작전");
                break;
            case 7:
                System.out.println();
                System.out.println("제목: 보호자, 감독: 정우성");
                showTimeOptions("보호자");
                break;
            default:
                System.out.println();
                System.out.println("잘못된 영화 선택입니다 다시 한번 입력해주세요.");
                break;
        }


        int timeChoice = getTimeChoice(scanner);
        int movieIndex = movieChoice - 1;

        if (timeChoice < 1 || timeChoice > movieTimes[movieIndex].length) {
            System.out.println("잘못된 메뉴 선택입니다 다시 한번 입력해주세요.");
            makeReservation(scanner);
            return;
        }

        String selectedTime = movieTimes[movieIndex][timeChoice - 1];
        System.out.println("선택하신 시간은  " + selectedTime +" "+ movieTitles[movieIndex] + "입니다.");

        showSeatAvailability(seatAvailability[movieIndex]);

        System.out.print("원하는 좌석 번호를 선택하세요: ");
        int seatNumber = Integer.parseInt(scanner.nextLine());
        if (seatNumber < 1 || seatNumber > seatAvailability[movieIndex].length) {
            System.out.println("잘못된 좌석 번호입니다. 다시 시도해주세요.");
            makeReservation(scanner);
            return;
        }

        if (!seatAvailability[movieIndex][seatNumber - 1]) {
            seatAvailability[movieIndex][seatNumber - 1] = true;
            int selectedMoviePrice = moviePrices[movieIndex];
            System.out.println("선택하신 좌석의 가격은 " + selectedMoviePrice + "원 입니다.");
            System.out.print("결제를 진행하시겠습니까? (y/n): ");
            String paymentChoice = scanner.nextLine();
            if (paymentChoice.equalsIgnoreCase("y")) {
                System.out.println("결제가 완료되었습니다.");

            } else {
                System.out.println("결제가 취소되었습니다.");
            }
        } else {
            System.out.println("이미 예약된 좌석입니다. 다른 좌석을 선택해주세요.");
        }



    }

    private String getSelectedRegion(int regionChoice) {
        switch (regionChoice) {
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

    private void showTimeOptions(String movieTitle) {
        switch (movieTitle) {
            case "오펜하이머":
                System.out.println("1. 10:00AM");
                System.out.println("2. 2:00PM");
                System.out.println("3. 6:00PM");
                break;
            case "콘크리트 유토피아":
                System.out.println("1. 11:00AM");
                System.out.println("2. 3:00PM");
                System.out.println("3. 7:00PM");
                break;
            case "밀수":
                System.out.println("1. 12:00PM");
                System.out.println("2. 4:00PM");
                System.out.println("3. 8:00PM");
                break;
            case "엘리멘탈":
                System.out.println("1. 1:00PM");
                System.out.println("2. 5:00PM");
                System.out.println("3. 9:00PM");
                break;
            case "더 문":
                System.out.println("1. 2:00PM");
                System.out.println("2. 6:00PM");
                System.out.println("3. 10:00PM");
                break;
            case "비공식작전":
                System.out.println("1. 3:00PM");
                System.out.println("2. 7:00PM");
                System.out.println("3. 11:00PM");
                break;
            case "보호자":
                System.out.println("1. 4:00PM");
                System.out.println("2. 8:00PM");
                System.out.println("3. 12:00AM");
                break;
            default:
                System.out.println("잘못된 메뉴 선택입니다 다시 한번 입력해주세요.");
                break;
        }
    }
    private int getTimeChoice(Scanner scanner) {
        System.out.print("시간을 선택하세요: ");
        int timeChoice = Integer.parseInt(scanner.nextLine());
        return timeChoice;
    }

    private void showSeatAvailability(boolean[] seats) {
        System.out.println("좌석 현황:");
        for (int i = 0; i < seats.length; i++) {
            char status = seats[i] ? 'X' : 'O';
            System.out.printf("%2d번 좌석: %c   ", i + 1, status);
            if ((i + 1) % 10 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }
}