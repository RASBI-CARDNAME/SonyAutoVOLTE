package act_code;

import common_code.ADB;
import common_code.FASTBOOT;

import static common_code.Misc.*;
import static common_code.Misc.cls;
import static menu_code.VoLTE_Menu.boot_num_sel;

public class Boot_img_install {
    public static void boot_install() {
        //instance
        ADB adb = new ADB();
        FASTBOOT fs = new FASTBOOT();

        //variable
        String file_path;
        int boot_menu_num;

        //reboot bootloader
        System.out.println("fastboot로 진입합니다...");
        enter();
        cls();
        adb.adb_command("reboot", "bootloader");


        //check driver
        System.out.println("장치 관리자에서 fastboot 드라이버가 잡혔는지 확인해주세요.");
        enter();
        cls();

        //select boot.img file
        System.out.println("설치 할 boot.img(또는 init_boot) 파일을 선택해주세요.");
        enter();

        file_path = selectFilePath();
        cls();

        System.out.println("선택한 파일:");
        System.out.println(file_path);
        enter();

        boot_menu_num = boot_num_sel();

        //boot or init_boot?
        if (boot_menu_num == 1) { //init_boot
            fs.fastboot_command("flash","init_boot","\""+file_path+"\"");
        } else if (boot_menu_num == 2) { //boot_b / boot_a
            fs.fastboot_command("flash","boot_b","\""+file_path+"\"");
            fs.fastboot_command("flash","boot_a","\""+file_path+"\"");
        }

        //exit fastboot
        fs.fastboot_reboot();
        cls();

        System.out.println("설치 완료!");
        enter();
        cls();
    }
}
