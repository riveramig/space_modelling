package org.javeriana;

import org.javeriana.world.layer.crop.cell.root.RootCell;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {
        RootCell name = new RootCell(
                0,
                0,
                0,
                900,
                1800,
                20,
                20,
                "name_1"
                );
        RootCell name2 = new RootCell(
                0,
                0,
                0,
                900,
                1800,
                30,
                30,
                "name_2"
        );
    }
}
