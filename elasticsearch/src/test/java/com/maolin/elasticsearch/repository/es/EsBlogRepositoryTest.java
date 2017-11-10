package com.maolin.elasticsearch.repository.es;

import com.maolin.elasticsearch.domain.es.EsBlog;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author 45022
 * @since 2017/11/9 20:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EsBlogRepositoryTest {

    @Autowired
    private EsBlogRepository esBlogRepository;

    @Before
    public void init() {
        esBlogRepository.deleteAll();
        esBlogRepository.save(new EsBlog("登鹳雀楼", "王之涣的登鹳雀楼", "白日依山尽,黄河入海流.欲穷千里目,更上一层楼."));
        esBlogRepository.save(new EsBlog("相思", "王维的相思", "红豆生南国,春来发几枝.愿君多采撷,此物最相思."));
        esBlogRepository.save(new EsBlog("静夜思", "李白的静夜思", "床前明月光,疑是地上霜.举头望明月,低头思故乡."));
    }

    @Test
    public void testFindDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining() throws Exception {
        Pageable pageable = new PageRequest(0, 20);
        String title = "思";
        String summary = "相思";
        String content = "相思";
        Page<EsBlog> page = esBlogRepository.findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(title, summary, content, pageable);
        assertThat(page.getTotalElements(), is(2L));

        for (EsBlog esBlog : page.getContent()) {
            System.out.println(esBlog.toString());
        }
    }

}