/*
 * Copyright (c) 2020. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.sik.project.web;

import com.sik.project.service.posts.PostsService;
import com.sik.project.web.dto.PostsResponseDto;
import com.sik.project.web.dto.PostsSaveRequestDto;
import com.sik.project.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

        private final PostsService postsService;

        @PostMapping("/api/v1/posts")
        public Long Save(@RequestBody PostsSaveRequestDto requestDto){
            return postsService.save(requestDto);
        }

        @PutMapping("/api/v1/posts/{id}")
        public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
            return postsService.update(id, requestDto);
        }

        @DeleteMapping("/api/v1/posts/{id}")
        public Long delete(@PathVariable Long id){
            postsService.delete(id);
            return id;
        }

        @GetMapping("/api/v1/posts/{id}")
        public PostsResponseDto findById (@PathVariable Long id){
            return postsService.findById(id);
        }
}
