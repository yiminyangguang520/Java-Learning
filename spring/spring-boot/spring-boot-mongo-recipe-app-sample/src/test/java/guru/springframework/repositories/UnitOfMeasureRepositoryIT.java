package guru.springframework.repositories;

import static org.junit.Assert.assertEquals;

import guru.springframework.domain.UnitOfMeasure;
import java.util.Optional;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by jt on 6/17/17.
 */
@Ignore
@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryIT {

  @Autowired
  UnitOfMeasureRepository unitOfMeasureRepository;

  @Before
  public void setUp() throws Exception {
  }

  @Test
  public void findByDescription() throws Exception {

    Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

    assertEquals("Teaspoon", uomOptional.get().getDescription());
  }

  @Test
  public void findByDescriptionCup() throws Exception {

    Optional<UnitOfMeasure> uomOptional = unitOfMeasureRepository.findByDescription("Cup");

    assertEquals("Cup", uomOptional.get().getDescription());
  }

}