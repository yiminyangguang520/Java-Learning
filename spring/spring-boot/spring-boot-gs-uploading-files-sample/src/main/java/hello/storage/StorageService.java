package hello.storage;

import java.nio.file.Path;
import java.util.stream.Stream;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author litz-a
 */
public interface StorageService {

  /**
   * init
   */
  void init();

  /**
   * store
   * @param file
   */
  void store(MultipartFile file);

  /**
   * loadAll
   * @return
   */
  Stream<Path> loadAll();

  /**
   * load
   * @param filename
   * @return
   */
  Path load(String filename);

  /**
   * loadAsResource
   * @param filename
   * @return
   */
  Resource loadAsResource(String filename);

  /**
   * deleteAll
   */
  void deleteAll();
}
