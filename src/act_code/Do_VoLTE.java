package act_code;
///////////////////////////////
import common_code.ADB;
import common_code.EFS;
import common_code.FASTBOOT;

import static act_code.Message.*;
import static common_code.Misc.*;
import static menu_code.VoLTE_Menu.*;
///////////////////////////////
public class Do_VoLTE {
    ///////////////////////////////
    //full patch
    // boot.img => open port => use EFSTOOLS
    ///////////////////////////////
    public static void full_volte_patch() {
        ///////////////////////////////
        //instance
        ///////////////////////////////
        ADB adb = new ADB();
        FASTBOOT fs = new FASTBOOT();
        EFS efs = new EFS();
        ///////////////////////////////
        //variable
        ///////////////////////////////
        String file_path;
        String efstools_folder_path;
        String efsfile_folder_path;

        int boot_menu_num;
        int simfix_menu_num;
        int rebootmenu_num;

        ///////////////////////////////
        //1. install boot.img
        ///////////////////////////////
        warn_1();

        //reboot bootloader
        System.out.println("fastboot로 진입합니다...");
        adb.adb_command("reboot", "bootloader");
        cls();

        //check driver
        System.out.println("장치 관리자에서 fastboot 드라이버가 잡혔는지 확인해주세요.");
        enter();
        cls();

        //select boot.img file
        System.out.println("magisk 패치된 boot.img(또는 init_boot) 파일을 선택해주세요.");
        enter();

        file_path = selectFilePath();
        System.out.println(file_path);
        cls();

        System.out.println("선택한 파일:");
        System.out.println(file_path);
        enter();

        //boot or init_boot?
        boot_menu_num = boot_num_sel();

        if (boot_menu_num == 1) { //init_boot
            fs.fastboot_command("flash","init_boot","\""+file_path+"\"");
        } else if (boot_menu_num == 2) { //boot_b / boot_a
            fs.fastboot_command("flash","boot_b","\""+file_path+"\"");
            fs.fastboot_command("flash","boot_a","\""+file_path+"\"");
        }

        //exit fastboot
        fs.fastboot_reboot();

        //complete boot img installation
        System.out.println("boot 파일 설치 완료!");
        System.out.println("기기가 부팅될 때까지 기다려 주세요.");
        System.out.println("");

        enter();
        cls();

        //efs port open message
        warn_2();

        //open port
        adb.efs_port_open();

        //debug toggle message
        warn_3();

        ///////////////////////////////
        //2. Using EFSTOOLS
        ///////////////////////////////
        System.out.println("이제 EFSTOOLS를 사용합니다.");
        System.out.println("");

        enter();
        cls();

        //GET EFSTOOLS path
        System.out.println("EFSTOOLS가 있는 폴더를 선택해주세요");
        System.out.println("");

        enter();

        efstools_folder_path = selectFolderPath();
        cls();

        //check EFSTOOLS SIM FIX
        simfix_menu_num = sim_fix_check();

        if (simfix_menu_num == 1) { //fix
            //upload file
            efs.EFS_writefile("\""+efstools_folder_path+"\\EfsTools.exe\"","\""+efstools_folder_path+"\\mcfg_autoselect_by_uim\"","\"/nv/item_files/mcfg/mcfg_autoselect_by_uim\"");
            efs.EFS_writefile("\""+efstools_folder_path+"\\EfsTools.exe\"","\""+efstools_folder_path+"\\mcfg_autoselect_by_uim\"","\"/nv/item_files/mcfg/mcfg_autoselect_by_uim_Subscription01\"");

            System.out.println("유심 고정 완료!");
            System.out.println("재부팅이 필요합니다...");
            System.out.println("");

            enter();
            cls();

            //reboot
            adb.adb_command("reboot","");

            //port re open
            System.out.println("포트 재개방이 필요합니다. 부팅되면 엔터를 눌러주세요.");
            System.out.println("");

            enter();
            cls();

            //open port
            adb.efs_port_open();
            System.out.println("포트 개방 완료. 디버깅 옵션을 껏다가 켜주세요.");
            System.out.println("");

            enter();

        } else if (simfix_menu_num == 2) { //if not fix
            System.out.println("고정 작업을 넘어갑니다...");
            System.out.println("");

            enter();
        }
        cls();

        ///////////////////////////////
        //PDC?
        ///////////////////////////////
        pdc_message();
        rebootmenu_num = check_reboot();
        if (rebootmenu_num == 1) {
            adb.adb_command("reboot","");
            cls();

            //port re open
            System.out.println("포트 재개방이 필요합니다. 부팅되면 엔터를 눌러주세요.");
            System.out.println("");

            enter();
            cls();

            adb.efs_port_open();
            System.out.println("포트 개방 완료. 디버깅 옵션을 껏다가 켜주세요.");
            System.out.println("");

            enter();
            cls();

            pdc_message2();

        } else if (rebootmenu_num == 2) {
            System.out.println("재부팅 안하기를 선택하셨습니다.");
            System.out.println("");

            enter();
        }
        cls();

        //EFS file install
        System.out.println("EFS 파일을 업로드 합니다..");
        System.out.println("");

        enter();
        cls();

        System.out.println("통신사에 맞는 EFS 폴더를 선택해주세요.");
        System.out.println("nv / data 폴더 등이 보이는 곳까지 이동 후, 선택해 주시면 됩니다.");
        System.out.println("");

        enter();

        efsfile_folder_path = selectFolderPath();

        cls();

        System.out.println("선택한 패치파일 경로:");
        System.out.println(efsfile_folder_path);
        System.out.println("패치를 시작하려면 엔터를 눌러주세요");
        System.out.println("");
        enter();

        cls();

        System.out.println("패치는 총 4번 하게 됩니다. 현재 패치 중 입니다..기다려주세요");
        efs.EFS_upload_folder("\""+efstools_folder_path+"\\EfsTools.exe\"","\""+efsfile_folder_path+"\"");

        cls();
        System.out.println("##안내사항!##");
        System.out.println("포트 에러가 나셨나요? EFSTOOLS가 있는 폴더로 가서");
        System.out.println("EfsTools.dll.config 파일을 연 다음, efstool port=\"\" 부분에 포트를 수동으로 지정해보세요");
        System.out.println("예제=> efstool port=\"COM8\"");
        System.out.println("");

        enter();

        efs.EFS_upload_folder("\""+efstools_folder_path+"\\EfsTools.exe\"","\""+efsfile_folder_path+"\"");
        efs.EFS_upload_folder("\""+efstools_folder_path+"\\EfsTools.exe\"","\""+efsfile_folder_path+"\"");
        efs.EFS_upload_folder("\""+efstools_folder_path+"\\EfsTools.exe\"","\""+efsfile_folder_path+"\"");

        adb.volte_toggle();

        adb.adb_command("reboot","");
        cls();

        System.out.println("패치 완료!");
        System.out.println("이후 설정에서 volte 토글 활성화 하는것을 잊지 말아주세요");
        System.out.println("");

        enter();
    }

    //do patch => efstools only
    public static void file_only() {
        //instance
        ADB adb = new ADB();
        EFS efs = new EFS();

        //variable
        String efstools_folder_path;
        String efsfile_folder_path;

        //EFS file install
        //port re open
        System.out.println("포트 재개방이 필요합니다.");
        System.out.println("");

        enter();

        warn_2();

        adb.efs_port_open();
        System.out.println("포트 개방 완료. 디버깅 옵션을 껏다가 켜주세요.");
        System.out.println("");

        enter();
        cls();

        System.out.println("EFS 파일을 업로드 합니다..");
        System.out.println("");

        enter();
        cls();

        System.out.println("EFSTOOLS 폴더를 선택해주세요.");
        System.out.println("");

        enter();

        efstools_folder_path = selectFolderPath();
        cls();

        System.out.println("통신사에 맞는 EFS 폴더를 선택해주세요.");
        System.out.println("nv / data 폴더 등이 보이는 곳까지 이동 후, 선택해 주시면 됩니다.");
        System.out.println("");

        enter();

        efsfile_folder_path = selectFolderPath();

        cls();

        System.out.println("선택한 패치파일 경로:");
        System.out.println(efsfile_folder_path);
        System.out.println("패치를 시작하려면 엔터를 눌러주세요");
        System.out.println("");
        enter();

        cls();

        System.out.println("패치는 총 4번 하게 됩니다. 현재 패치 중 입니다..기다려주세요");
        efs.EFS_upload_folder("\""+efstools_folder_path+"\\EfsTools.exe\"","\""+efsfile_folder_path+"\"");

        System.out.println("##안내사항!##");
        System.out.println("포트 에러가 나셨나요? EFSTOOLS가 있는 폴더로 가서");
        System.out.println("EfsTools.dll.config 파일을 연 다음, efstool port=\"\" 부분에 포트를 수동으로 지정해보세요");
        System.out.println("예제=> efstool port=\"COM8\"");
        System.out.println("");

        enter();


        efs.EFS_upload_folder("\""+efstools_folder_path+"\\EfsTools.exe\"","\""+efsfile_folder_path+"\"");
        efs.EFS_upload_folder("\""+efstools_folder_path+"\\EfsTools.exe\"","\""+efsfile_folder_path+"\"");
        efs.EFS_upload_folder("\""+efstools_folder_path+"\\EfsTools.exe\"","\""+efsfile_folder_path+"\"");

        adb.adb_command("reboot","");
        cls();

        System.out.println("패치 완료!");

        enter();
    }
}
