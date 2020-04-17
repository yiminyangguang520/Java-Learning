package com.sy.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 */
@RestController
@RequestMapping("/test")
@Api("测试")
public class TestController {

  @ApiOperation(value = "计算+", notes = "加法")
  @ApiImplicitParams({
      @ApiImplicitParam(name = "a", paramType = "path", value = "数字a", required = true, dataType = "Long"),
      @ApiImplicitParam(name = "b", paramType = "path", value = "数字b", required = true, dataType = "Long")
  })
  @GetMapping("/{a}/{b}")
  public Integer get(@PathVariable Integer a, @PathVariable Integer b) {
    return a + b;
  }
}
