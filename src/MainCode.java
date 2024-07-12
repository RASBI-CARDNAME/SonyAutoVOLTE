/*Made By RASBI
VERSION 1.0
 */


import static common_code.Misc.cls;
import static menu_code.MainMenu.load_menu;
import static menu_code.MenuSelector.menu_sel;
import static menu_code.Warning.warn;

public class MainCode {
    public static void main(String[] args) {

        //경고 메시지 출력
        warn();

        //메인 메뉴
        while (true) {
            menu_sel(load_menu());
            cls();
        }
    }
}