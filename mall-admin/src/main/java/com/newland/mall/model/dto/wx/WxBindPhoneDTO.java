package com.newland.mall.model.dto.wx;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 绑定手机号码
 *
 * @author liulun
 * @date 2023/4/3 15:18
 */
@Data
public class WxBindPhoneDTO {
    @NotEmpty(message = "数据异常")
    private String encryptedData;
    @NotEmpty(message = "数据异常")
    private String iv;
}
