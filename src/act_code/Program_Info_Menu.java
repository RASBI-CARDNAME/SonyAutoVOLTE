package act_code;

import static common_code.Misc.enter;

public class Program_Info_Menu {
    public static void print_program_info(){
        //constant
        final String version_num = "1.2.0";
        final String version_date = "2024-07-12";
        final String u_log = "1. 코드 최적화 및 가독성 최적화\n2. 배터리 수명 확인 기능 추가";


        System.out.println("Sony Auto VOLTE");
        System.out.println("Made By RASBI");
        System.out.println("Version:"+version_num);
        System.out.println("Build Date:"+version_date+"\n");

        System.out.println("업데이트 내역:");
        System.out.println(u_log);

        System.out.println("\n도움을 주신 소니사용자모임 회원분들께 감사 말씀드립니다.");

        System.out.println();
        enter();
    }
}
