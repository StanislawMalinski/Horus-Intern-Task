package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class Wall implements Structure {
    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    private List<Block> findBlock(Function<Block, Boolean> function, List<Block> blocks){
        ArrayList<Block> ls = new ArrayList<>();
        for (Block b : blocks) {
            if (function.apply(b))
                ls.add(b);
            if (b instanceof CompositeBlock)
                ls.addAll(findBlock(function, ((CompositeBlock) b).getBlocks()));
        }
        return ls;
    }

    private List<Block> findBlock(Function<Block, Boolean> function){
        return findBlock(function, this.blocks);
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        List<Block> ls = findBlock(new Function<Block, Boolean>() {
            @Override
            public Boolean apply(Block block) {
                return color.equals(block.getColor());
            }
        });
        return ls.isEmpty() ? Optional.empty() : Optional.of(ls.get(0));
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return findBlock(new Function<Block, Boolean>() {
            @Override
            public Boolean apply(Block block) {
                return material.equals(block.getMaterial());
            }
        });
    }

    @Override
    public int count() {
        return blocks.size();
    }
}