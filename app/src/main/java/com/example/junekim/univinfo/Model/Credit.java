package com.example.junekim.univinfo.Model;

/**
 * Created by JuneKim on 2017. 6. 13..
 */

public class Credit {

    public String source_name; // 출처 제목
    public String source_by;  // 출처 (사람)
    public String link_url; // 연결 url

    public Credit() {
        // 데이터 스냅샷을 위해서 필요한 생성자
    }

    public Credit(String source_name, String source_by, String link_url) {
        this.source_name = source_name;
        this.source_by = source_by;
        this.link_url = link_url;
    }
}
