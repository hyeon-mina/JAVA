package com.movie.reservation;

import java.util.Scanner;

public class MemberManager {
    private Member[] members; //회원 정보 저장하는 배열
    private int memberCount; // 현재 등록된 회원 수

    public MemberManager() {
        members = new Member[100]; //최대 100명의 회원 저장할 수 있다.
        memberCount = 0; //초기 회원 수를 0으로 설정
    }
    	// 회원 가입 메소드
    public void registerMember(Scanner scanner) {
        System.out.print("ID: ");
        String id = scanner.nextLine();
        
        // 아이디 중복 여부 확인
        if (isDuplicateId(id)) {
            System.out.println("이미 중복된 아이디입니다. 다른 아이디를 생성해 주세요.");
            return;
        }

        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("이름: ");
        String name = scanner.nextLine();

        // 회원 객체를 배열에 추가하고 회원 수 증가
        members[memberCount++] = new Member(id, password, name);
        System.out.println(name + "님, 가입을 축하합니다!");
    }

    private boolean isDuplicateId(String id) {
        for (int i = 0; i < memberCount; i++) {
            if (members[i].getId().equals(id)) {
                return true; // 중복된 아이디가 존재하면 true 반환
            }
        }
        return false;// 중복된 아이디가 없으면 false 반환
    }
    
    // 회원 로그인 메소드
    public Member loginMember(Scanner scanner) {
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

     // 입력한 아이디와 패스워드와 일치하는 회원 찾기
        for (int i = 0; i < memberCount; i++) {
            if (members[i].getId().equals(id) && members[i].getPassword().equals(password)) {
                return members[i]; // 일치하는 회원을 반환
            }
        }

        return null; //불일치시 null값 반환
    }
}
