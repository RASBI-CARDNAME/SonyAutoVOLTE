/*Made By RASBI
VERSION 1.0
 */


import static common_code.Misc.cls;
import static menu_code.MainMenu.load_menu;
import static menu_code.MenuSelector.menu_sel;
import static menu_code.Warning.warn;

public class MainCode {
    public static void main(String[] args) {
        //constant
        final String version_num = "1.1.0";
        final String version_date = "2024-07-03";

        //warn_message
        warn();

        //print_menu
        while (true) {
            menu_sel(load_menu(),version_num,version_date);
            cls();
        }
    }
}