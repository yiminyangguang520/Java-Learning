package com.lee.example.webflux.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lee
 * @date 2020-03-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coffee implements Serializable {

  private String id;

  private String name;
}
