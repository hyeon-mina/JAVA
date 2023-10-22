import java.util.Scanner;

public class Main {

    /**
     * 중복해서 객체가 생성되어 좌석 값이 초기화 되기 때문에 static을 사용하여 상수로 만들어 좌석의 값이 초기화 되지않는다
     */
    public static MovieManager movieManager = new MovieManager();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        MemberManager memberManager = new MemberManager(100);

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("---------------------");
            System.out.println("안녕하세요 CGV입니다.");
            System.out.println();
            System.out.println("1. 회원 가입");
            System.out.println("2. 로그인");
            System.out.println("3. 종료");
            System.out.println("---------------------");
            System.out.print("메뉴 선택> ");
            int menu = Integer.parseInt(scanner.nextLine());
            switch (menu) {
                case 1:
                    memberManager.registerMember(scanner);
                    break;
                case 2:
                    Member loginMember = memberManager.loginMember(scanner);
                    if (loginMember != null) {
                        System.out.println(loginMember.getName() + "님, 환영합니다!");
                        loginMenu(scanner);
                    } else {
                        System.out.println("로그인에 실패하였습니다.");
                    }

                    break;
                case 3:
                    isRunning = false;
                    break;
                default:
                    System.out.println("잘못된 메뉴 선택입니다 다시 한번 입력해주세요.");
                    break;
            }
        }
    }

    private static void loginMenu(Scanner scanner) {

        /*로그인 할때 마다 새로 객체가 생성됨.(좌석 정보가 초기화됨)*/
       //MovieManager movieManager = new MovieManager();

        boolean loginMenu = true;
        while (loginMenu) {
            System.out.println("---------------------");
            System.out.println("1. 영화 랭킹");
            System.out.println("2. 영화 예매");
            System.out.println("3. 뒤로 가기");
            System.out.println("---------------------");
            System.out.print("메뉴 선택> ");
            int loggedInOption = Integer.parseInt(scanner.nextLine());

            switch (loggedInOption) {
                case 1:
                    movieManager.MovieRankings();
                    break;
                case 2:
                    movieManager.makeReservation(scanner);
                    break;
                case 3:
                    loginMenu = false;
                    break;
                default:
                    System.out.println("잘못된 메뉴 선택입니다  다시 한번 입력해주세요.");
                    break;
            }
        }
    }
}