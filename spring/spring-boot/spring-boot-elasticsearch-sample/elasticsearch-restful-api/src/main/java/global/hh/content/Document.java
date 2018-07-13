package global.hh.content;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("文档基本信息")
public class Document {

  @Size(max = 50)
  @ApiModelProperty("标题")
  private String title;

  @NotNull
  @ApiModelProperty("内容")
  private String content;

  @ApiModelProperty("路径")
  private String url;

}
