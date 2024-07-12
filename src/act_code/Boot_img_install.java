//부트 img 설치 코드

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

        //1. 부트로더로 재부팅
        System.out.println("fastboot로 진입합니다...");
        adb.adb_command("reboot", "bootloader");
        cls();

        //2. 드라이버 확인
        System.out.println("장치 관리자에서 fastboot 드라이버가 잡혔는지 확인해주세요.");
        enter();
        cls();

        //3. boot img(init) 파일 선택
        System.out.println("magisk 패치된 boot.img(또는 init_boot) 파일을 선택해주세요.");
        enter();

        file_path = selectFilePath();
        cls();

        //4. 선택한 파일 확인
        System.out.println("선택한 파일:");
        System.out.println(file_path);
        enter();
        cls();

        //5. boot 설치 장소 확인
        boot_menu_num = boot_num_sel();

        if (boot_menu_num == 1) { //init_boot에 설치
            fs.fastboot_command("flash","init_boot","\""+file_path+"\"");
        } else if (boot_menu_num == 2) { //boot에 설치 (a, b 파티션에)
            fs.fastboot_command("flash","boot_b","\""+file_path+"\"");
            fs.fastboot_command("flash","boot_a","\""+file_path+"\"");
        }

        //6. 부트로더 탈출
        fs.fastboot_reboot();

        //7. boot 설치 완료 메시지 출력
        System.out.println("boot 파일 설치 완료!");

        enter();
        cls();
    }
}
