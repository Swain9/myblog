package cn.maolin.myblog.controller;

import cn.maolin.myblog.entity.Users;
import cn.maolin.myblog.service.UserService;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 张茂林
 * @since 2018/4/13 17:33
 */
@Controller
@RequestMapping("/upload")
public class FileController {

    private final UserService userService;
    private final ResourceLoader resourceLoader;

    public FileController(UserService userService, ResourceLoader resourceLoader) {
        this.userService = userService;
        this.resourceLoader = resourceLoader;
    }

    @RequestMapping(value = "/test")
    public String test(Model model) {
        Users user = userService.selectById(1);
        List<Users> list = new ArrayList<>();
        if (user != null) {
            list.add(user);
        }
        model.addAttribute("list", list);
        return "test";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{year}/{month}/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String year, @PathVariable String month, @PathVariable String filename) {
        String path = AttachController.UP_DIR + "/upload/" + year + "/" + month;
        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(path, filename).toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
