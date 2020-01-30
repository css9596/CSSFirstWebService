/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.sik.project.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter //클래스 내 모든 필드의 Getter 메소드를 자동생성
@NoArgsConstructor  //기본 생성자 자동 추가 EX) public Posts(){}와 같은 효과.
@Entity //테이블과 링크될 클래스임을 나타냄.
public class Posts{

    @Id //해당 테이블의 PK를 의미
    @GeneratedValue(strategy  = GenerationType.IDENTITY) //PK 생성 규칙을 나타낸다.
    private  Long id;

    @Column(length = 500, nullable = false) //테이블의 칼럼을 나타내며 굳이 선언을 하지 않더라도 해당 클래스의 필드는 모두 칼럼이 된다. 기본값 외의 필요한 설정이 있을 때 사용.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
