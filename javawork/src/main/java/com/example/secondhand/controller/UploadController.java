
package com.example.secondhand.controller;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadController {

    private static final Logger log = LoggerFactory.getLogger(UploadController.class);

    private final Path uploadDir = Paths.get("uploads").toAbsolutePath().normalize();

    private static final Set<String> ALLOWED_EXTENSIONS = Set.of(".jpg", ".jpeg", ".png", ".gif", ".webp");
    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024L; // 5MB

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(uploadDir);
            log.info("Upload directory created: {}", uploadDir);
        } catch (IOException e) {
            throw new RuntimeException("无法创建上传目录: " + uploadDir, e);
        }
    }

    @PostMapping
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return error("上传文件为空");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            return error("文件大小不能超过5MB");
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || !originalFilename.contains(".")) {
            return error("文件名不合法");
        }

        String extension = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            return error("不支持的文件类型，仅支持JPG、PNG、GIF、WebP");
        }

        String filename = UUID.randomUUID().toString() + extension;
        Path targetPath = uploadDir.resolve(filename);

        try (InputStream is = file.getInputStream()) {
            Files.copy(is, targetPath, StandardCopyOption.REPLACE_EXISTING);
            log.info("File saved: {} (size: {})", filename, file.getSize());
        } catch (IOException e) {
            log.error("File upload failed: {}", e.getMessage(), e);
            return error("文件上传失败，请重试");
        }

        String url = "/uploads/" + filename;
        Map<String, Object> data = new HashMap<>();
        data.put("src", url);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 0);
        result.put("msg", "上传成功");
        result.put("data", data);
        return result;
    }

    private Map<String, Object> error(String msg) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 1);
        result.put("msg", msg);
        result.put("data", null);
        return result;
    }
}
