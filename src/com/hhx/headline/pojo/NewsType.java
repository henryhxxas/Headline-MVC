package com.hhx.headline.pojo;

/**
 * @Author: hhx
 * @Date: 2024/3/28 22:22
 * @Description: NewsType实体类
 * @Version: 1.0
 */
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsType implements Serializable {
    private Integer tid;
    private String tname;
}
