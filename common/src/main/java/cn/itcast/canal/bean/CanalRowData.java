package cn.itcast.canal.bean;

/*
  Author：     Tao.W
  D a te：     2022/7/28
  Description:  
*/


import cn.itcast.canal.protobuf.CanalModel;
import cn.itcast.canal.protobuf.ProtoBufable;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.kafka.common.protocol.types.Field;

import java.util.Map;

/**
 * 这个类是canal数据的protobuf的实现类
 * 能够使用protobuf序列化成bean对象
 * 目的：将binlog解析后的map对象，转换成portobuf序列化后的字节码数据，最终写入到kafka集群
 */
@Data
public class CanalRowData implements ProtoBufable {

    private String logfileName;
    private Long logfileOffset;
    private Long executeTime;
    private String schemaName;
    private String tableName;
    private String eventType;
    private Map<String, String> columns;


    /**
     * 定义构造方法，解析map对象的binlog日志
     */
    public CanalRowData(Map map) {
        //解析map对象中所有的参数
        if (map.size() > 0) {
            this.logfileName = map.get("logfileName").toString();
            this.logfileOffset = Long.parseLong(map.get("logfileOffset").toString());
            this.executeTime = Long.parseLong(map.get("executeTime").toString());
            this.schemaName = map.get("schemaName").toString();
            this.tableName = map.get("tableName").toString();
            this.eventType = map.get("eventType").toString();
            this.columns = (Map<String, String>) map.get("columns");
        }
    }

    /**
     * 需要将map对象解析出来的参数，赋值给protobuf对象，然后序列化后字节码返回
     *
     * @return
     */
    @Override
    public byte[] toBytes() {
        CanalModel.RowData.Builder builder = CanalModel.RowData.newBuilder();
        builder.setLogfileName(this.getLogfileName());
        builder.setLogfileOffset(this.getLogfileOffset());
        builder.setExecuteTime(this.getExecuteTime());
        builder.setSchemaName(this.getSchemaName());
        builder.setTableName(this.getTableName());
        builder.setEventType(this.getEventType());

        for (String key : this.getColumns().keySet()) {
            builder.putColumns(key, this.getColumns().get(key));
        }

        //将传递的binlog数据解析后序列化成字节码数据返回
        return builder.build().toByteArray();

    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}

