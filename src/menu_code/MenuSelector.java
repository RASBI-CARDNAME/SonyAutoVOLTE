//메뉴 선택기

package menu_code;

import static act_code.Boot_img_install.boot_install;
import static act_code.Print_device_info.print_device_info;
import static act_code.Exit_program.exit_program;
import static act_code.Program_Info_Menu.print_program_info;
import static menu_code.VoLTE_Menu.volte_menu_sel;

public class MenuSelector {
    public static void menu_sel(int num) {
        //메인 코드
        if (num == 1) {
            print_device_info();
        } else if (num == 2) {
            volte_menu_sel();
        } else if (num == 3) {
            boot_install();
        } else if (num == 4) {
            print_program_info();
        } else if (num == 5) {
            exit_program();
        }
    }
}
