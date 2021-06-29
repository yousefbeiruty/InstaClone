package com.it.yousefl.dataalgo.da.graph_java;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BFSGraph {


    int size;
    adjList[] array;

    public BFSGraph(int size) {
        this.size = size;
        array = new adjList[this.size];
        for (int i = 0; i < size; i++) {
            array[i] = new adjList();
            array[i].head = null;
        }
    }

    public void addNode(int src,int dest){
        Node n=new Node(dest,null);
        n.next =array[src].head;
        array[src].head=n;
    }

    public void bfsExplore(int startVertex){
        Boolean[]visited=new Boolean[size];
        for(int i=0;i<size;i++)
            visited[i]=false;
        Queue<Integer> s=new LinkedList<>();
        s.add(startVertex);

        while (!s.isEmpty()){
            int n=s.poll();
            visited[n]=true;
            System.out.println("Visited: "+n);
            Node head=array[n].head;

            while (head!=null){
                if (visited[head.dest]==false) {
                    s.add(head.dest);
                    visited[head.dest]=true;
                }
                head=head.next;
            }

        }
    }


}
