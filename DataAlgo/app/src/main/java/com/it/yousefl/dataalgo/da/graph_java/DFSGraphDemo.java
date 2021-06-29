package com.it.yousefl.dataalgo.da.graph_java;

public class DFSGraphDemo {
    public static void main(String[] args) {
        DFSGraph dfsGraph=new DFSGraph(6);

        /**
         *             0
         *           /   \
         *         1      2
         *        / \      \
         *      3    4      5
         * */


        dfsGraph.addNode(0,2);
        dfsGraph.addNode(0,1);


        dfsGraph.addNode(1,4);
        dfsGraph.addNode(1,3);
        dfsGraph.addNode(1,0);

        dfsGraph.addNode(3,1);
        dfsGraph.addNode(4,1);

        dfsGraph.addNode(2,5);
        dfsGraph.addNode(2,0);
        dfsGraph.addNode(5,2);

      //  dfsGraph.dfsExplore(0);

        dfsGraph.dfsSearch(0,5);

    }
}
