package cn.maolin.myblog.controller;

import cn.maolin.myblog.annotation.Log;
import cn.maolin.myblog.annotation.Login;
import cn.maolin.myblog.annotation.LoginUser;
import cn.maolin.myblog.entity.Attach;
import cn.maolin.myblog.entity.Users;
import cn.maolin.myblog.exception.TipException;
import cn.maolin.myblog.model.dto.LogActions;
import cn.maolin.myblog.model.dto.Types;
import cn.maolin.myblog.service.AttachService;
import cn.maolin.myblog.service.SiteService;
import cn.maolin.myblog.util.BlogConstant;
import cn.maolin.myblog.util.DateUtil;
import cn.maolin.myblog.util.ResultBean;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 附件管理
 *
 * @author 张茂林
 * @since 2018/5/3 9:54
 */
@Slf4j
@Controller
@RequestMapping("/admin/attach")
public class AttachController {

    //private static final String CLASSPATH = ClassUtils.getDefaultClassLoader().getResource("").getPath();
    public static final String UP_DIR;

    static {
        ApplicationHome home = new ApplicationHome(AttachController.class);
        File jarFile = home.getSource();
        UP_DIR = jarFile.getParentFile().getPath();
    }



    private final SiteService siteService;
    private final AttachService attachService;

    public AttachController(SiteService siteService, AttachService attachService) {
        this.siteService = siteService;
        this.attachService = attachService;
    }

    /**
     * 附件页面
     *
     * @param model
     * @param page
     * @param limit
     * @return
     */
    @Login
    @GetMapping(value = "")
    public String index(Model model, @RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "12") int limit) {

        Attach attach = new Attach();
        List<Attach> list = attachService.page(page, limit);
        PageInfo<Attach> attachPage = new PageInfo<>(list);
        model.addAttribute("attachs", attachPage);
        //model.addAttribute(Types.ATTACH_URL, Commons.site_option(Types.ATTACH_URL, Commons.site_url()));
        model.addAttribute("max_file_size", BlogConstant.MAX_FILE_SIZE / 1024);
        return "admin/attach";
    }



    /**
     * 上传文件接口
     * 返回格式
     *
     * @return
     */
    @Login
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public ResultBean upload(@RequestParam("file") MultipartFile[] fileItems, @LoginUser Users users) {

        //log.info("UPLOAD DIR = {}", TaleUtils.UP_DIR);

        Integer uid = users.getUid();
        List<Attach> errorFiles = new ArrayList<>();
        List<Attach> urls = new ArrayList<>();

        for (MultipartFile file : fileItems) {
            if (file == null) {
                continue;
            }
            String fname = file.getOriginalFilename();
            if ((file.getSize() / 1024) <= BlogConstant.MAX_FILE_SIZE) {
                String fkey = getFileKey(fname);

                String ftype = file.getContentType().contains("image") ? Types.IMAGE : Types.FILE;
                String filePath = UP_DIR + fkey;

                try {
                    //file.transferTo(Paths.get(filePath).toFile());
                    Files.write(Paths.get(filePath), file.getBytes());
                } catch (IOException e) {
                    log.error("", e);
                }

                Attach attach = new Attach();
                attach.setFname(fname);
                attach.setAuthorId(uid);
                attach.setFkey(fkey);
                attach.setFtype(ftype);
                attach.setCreated(DateUtil.nowUnix());

                attachService.save(attach);

                urls.add(attach);
                siteService.cleanCache(Types.C_STATISTICS);
            } else {
                Attach attach = new Attach();
                attach.setFname(fname);
                errorFiles.add(attach);
            }
        }
        if (errorFiles.size() > 0) {
            return ResultBean.error(500, "文件上传错误", errorFiles);
        }
        return ResultBean.ok(urls);
    }

    @Login
    @Log(LogActions.DEL_ATTACH)
    @RequestMapping(value = "/delete")
    @ResponseBody
    public ResultBean delete(Integer id) {
        try {
            Attach attach = attachService.findById(id);
            if (null == attach) {
                return ResultBean.error("不存在该附件");
            }
            String fkey = attach.getFkey();
            siteService.cleanCache(Types.C_STATISTICS);
            String filePath = UP_DIR + fkey;
            Path path = Paths.get(filePath);
            log.info("Delete attach: [{}]", filePath);
            if (Files.exists(path)) {
                Files.delete(path);
            }
            attachService.delete(id);

        } catch (Exception e) {
            String msg = "附件删除失败";
            if (e instanceof TipException) {
                msg = e.getMessage();
            } else {
                log.error(msg, e);
            }
            return ResultBean.error(msg);
        }
        return ResultBean.ok();
    }

    private String getFileKey(String name) {
        String prefix = "/upload/" + DateUtil.toString(new Date(), "yyyy/MM");
        String dir = UP_DIR + prefix;
        if (!Files.exists(Paths.get(dir))) {
            new File(dir).mkdirs();
        }
        return prefix + "/" + uuid() + "." + fileExt(name);
    }

    private String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private String fileExt(String fname) {
        if (StringUtils.isEmpty(fname) || fname.indexOf('.') == -1) {
            return null;
        }
        return fname.substring(fname.lastIndexOf('.') + 1);
    }

}
