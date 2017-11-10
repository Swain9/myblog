package com.maolin.elasticsearch.controller;

import com.maolin.elasticsearch.domain.es.EsBlog;
import com.maolin.elasticsearch.repository.es.EsBlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 45022
 * @since 2017/11/10 10:56
 */
@RestController
@RequestMapping("/blogs")
public class BlogController {

    private final EsBlogRepository esBlogRepository;

    @Autowired
    public BlogController(EsBlogRepository esBlogRepository) {
        this.esBlogRepository = esBlogRepository;
    }

    @GetMapping
    public List<EsBlog> list(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "summary") String summary,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {


        Pageable pageable = new PageRequest(pageIndex, pageSize);
        Page<EsBlog> page = esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(title, summary, content, pageable);
        return page.getContent();
    }
}
