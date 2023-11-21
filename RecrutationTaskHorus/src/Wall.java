import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class Wall implements Structure {
    private List<Block> blocks;

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        for (Block block : blocks) {
            if (color.equals(block.getColor())) {
                return Optional.of(block);
            }
            if (block instanceof CompositeBlock) {
                for (Block childBlock : ((CompositeBlock) block).getBlocks()) {
                    if (color.equals(childBlock.getColor())) {
                        return Optional.of(childBlock);
                    }
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        List<Block> result = new ArrayList<>();
        for (Block block : blocks) {
            if (material.equals(block.getMaterial())) {
                result.add(block);
            }
            if (block instanceof CompositeBlock) {
                for (Block childBlock : ((CompositeBlock) block).getBlocks()) {
                    if (material.equals(childBlock.getMaterial())) {
                        result.add(childBlock);
                    }
                }
            }
        }
        return result;
    }

    @Override
    public int count() {
        int counter = 0;
        for (Block block : blocks) {
            counter++;
            if (block instanceof CompositeBlock) {
                counter += ((CompositeBlock) block).getBlocks().size();
            }
        }
        return counter;
    }
}