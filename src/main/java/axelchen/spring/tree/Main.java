package axelchen.spring.tree;

import axelchen.spring.tree.pojo.FolderDTO;
import axelchen.spring.tree.serilaize.BuildTree;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.tomcat.websocket.AsyncChannelGroupUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Main {
    static public void main(String[] args) throws FileNotFoundException {
        BuildTree buildTree = new BuildTree();

        // 构造一棵树的时间：
        Long t0_build_tree = System.currentTimeMillis();
        FolderDTO tree = buildTree.build(5, 7);
        Long t1_build_tree = System.currentTimeMillis();

        System.out.println("build tree: " + String.valueOf(t1_build_tree - t0_build_tree));
        // 计算总数
        System.out.println("tree-sum: " + tree.calNodeSum());
        // 序列化时间。

        Gson gson = new Gson();
        System.out.println("####################### 序列化测评");
        System.out.println("#################### gson");


        // gson
        Long t0_gson = System.currentTimeMillis();
        String str_gson =  gson.toJson(tree);
        Long t1_gson = System.currentTimeMillis();
        System.out.println("gson " + String.valueOf(t1_gson - t0_gson));

        // 反序列化
        t0_gson  = System.currentTimeMillis();
        FolderDTO reObject = gson.fromJson(str_gson, FolderDTO.class);
        t1_gson = System.currentTimeMillis();
        System.out.println("gson reobject " + String.valueOf(t1_gson - t0_gson));

        System.out.println("gson_length " + str_gson.length());
        // redis 持久化。
        System.out.println("#################### jackJson");

        // jackson
        ObjectMapper objectMapper = new ObjectMapper();
        Long t0_jackJson = 0L, t1_jackJson = 0L;
        String str_jackJson = "";
        try {
            t0_jackJson = System.currentTimeMillis();
            str_jackJson = objectMapper.writeValueAsString(tree);
            t1_jackJson = System.currentTimeMillis();
            System.out.println("jackSon " + String.valueOf(t1_jackJson - t0_jackJson));


            t0_jackJson = System.currentTimeMillis();
            objectMapper.readValue(str_jackJson, FolderDTO.class);
            t1_jackJson = System.currentTimeMillis();
            System.out.println("jackson reobject " + String.valueOf(t1_jackJson - t0_jackJson));
        }catch (Exception e){
            System.out.println("error");
        }
        System.out.println("jackSon_length: " + str_jackJson.length());

        // kryo 测评
        Kryo kryo = new Kryo();
        kryo.setReferences(true);
        kryo.setRegistrationRequired(false);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Output output = new Output(bos);
        System.out.println("#################### Kryo");
// kryo 序列化
        Long t0_kryo = System.currentTimeMillis();
        kryo.writeClassAndObject(output, tree); // 只写入一次
        output.flush(); // 确保数据写入
        Long t1_kryo = System.currentTimeMillis();
        System.out.println("kryo length " + bos.toByteArray().length);
        System.out.println("kryo " + String.valueOf(t1_kryo - t0_kryo));

// kryo 反序列化
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        Input input = new Input(bis);
        t0_kryo = System.currentTimeMillis();
        FolderDTO temp = (FolderDTO) kryo.readClassAndObject(input);
        t1_kryo = System.currentTimeMillis();
        System.out.println("kryo reObject " + String.valueOf(t1_kryo - t0_kryo));
    }
}
