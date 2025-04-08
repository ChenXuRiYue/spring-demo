package axelchen.spring.tree.pojo;

import lombok.Data;
import java.util.List;

@Data
public class FolderDTO {
    private Long id;              // 文件夹 ID
    private String name;          // 文件夹名称
    private Long parentId;        // 父文件夹 ID
    private Integer isPublic;     // 是否公共文件夹 (0: 否, 1: 是)
    private String path;          // 物化路径 (如 "/1/2/3")
    private Integer orderIdx;     // 节点偏序关系

    private List<FolderDTO> children;       // 子文件夹列表

    public int calNodeSum() {
        return dfs_nodes(this);
    }
    public int dfs_nodes(FolderDTO u) {
        int res = 1;
        for(FolderDTO v : u.getChildren()) {
            res += dfs_nodes(v);
        }
        return res;
    }
}