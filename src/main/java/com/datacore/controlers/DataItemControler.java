package com.example.controlers;

import com.example.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/shop")
public class ShopControler {

    private final ShopService shopService;

    @Autowired
    public ShopControler(ShopService shopService, ShopService shopService1) {
        this.shopService = shopService1;
    }


    @Scheduled(cron = "0/5 * * * * ?")
    public void chronometar(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String date = sdf.format(now);
        System.out.println("Controler/Chronometar fire : " + date);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam(value = "file") MultipartFile file, HttpServletResponse response) throws IOException {

        File convertFile = new File("tmp/" + file.getOriginalFilename());
        boolean f = convertFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convertFile);
        fos.write(file.getBytes());
        fos.close();
        return "Upload successful" + convertFile.getAbsolutePath();
    }

    @RequestMapping(value = "/files", method = RequestMethod.GET)
    public static List<ObjectNode> getFileNames() {
        List<ObjectNode> list = new ArrayList<>();
        File dir = new File("tmp");
        ObjectMapper mapper = new ObjectMapper();
        for (File file : dir.listFiles()) {

            ObjectNode item = mapper.createObjectNode();
            item.put("name", file.getName());
            list.add(item);

        }
        return list;
    }

    @RequestMapping(value = "/download/{file}", method = RequestMethod.GET)
    public ResponseEntity<Object> download(@PathVariable("file") String name) throws FileNotFoundException {
        File file = new File("tmp/" + name);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Credentials", "true");
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ResponseEntity<Object> response = ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);

        return response;
    }

    @GetMapping(value = "/go")
    public String get(HttpServletRequest r) {
        return this.shopService.myG();
    }

    @GetMapping(value = "/message")
    public ResponseEntity<?> hello(HttpServletRequest request, HttpServletResponse servletResponse) {
        request.getSession();
        User user = new User(1, "Load up start-up data", "bane");
        Cookie c = new Cookie("MOJcookie", UUID.randomUUID().toString());
        c.setPath("/");
        c.setMaxAge(3600);
        c.setSecure(false);
        c.setHttpOnly(true);
        c.setDomain("localhost");
        servletResponse.addCookie(c);

        HttpHeaders header = new HttpHeaders();
        header.add("Access-Control-Allow-Credentials", "true");
        header.add("Set-Cookie", String.format("NoviCookie=%s; Max-Age=4444; Expires=true; Domain=localhost; Path=\"/\"; HttpOnly;", UUID.randomUUID()));
        ResponseEntity<User> response = ResponseEntity.ok().headers(header).body(user);

        return response;
    }


}
