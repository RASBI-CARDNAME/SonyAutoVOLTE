//공통 메시지 출력문
package act_code;

import static common_code.Misc.cls;
import static common_code.Misc.enter;

public class Message {
    public static void warn_1() {
        System.out.println("##싱글심 패치(슬롯 1)만 지원합니다!!!##");
        System.out.println("1. 디버깅 모드를 활성화 해주세요.");
        System.out.println("2. 부트로더 언락은 직접 해주세요.");
        System.out.println("3. ADB 드라이버 및 FASTBOOT 드라이버를 확인해주세요.");
        System.out.println("4. 닷넷프레임워크 5.0을 설치 해주세요");

        enter();
        cls();
    }

    public static void warn_2() {
        cls();
        System.out.println("#포트 개방을 시작합니다#");
        System.out.println("휴대폰 화면을 보고, shell 루트 권한 요청이 뜨면 허용해주세요.");
        System.out.println("또는 APATCH 매니저 앱에서 슈퍼키 입력후, 슈퍼유저 탭에서 쉘에 권한을 주세요.");
        System.out.println("또한 APATCH 사용시 안드로이드 패치까지 설치하셔야 합니다.");

        enter();
        cls();
    }

    public static void warn_3() {
        System.out.println("포트 개방 완료. 디버깅 옵션을 껏다가 켜주세요.");
        System.out.println("이후 장치 관리자에서 드라이버를 잡아주세요.");
        System.out.println("(드라이버는 강좌글 참고 하시면 됩니다.)");

        enter();
        cls();
    }

    public static void pdc_message() {
        System.out.println("PDC 작업이 필요한 경우 지금! 해주세요");
        System.out.println("(강좌글에 해당사항이 있는지 확인해 주세요)");
        System.out.println("(유심 고정하신 분들은 기기 부팅 후 작업하세요)");

        enter();
        cls();
    }

    public static void pdc_message2() {
        System.out.println("추가적인 PDC 작업이 필요한 경우 지금! 해주세요");
        System.out.println("(강좌글에 해당사항이 있는지 확인해 주세요 => ex 1 마크4 U+)");
        System.out.println("(기기 부팅이 된 후에 작업하세요)");

        enter();
        cls();
    }
}
