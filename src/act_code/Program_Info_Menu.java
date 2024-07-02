package act_code;

import static common_code.Misc.enter;

public class Program_Info_Menu {
    public static void print_program_info(String ver,String date){
        System.out.println("Sony Auto VOLTE");
        System.out.println("Made By RASBI");
        System.out.println("Version:"+ver);
        System.out.println("Build Date:"+date+"\n");

        System.out.println("도움을 주신 소니사용자모임 회원분들께 감사 말씀드립니다.");

        System.out.println();
        enter();
    }
}
