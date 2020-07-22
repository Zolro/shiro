package com.zyd.shiro.utils;

import com.zyd.shiro.entity.ArcDire;
import com.zyd.shiro.vo.ArcDireVo;

import java.util.List;

public class TreeStructureUtil {

    /**
     * 递归算法获取子目录和文件
     * @param id
     * @param dirlist
     * @param doclist
     * @return
     */
   /* public static List<ArcDireVo> getVoDrenlist(Long id , List<DirectoryStructure> dirlist, List<document> doclist){
        List<DocumentListVo> listvo = new ArrayList<>();
        for(int j=0;j<dirlist.size();j++){
            DirectoryStructure dir = dirlist.get(j);
            if(dir.getPid() == id){
                DocumentListVo docvo = new DocumentListVo();
                docvo.setName(dir.getName());
                docvo.setList(getVoDrenlist(dir.getId(),dirlist,doclist));
                listvo.add(docvo);
            }
            //循环完当前目录级，去看当前级下面有没有文件
            if(j == (dirlist.size()-1)){
                for(int i = 0;i<doclist.size();i++){
                    document doc = doclist.get(i);
                    if(doc.getdSId() == id){
                        DocumentListVo docvo1 = new DocumentListVo();
                        docvo1.setName(doc.getName());
                        docvo1.setHashCode(doc.getHashCode());
                        docvo1.setUpdateTime(doc.getUpdateTime());
                        listvo.add(docvo1);
                    }
                }
            }
        }
        return listvo;
    }*/
}
