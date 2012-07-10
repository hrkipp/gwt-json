package com.nkdata.gwt.streamer.test.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;


public class JSNIAccessTest extends GWTTestCase {
	public static class Tester {
		private String a = "abcd";
		private String b = "efgh";
		private Integer c = 5;
		private int d = 6;
		private Long e = Long.MAX_VALUE;
		private long f = Long.MIN_VALUE;
		
		String[] g = new String[] { "1", "2", "3" };
		int[] h = new int[] { 1, 2, 3 };
		long[] k = new long[] { 5L, 6L, 7L };
	}
	
	@Override
	public String getModuleName() {
		return "com.nkdata.gwt.streamer.test.GWTStreamerTest";
	}

	public void testHelloWorld() {
		GWT.log( "Hello world!" );
	}
	
	
	public void testReadFields() {
		Tester t = new Tester();
		List<?> values = new ArrayList<Object>();
		getFields( t, values );
		assertEquals( "abcd", values.get(0) );
		assertEquals( "efgh", values.get(1) );
		assertEquals( Integer.class, values.get(2).getClass() );
		assertEquals( 5, values.get(2) );
		assertEquals( Integer.class, values.get(3).getClass() );
		assertEquals( 6, values.get(3) );

		assertEquals( Long.class, values.get(4).getClass() );
		assertEquals( Long.MAX_VALUE, values.get(4) );
		assertEquals( Long.class, values.get(5).getClass() );
		assertEquals( Long.MIN_VALUE, values.get(5) );
		
		assertTrue(  Arrays.deepEquals( new String[] { "1", "2", "3" }, (String[]) values.get(6) ) );
		assertTrue( Arrays.equals( new int[] { 1, 2, 3 }, (int[]) values.get(7) ) );
		assertTrue( Arrays.equals( new long[] { 5L, 6L, 7L }, (long[])values.get( 8 ) ) );
	}

	
	public void testWriteLongFields() {
		Tester t = new Tester();
		List<Object> values = new ArrayList<Object>();
		values.add( "test" );
		values.add( new Long( 10L ) );
		values.add( new Long( 20L ) );
		values.add( new String[] { "a", "b", "c" } );
		values.add( new int[] { 4, 5, 6 } );
		values.add( new long[] { 10L, 11L, 12L } );
		
		setFields( t, values );
		
		assertEquals( t.a, "test" );
		assertEquals( 10L, t.e.longValue() );
		assertEquals( 20L, t.f );
		
		assertTrue( Arrays.deepEquals(new String[] { "a", "b", "c" } , t.g) );
		assertTrue( Arrays.equals(new int[] { 4, 5, 6 }, t.h ) );
		assertTrue( Arrays.equals(new long[] { 10L, 11L, 12L } , t.k ) );
	}

	
	@com.google.gwt.core.client.UnsafeNativeLong
	private native void getFields( Object obj, List<?> values ) /*-{
		values.@java.util.List::add(Ljava/lang/Object;)( obj.@com.nkdata.gwt.streamer.test.client.JSNIAccessTest.Tester::a );
		values.@java.util.List::add(Ljava/lang/Object;)( obj.@com.nkdata.gwt.streamer.test.client.JSNIAccessTest.Tester::b );
		values.@java.util.List::add(Ljava/lang/Object;)( obj.@com.nkdata.gwt.streamer.test.client.JSNIAccessTest.Tester::c );
		values.@java.util.List::add(Ljava/lang/Object;)(
		 	@java.lang.Integer::valueOf(I)(
				obj.@com.nkdata.gwt.streamer.test.client.JSNIAccessTest.Tester::d	) );
		values.@java.util.List::add(Ljava/lang/Object;)( obj.@com.nkdata.gwt.streamer.test.client.JSNIAccessTest.Tester::e );
		//var v = obj.@com.nkdata.gwt.streamer.client.test.JSNIAccessTest.Tester::f;
		//alert( String(v) );
		values.@java.util.List::add(Ljava/lang/Object;)(
		 	@java.lang.Long::valueOf(J)(
				obj.@com.nkdata.gwt.streamer.test.client.JSNIAccessTest.Tester::f	) );
		values.@java.util.List::add(Ljava/lang/Object;)( obj.@com.nkdata.gwt.streamer.test.client.JSNIAccessTest.Tester::g );
		values.@java.util.List::add(Ljava/lang/Object;)( obj.@com.nkdata.gwt.streamer.test.client.JSNIAccessTest.Tester::h );
		values.@java.util.List::add(Ljava/lang/Object;)( obj.@com.nkdata.gwt.streamer.test.client.JSNIAccessTest.Tester::k );
	}-*/;


	@com.google.gwt.core.client.UnsafeNativeLong
	private native void setFields( Object obj, List<?> values ) /*-{
		obj.@com.nkdata.gwt.streamer.test.client.JSNIAccessTest.Tester::a = values.@java.util.List::get(I)(0);
		obj.@com.nkdata.gwt.streamer.test.client.JSNIAccessTest.Tester::e = values.@java.util.List::get(I)(1);
		obj.@com.nkdata.gwt.streamer.test.client.JSNIAccessTest.Tester::f = values.@java.util.List::get(I)(2).@java.lang.Long::longValue()();

		obj.@com.nkdata.gwt.streamer.test.client.JSNIAccessTest.Tester::g = values.@java.util.List::get(I)(3);
		obj.@com.nkdata.gwt.streamer.test.client.JSNIAccessTest.Tester::h = values.@java.util.List::get(I)(4);
		obj.@com.nkdata.gwt.streamer.test.client.JSNIAccessTest.Tester::k = values.@java.util.List::get(I)(5);
	}-*/;
}
