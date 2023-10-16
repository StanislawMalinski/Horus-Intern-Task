package org.example;

import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class WallTest {
    private Wall w;

    private Block getMock(String color, String material, List<Block> blocks){
        CompositeBlock b = Mockito.mock(CompositeBlock.class);
        Mockito.when(b.getColor()).thenReturn(color);
        Mockito.when(b.getMaterial()).thenReturn(material);

        if (blocks != null){
            Mockito.when(b.getBlocks()).thenReturn(blocks);
        }
        return b;
    }

    private Block getMock(String color, String material){
        return getMock(color, material, null);
    }

    @org.junit.jupiter.api.BeforeEach
    void setUp(){
        Block b1 = getMock("green", "dirt");
        Block b2 = getMock("green", "wood");

        ArrayList<Block> cb3 = new ArrayList<>();
        Block b3b1 = getMock("brown", "wood");
        Block b3b2 = getMock("green", "dirt");

        cb3.add(b3b1);
        cb3.add(b3b2);

        Block b3 = getMock("brown", "wood", cb3);

        ArrayList<Block> ls = new ArrayList<>();

        ls.add(b1);
        ls.add(b2);
        ls.add(b3);

        w = new Wall(ls);
    }

    @org.junit.jupiter.api.Test
    void findBlockByColor() {
        Optional<Block> res = w.findBlockByColor("green");
        assertTrue(res.isPresent());
        assertEquals(res.get().getColor(), "green");
    }

    @org.junit.jupiter.api.Test
    void findBlocksByMaterial() {
        List<Block> ls = w.findBlocksByMaterial("wood");
        int count = 0;
        for (Block b : ls) {
            assertEquals(b.getMaterial(), "wood");
            count++;
        }
        assertEquals(count, 3);
    }

    @org.junit.jupiter.api.Test
    void count() {
        assertEquals(w.count(), 3);
    }
}