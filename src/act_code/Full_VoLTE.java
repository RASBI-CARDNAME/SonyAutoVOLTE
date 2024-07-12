//volte 전체 패치 코드
package act_code;

import common_code.ADB;
import common_code.EFS;
import common_code.FASTBOOT;

import static common_code.Misc.*;
import static menu_code.VoLTE_Menu.*;

public class Full_VoLTE extends Message {
    public static void full_volte_patch() {
        //instance
        ADB adb = new ADB();
        FASTBOOT fs = new FASTBOOT();
        EFS efs = new EFS();

        //variable
        String file_path;
        String efstools_folder_path;
        String efsfile_folder_path;

        int boot_menu_num;
        int simfix_menu_num;
        int rebootmenu_num;

        //1. boot.img 설치전 메시지 출력
        warn_1();

        //2. 부트로더로 재부팅
        System.out.println("fastboot로 진입합니다...");
        adb.adb_command("reboot", "bootloader");
        cls();

        //3. 드라이버 확인
        System.out.println("장치 관리자에서 fastboot 드라이버가 잡혔는지 확인해주세요.");
        enter();
        cls();

        //4. boot img(init) 파일 선택
        System.out.println("magisk 패치된 boot.img(또는 init_boot) 파일을 선택해주세요.");
        enter();

        file_path = selectFilePath();
        cls();

        //5. 선택한 파일 확인
        System.out.println("선택한 파일:");
        System.out.println(file_path);
        enter();

        //6. boot 설치 장소 확인
        boot_menu_num = boot_num_sel();
        cls();

        if (boot_menu_num == 1) { //init_boot에 설치
            fs.fastboot_command("flash","init_boot","\""+file_path+"\"");
        } else if (boot_menu_num == 2) { //boot에 설치 (a, b 파티션에)
            fs.fastboot_command("flash","boot_b","\""+file_path+"\"");
            fs.fastboot_command("flash","boot_a","\""+file_path+"\"");
        }

        //7. 부트로더 탈출
        fs.fastboot_reboot();

        //8. magisk boot 설치 완료 메시지 출력
        System.out.println("boot 파일 설치 완료!");
        System.out.println("기기가 부팅될 때까지 기다려 주세요.");

        enter();
        cls();

        //9. EFS 사용 메시지 출력
        warn_2();

        //10. 포트 개방
        adb.efs_port_open();

        //11. 포트 개방을 위한 디버깅 off on 메시지 출력
        warn_3();

        //12. EFS 툴 사용 시작

        System.out.println("이제 EFSTOOLS를 사용합니다.");
        enter();
        cls();

        //13. 다운받은 EFS 툴 폴더 선택
        System.out.println("EFSTOOLS가 있는 폴더를 선택해주세요");
        enter();

        efstools_folder_path = selectFolderPath();
        cls();

        //14. 유심 고정 여부 선택
        simfix_menu_num = sim_fix_check();

        if (simfix_menu_num == 1) { //고정 한다면 => 1번심만 지원하므로 1번심만 고정
            //EFS 툴을 통해 파일 업로드
            efs.EFS_writefile("\""+efstools_folder_path+"\\EfsTools.exe\"","\""+efstools_folder_path+"\\mcfg_autoselect_by_uim\"","\"/nv/item_files/mcfg/mcfg_autoselect_by_uim\"");

            System.out.println("유심 고정 완료!");
            System.out.println("재부팅이 필요합니다...");
            enter();
            cls();

            //재부팅
            adb.adb_command("reboot","");

            //재부팅 후 포트 다시 개방
            System.out.println("포트 재개방이 필요합니다. 부팅되면 엔터를 눌러주세요.");
            enter();
            cls();

            //포트 개방 실시
            adb.efs_port_open();
            System.out.println("포트 개방 완료. 디버깅 옵션을 껏다가 켜주세요.");
            enter();

        } else if (simfix_menu_num == 2) { //고정 하지 않는다면
            System.out.println("고정 작업을 넘어갑니다...");
            enter();
        }
        cls();

        //15. PDC 사용 매시지 출력
        pdc_message();

        //16. PDC 작업 유무 확인
        rebootmenu_num = check_reboot();

        if (rebootmenu_num == 1) {
            adb.adb_command("reboot","");
            cls();

            //포트 재개방
            System.out.println("포트 재개방이 필요합니다. 부팅되면 엔터를 눌러주세요.");
            enter();
            cls();

            //포트 개방
            adb.efs_port_open();

            System.out.println("포트 개방 완료. 디버깅 옵션을 껏다가 켜주세요.");
            enter();
            cls();

            // 추가 PDC 작업 필요한지 확인
            pdc_message2();

        } else if (rebootmenu_num == 2) {
            System.out.println("재부팅 안하기를 선택하셨습니다.");
            enter();
        }
        cls();

        //17. 통신사 파일 업로드
        System.out.println("EFS 파일을 업로드 합니다..");
        enter();
        cls();

        //18. 통신사 폴더 선택
        System.out.println("통신사에 맞는 EFS 폴더를 선택해주세요.");
        System.out.println("nv / data 폴더 등이 보이는 곳까지 이동 후, 선택해 주시면 됩니다.");
        enter();

        efsfile_folder_path = selectFolderPath();
        cls();

        //19. 폴더 확인
        System.out.println("선택한 패치파일 경로:");
        System.out.println(efsfile_folder_path);

        System.out.println("패치를 시작하려면 엔터를 눌러주세요");
        enter();
        cls();

        //20. 패치 시작. 총 4번 돌림
        System.out.println("패치는 총 4번 하게 됩니다. 현재 패치 중 입니다..기다려주세요");
        efs.EFS_upload_folder("\""+efstools_folder_path+"\\EfsTools.exe\"","\""+efsfile_folder_path+"\"");

        cls();
        System.out.println("##안내사항!##");
        System.out.println("포트 에러가 나셨나요? EFSTOOLS가 있는 폴더로 가서");
        System.out.println("EfsTools.dll.config 파일을 연 다음, efstool port=\"\" 부분에 포트를 수동으로 지정해보세요");
        System.out.println("예제=> efstool port=\"COM8\"");
        enter();

        //21. 나머지 3번 반복 업로드 (파일 누락 방지용)
        efs.EFS_upload_folder("\""+efstools_folder_path+"\\EfsTools.exe\"","\""+efsfile_folder_path+"\"");
        efs.EFS_upload_folder("\""+efstools_folder_path+"\\EfsTools.exe\"","\""+efsfile_folder_path+"\"");
        efs.EFS_upload_folder("\""+efstools_folder_path+"\\EfsTools.exe\"","\""+efsfile_folder_path+"\"");

        //22. volte 메뉴 버튼 생성
        adb.volte_toggle();

        //23. 재부팅
        adb.adb_command("reboot","");
        cls();

        //24. 패치 완료 메시지 출력
        System.out.println("패치 완료!");
        System.out.println("이후 설정에서 volte 토글 활성화 하는것을 잊지 말아주세요");
        enter();
    }
}
