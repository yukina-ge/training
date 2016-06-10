package jp.genuine.test.util;


import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import jp.genuine.test.util.model.exceldata.ExcelDataFile;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.excel.CustomXlsDataSet;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.operation.DatabaseOperation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map.Entry;

public class DBUnitHelper
{
    public static void initializeDatabase( IDatabaseConnection connection, ExcelDataFile excelDataFile ) throws IOException, DatabaseUnitException, SQLException
    {
        prepareQualifiedTableNames( connection );
        prepareAllowEmptyFields( connection );
        cleanInsert( connection, excelDataFile );
        initializeSequence( connection, excelDataFile );
    }

    public static void cleanDatabase( IDatabaseConnection connection, ExcelDataFile excelDataFile ) throws IOException, DatabaseUnitException, SQLException
    {
        prepareQualifiedTableNames( connection );
        prepareAllowEmptyFields( connection );
        deleteAll( connection, excelDataFile );
        cleanSequence( connection, excelDataFile );
    }

    public static ITable table( IDatabaseConnection connection, String tableName, List<String> excludeColumns ) throws DataSetException, SQLException
    {
        prepareQualifiedTableNames( connection );
        prepareAllowEmptyFields( connection );
        IDataSet dataSet = connection.createDataSet();
        return excludedColumnsTable( tableName, excludeColumns, dataSet );
    }
    
    public static ITable table( ExcelDataFile excelDataFile, String tableName,  List<String> excludeColumns ) throws DataSetException, IOException
    {
        IDataSet dataSet = xlsDataSet( excelDataFile );
        return excludedColumnsTable( tableName, excludeColumns, dataSet );
    }
    
    private static ITable excludedColumnsTable( String tableName, List<String> excludeColumns, IDataSet dataSet ) throws DataSetException
    {
        String[] exclude = {};
        if ( excludeColumns != null )
            exclude = excludeColumns.toArray( exclude );
        
        return DefaultColumnFilter.excludedColumnsTable( dataSet.getTable( tableName ), exclude );
    }
    
    private static IDataSet xlsDataSet( ExcelDataFile excelDataFile ) throws DataSetException, IOException
    {
        Resource resource = new ClassPathResource( excelDataFile.getFilePath() );
        if( excelDataFile.isEmptyTableMap() )
            return new CustomXlsDataSet( resource.getInputStream() ); 
        
        IDataSet dataSet = new CustomXlsDataSet( resource.getInputStream(), excelDataFile.getTableMap() );
        return replaceEmptyCellTablel( dataSet );
    }
    
    private static void cleanInsert( IDatabaseConnection connection, ExcelDataFile excelDataFile ) throws DataSetException, SQLException, IOException,
    DatabaseUnitException
    {
        if(excelDataFile.isEmptyFilePath() )
            return;
        
        IDataSet dataSet = xlsDataSet( excelDataFile );
        DatabaseOperation.CLEAN_INSERT.execute( connection, dataSet );
    }
    
    private static void deleteAll( IDatabaseConnection connection, ExcelDataFile excelDataFile ) throws DataSetException, SQLException, IOException,
    DatabaseUnitException
    {
        if( excelDataFile.isEmptyFilePath() )
            return;
        
        IDataSet dataSet = xlsDataSet( excelDataFile );
        DatabaseOperation.DELETE_ALL.execute( connection, dataSet );
    }
    
    private static void initializeSequence( IDatabaseConnection connection, ExcelDataFile excelDataFile ) throws SQLException
    {
        if( excelDataFile.isEmptySequenceMap() )
            return;
        
        for( Entry<String, Integer> entry : excelDataFile.getSequenceMap().entrySet()){
            String sql = String.format( "SELECT SETVAL ('%s',%s, true);", entry.getKey(), entry.getValue() );
            connection.getConnection().createStatement().executeQuery( sql );
        }
    }
    
    private static void cleanSequence( IDatabaseConnection connection, ExcelDataFile excelDataFile ) throws SQLException
    {
        if( excelDataFile.isEmptySequenceMap() )
            return;
        
        for( Entry<String, Integer> entry : excelDataFile.getSequenceMap().entrySet()){
            String sql = String.format( "SELECT SETVAL ('%s',%s, false);", entry.getKey(), 1 );
            connection.getConnection().createStatement().executeQuery( sql );
        }
    }
    
    private static IDataSet replaceEmptyCellTablel( IDataSet dataSet ) throws DataSetException
    {
        ReplacementDataSet replacementDataSetReplacement = new ReplacementDataSet(dataSet);
        replacementDataSetReplacement.addReplacementObject("[EMPTY_STRING]", "");
        return replacementDataSetReplacement;
    }
    
    private static void prepareQualifiedTableNames( IDatabaseConnection connection )
    {
        connection.getConfig().setProperty( "http://www.dbunit.org/features/qualifiedTableNames", true );
    }
    
    private static void prepareAllowEmptyFields( IDatabaseConnection connection )
    {
        connection.getConfig().setProperty( "http://www.dbunit.org/features/allowEmptyFields", true );
    }
}
