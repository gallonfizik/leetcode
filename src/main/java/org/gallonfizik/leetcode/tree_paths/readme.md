### Basic implementation

The basic idea is to traverse the tree with BFS/DFS. "Natural" DFS implementation is recursive. However, it has limited risking stack overflow on large trees (~1000 nodes
and more). In-heap stack/queue (basically, the only implementation difference between DFS and BFS) is much safer, limited only by available heap.

### Basic properties of a balanced binary tree

Provided we know that the tree contains N nodes,

* height of the tree is at most log<sub>2</sub>N
* number of leaves is ~N/2

<super>*</super> Actually, _balanced_ is not specified in the problem.

### Doing something useful

To have some useful output, we need to perform some actions on a tree node, and possibly on a path.
There are two well known patterns for that Visitor and Listener. Visitor can keep track of the visits (basically it's an "extended stack context").
Listener generally doesn't do that, focusing on handling specific events during traversal (in this case, on hitting a leaf).

### Cost structure

#### DFS – traversal

* stack growth: generally linear time (O(logN)), but for small problems resizes can happen quite often
* computation: just several null checks per node (left, right, both) is effectively negligible.

#### Listener: path building

"Useful" work in this problem is building a path from integers, which involves integer to string conversion and concatenation, which is way more resource greedy than tree
traversal.

Extra consideration is that root node is somewhat distinct from the rest: it doesn't need an arrow `->` in the output.

Costs:

* int -> string conversion
* string concatenation
* pushing to the output array

Possible solutions/optimizations:

* precache strings in a static array, since by conditions there are just 201 _distinct contiguous_ integers possible. Adjusted int value is suitable for array indexing.
    * pro: computed once on class loading, just one array read on subsequent calls
    * pro: greater gains on duplicate node values
* path nodes concatenation "on the go" or "on a leaf"?
    * on the go: on each node we know path to this node as string. On a leaf, just return the path
        * pro: both child nodes share a single precompiled path to their parent. Every value is compiled once.
        * con: root node must be handled separately (printed without `->`). Probably the best way to achieve this is to unroll visiting the root node.
        * con: doesn't transfer well to other similar problems (we wish we could just attach another listener/visitor)
        * complexity O(NlogN) (append a string of length ~logN * N number of nodes)

      Summary: O(NlogN), design downsides.
    * on a leaf: collect integers. Compile them string when hit a leaf
        * pro: resulting string length can be precomputed; concatenations will be replaced by offset copies, which is faster (probably logN for a complete path).
        * pro: handling root node doesn't cause changes to the traversal part
        * pro: space complexity is O(logN): we must remember (i.e. copy) the path from the root
        * entire path up to current node needs to be rebuilt every time. Total complexity is O(NlogN) (leaves * depth)

      Summary: O(NlogN).
    
    Tried both, stopped on the "on the go" solution which proved to be fater.

### Implementation optimizations

* a dedicated `GrowOnlyArray<T>` with initial capacity of 10 is a bit faster than ArrayList
* use `"" + int` to convert int to string, seems the fastest way in Java
* unroll visiting root node to handle "arrow/no arrow" in the output 
