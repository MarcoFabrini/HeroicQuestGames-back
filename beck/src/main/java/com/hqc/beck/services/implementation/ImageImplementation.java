package com.hqc.beck.services.implementation;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hqc.beck.model.Image;
import com.hqc.beck.model.Product;
import com.hqc.beck.repository.IImageRepository;
import com.hqc.beck.repository.IProductRepository;
import com.hqc.beck.request.ImageRequest;
import com.hqc.beck.services.interfaces.IImageService;

@Service
public class ImageImplementation implements IImageService {

    @Value("${file.upload-dir}")
    private String uploadDir;
    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private IImageRepository imageRepository;

    @Override
    public void uploadImage(ImageRequest req) throws Exception {
        Product product = productRepository.findById(req.getProductId())
                .orElseThrow(() -> new Exception("Product not found with ID: " + req.getProductId()));

        String filePath = saveImage(req.getFile());

        Image image = new Image();
        image.setUrl(filePath);
        image.setProduct(product);

        imageRepository.save(image);
    }// uploadImage

    @Override
    public List<String> getImagesByProductId(ImageRequest req) throws Exception {
        if (!(productRepository.findById(req.getProductId()).isPresent()))
            throw new Exception("Product not found with ID: " + req.getProductId());

        List<Image> images = imageRepository.findByProductId(req.getProductId());
        if (images.isEmpty())
            throw new Exception("Image not found for Product ID: " + req.getProductId());

        return images.stream()
                .map(img -> "http://localhost:8080/api/public/images/" +
                        Paths.get(img.getUrl()).getFileName().toString())
                .collect(Collectors.toList());

    }// getImagesByProductId

    @Override
    public Resource getImage(String filename) throws Exception {
        try {
            Path filePath = Paths.get(uploadDir).resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable())
                throw new Exception("File not found: " + filename);

            return resource;
        } catch (MalformedURLException e) {
            throw new Exception("Error loading file: " + filename, e);
        }
    }// getImage

    private String saveImage(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath))
            Files.createDirectories(uploadPath);

        String fileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return filePath.toString();
    }// saveImage

}// class
