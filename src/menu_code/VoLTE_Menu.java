package menu_code;

import act_code.Do_VoLTE;

import java.util.Scanner;

import static common_code.Misc.cls;
import static common_code.Misc.enter;

public class VoLTE_Menu {
    //select volte patch menu
    public static void volte_menu_sel() {
        //instance
        Scanner input = new Scanner(System.in);
        Do_VoLTE dv = new Do_VoLTE();
        //constance
        final int first_menu_num = 1;
        final int last_menu_num = 3;

        //variable
        int num = 0;
        boolean status = true;

        //select_code
        while (status) {
            //print Menu
            System.out.println("#VoLTE 패치 메뉴 선택#");
            System.out.println("1. 처음부터 끝까지");
            System.out.println("2. 포트 개방부터 마지막까지");
            System.out.println("3. 메뉴로 돌아가기");
            System.out.print("번호 입력:");


            try {
                num = input.nextInt();

                if (num < first_menu_num || num > last_menu_num) {
                    throw new IllegalArgumentException();
                } else {
                    status = false;
                }

            } catch (Exception e) {
                cls();
                System.out.println("잘못된 입력입니다.");
                enter();

                //reset
                num = 0;
            }
        }
        cls();

        //main code
        if (num == 1) {
            Do_VoLTE.full_volte_patch();
        } else if (num == 2) {
            Do_VoLTE.file_only();
        } else if (num == 3) {
            System.out.println("메뉴로 돌아갑니다...");
            enter();
        }
    }

    //select boot img install method
    public static int boot_num_sel() {
        //instance
        Scanner input = new Scanner(System.in);

        //constance
        final int first_menu_num = 1;
        final int last_menu_num = 2;

        //variable
        int num = 0;
        boolean status = true;

        //select_code
        while (status) {
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
                    status = false;

                }

            } catch (Exception e) {
                cls();
                System.out.println("잘못된 입력입니다.");
                enter();
            }
        }
        cls();
        return num;
    }

    //select sim fix
    public static int sim_fix_check() {
        //instance
        Scanner input = new Scanner(System.in);

        //constance
        final int first_menu_num = 1;
        final int last_menu_num = 2;

        //variable
        int num = 0;
        boolean status = true;

        //select_code
        while (status) {
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
                    status = false;
                }

            } catch (Exception e) {
                cls();
                System.out.println("잘못된 입력입니다.");
                enter();

                //reset
                num = 0;
            }
        }
        cls();
        return num;
    }

    //reboot?
    public static int check_reboot() {
        //instance
        Scanner input = new Scanner(System.in);

        //constance
        final int first_menu_num = 1;
        final int last_menu_num = 2;

        //variable
        int num = 0;
        boolean status = true;

        //select_code
        while (status) {
            //print Menu
            System.out.println("#PDC 작업을 하셨나요?#");
            System.out.println("1. PDC 작업 후 재부팅 필요");
            System.out.println("2. 그냥 계속 진행 (PDC 안씀)");
            System.out.print("번호 입력:");

            try {
                num = input.nextInt();

                if (num < first_menu_num || num > last_menu_num) {
                    throw new IllegalArgumentException();
                } else {
                    status = false;
                }

            } catch (Exception e) {
                cls();
                System.out.println("잘못된 입력입니다.");
                enter();

                //reset
                num = 0;
            }
        }
        cls();
        return num;
    }
}
