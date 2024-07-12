//volte 패치 메뉴 관련 코드

package menu_code;

import act_code.Full_VoLTE;
import act_code.Half_VoLTE;

import java.util.Scanner;
import static common_code.Misc.cls;
import static common_code.Misc.enter;

public class VoLTE_Menu {
    //volte 패치 메뉴
    public static void volte_menu_sel() {
        //instance
        Scanner input = new Scanner(System.in);

        //constance
        final int first_menu_num = 1;
        final int last_menu_num = 3;

        //variable
        int num;

        //select_code
        while (true) {
            //print Menu
            System.out.println("#VoLTE 패치 메뉴 선택#");
            System.out.println("1. 처음부터 끝까지");
            System.out.println("2. 포트 개방부터 마지막까지 (루팅 필요, 파일만 다시 넣기)");
            System.out.println("3. 메뉴로 돌아가기");
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
        cls();

        //선택 코드
        if (num == 1) {
            Full_VoLTE.full_volte_patch(); // 전체 패치
        } else if (num == 2) {
            Half_VoLTE.file_only(); //포트 개방후 패치만
        } else {
            System.out.println("메뉴로 돌아갑니다...");
            enter();
        }
    }

    //부트 img 설치 방법
    public static int boot_num_sel() {
        //instance
        Scanner input = new Scanner(System.in);

        //constance
        final int first_menu_num = 1;
        final int last_menu_num = 2;

        //variable
        int num;

        //select_code
        while (true) {
            //print Menu
            System.out.println("#boot.img 설치 메뉴 선택#");
            System.out.println("1. init_boot로 설치 : 마크5 포함, 그 이상");
            System.out.println("2. boot로 설치 : 마크4 포함, 그 이하");
            System.out.println("(#APATCH 이용 시 2번 선택해주세요#)");
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
        cls();
        return num;
    }

    //심 고정 방법 선택
    public static int sim_fix_check() {
        //instance
        Scanner input = new Scanner(System.in);

        //constance
        final int first_menu_num = 1;
        final int last_menu_num = 2;

        //variable
        int num;

        //select_code
        while (true) {
            //print Menu
            System.out.println("#EFSTOOL 심 고정 메뉴 선택#");
            System.out.println("1. EFS 툴로 심 고정 (강좌글 해당사항이 있다면)");
            System.out.println("2. 심 고정X (나중에 고정 or 해당사항 없음)");
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
        cls();
        return num;
    }

    //재부팅 의사 확인
    public static int check_reboot() {
        //instance
        Scanner input = new Scanner(System.in);

        //constance
        final int first_menu_num = 1;
        final int last_menu_num = 2;

        //variable
        int num;

        //select_code
        while (true) {
            //print Menu
            System.out.println("#PDC 작업을 하셨나요?#");
            System.out.println("1. PDC 작업 후 재부팅 하기");
            System.out.println("2. 그냥 계속 진행 (PDC 사용하지 않았음)");
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
        cls();
        return num;
    }
}