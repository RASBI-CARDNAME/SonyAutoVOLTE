package menu_code;

import java.util.*;

import static common_code.Misc.cls;
import static common_code.Misc.enter;

public class MainMenu {
    public static int load_menu() {
        //instance
        Scanner input = new Scanner(System.in);

        //constance
        final int first_menu_num = 1;
        final int last_menu_num = 5;

        //variable
        int num;

        //main_code
        while (true) {
            //print Menu
            System.out.println("#메뉴 선택#");
            System.out.println("1. 기기 정보");
            System.out.println("2. VoLTE 패치하기");
            System.out.println("3. boot.img 설치하기");
            System.out.println("4. 기타");
            System.out.println("5. 종료");
            System.out.print("번호 입력:");

            try {
                num = input.nextInt();

                if (num < first_menu_num || num > last_menu_num) {
                    throw new IllegalArgumentException();
                } else {
                    break;
                }

            } catch (Exception e) {
                cls();
                System.out.println("잘못된 입력입니다.");
                enter();

                //에러 방지용
                num = 0;
            }
        }

        //루프 탈출
        cls();
        return num;
    }
}
