package shop.uz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.uz.service.AttachService;

@RestController
@RequestMapping("attach")
public class AttachController {
    @Autowired
    private AttachService attachService;

}
