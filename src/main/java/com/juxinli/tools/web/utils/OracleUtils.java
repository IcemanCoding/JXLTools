package com.juxinli.tools.web.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.juxinli.tools.web.entity.BillingUserEntity;
import com.juxinli.tools.web.vo.BillingUserVO;

public class OracleUtils {
	
	public static void main( String[] args ) {
		
		testOracle();
		
	}
	
	private static final String URL = "jdbc:oracle:thin:@192.168.200.108:1521:ora200108";
	private static final String USERNAME = "bill";
	private static final String PASSWORD = "bill@123";
	
	public static List<BillingUserEntity> getBillingUserByCondition( BillingUserVO billingUserVo ) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		List<BillingUserEntity> resData = new ArrayList<BillingUserEntity>();
		BillingUserEntity billingUserEntity = null;
		
		try {
			conn = getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append( " select ORG_ACC, NM, IC from T_BILL_ALL " );
			sql.append( " where ORG_ACC = ? and rptver = ? and ct >= ? and ct <= ? " );
			ps = conn.prepareStatement( sql.toString() );
			ps.setString( 1, billingUserVo.getOrg() );
			ps.setString( 2, billingUserVo.getRpt_version() );
			ps.setDate( 3, 
					new java.sql.Date( 
							DateUtils.transStrToDate( 
									billingUserVo.getSt(), DateUtils.PATTERN1 ).getTime() ) );
			ps.setDate( 4, 
					new java.sql.Date(
							DateUtils.transStrToDate(
									billingUserVo.getEt(), DateUtils.PATTERN1 ).getTime() ) );
			result = ps.executeQuery();
			while ( result.next() ) {
				billingUserEntity = new BillingUserEntity();
				billingUserEntity.setIdcard( result.getString( "IC" ) );
				billingUserEntity.setName( result.getString( "NM" ) );
				billingUserEntity.setOrg( result.getString( "ORG_ACC" ) );
				resData.add( billingUserEntity );
			}
		} catch ( Exception e ) {
			return null;
		} finally {
			try {
				// 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
				// 注意关闭的顺序，最后使用的最先关闭
				if ( result != null )
					result.close();
				if ( ps != null )
					ps.close();
				if ( conn != null )
					conn.close();
				System.out.println( "数据库连接已关闭！" );
			} catch ( Exception e ) {
				e.printStackTrace();
			}
		}
		return resData;
		
	}
	
	private static Connection getConnection() throws Exception {
		return DriverManager.getConnection( URL, USERNAME, PASSWORD );
	}

	public static void testOracle() {
		Connection con = null;
		PreparedStatement pre = null;
		ResultSet result = null;
		try {
			Class.forName( "oracle.jdbc.driver.OracleDriver" );
			System.out.println( "开始尝试连接数据库！" );
			con = DriverManager.getConnection( URL, USERNAME, PASSWORD );
			System.out.println( "连接成功！" );
			String sql = " select count(1) from T_BILL_ALL ";
			pre = con.prepareStatement( sql );
			result = pre.executeQuery();
			while ( result.next() ) {
				System.out.println( result );
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		} finally {
			try {
				// 逐一将上面的几个对象关闭，因为不关闭的话会影响性能、并且占用资源
				// 注意关闭的顺序，最后使用的最先关闭
				if ( result != null )
					result.close();
				if ( pre != null )
					pre.close();
				if ( con != null )
					con.close();
				System.out.println( "数据库连接已关闭！" );
			} catch ( Exception e ) {
				e.printStackTrace();
			}
		}
	}
}