package com.hhx.headline.pojo;

/**
 * @Author: hhx
 * @Date: 2024/3/28 22:19
 * @Description: NewsUser实体类
 * @Version: 1.0
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsUser implements Serializable {
    private Integer uid;
    private String username;
    private String userPwd;
    private String nickName;
}
