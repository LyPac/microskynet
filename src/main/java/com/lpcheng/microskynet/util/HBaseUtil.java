package com.lpcheng.microskynet.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class HBaseUtil {

    @Autowired
    private HbaseTemplate hbaseTemplate;
    // private static final String TABLE_NAME = "microskynet";

    public Map<String, Map<String, String>> get(String tableName, String rowKey) {
        return hbaseTemplate.get(tableName, rowKey, (result, i) -> {
            Cell[] cells = result.rawCells();
            Map<String, Map<String, String>> data = new HashMap<>();
            Map<String, String> row;
            for (Cell cell : cells) {
                //获取一行的数据
                String columnFamily = new String(CellUtil.cloneFamily(cell));
                String rowName = new String(CellUtil.cloneQualifier(cell));
                String value = new String(CellUtil.cloneValue(cell));

                //将数据插入map中
                if (data.containsKey(columnFamily)) {
                    row = data.get(columnFamily);
                } else {
                    row = new HashMap<>();
                    data.put(columnFamily, row);
                }
                row.put(rowName, value);
            }
            return data;
        });
    }

    public List find(String tableName, String startRow, String stopRow) {
        if (null == startRow) {
            startRow = "";
        }
        if (null == stopRow) {
            stopRow = "";
        }
        Scan scan = new Scan();
        scan.setStartRow(Bytes.toBytes(startRow));
        scan.setStopRow(Bytes.toBytes(stopRow));
        return hbaseTemplate.find(tableName, scan, (result, i) -> {
            Cell[] cells = result.rawCells();
            Map<String, Object> data = new HashMap();
            Arrays.stream(cells).forEach(cell -> {
                Map<String, String> row;
                String rowKey = Bytes.toString(CellUtil.cloneRow(cell));
                String columnFamily = Bytes.toString(CellUtil.cloneFamily(cell));
                String rowName = Bytes.toString(CellUtil.cloneQualifier(cell));
                String value = Bytes.toString(CellUtil.cloneValue(cell));

                data.put("rowKey", rowKey);

                if (data.containsKey(columnFamily)) {
                    row = (Map<String, String>) data.get(columnFamily);
                } else {
                    row = new HashMap<>();
                    data.put(columnFamily, row);
                }

                row.put(rowName, value);
            });
            return data;
        });
    }

    public boolean save(String tableName, String rowKey, String columnFamily, String columnName, String value) {
        return hbaseTemplate.execute(tableName, hTableInterface -> {
            Put put = new Put(Bytes.toBytes(rowKey));
            put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(columnName), Bytes.toBytes(value));
            hTableInterface.put(put);
            hTableInterface.close();
            return true;
        });
    }

    public boolean delete(String tableName, String rowKey){
        return hbaseTemplate.execute(tableName, hTableInterface -> {
            Delete delete=new Delete(Bytes.toBytes(rowKey));
            hTableInterface.delete(delete);
            hTableInterface.close();
            return true;
        });
    }
}
