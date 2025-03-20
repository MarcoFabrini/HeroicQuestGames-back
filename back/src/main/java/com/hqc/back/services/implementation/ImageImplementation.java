package com.hqc.back.services.implementation;

import java.net.MalformedURLException;
import java.nio.file.DirectoryStream;
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

import com.hqc.back.model.Image;
import com.hqc.back.model.Product;
import com.hqc.back.repository.IImageRepository;
import com.hqc.back.repository.IProductRepository;
import com.hqc.back.request.ImageRequest;
import com.hqc.back.services.interfaces.IImageService;

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

        String filePath = saveImage(req.getFile(), product);

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

    private String saveImage(MultipartFile file, Product product) throws Exception {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)){
            System.out.println("directory non trovata");
            Files.createDirectories(uploadPath);
        }
        // Ottenere il numero di file già presenti con lo stesso nome base
        String baseFilename = product.getName().replaceAll("\\s+", "_");
        int fileCount = 0;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(uploadPath, baseFilename + "_*")) {
            for (Path path : stream) {
                fileCount++;
            }
        }

        // Creazione del nuovo nome del file con numero crescente
        String originalFilename = file.getOriginalFilename();
        String fileExtension = "";

        if (originalFilename != null && originalFilename.contains("."))
            fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));

        String newFilename = baseFilename + "_" + fileCount + fileExtension;
        Path filePath = uploadPath.resolve(newFilename);

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return filePath.toString();
    }// saveImage

}// class
