package de.comparus.opensource.longmap;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LongMapImplTest {

    LongMap longMapImpl = Mockito.spy(LongMapImpl.class);

    @Test
    public void testPut(){
        //GIVEN
        int size = 1;
        //WHEN
        longMapImpl.put(1L,"23");
        //THEN
        assertEquals(size, longMapImpl.size());
        verify(longMapImpl, times(1)).put(1L, "23");
    }

    @Test
    public void testGetCollision(){
        //GIVEN
        int size = 14;
        String element = "2g3h";
        //WHEN
        longMapImpl.put(1L,"23");
        longMapImpl.put(2L,"253");
        longMapImpl.put(3L,"423");
        longMapImpl.put(4L,"283");
        longMapImpl.put(5L,"233");
        longMapImpl.put(6L,"2o83");
        longMapImpl.put(7L,"2g3");
        longMapImpl.put(8L,"2o83");
        longMapImpl.put(9L,"2g3");
        longMapImpl.put(10L,"2o83");
        longMapImpl.put(1000000000L,"2g3s");
        longMapImpl.put(100000000L,"2g3h");
        longMapImpl.put(10000000L,"2g3bn");
        longMapImpl.put(10000000000L,"23апрлрп");
        //THEN
        assertEquals(element, longMapImpl.get(100000000L));
        assertEquals(size, longMapImpl.size());
        verify(longMapImpl, times(4)).put(1L, "23");
    }

    @Test
    public void testPutCollision(){
        //GIVEN
        int size = 14;
        //WHEN
        longMapImpl.put(1L,"23");
        longMapImpl.put(2L,"253");
        longMapImpl.put(3L,"423");
        longMapImpl.put(4L,"283");
        longMapImpl.put(5L,"233");
        longMapImpl.put(6L,"2o83");
        longMapImpl.put(7L,"2g3");
        longMapImpl.put(8L,"2o83");
        longMapImpl.put(9L,"2g3");
        longMapImpl.put(10L,"2o83");
        longMapImpl.put(1000000000L,"2g3s");
        longMapImpl.put(100000000L,"2g3h");
        longMapImpl.put(10000000L,"2g3bn");
        longMapImpl.put(10000000000L,"23апрлрп");
        //THEN
        assertEquals(size, longMapImpl.size());
        verify(longMapImpl, times(4)).put(1L, "23");
    }

    @Test
    public void testPutSame(){
        //GIVEN
        int size = 1;
        //WHEN
        longMapImpl.put(1L,"23");
        longMapImpl.put(1L,"23");
        //THEN
        assertEquals(size, longMapImpl.size());
        verify(longMapImpl, times(2)).put(1L, "23");
    }

    @Test
    public void testPut10000000000(){
        //GIVEN
        int size = 1;
        //WHEN
        longMapImpl.put(10000000000L,"23");
        //THEN
        assertEquals(size, longMapImpl.size());
        verify(longMapImpl, times(1)).put(10000000000L, "23");
    }

    @Test
    public void testGet(){
        //GIVEN
        int size = 1;
        String element = "23";
        //WHEN
        longMapImpl.put(4L, "23");
        //THEN
        assertEquals(element, longMapImpl.get(4));
        assertEquals(size, longMapImpl.size());
        verify(longMapImpl, times(1)).put(4L, "23");
    }

    @Test
    public void testSize(){
        //GIVEN
        int size = 0;
        //WHEN

        //THEN
        assertEquals(size, longMapImpl.size());
    }

    @Test
    public void testRemove(){
        //GIVEN
        int size = 1;
        String element = "33";
        //WHEN
        longMapImpl.put(1L,"23");
        longMapImpl.put(4L, "33");
        longMapImpl.remove(1L);
        //THEN
        assertEquals(size, longMapImpl.size());
        assertEquals(element, longMapImpl.get(4L));
        assertNull(longMapImpl.get(1L));
        verify(longMapImpl, times(1)).put(4L, "33");
        verify(longMapImpl, times(1)).put(1L,"23");
        verify(longMapImpl, times(1)).remove(1L);
    }

    @Test
    public void testKeys(){
        //GIVEN
        int size = 2;
        long element = 4;
        //WHEN
        longMapImpl.put(1L,"23");
        longMapImpl.put(4L, "33");
        long[] keys = longMapImpl.keys();
        //THEN
        assertEquals(size, keys.length);
        assertEquals(element, keys[1]);
        verify(longMapImpl, times(1)).put(4L, "33");
        verify(longMapImpl, times(1)).put(1L,"23");

    }

    @Test
    public void testContainsKeyTrue(){
        //GIVEN
        long element = 4;
        //WHEN
        longMapImpl.put(1L,"23");
        longMapImpl.put(4L, "33");
        boolean key = longMapImpl.containsKey(element);
        //THEN
        assertTrue(key);
        verify(longMapImpl, times(1)).put(4L, "33");
        verify(longMapImpl, times(1)).put(1L,"23");

    }

    @Test
    public void testContainsKeyFalse(){
        //GIVEN
        long element = 5;
        //WHEN
        longMapImpl.put(1L,"23");
        longMapImpl.put(4L, "33");
        boolean key = longMapImpl.containsKey(element);
        //THEN
        assertFalse(key);
        verify(longMapImpl, times(1)).put(4L, "33");
        verify(longMapImpl, times(1)).put(1L,"23");

    }

    @Test
    public void testContainsValueTrue(){
        //GIVEN
        String element = "33";
        //WHEN
        longMapImpl.put(1L,"23");
        longMapImpl.put(4L, "33");
        boolean value = longMapImpl.containsValue(element);
        //THEN
        assertTrue(value);
        verify(longMapImpl, times(1)).put(4L, "33");
        verify(longMapImpl, times(1)).put(1L,"23");

    }

    @Test
    public void testContainsValueFalse(){
        //GIVEN
        String element = "333";
        //WHEN
        longMapImpl.put(1L,"23");
        longMapImpl.put(4L, "33");
        boolean value = longMapImpl.containsValue(element);
        //THEN
        assertFalse(value);
        verify(longMapImpl, times(1)).put(4L, "33");
        verify(longMapImpl, times(1)).put(1L,"23");

    }

    @Test
    public void testValues(){
        //GIVEN
        int size = 2;
        String element = "33";
        //WHEN
        longMapImpl.put(1L,"23");
        longMapImpl.put(4L, "33");
        Object[] values = longMapImpl.values();
        //THEN
        assertEquals(size, values.length);
        assertEquals(element, values[1]);
        verify(longMapImpl, times(1)).put(1L, "23");

    }

    @Test
    public void testValuesIntegerClass(){
        //GIVEN
        int size = 2;
        Integer element = 33;
        //WHEN
        longMapImpl.put(1L,23);
        longMapImpl.put(4L, 33);
        Object[] values = longMapImpl.values();
        //THEN
        assertEquals(size, values.length);
        assertEquals(element, values[1]);
        verify(longMapImpl, times(1)).put(1L, 23);

    }
}
