package asia.embla.service;

import asia.embla.dto.CountryCodeDTO;
import java.util.List;

/**
 *
 * @author tharsan
 * @date 2/22/18
 */
public interface CountryCodeService {

  /**
   * getCountryCodes
   * @return
   */
  List<CountryCodeDTO> getCountryCodes();

  /**
   * createCountryCode
   * @param countryDTO
   * @return
   */
  String createCountryCode(CountryCodeDTO countryDTO);

  /**
   * deleteCountryCode
   * @param id
   * @return
   */
  String deleteCountryCode(int id);
}
