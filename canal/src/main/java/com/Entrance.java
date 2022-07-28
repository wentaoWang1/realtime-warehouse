package com;

/*
  Author：     Tao.W
  D a te：     2022/7/28
  Description:  
*/

/**
 * canal客户端的入口类
 */
public class Entrance {
    public static void main(String[] args) {
        //实例化canal的客户端对象，调用start方法拉取canalserver端binlog日志
        CanalClient canalClient = new CanalClient();
        canalClient.start();
    }
}

