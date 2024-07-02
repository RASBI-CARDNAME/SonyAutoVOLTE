package menu_code;

import static act_code.Boot_img_install.boot_install;
import static act_code.Print_device_info.print_device_info;
import static act_code.Exit_program.exit_program;
import static act_code.Program_Info_Menu.print_program_info;
import static menu_code.VoLTE_Menu.volte_menu_sel;

public class MenuSelector {
    public static void menu_sel(int num,String ver,String date) {
        //main_code
        if (num == 1) {
            print_device_info();
        } else if (num == 2) {
            volte_menu_sel();
        } else if (num == 3) {
            boot_install();
        } else if (num == 4) {
            print_program_info(ver,date);
        } else if (num == 5) {
            exit_program();
        }
    }
}
