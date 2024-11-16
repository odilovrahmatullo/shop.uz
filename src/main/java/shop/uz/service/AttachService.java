package shop.uz.service;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import shop.uz.dto.AttachDTO;
import shop.uz.entity.AttachEntity;
import shop.uz.exceptions.ResourceNotFoundException;
import shop.uz.repository.AttachRepository;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;


@Service
public class AttachService {

    @Value("${server.domain}")
    private String domainName;
    @Autowired
    private AttachRepository attachRepository;

    private String folderName = "attaches";

    public AttachDTO upload(MultipartFile file) {
        String pathFolder = getYmDString();
        String key = UUID.randomUUID().toString();
        String extension = getExtension(file.getOriginalFilename());

        File folder = new File(folderName + "/" + pathFolder);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        try {

            byte[] bytes = file.getBytes();
            Path path = Paths.get(folderName + "/" + pathFolder + "/" + key + "." + extension);
            Files.write(path, bytes);

            AttachEntity entity = new AttachEntity();
            entity.setId(key + "." + extension);
            entity.setSize(file.getSize());
            entity.setPath(pathFolder);
            entity.setVisible(Boolean.TRUE);
            entity.setExtension(extension);
            entity.setOrigenName(file.getOriginalFilename());
            entity.setCreatedDate(LocalDateTime.now());
            attachRepository.save(entity);

            return toDTO(entity);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    private AttachDTO toDTO(AttachEntity entity) {
        AttachDTO attachDTO = new AttachDTO();
        attachDTO.setId(entity.getId());
        attachDTO.setOriginName(entity.getOrigenName());
        attachDTO.setSize(entity.getSize());
        attachDTO.setExtension(entity.getExtension());
        attachDTO.setCreatedData(entity.getCreatedDate());
        attachDTO.setUrl(getUrl(entity.getId()));

        return attachDTO;
    }

    private String getYmDString() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DATE);
        return year + "/" + month + "/" + day;
    }

    private String getExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf("."); // mazgi.latta.jpg
        return fileName.substring(lastIndex + 1);
    }

    public String getUrl(String id) {
        return domainName + "/attach/open/" + id;
    }

    public ResponseEntity<Resource> open(String id) {
        AttachEntity entity = getById(id);
        Path filePath = Paths.get(folderName + "/" + entity.getPath() + "/" + entity.getId()).normalize();
        Resource resource = null;
        try {
            resource = new UrlResource(filePath.toUri());
            if (!resource.exists()) {
                throw new RuntimeException("File not found: " + id);
            }
            String contentType = Files.probeContentType(filePath);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        }
    }

    public Page<AttachDTO> getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AttachEntity> entityPage = attachRepository.getAll(pageable);
        List<AttachDTO> dtoList = new LinkedList<>();
        for (AttachEntity entity : entityPage) {
            dtoList.add(toDTO(entity));
        }
        return new PageImpl<>(dtoList, pageable, entityPage.getTotalPages());
    }

    public String delete(String id) {
        AttachEntity entity = getById(id);
        attachRepository.delete(entity);
        return "Successfully deleted";
    }

    private AttachEntity getById(String id) {
        return attachRepository.findByIdAndVisibleTrue(id).orElseThrow(() -> new ResourceNotFoundException("File not found: " + id));
    }

    public AttachDTO getDto(String id) {
        if(id==null){
            return null;
        }
        AttachDTO dto = new AttachDTO();
        dto.setId(id);
        dto.setUrl(getUrl(id));
        return dto;
    }
}
