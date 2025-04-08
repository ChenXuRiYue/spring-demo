package axelchen.spring.tree.serilaize;

import axelchen.spring.tree.pojo.FolderDTO;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;

// 构造一棵树
public class BuildTree {
    public int width, height;
    public FolderDTO build(int width, int height) {
        // 估计上可能会形成两千个用例。瓶颈。
        FolderDTO root = new FolderDTO();

        // 初始化
        this.width = width;
        this.height = height;
        root = dfsBuild(1);
        return root;
    }
    public FolderDTO dfsBuild(int height) {
        if(height > this.height) return null;
        // 构造
        FolderDTO u = new FolderDTO();
        u.setId(1L);
        u.setChildren(new ArrayList<>());
        u.setIsPublic(0);
        u.setParentId(0L);
        u.setName("Name");
        for(int i = 0; i < this.width; i++) {
            FolderDTO son = dfsBuild(height + 1);
            if(son == null) continue;
            u.getChildren().add(son);
        }
        return u;
    }
}