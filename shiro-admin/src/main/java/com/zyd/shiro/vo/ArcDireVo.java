package com.zyd.shiro.vo;

import com.zyd.shiro.entity.ArcDire;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class ArcDireVo{

    private long id;
    private String name;
    private long perId;
    private List<ArcDireVo> arcDireList;


    public static void main(String[] args) {
        List<ArcDireVo> arcDires = new ArrayList<>();
        ArcDireVo arcDire1 = new ArcDireVo();
        arcDire1.setId(1L);
        arcDire1.setName("龙湾");
        arcDire1.setPerId(0L);

        ArcDireVo arcDire11 = new ArcDireVo();
        arcDire11.setId(4L);
        arcDire11.setName("状元");
        arcDire11.setPerId(1L);

        ArcDireVo arcDire111 = new ArcDireVo();
        arcDire11.setId(6L);
        arcDire11.setName("御史桥");
        arcDire11.setPerId(4L);

        ArcDireVo arcDire12 = new ArcDireVo();
        arcDire12.setId(5L);
        arcDire12.setName("黄埔");
        arcDire12.setPerId(1L);


        ArcDireVo arcDire2 = new ArcDireVo();
        arcDire2.setId(2L);
        arcDire2.setName("瓯海");
        arcDire2.setPerId(0L);

        ArcDireVo arcDire21 = new ArcDireVo();
        arcDire11.setId(14L);
        arcDire11.setName("南白象");
        arcDire11.setPerId(2L);

        ArcDireVo arcDire211 = new ArcDireVo();
        arcDire11.setId(6L);
        arcDire11.setName("西象");
        arcDire11.setPerId(14L);

        ArcDireVo arcDire22 = new ArcDireVo();
        arcDire12.setId(15L);
        arcDire12.setName("茶山");
        arcDire12.setPerId(2L);



        ArcDireVo arcDire3 = new ArcDireVo();
        arcDire3.setId(3L);
        arcDire3.setName("鹿城");
        arcDire3.setPerId(0L);
        ArcDireVo arcDire31 = new ArcDireVo();

        arcDire11.setId(28L);
        arcDire11.setName("天和");
        arcDire11.setPerId(3L);
    }
}

