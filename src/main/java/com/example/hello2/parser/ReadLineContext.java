package com.example.hello2.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
Context -> 변하지 않는 공통 된 값
ReadLineContext는 바뀌지 않지만 어떤 Parser를 받아올지가 바뀐다
*/
public class ReadLineContext<T> {

    private Parser<T> parser;

    public ReadLineContext(Parser<T> parser) {
        this.parser = parser;
    }

    // 파일 내용을 한줄 씩 읽어오는 메서드
    public List<T> readByLine(String filename) throws IOException {
        List<T> result = new ArrayList<>(); // 읽어 온 정보를 담을 List 생성
        BufferedReader br = new BufferedReader(new FileReader(filename));

        String str; // 위에서 읽어온 것을 담을 str
        br.readLine(); // 첫번째줄은 타이틀 라인이기 때문에 이 한줄을 읽고 다음꺼부터 아래의 내용을 진행
        // readLine -> BufferedReader에서 한줄을 읽는 것
        while ((str = br.readLine()) != null){
            // 한줄 읽어온 객체를 parse로 잘라서 result list에 add한다
            try {
                result.add(parser.parse(str));
            } catch (Exception e) {
                System.out.printf("파싱 중 문제가 생겨 이 라인은 넘어갑니다. 파일내용: %s\n", str);
            }
        }
        br.close();

        return result;
    }
}
