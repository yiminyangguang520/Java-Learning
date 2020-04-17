package io.ken.springboot.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 */
@RestController
public class HelloController {

    @GetMapping("/download/symbols/{pdbfolder}/{uuid}/{pdb}")
    String index(@PathVariable String pdbfolder, @PathVariable String uuid, @PathVariable String pdb) {
        System.out.println("pdbfolder======= " + pdbfolder);
        System.out.println("uuid======= " + uuid);
        System.out.println("pdb======= " + pdb);
        return "Hello World!";
    }

}
