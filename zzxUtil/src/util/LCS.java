/*
 * @author talent_marquis<甜菜侯爵>
 * Email: talent_marquis@163.com
 * Copyright (C) 2007 talent_marquis<甜菜侯爵>
 * All rights reserved.
 */

package com.fid.util;

/*
 * LCS, Longest-Common-Subsequence
 */
public class LCS
{	
	public enum DIRECTION{ TOP, TOP_LEFT, LEFT };
	private char[] first;
	private char[] second;
	private int[][] lcsTable;
	private DIRECTION[][] lcsAssistTable;
	private int lcsLength;
	private String lcs_str, lcsMatrix_str, lcsAssistMatrix_str;
	private StringBuffer str_buffer;
	
	public LCS( String str1, String str2 )
	{
		first = str1.toCharArray();
		second = str2.toCharArray();
		lcsTable = new int[ first.length + 1 ][ second.length + 1 ];
		lcsAssistTable = new DIRECTION[ first.length + 1 ][ second.length + 1];
		lcs_str = null;
		str_buffer = new StringBuffer();
	}	
	
	public static void main(String[] args)
	{
		/*String a = "张振兴的张振兴打发";
		String b = "张振兴的的打发";*/
//		String a = "【现货白银涨破21美元】财联社4日讯，现货白银涨幅扩大至6.6%，报21.05美元/盎司。自英国脱欧公投以来，现货白银涨幅近24%。";
//		String a = "【现货白银涨破21美元】财联社4日讯，现货白银涨幅扩大至6.6%，报21.05美元/盎司。自英国脱欧公投以来，现货白银涨幅近24%。";
//		String a = "保监会：原则同意中国人寿保险股份有限公司以受让方式增持广发银行股份至43.686%。";
//		String b = "财联社4日讯，保监会表示，原则同意中国人寿保险股份有限公司以受让方式增持广发银行股份至43.686%。";
		String a = "财联社4日讯，宝能系人士透露，不排除股价走低后继续增持万科的可能性。（澎湃）";
		String b = "宝能系人士透露，不排除股价走低后继续增持的可能性。";
		LCS lcs = new LCS( a, b );		
		
		lcs.getLCSLength();
		lcs.runLCS();
		println( "最大相似子字符串长度是：" + lcs.getLCSLength());
		println( "最大相似子字符串为：" + lcs.getLCS() );
		println("b相对a的相似度:"+Double.valueOf(lcs.getLCSLength())/a.length());
		println("a相对b的相似度:"+Double.valueOf(lcs.getLCSLength())/b.length());
	}
	
	public int getLCSLength()
	{
		lcsLength = getLCSLength( first, second );
		return lcsLength;
	}
	
	private int getLCSLength( char[] one, char[] two )
	{
		lcsTable = new int[ one.length + 1 ][ two.length + 1 ];
		lcsAssistTable = new DIRECTION[ one.length + 1 ][ two.length + 1];
		
		for ( int i = 0; i < one.length ; i++ )
		{
			lcsTable[ i ][ 0 ] = 0;
		}

for ( int j = 0; j < two.length - 1; j++ )
		{
			lcsTable[ 0 ][ j ] = 0;
		}
		
		for ( int i = 0; i < one.length; i++ )
		{
			for ( int j = 0; j < two.length; j++ )
			{
				if ( one[ i ] == two[ j ] )
				{
					lcsTable[ i + 1 ][ j + 1 ] = lcsTable[ i ][ j ] + 1;
					lcsAssistTable[ i + 1 ][ j + 1 ] = DIRECTION.TOP_LEFT;	
				}
				else if ( lcsTable[ i ][ j + 1 ] >= lcsTable[ i + 1 ][ j ] )
				{
					lcsTable[ i + 1 ][ j + 1 ] = lcsTable[ i ][ j + 1 ];
					lcsAssistTable[ i + 1 ][ j + 1 ] = DIRECTION.TOP;
				}
				else
				{
					lcsTable[ i + 1 ][ j + 1 ] = lcsTable[ i + 1 ][ j ];
					lcsAssistTable[ i + 1 ][ j + 1 ] = DIRECTION.LEFT;
				}
			}
		}
		
		lcsLength = lcsTable[ one.length ][ two.length ];		
		return lcsLength;
	}
	
	public void runLCS()
	{
		runLCS( lcsAssistTable, first, first.length, second.length );
		lcs_str = str_buffer.toString();
	}
	
	private void runLCS( DIRECTION[][] lcsAssistTable, char[] one, int oneLength, int twoLength )
	{
		if( oneLength == 0 || twoLength == 0 )
		{
			return;
		}
		int i = oneLength ;
		int j = twoLength ;
		
		if( lcsAssistTable[ i ][ j ] == DIRECTION.TOP_LEFT )
		{
			runLCS( lcsAssistTable, one, i - 1, j -1 );
			str_buffer.append( one[ i - 1 ] );
		}
		else if ( lcsAssistTable[ i ][ j ] == DIRECTION.TOP )
		{
			runLCS( lcsAssistTable, one, i - 1, j );
		}
		else
		{
			runLCS( lcsAssistTable, one, i, j -1 );
		}
		
	}
	
	public String getLCSAssistMatrixString()
	{
		str_buffer = new StringBuffer();
		for( DIRECTION[] row: lcsAssistTable)
		{
			for( DIRECTION element : row )
			{
				if( element == DIRECTION.LEFT )
				{
					str_buffer.append( "�� " );
				}
				else if (element == DIRECTION.TOP )
				{
					str_buffer.append( "�� " );
				}
				else if (element == DIRECTION.TOP_LEFT)
				{
					str_buffer.append( "�I " );
				}
				else
				{
					//str_buffer.append( "\t" );
				}
			}
			str_buffer.append( "\n" );
		}
		lcsAssistMatrix_str = str_buffer.toString();
		
		return lcsAssistMatrix_str;
	}
	
	public String getLCSMatrixString()
	{
		str_buffer = new StringBuffer();
		for( int[] row: lcsTable)
		{
			for( int element : row )
			{
				str_buffer.append( element + " " );
			}
			str_buffer.append( "\n" );
		}
		lcsMatrix_str = str_buffer.toString();
		return lcsMatrix_str;
	}
	
	public static void print( Object o )
	{
		System.out.print( o );
	}
	
	public static void println( Object o )
	{
		System.out.println( o );
	}

	public String getLCS()
	{
		return lcs_str;
	}

	/**
	* @return first
	*/
	public char[] getFirstCharArray()
	{
		return first;
	}

/**
	* @return second
	*/
	public char[] getSecondCharArray()
	{
		return second;
	}

/**
	* @return lcsAssistTable
	*/
	public DIRECTION[][] getLcsAssistTable()
	{
		return lcsAssistTable;
	}

/**
	* @return lcsTable
	*/
	public int[][] getLcsTable()
	{
		return lcsTable;
	}
}