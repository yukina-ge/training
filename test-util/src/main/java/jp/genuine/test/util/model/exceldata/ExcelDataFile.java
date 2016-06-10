package jp.genuine.test.util.model.exceldata;

import java.util.Map;

public interface ExcelDataFile
{
    public String getFilePath();
    public Map<String, String> getTableMap();
    public Map<String, Integer> getSequenceMap();
    public boolean isEmptyFilePath();
    public boolean isEmptyTableMap();
    public boolean isEmptySequenceMap();
    
}
