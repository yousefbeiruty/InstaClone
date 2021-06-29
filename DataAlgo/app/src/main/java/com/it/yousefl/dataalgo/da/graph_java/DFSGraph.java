package com.it.yousefl.dataalgo.da.graph_java;

import java.util.Stack;

public class DFSGraph {

    int size;
    adjList[] array;

    public DFSGraph(int size) {
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

    public void dfsExplore(int startVertex){
        Boolean[]visited=new Boolean[size];
        for(int i=0;i<size;i++)
            visited[i]=false;
        Stack<Integer> s=new Stack<>();
        s.push(startVertex);

        while (!s.isEmpty()){
            int n=s.pop();
            s.push(n);
            visited[n]=true;
            Node head=array[n].head;
            Boolean isDone=true;
            while (head!=null){
                if (visited[head.dest]==false) {
                   s.push(head.dest);
                    visited[head.dest]=true;
                    isDone=false;
                    break;
                }
                head=head.next;
            }
            if (isDone){
                int out=s.pop();
                System.out.println("Visited Node: "+out);
            }
        }
    }

    public void dfsSearch(int startVertex,int search){
        Boolean[]visited=new Boolean[size];
        Boolean isFound=false;
        for(int i=0;i<size;i++)
            visited[i]=false;
        Stack<Integer> s=new Stack<>();
        s.push(startVertex);

        while (!s.isEmpty()){
            int n=s.pop();
            s.push(n);
            visited[n]=true;
            Node head=array[n].head;
            Boolean isDone=true;
            while (head!=null){
                if (search==head.dest){
                    System.out.println("Found");
                    isFound=true;
                    break;
                }
                if (visited[head.dest]==false) {
                    s.push(head.dest);
                    visited[head.dest]=true;
                    isDone=false;
                    break;
                }
                head=head.next;
            }
            if (isFound){
                break;
            }
            if (isDone){
                int out=s.pop();
                System.out.println("Visited Node: "+out);
            }
            if (isFound==false){
                System.out.println("Not Found");
            }

        }
    }
}
