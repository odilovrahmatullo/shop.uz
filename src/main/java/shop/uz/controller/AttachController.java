package shop.uz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import shop.uz.dto.AttachDTO;
import shop.uz.service.AttachService;

@RestController
@RequestMapping("attach")
public class AttachController {
    @Autowired
    private AttachService attachService;

    @PostMapping("/upload")
    private ResponseEntity<AttachDTO> upload(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(attachService.upload(file));
    }

    @GetMapping("/open/{id}")
    private ResponseEntity<?> open(@PathVariable String id) {
        return attachService.open(id);
    }

    @GetMapping("/all")
    public ResponseEntity<?> all(@RequestParam int page,
                                 @RequestParam int size) {
        page = Math.max(page - 1, 0);
        return ResponseEntity.ok(attachService.getAll(page, size));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return ResponseEntity.ok(attachService.delete(id));
    }

}
