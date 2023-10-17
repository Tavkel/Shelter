package zhy.votniye.Shelter.helpers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import zhy.votniye.Shelter.services.implimentations.PetServiceImpl;

@Service
public class PhotoCompression {

    private final Logger logger = LoggerFactory.getLogger(PetServiceImpl.class);

    public byte[] generatePreview(Path filePath) throws IOException {
        try (InputStream is = Files.newInputStream(filePath);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()
        ) {
            BufferedImage image = ImageIO.read(bis);

            int reduceFactor = Math.max(image.getHeight() / 60, image.getWidth() / 60);
            int pHeight = image.getHeight() / reduceFactor;
            int pWidth = image.getWidth() / reduceFactor;

            BufferedImage preview = new BufferedImage(pWidth, pHeight, image.getType());
            Graphics2D graphics = preview.createGraphics();
            graphics.drawImage(image, 0, 0, pWidth, pHeight, null);
            graphics.dispose();

            ImageIO.write(preview, "JPG", baos);
            logger.debug("Preview for avatar generated");
            return baos.toByteArray();
        }
    }
}
