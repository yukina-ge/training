package org.dbunit.dataset.excel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.dbunit.dataset.AbstractDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.DefaultTableIterator;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ITableIterator;
import org.dbunit.dataset.OrderedTableNameMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class CustomXlsDataSet extends AbstractDataSet
{
    private static final Logger logger = LoggerFactory.getLogger( CustomXlsDataSet.class );
    private final OrderedTableNameMap _tables;

    public CustomXlsDataSet( File file ) throws IOException, DataSetException
    {
        this( new FileInputStream( file ), new HashMap<>() );
    }

    public CustomXlsDataSet( File file, Map<String, String> tableMap ) throws IOException, DataSetException
    {
        this( new FileInputStream( file ), tableMap );
    }

    public CustomXlsDataSet( InputStream in ) throws IOException, DataSetException
    {
        this( in, new HashMap<>() );
    }

    public CustomXlsDataSet( InputStream in, Map<String, String> tableMap ) throws IOException, DataSetException
    {
        _tables = super.createTableNameMap();

        Workbook workbook;
        try
        {
            workbook = WorkbookFactory.create( in );
        }
        catch ( InvalidFormatException e )
        {
            throw new IOException( e );
        }

        int sheetCount = workbook.getNumberOfSheets();
        for ( int i = 0; i < sheetCount; i++ )
        {
            String tableName = tableName( workbook.getSheetName( i ), tableMap );
            ITable table = new XlsTable( tableName, workbook.getSheetAt( i ) );
            _tables.add( tableName, table );
        }
    }

    public static void write( IDataSet dataSet, OutputStream out ) throws IOException, DataSetException
    {
        logger.debug( "write(dataSet={}, out={}) - start", dataSet, out );

        new XlsDataSetWriter().write( dataSet, out );
    }

    protected ITableIterator createIterator( boolean reversed ) throws DataSetException
    {
        if ( logger.isDebugEnabled() )
            logger.debug( "createIterator(reversed={}) - start", String.valueOf( reversed ) );

        @SuppressWarnings( "unchecked" )
        ITable[] tables = (ITable[]) _tables.orderedValues().toArray( new ITable[0] );
        return new DefaultTableIterator( tables, reversed );
    }

    private String tableName( String sheetName, Map<String, String> tableMap )
    {
        if ( tableMap.containsKey( sheetName ) )
            return tableMap.get( sheetName );

        return sheetName;
    }
}
