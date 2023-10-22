package com.movie.reservation;

import java.util.Scanner;

public class PaymentManager {
    public void processPayment(Scanner scanner, int totalAmount) {
        System.out.println("결제 방법을 선택하세요:");
        System.out.println("1. 계좌 이체");
        System.out.println("2. 신용 카드");
        System.out.println("3. 카카오페이");
        System.out.println("4. 삼성페이");
        int paymentChoice = Integer.parseInt(scanner.nextLine());

        switch (paymentChoice) {
            case 1:
                System.out.println("계좌 이체가 완료되었습니다.");
                break;
            case 2:
                System.out.println("신용 카드 결제가 완료되었습니다.");
                break;
            case 3:
                System.out.println("카카오페이 결제가 완료되었습니다.");
                break;
            case 4:
                System.out.println("삼성페이 결제가 완료되었습니다.");
                break;
            default:
                System.out.println("잘못된 결제 방법 선택입니다.");
                return;
        }
    }
}
