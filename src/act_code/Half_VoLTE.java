//volte 파일 만 다시 넣는 패치

package act_code;

import common_code.ADB;
import common_code.EFS;
import common_code.FASTBOOT;

import static common_code.Misc.*;
import static common_code.Misc.enter;

public class Half_VoLTE extends Message{
    public static void file_only() {
        //instance
        ADB adb = new ADB();
        FASTBOOT fs = new FASTBOOT();
        EFS efs = new EFS();

        //variable
        String file_path;
        String efstools_folder_path;
        String efsfile_folder_path;

        //1. EFS 사용 메시지 출력
        warn_2();

        //2. 포트 개방
        adb.efs_port_open();

        //3. 포트 개방을 위한 디버깅 off on 메시지 출력
        warn_3();

        //4. EFS 툴 사용 시작

        System.out.println("이제 EFSTOOLS를 사용합니다.");
        enter();
        cls();

        //5. 다운받은 EFS 툴 폴더 선택
        System.out.println("EFSTOOLS가 있는 폴더를 선택해주세요");
        enter();

        efstools_folder_path = selectFolderPath();
        cls();

        //6. 통신사 파일 업로드
        System.out.println("EFS 파일을 업로드 합니다..");
        enter();
        cls();

        //7. 통신사 폴더 선택
        System.out.println("통신사에 맞는 EFS 폴더를 선택해주세요.");
        System.out.println("nv / data 폴더 등이 보이는 곳까지 이동 후, 선택해 주시면 됩니다.");
        enter();

        efsfile_folder_path = selectFolderPath();
        cls();

        //8. 폴더 확인
        System.out.println("선택한 패치파일 경로:");
        System.out.println(efsfile_folder_path);

        System.out.println("패치를 시작하려면 엔터를 눌러주세요");
        enter();
        cls();

        //9. 패치 시작. 총 4번 돌림
        System.out.println("패치는 총 4번 하게 됩니다. 현재 패치 중 입니다..기다려주세요");
        efs.EFS_upload_folder("\""+efstools_folder_path+"\\EfsTools.exe\"","\""+efsfile_folder_path+"\"");

        cls();
        System.out.println("##안내사항!##");
        System.out.println("포트 에러가 나셨나요? EFSTOOLS가 있는 폴더로 가서");
        System.out.println("EfsTools.dll.config 파일을 연 다음, efstool port=\"\" 부분에 포트를 수동으로 지정해보세요");
        System.out.println("예제=> efstool port=\"COM8\"");
        enter();

        //10. 나머지 3번 반복 업로드 (파일 누락 방지용)
        efs.EFS_upload_folder("\""+efstools_folder_path+"\\EfsTools.exe\"","\""+efsfile_folder_path+"\"");
        efs.EFS_upload_folder("\""+efstools_folder_path+"\\EfsTools.exe\"","\""+efsfile_folder_path+"\"");
        efs.EFS_upload_folder("\""+efstools_folder_path+"\\EfsTools.exe\"","\""+efsfile_folder_path+"\"");

        //11. 재부팅
        adb.adb_command("reboot","");
        cls();

        //12. 패치 완료 메시지 출력
        System.out.println("패치 완료!");
        enter();
    }
}
