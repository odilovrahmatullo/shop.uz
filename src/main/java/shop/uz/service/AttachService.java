package shop.uz.service;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.uz.repository.AttachRepository;

@Service
public class AttachService {
    @Autowired
    private AttachRepository attachRepository;
}
