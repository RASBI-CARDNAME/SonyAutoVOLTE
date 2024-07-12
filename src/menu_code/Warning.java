//경고문 출력 코드

package menu_code;

import java.util.Scanner;

import static common_code.Misc.cls;
import static common_code.Misc.enter;


public class Warning {
    public static void warn () {
        //instance
        Scanner input = new Scanner(System.in);

        //variable
        String temp;
        char answer;

        //print message
        while (true) {
            System.out.println("####경고####");
            System.out.println("프로그램 사용으로 인한 모든 손실은 사용자에게 책임이 있습니다.");
            System.out.println("동의 - Y / 비동의 - N");
            System.out.print("입력:");

            //check answer
            try {
                temp = input.nextLine().toLowerCase();

                //검증1 => 길이 체크
                if (temp.length() > 1) {
                    throw new IllegalArgumentException();
                } else {
                    answer = temp.charAt(0);
                }

                //검증2 => 동의 여부 체크
                if (answer == 'y') {
                    break;
                } else if (answer == 'n') {
                    cls();
                    System.out.println("동의하지 않으셨습니다. 프로그램을 종료합니다.");
                    enter();
                    System.exit(0); //종료
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (Exception e) {
                cls();
                System.out.println("잘못된 입력입니다.");
                enter();

                //에러 방지용
                answer = '\u0000';
                cls();
            }
        }
        cls();
    }
}
