package us.dev.shipandcargo.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
@Getter
@Setter
@ApiModel(value = "RegisterReqBody", description =  "register request body")
public class RegisterReqBody {

    @ApiModelProperty(example = "美国海军", value = "name", required = true)
    @NotEmpty(message = "需要填写船公司名称")
    private String name;
    @ApiModelProperty(example = "foo@bar.com", value = "email", required = true)
    @NotEmpty(message = "需要填写邮箱地址")
    private String email;
    @ApiModelProperty(example = "123456", value = "password", required = true)
    @NotEmpty(message = "需要填写密码")
    private String password;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
