package com.myproject.Osahaneat.Service;

import com.myproject.Osahaneat.Service.Imp.FileServiceImp;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService implements FileServiceImp {

    @Value("${fileUpload.rootPath}")
    private String rootPath;
    private Path root;

    public void init(){
        try {
            root = Paths.get(rootPath);
            if(Files.notExists(root)){
                Files.createDirectories(root);
            }
        } catch (Exception e) {
            System.out.println("Error create folder root!" + e.getMessage());
        }
    }

    //Lưu file vào máy
    @Override
    public boolean saveFile(MultipartFile file) {
        try {
            init();
            //file.getInputStream -> lấy đường dẫn file người dùng truyền vào
            //D:-LAP_TRINH-PROJECT-OsahaneatProject-uploads
            //this.root => D:-LAP_TRINH-PROJECT-OsahaneatProject
            //resolve: để lấy tên file cuối cùng (uploads)
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
            return true;
        } catch (Exception e) {
            System.out.println("Error save file!" + e.getMessage());
            return false;
        }
    }


    //Tải file xuống
    @Override
    public Resource loadFile(String fileName) {
        //Resolve tương ứng với ấu gạch chéo trước fileName
        try {
            init();
            Path file = this.root.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()){
                return resource;
            }
        } catch (Exception e) {
            System.out.println("Error load file!" + e.getMessage());
        }
        return null;
    }
}
