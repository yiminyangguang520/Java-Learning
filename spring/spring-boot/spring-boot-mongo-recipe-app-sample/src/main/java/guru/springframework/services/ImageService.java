package guru.springframework.services;

import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author jt
 * @date 7/3/17
 */
public interface ImageService {

  void saveImageFile(String recipeId, MultipartFile file);
}
