package guru.springframework.services;

import guru.springframework.commands.UnitOfMeasureCommand;
import java.util.Set;

/**
 *
 * @author jt
 * @date 6/28/17
 */
public interface UnitOfMeasureService {

  Set<UnitOfMeasureCommand> listAllUoms();
}
