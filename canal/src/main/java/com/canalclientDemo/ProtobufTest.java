package com.canalclient;

import com.google.protobuf.InvalidProtocolBufferException;
import com.protobuf.UserModel;

/*
  Author：     Tao.W
  D a te：     2022/7/27
  Description:
  protobuf 的使用 测试
  前提 在proto文件中编写proto文件 --> 使用maven-plugins-protobuf-protobuf:compile 进行编译。扫描proto文件，生成java class
  
  进行 序列化 与 反序列测试
*/
public class ProtobufTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {

        //实例化protobuf的对象
        UserModel.User.Builder builder  = UserModel.User.newBuilder();

        //给user对象进行赋值
        builder.setId(1);   //用户id
        builder.setName("张三");//用户名
        builder.setSex("男");//用户性别

        //获取User对象的属性
        UserModel.User userBuilder = builder.build();
        System.out.println(userBuilder.getId());
        System.out.println(userBuilder.getName());
        System.out.println(userBuilder.getSex());

        System.out.println("*********************************");

        /**
         * 数据的序列化和反序列化
         * 序列化：意味着可以将对象转换成字节码数据存储到kafka中
         * 反序列：意味着可以将kafka中的数据消费出来以后，反序列化成对象使用
         */
        //将一个对象序列换成二进制的字节码数据存储到kafka
        byte[] bytes = builder.build().toByteArray();
        System.out.println("序列化------------------------------");
        for (byte aByte : bytes) {
            System.out.println(aByte);
        }

        System.out.println("反序列化----------------------------------");
        //从kafka中心消费出来的序列化数据，可以反序列化成对象使用
        UserModel.User user = UserModel.User.parseFrom(bytes);
        System.out.println(user.getId());
        System.out.println(user.getName());
        System.out.println(user.getSex());
    }
}
