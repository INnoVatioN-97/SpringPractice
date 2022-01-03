package com.practice.spring.youngil.songjava.lecture.mvc.domain;

public enum BoardType implements BaseCodeLabelEnum{
    NOTICE("공지사항"),
    FAQ("자주 묻는 질문"),
    INQUIRY("1:1 문의"),
    ;

    private String code;
    private String label;

    /**
     * enum 에서 제공하는 name() 메소드는
     * 기본적으로 선언한 enum 값을 리턴한다.
     * 예를 들어 label이 "공지사항" 이라면
     * code 에는 NOTICE 가 들어오는 것.
     * @param label
     */
    BoardType(String label) {
        this.code = name();
        this.label = label;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String label() {
        return label;
    }
}
