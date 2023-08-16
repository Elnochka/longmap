package de.comparus.opensource.longmap;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LongMapImplTest {

    private LongMap<String> longMap = new LongMapImpl<>();

    @Test
    public void testPut(){
        //GIVEN
        int size = 1;
        //WHEN
        longMap.put(1L,"23");
        //THEN
        assertEquals(size, longMap.size());
    }

    @Test
    public void testPut10000000000(){
        //GIVEN
        int size = 1;
        //WHEN
        longMap.put(10000000000L,"23");
        //THEN
        assertEquals(size, longMap.size());
    }

    @Test
    public void testGet(){
        //GIVEN
        int size = 1;
        String element = "23";
        //WHEN
        longMap.put(4L, "23");
        //THEN
        assertEquals(element, longMap.get(4));
        assertEquals(size, longMap.size());
    }

    @Test
    public void testSize(){
        //GIVEN
        int size = 0;
        //WHEN

        //THEN
        assertEquals(size, longMap.size());
    }

    @Test
    public void testRemove(){
        //GIVEN
        int size = 1;
        String element = "33";
        //WHEN
        longMap.put(1L,"23");
        longMap.put(4L, "33");
        longMap.remove(1);
        //THEN
        assertEquals(size, longMap.size());
        assertEquals(element, longMap.get(4));
        assertNull(longMap.get(1));
    }

    @Test
    public void testKeys(){
        //GIVEN
        int size = 2;
        long element = 4;
        //WHEN
        longMap.put(1L,"23");
        longMap.put(4L, "33");
        long[] keys = longMap.keys();
        //THEN
        assertEquals(size, keys.length);
        assertEquals(element, keys[1]);

    }

    @Test
    public void testContainsKeyTrue(){
        //GIVEN
        long element = 4;
        //WHEN
        longMap.put(1L,"23");
        longMap.put(4L, "33");
        boolean key = longMap.containsKey(element);
        //THEN
        assertTrue(key);

    }

    @Test
    public void testContainsKeyFalse(){
        //GIVEN
        long element = 5;
        //WHEN
        longMap.put(1L,"23");
        longMap.put(4L, "33");
        boolean key = longMap.containsKey(element);
        //THEN
        assertFalse(key);

    }

    @Test
    public void testContainsValueTrue(){
        //GIVEN
        String element = "33";
        //WHEN
        longMap.put(1L,"23");
        longMap.put(4L, "33");
        boolean value = longMap.containsValue(element);
        //THEN
        assertTrue(value);

    }

    @Test
    public void testContainsValueFalse(){
        //GIVEN
        String element = "333";
        //WHEN
        longMap.put(1L,"23");
        longMap.put(4L, "33");
        boolean value = longMap.containsValue(element);
        //THEN
        assertFalse(value);

    }

    @Test
    public void testValues(){
        //GIVEN
        int size = 2;
        String element = "33";
        //WHEN
        longMap.put(1L,"23");
        longMap.put(4L, "33");
        List<String> values = longMap.values();
        //THEN
        assertEquals(size, values.size());
        assertEquals(element, values.get(1));

    }
}
