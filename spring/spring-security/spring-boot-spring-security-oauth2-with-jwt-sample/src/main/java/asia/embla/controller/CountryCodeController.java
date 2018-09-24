package asia.embla.controller;

import asia.embla.dto.CountryCodeDTO;
import asia.embla.service.CountryCodeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tharsan
 * @date 4/24/18
 */
@RestController
@RequestMapping("/countrycodes")
public class CountryCodeController {

  @Autowired
  private CountryCodeService countryCodeService;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public List<CountryCodeDTO> getCountries() {
    return countryCodeService.getCountryCodes();
  }

  @PreAuthorize("hasAuthority('SUPER_ADMIN') OR hasAuthority('ADMIN')")
  @RequestMapping(value = "/", method = RequestMethod.POST)
  public String createCountry(@RequestBody CountryCodeDTO countryDTO) {
    return countryCodeService.createCountryCode(countryDTO);
  }

  @PreAuthorize("hasAuthority('SUPER_ADMIN')")
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public String deleteCountry(@RequestParam("id") int id) {
    return countryCodeService.deleteCountryCode(id);
  }
}
