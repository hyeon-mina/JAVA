import java.util.Scanner;

    public class MemberManager {
        private Member[] members;
        private int memberCount;

        public MemberManager(int maxMembers) {
            members = new Member[maxMembers];
            memberCount = 0;
        }

        public void registerMember(Scanner scanner) {
            System.out.print("ID: ");
            String id = scanner.nextLine();

            if (isDuplicateId(id)) {
                System.out.println("이미 중복된 아이디입니다. 다른 아이디를 생성해 주세요.");
                return;
            }

            System.out.print("Password: ");
            String password = scanner.nextLine();
            System.out.print("이름: ");
            String name = scanner.nextLine();

            members[memberCount++] = new Member(id, password, name);
            System.out.println("가입을 축하드립니다!");
        }

        private boolean isDuplicateId(String id) {
            for (int i = 0; i < memberCount; i++) {
                if (members[i].getId().equals(id)) {
                    return true;
                }
            }
            return false;
        }

        public Member loginMember(Scanner scanner) {
            System.out.print("ID: ");
            String id = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            for (int i = 0; i < memberCount; i++) {
                if (members[i].getId().equals(id) && members[i].getPassword().equals(password)) {
                    return members[i];
                }
            }

            return null;
        }
    }

