package cn.itcast.canal.protobuf;

import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/*
  Author：     Tao.W
  D a te：     2022/7/28
  Description:  
*/
public class ProtoBufSerializer implements Serializer<ProtoBufable> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String s, ProtoBufable data) {
        return data.toBytes();
    }

    @Override
    public void close() {

    }
}
