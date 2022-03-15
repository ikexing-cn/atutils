package ikexing.atutils.core.utils;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

import java.util.List;

public class Utils {


    public static ItemStack getStackFromState(IBlockState state) {
        Block block = state.getBlock();
        Item item = Item.getItemFromBlock(state.getBlock());
        int damage = block.damageDropped(state);
        return new ItemStack(item, damage);
    }


    public static ImmutableList<IBlockState> getBlockStatesByOreDict(String oreDict) {
        ImmutableList.Builder<IBlockState> result = ImmutableList.builder();
        NonNullList<ItemStack> stacks = OreDictionary.getOres(oreDict);

        for (ItemStack stack : stacks) {
            Item item = stack.getItem();
            if (item instanceof ItemBlock) {
                Block block = ((ItemBlock) item).getBlock();
                int meta = stack.getMetadata();

                try {
                    IBlockState state = block.getStateFromMeta(meta);
                    result.add(state);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result.build();

    }


}
